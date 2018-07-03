package ru.valuyskiy.chooseyourlunch.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.valuyskiy.chooseyourlunch.model.User;
import ru.valuyskiy.chooseyourlunch.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping(RegisterRestController.REST_URL)
public class RegisterRestController {

    private static final Logger log = LoggerFactory.getLogger(ProfileRestController.class);

    static final String REST_URL = "/rest/register";

    @Autowired
    UserService userService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public User register(@Valid @RequestBody User user) {

        User registered = userService.create(user);
        log.info("New user registered: id:{}", registered.getId());
        return user;
    }
}
