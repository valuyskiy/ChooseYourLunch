package ru.valuyskiy.chooseyourlunch.web.admin;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.valuyskiy.chooseyourlunch.model.Menu;
import ru.valuyskiy.chooseyourlunch.service.MenuService;
import ru.valuyskiy.chooseyourlunch.to.MenuTo;
import ru.valuyskiy.chooseyourlunch.util.exception.ErrorType;
import ru.valuyskiy.chooseyourlunch.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.valuyskiy.chooseyourlunch.MenuTestData.*;
import static ru.valuyskiy.chooseyourlunch.RestaurantTestData.RESTAURANT1_ID;
import static ru.valuyskiy.chooseyourlunch.TestUtil.readFromJson;
import static ru.valuyskiy.chooseyourlunch.TestUtil.userHttpBasic;
import static ru.valuyskiy.chooseyourlunch.UserTestData.ADMIN;
import static ru.valuyskiy.chooseyourlunch.UserTestData.USER;
import static ru.valuyskiy.chooseyourlunch.web.json.JsonUtil.writeValue;

public class MenusAdminRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RestaurantsAdminRestController.REST_URL + "/" + RESTAURANT1_ID + "/menus/";

    @Autowired
    MenuService menuService;

    @Test
    public void testGetByRestaurantId() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MENU1_RESTAURANT1, MENU2_RESTAURANT1, MENU3_RESTAURANT1));
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + MENU1_RESTAURANT1_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MENU1_RESTAURANT1));
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + 1)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void testGetNotAuth() throws Exception {
        mockMvc.perform(get(REST_URL + MENU1_RESTAURANT1_ID))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    public void testGetForbidden() throws Exception {
        mockMvc.perform(get(REST_URL + MENU1_RESTAURANT1_ID)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + MENU1_RESTAURANT1_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertMatch(menuService.getAll(), MENU1_RESTAURANT2, MENU2_RESTAURANT1, MENU2_RESTAURANT2, MENU3_RESTAURANT1, MENU3_RESTAURANT2);
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + 1)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void testUpdate() throws Exception {
        Menu updated = getUpdated();
        mockMvc.perform(put(REST_URL + MENU1_RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(writeValue(menuService.toTo(updated))))
                .andExpect(status().isOk())
                .andDo(print());

        assertMatch(menuService.get(MENU1_RESTAURANT1_ID), updated);
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void testUpdateDuplicate() throws Exception {
        Menu updated = getUpdated();
        updated.setDate(MENU2_RESTAURANT1.getDate());
        mockMvc.perform(put(REST_URL + updated.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(writeValue(menuService.toTo(updated))))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void testCreate() throws Exception {
        Menu expected = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL + "?date=" + expected.getDate())
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isCreated())
                .andDo(print());

        MenuTo returned = readFromJson(action, MenuTo.class);
        expected.setId(returned.getId());

        assertMatch(returned, menuService.toTo(expected));
        assertMatch(menuService.getAll(), MENU1_RESTAURANT1, MENU1_RESTAURANT2, MENU2_RESTAURANT1, MENU2_RESTAURANT2, MENU3_RESTAURANT1, MENU3_RESTAURANT2, expected);
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void testCreateDuplicate() throws Exception {
        Menu expected = getCreated();
        expected.setDate(MENU2_RESTAURANT1.getDate());
        mockMvc.perform(post(REST_URL + "?date=" + expected.getDate())
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(errorType(ErrorType.DATA_ERROR));
    }

}