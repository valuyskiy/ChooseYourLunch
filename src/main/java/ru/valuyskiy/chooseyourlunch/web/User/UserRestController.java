package ru.valuyskiy.chooseyourlunch.web.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.valuyskiy.chooseyourlunch.model.Menu;
import ru.valuyskiy.chooseyourlunch.service.MenuService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rest/user")
public class UserRestController {

    @Autowired
    private MenuService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> getTodayMenu(){
        return service.getByDate(LocalDate.of(2018,5,20));
    }
}
