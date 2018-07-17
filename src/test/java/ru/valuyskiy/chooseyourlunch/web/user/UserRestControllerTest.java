package ru.valuyskiy.chooseyourlunch.web.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.valuyskiy.chooseyourlunch.model.Vote;
import ru.valuyskiy.chooseyourlunch.service.MenuService;
import ru.valuyskiy.chooseyourlunch.to.MenuToWithDishes;
import ru.valuyskiy.chooseyourlunch.util.TimeMachine;
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
import static ru.valuyskiy.chooseyourlunch.UserTestData.USER_ID;

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
}