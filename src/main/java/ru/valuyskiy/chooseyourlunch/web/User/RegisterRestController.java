package ru.valuyskiy.chooseyourlunch.web.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.valuyskiy.chooseyourlunch.model.User;
import ru.valuyskiy.chooseyourlunch.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping(RegisterRestController.REST_URL)
public class RegisterRestController {

    static final String REST_URL = "/rest/register";

    @Autowired
    UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public User update(@Valid @RequestBody User user) {
        return userService.create(user);
    }
}
