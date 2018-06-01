package ru.valuyskiy.chooseyourlunch.web.admin;

import org.junit.Test;
import org.springframework.http.MediaType;
import ru.valuyskiy.chooseyourlunch.UserTestData;
import ru.valuyskiy.chooseyourlunch.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class UserAdminRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = UsersAdminRestController.REST_URL + "/";


    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)).andDo(print());
    }

    @Test
    public void TestGet() throws Exception {
        mockMvc.perform(get(REST_URL + UserTestData.ADMIN_ID))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}