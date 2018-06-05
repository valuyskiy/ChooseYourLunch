package ru.valuyskiy.chooseyourlunch.web.User;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.valuyskiy.chooseyourlunch.service.MenuService;
import ru.valuyskiy.chooseyourlunch.to.MenuToWithDishes;
import ru.valuyskiy.chooseyourlunch.web.AbstractControllerTest;
import ru.valuyskiy.chooseyourlunch.web.json.JsonUtil;

import java.time.LocalDate;
import java.util.List;

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


    // TODO mock на LocalDateTime до 11:00
    @Test
    public void testVoting() throws Exception {

//        MenuToWithDishes menuToWithDishesBeforeVoting = menuService.getToWithDishes(menuService.get(MENU3_RESTAURANT2_ID));

        mockMvc.perform(put("/rest/user/menus/" + MENU3_RESTAURANT2_ID + "/votes")
                .with(userHttpBasic(USER)))
                //              .andExpect(status().isCreated())
                .andDo(print());

//        MenuToWithDishes menuToWithDishesAfterVoting = menuService.getToWithDishes(menuService.get(MENU3_RESTAURANT2_ID));
//
//        assertThat("The vote is not counted",
//                menuToWithDishesAfterVoting.getVoteCounter() - menuToWithDishesBeforeVoting.getVoteCounter(),
//                is(1) );

    }

    // TODO mock на LocalDateTime после 11:00
    @Test
    public void testVotingOutOfTime() throws Exception {
        mockMvc.perform(put("/rest/user/menus/" + MENU2_RESTAURANT2_ID + "/votes")
                .with(userHttpBasic(USER)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    // TODO mock на LocalDate до 11:00
    @Test
    public void testVotingAnotherDay() throws Exception {
        mockMvc.perform(put("/rest/user/menus/" + "100009" + "/votes")
                .with(userHttpBasic(USER)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }
}