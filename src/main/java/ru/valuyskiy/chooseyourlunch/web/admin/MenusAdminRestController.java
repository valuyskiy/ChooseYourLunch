package ru.valuyskiy.chooseyourlunch.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.valuyskiy.chooseyourlunch.AuthorizedUser;
import ru.valuyskiy.chooseyourlunch.model.Menu;
import ru.valuyskiy.chooseyourlunch.service.MenuService;
import ru.valuyskiy.chooseyourlunch.to.MenuTo;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = MenusAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenusAdminRestController extends AbstractAdminRestController {

    static final String REST_URL = RestaurantsAdminRestController.REST_URL + "/{restaurantId}/menus";

    @Autowired
    private MenuService menuService;

    @GetMapping
    public List<MenuTo> getByRestaurantId(@PathVariable("restaurantId") int restaurantId) {
        log.info("User id:{} get all menus of Restaurant id:{}", AuthorizedUser.id(), restaurantId);
        return menuService.getToByRestaurantId(restaurantId);
    }

    @GetMapping("/{menuId}")
    public MenuTo getByMenuId(@PathVariable("menuId") int menuId) {
        log.info("User id:{} get Menu id:{}", AuthorizedUser.id(), menuId);
        return menuService.toTo(menuService.get(menuId));
    }

    @PostMapping
    public MenuTo create(@PathVariable("restaurantId") int restaurantId,
                         @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        Menu created = menuService.createByRestaurantIdAndDate(restaurantId, date);
        log.info("User id:{} add new Menu id:{} for Restaurant id:{}", AuthorizedUser.id(), created.getId(), restaurantId);

        return menuService.toTo(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public MenuTo update(@Valid @RequestBody MenuTo menuTo) {

        Menu updated = menuService.update(menuTo);
        log.info("User id:{} updated Menu id:{}", AuthorizedUser.id(), updated.getId());
        return menuService.toTo(updated);
    }

    @DeleteMapping("/{menuId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    private void delete(@PathVariable("menuId") int menuId) {
        menuService.delete(menuId);
        log.info("User id:{} deleted Menu id:{}", AuthorizedUser.id(), menuId);
    }
}
