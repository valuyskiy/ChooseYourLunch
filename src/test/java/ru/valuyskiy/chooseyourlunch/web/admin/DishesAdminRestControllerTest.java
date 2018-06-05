package ru.valuyskiy.chooseyourlunch.web.admin;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.valuyskiy.chooseyourlunch.service.DishService;
import ru.valuyskiy.chooseyourlunch.to.DishTo;
import ru.valuyskiy.chooseyourlunch.util.exception.ErrorType;
import ru.valuyskiy.chooseyourlunch.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.valuyskiy.chooseyourlunch.DishTestData.*;
import static ru.valuyskiy.chooseyourlunch.MenuTestData.MENU1_RESTAURANT1_ID;
import static ru.valuyskiy.chooseyourlunch.RestaurantTestData.RESTAURANT1_ID;
import static ru.valuyskiy.chooseyourlunch.TestUtil.readFromJson;
import static ru.valuyskiy.chooseyourlunch.TestUtil.userHttpBasic;
import static ru.valuyskiy.chooseyourlunch.UserTestData.ADMIN;
import static ru.valuyskiy.chooseyourlunch.UserTestData.USER;
import static ru.valuyskiy.chooseyourlunch.web.json.JsonUtil.writeValue;

public class DishesAdminRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RestaurantsAdminRestController.REST_URL + "/" + RESTAURANT1_ID + "/menus/" + MENU1_RESTAURANT1_ID + "/dishes/";

    @Autowired
    DishService dishService;

    @Test
    public void getToByMenuId() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(
                        dishService.toTo(DISH1_MENU1_REST1),
                        dishService.toTo(DISH2_MENU1_REST1),
                        dishService.toTo(DISH3_MENU1_REST1),
                        dishService.toTo(DISH4_MENU1_REST1)));
    }


    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + DISH1_MENU1_REST1_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(dishService.toTo(DISH1_MENU1_REST1)));
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
        mockMvc.perform(get(REST_URL + DISH1_MENU1_REST1_ID))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    public void testGetForbidden() throws Exception {
        mockMvc.perform(get(REST_URL + DISH1_MENU1_REST1_ID)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + DISH1_MENU1_REST1_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertMatch(dishService.getToByMenuId(MENU1_RESTAURANT1_ID),
                dishService.toTo(DISH2_MENU1_REST1),
                dishService.toTo(DISH3_MENU1_REST1),
                dishService.toTo(DISH4_MENU1_REST1));
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
        DishTo updated = getUpdated();
        mockMvc.perform(put(REST_URL + DISH1_MENU1_REST1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(writeValue(updated)))
                .andExpect(status().isOk())
                .andDo(print());

        assertMatch(dishService.toTo(dishService.get(DISH1_MENU1_REST1_ID)), updated);
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void testUpdateDuplicate() throws Exception {
        DishTo updated = getUpdated();
        updated.setName(DISH2_MENU1_REST1.getName());
        mockMvc.perform(put(REST_URL + updated.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(writeValue(updated)))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void testCreate() throws Exception {
        DishTo expected = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(writeValue(expected)))
                .andExpect(status().isCreated())
                .andDo(print());

        DishTo returned = readFromJson(action, DishTo.class);
        expected.setId(returned.getId());

        assertMatch(returned, expected);
        assertMatch(dishService.getToByMenuId(MENU1_RESTAURANT1_ID),
                dishService.toTo(DISH1_MENU1_REST1),
                dishService.toTo(DISH2_MENU1_REST1),
                dishService.toTo(DISH3_MENU1_REST1),
                dishService.toTo(DISH4_MENU1_REST1),
                expected);
    }

    @Test
    public void testCreateInvalid() throws Exception {
        DishTo expected = getCreated();
        expected.setName("");
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(writeValue(expected)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(ErrorType.VALIDATION_ERROR));
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void testCreateDuplicate() throws Exception {
        DishTo created = getCreated();
        created.setName(DISH1_MENU1_REST1.getName());
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(writeValue(created)))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(errorType(ErrorType.DATA_ERROR));
    }
}