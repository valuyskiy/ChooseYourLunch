package ru.valuyskiy.chooseyourlunch.web.User;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.valuyskiy.chooseyourlunch.model.User;
import ru.valuyskiy.chooseyourlunch.service.UserService;
import ru.valuyskiy.chooseyourlunch.util.exception.ErrorType;
import ru.valuyskiy.chooseyourlunch.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.valuyskiy.chooseyourlunch.TestUtil.userHttpBasic;
import static ru.valuyskiy.chooseyourlunch.UserTestData.*;

public class ProfileRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = ProfileRestController.REST_URL;

    @Autowired
    UserService service;


    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testGetUnAuth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isNoContent())
                .andDo(print());
        assertMatch(service.getAll(), ADMIN);
    }

    @Test
    public void testUpdate() throws Exception {

        User updatedUser = getUpdated();

        mockMvc.perform(put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithPassword(updatedUser, updatedUser.getPassword()))
                .with(userHttpBasic(USER)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        assertMatch(service.getByEmail(updatedUser.getEmail()), updatedUser);
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void testUpdateDuplicateEmail() throws Exception {

        User updatedUser = getUpdated();
        updatedUser.setEmail(ADMIN.getEmail());

        mockMvc.perform(put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER))
                .content(jsonWithPassword(updatedUser, updatedUser.getPassword())))
                .andExpect(status().isConflict())
                .andExpect(errorType(ErrorType.DATA_ERROR))
                .andDo(print());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void testUpdateAnotherProfile() throws Exception {

        User updatedUser = getUpdated();
        updatedUser.setId(ADMIN_ID);

        mockMvc.perform(put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER))
                .content(jsonWithPassword(updatedUser, updatedUser.getPassword())))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                .andDo(print());
    }
}