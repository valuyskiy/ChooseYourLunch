package ru.valuyskiy.chooseyourlunch.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.valuyskiy.chooseyourlunch.model.Restaurant;
import ru.valuyskiy.chooseyourlunch.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping(value = RestaurantAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantAdminRestController extends AbstractAdminRestController {

    static final String REST_URL = AbstractAdminRestController.REST_URL + "/restaurant";

    @Autowired
    private RestaurantService service;

    @GetMapping
    public List<Restaurant> getAll(){
        log.info("getAll");
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public Restaurant get(@PathVariable("id") int id) {
        log.info("get {}", id);
        return service.get(id);
    }
}
