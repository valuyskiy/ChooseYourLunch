package ru.valuyskiy.chooseyourlunch.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.valuyskiy.chooseyourlunch.AuthorizedUser;
import ru.valuyskiy.chooseyourlunch.service.MenuService;
import ru.valuyskiy.chooseyourlunch.service.VotingService;
import ru.valuyskiy.chooseyourlunch.to.MenuToWithDishes;
import ru.valuyskiy.chooseyourlunch.to.VotingStatisticsTo;
import ru.valuyskiy.chooseyourlunch.web.admin.UsersAdminRestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rest/user")
public class UserRestController {

    private static final Logger log = LoggerFactory.getLogger(UsersAdminRestController.class);

    @Autowired
    private MenuService menuService;

    @Autowired
    private VotingService votingService;

    @GetMapping(value = "/menus")
    public List<MenuToWithDishes> get(@RequestParam(value = "date", required = false)
                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }

        log.info("Get all Menus for date {} by user id={}", date, AuthorizedUser.id());
        return menuService.getToWithDishes(date);
    }

    @GetMapping(value = "/menus/stats")
    public List<VotingStatisticsTo> getVotingStatistics(@RequestParam(value = "date", required = false)
                                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }

        log.info("Get voting statistics for date {} by user id={}", date, AuthorizedUser.id());
        return votingService.getVotingStatistics(date);
    }

    @PutMapping(value = "/menus/{id}/votes")
    public ResponseEntity voting(@PathVariable("id") int menuId) {
        if (votingService.voting(menuId) != null) {
            log.info("user id={} vote for menu id={}", AuthorizedUser.id(), menuId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            log.info("Vote of user id={} for menu id={} is rejected.", AuthorizedUser.id(), menuId);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }
}
