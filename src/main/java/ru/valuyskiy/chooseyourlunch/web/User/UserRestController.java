package ru.valuyskiy.chooseyourlunch.web.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.valuyskiy.chooseyourlunch.AuthorizedUser;
import ru.valuyskiy.chooseyourlunch.service.MenuService;
import ru.valuyskiy.chooseyourlunch.service.UserService;
import ru.valuyskiy.chooseyourlunch.service.VotingService;
import ru.valuyskiy.chooseyourlunch.to.MenuTo;
import ru.valuyskiy.chooseyourlunch.web.admin.UsersAdminRestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rest/user")
public class UserRestController {

    protected static final Logger log = LoggerFactory.getLogger(UsersAdminRestController.class);

    @Autowired
    private MenuService menuService;

    @Autowired
    private VotingService votingService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/menus", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MenuTo> getTodayMenu(@RequestParam(value = "date", required = false)
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        if (date == null) {
            date = LocalDate.now();
        }

        log.info("Get all Memus for date {} by user id={}", date, AuthorizedUser.id());
        return menuService.getTo(date);
    }

    @PutMapping(value = "/menus/{id}/votes")
    public ResponseEntity voting(@PathVariable("id") int menuId) {
        if (votingService.votingById(menuId) != null) {
            log.info("User id={} vote for memu id={}", AuthorizedUser.id(), menuId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            log.info("Vote of user id={} for memu id={} is rejected.", AuthorizedUser.id(), menuId);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }
}
