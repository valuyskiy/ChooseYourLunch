package ru.valuyskiy.chooseyourlunch.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.valuyskiy.chooseyourlunch.AuthorizedUser;
import ru.valuyskiy.chooseyourlunch.model.User;
import ru.valuyskiy.chooseyourlunch.service.UserService;

import javax.validation.Valid;

import static ru.valuyskiy.chooseyourlunch.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(ProfileRestController.REST_URL)
public class ProfileRestController {

    private static final Logger log = LoggerFactory.getLogger(ProfileRestController.class);

    static final String REST_URL = "/rest/user/profile";

    @Autowired
    UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@AuthenticationPrincipal AuthorizedUser authorizedUser) {

        log.info("User id:{} get profile", authorizedUser.getId());
        return userService.get(authorizedUser.getId());
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthorizedUser authorizedUser) {

        userService.delete(authorizedUser.getId());
        log.info("User id:{} deleted profile", authorizedUser.getId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public User update(@Valid @RequestBody User user, @AuthenticationPrincipal AuthorizedUser authorizedUser) {

        assureIdConsistent(user, authorizedUser.getId());
        log.info("User id:{} update profile", authorizedUser.getId());
        return userService.updateUserProfile(user);
    }
}