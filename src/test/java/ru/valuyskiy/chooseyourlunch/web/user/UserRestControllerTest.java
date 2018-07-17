package ru.valuyskiy.chooseyourlunch.web.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.valuyskiy.chooseyourlunch.model.Vote;
import ru.valuyskiy.chooseyourlunch.service.MenuService;
import ru.valuyskiy.chooseyourlunch.service.VotingService;
import ru.valuyskiy.chooseyourlunch.to.MenuToWithDishes;
import ru.valuyskiy.chooseyourlunch.to.VotingStatisticsTo;
import ru.valuyskiy.chooseyourlunch.web.AbstractControllerTest;
import ru.valuyskiy.chooseyourlunch.web.json.JsonUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.valuyskiy.chooseyourlunch.MenuTestData.*;
import static ru.valuyskiy.chooseyourlunch.TestUtil.getContent;
import static ru.valuyskiy.chooseyourlunch.TestUtil.userHttpBasic;
import static ru.valuyskiy.chooseyourlunch.UserTestData.USER;

public class UserRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = "/rest/user/menus/";

    @Autowired
    MenuService menuService;

    @Autowired
    VotingService votingService;

    @Test
    public void getTodayMenus() throws Exception {

        ResultActions action = mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        List<MenuToWithDishes> returned = JsonUtil.readValues(getContent(action), MenuToWithDishes.class);
        assertMatch(returned, MENU3_TO_WITH_DISHES_RESTAURANT1, MENU3_TO_WITH_DISHES_RESTAURANT2);
    }

    @Test
    public void getMenusByDate() throws Exception {

        ResultActions action = mockMvc.perform(get(REST_URL + "?date=" + LocalDate.now().minusDays(1))
                .with(userHttpBasic(USER)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        List<MenuToWithDishes> returned = JsonUtil.readValues(getContent(action), MenuToWithDishes.class);
        assertMatch(returned, MENU2_TO_WITH_DISHES_RESTAURANT1, MENU2_TO_WITH_DISHES_RESTAURANT2);
    }

    @Test
    public void getTodayMenuUnAuth() throws Exception {
        mockMvc.perform(get("/rest/user/menus"))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    // mock for time. before 11:00
    @Test
    public void testVoting() throws Exception {

        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalDateTime fixedDateTime = LocalDateTime.of(
                nowDateTime.getYear(),
                nowDateTime.getMonthValue(),
                nowDateTime.getDayOfMonth(),
                Vote.VOTING_TIME.getHour() - 1,
                nowDateTime.getMinute()
        );

        votingService.setClock(fixedDateTime);

        List<VotingStatisticsTo> statisticsBeforeVoting = votingService.getVotingStatistics(LocalDate.now());

        mockMvc.perform(put("/rest/user/menus/" + MENU3_RESTAURANT1_ID + "/votes")
                .with(userHttpBasic(USER)))
                .andExpect(status().isCreated())
                .andDo(print());

        List<VotingStatisticsTo> statisticsAfterVoting = votingService.getVotingStatistics(LocalDate.now());

        assertThat(statisticsAfterVoting.get(0).getVotes() - statisticsBeforeVoting.get(0).getVotes())
                .isEqualTo(1);
    }

    // mock for time. after 11:00
    @Test
    public void testVotingOutOfTime() throws Exception {

        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalDateTime fixedDateTime = LocalDateTime.of(
                nowDateTime.getYear(),
                nowDateTime.getMonthValue(),
                nowDateTime.getDayOfMonth(),
                Vote.VOTING_TIME.getHour() + 1,
                nowDateTime.getMinute()
        );

        votingService.setClock(fixedDateTime);

        List<VotingStatisticsTo> statisticsBeforeVoting = votingService.getVotingStatistics(LocalDate.now());

        mockMvc.perform(put("/rest/user/menus/" + MENU3_RESTAURANT1_ID + "/votes")
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        List<VotingStatisticsTo> statisticsAfterVoting = votingService.getVotingStatistics(LocalDate.now());

        assertThat(statisticsAfterVoting.get(0).getVotes() - statisticsBeforeVoting.get(0).getVotes())
                .isEqualTo(0);
    }
}