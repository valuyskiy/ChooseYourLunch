package ru.valuyskiy.chooseyourlunch.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.valuyskiy.chooseyourlunch.AuthorizedUser;
import ru.valuyskiy.chooseyourlunch.service.DishService;
import ru.valuyskiy.chooseyourlunch.to.DishTo;

import javax.validation.Valid;
import java.util.List;

import static ru.valuyskiy.chooseyourlunch.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = DishesAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishesAdminRestController extends AbstractAdminRestController {

    static final String REST_URL = MenusAdminRestController.REST_URL + "/{menuId}/dishes";

    @Autowired
    private DishService dishService;

    @GetMapping
    public List<DishTo> getToByMenuId(@PathVariable("menuId") int menuId) {

        List<DishTo> dishes = dishService.getToByMenuId(menuId);
        log.info("User id:{} get dishes of menu id:{}", AuthorizedUser.id(), menuId);
        return dishes;
    }

    @GetMapping("/{dishId}")
    public DishTo get(@PathVariable("dishId") int dishId) {

        DishTo dish = dishService.getToById(dishId);
        log.info("User id:{} get dish id:{}", AuthorizedUser.id(), dishId);
        return dish;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public DishTo create(@Valid @RequestBody DishTo dishTo) {

        DishTo created = dishService.createFromTo(dishTo);
        log.info("User id:{} add new dish id:{} to menu id:{}", AuthorizedUser.id(), created.getId(), created.getMenuId());
        return created;
    }

    @PutMapping(value = "/{dishId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DishTo update(@Valid @RequestBody DishTo dishTo, @PathVariable("dishId") int dishId) {

        assureIdConsistent(dishTo, dishId);
        DishTo updated = dishService.updateFromTo(dishTo);
        log.info("User id:{} updated dish id:{}", AuthorizedUser.id(), updated.getId());
        return updated;
    }

    @DeleteMapping("/{dishId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("dishId") int dishId) {

        dishService.delete(dishId);
        log.info("User id:{} deleted dish id:{}", AuthorizedUser.id(), dishId);
    }
}
