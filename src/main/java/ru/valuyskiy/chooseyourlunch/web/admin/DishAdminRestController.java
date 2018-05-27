package ru.valuyskiy.chooseyourlunch.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.valuyskiy.chooseyourlunch.model.Dish;
import ru.valuyskiy.chooseyourlunch.service.DishService;

import java.util.List;

@RestController
@RequestMapping(value = DishAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishAdminRestController extends AbstractAdminRestController {

    static final String REST_URL = AbstractAdminRestController.REST_URL + "/dish";

    @Autowired
    private DishService service;

    @GetMapping
    public List<Dish> getAll(){
        log.info("getAll");
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public Dish get(@PathVariable("id") int id) {
        log.info("get {}", id);
        return service.get(id);
    }
}
