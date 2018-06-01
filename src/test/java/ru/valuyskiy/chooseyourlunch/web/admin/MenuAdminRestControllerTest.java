package ru.valuyskiy.chooseyourlunch.web.admin;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import ru.valuyskiy.chooseyourlunch.model.Menu;
import ru.valuyskiy.chooseyourlunch.service.MenuService;
import ru.valuyskiy.chooseyourlunch.web.AbstractControllerTest;
import ru.valuyskiy.chooseyourlunch.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class MenuAdminRestControllerTest extends AbstractControllerTest{

    private static final String REST_URL = MenusAdminRestController.REST_URL + "/";

    @Autowired
    MenuService service;

    @Test
    public void testGetAll() throws Exception {
        Menu menu = service.get(100004);
        mockMvc.perform(get(REST_URL))
                .andDo(print())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(content().json(JsonUtil.writeValue(menu)));
    }

    @Test
    public void testGet() throws Exception{
    }
}