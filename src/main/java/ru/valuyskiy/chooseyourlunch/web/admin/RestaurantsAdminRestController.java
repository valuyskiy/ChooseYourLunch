package ru.valuyskiy.chooseyourlunch.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.valuyskiy.chooseyourlunch.AuthorizedUser;
import ru.valuyskiy.chooseyourlunch.model.Restaurant;
import ru.valuyskiy.chooseyourlunch.service.RestaurantService;

import javax.validation.Valid;
import java.util.List;

import static ru.valuyskiy.chooseyourlunch.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = RestaurantsAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantsAdminRestController extends AbstractAdminRestController {

    static final String REST_URL = AbstractAdminRestController.REST_URL + "/restaurants";

    @Autowired
    private RestaurantService service;

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("User id:{} get all Restaurants", AuthorizedUser.id());
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public Restaurant get(@PathVariable("id") int id) {
        log.info("User id:{} get Restaurant id:{}", AuthorizedUser.id(), id);
        return service.get(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant create(@Valid @RequestBody Restaurant restaurant) {
        Restaurant created = service.create(restaurant);
        log.info("User id:{} add new Restaurant id:{}", AuthorizedUser.id(), created.getId());
        return created;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant update(@Valid @RequestBody Restaurant restaurant, @PathVariable("id") int restaurantId) {
        Restaurant updated = service.update(restaurant);
        assureIdConsistent(restaurant, restaurantId);
        log.info("User id:{} rename Restaurant id:{}", AuthorizedUser.id(), updated.getId());
        return updated;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
        log.info("User id:{} delete Restaurant id:{}", AuthorizedUser.id(), id);
    }
}
