package ru.valuyskiy.chooseyourlunch.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.valuyskiy.chooseyourlunch.model.Menu;
import ru.valuyskiy.chooseyourlunch.service.MenuService;
import ru.valuyskiy.chooseyourlunch.to.MenuTo;
import ru.valuyskiy.chooseyourlunch.util.MenuUtil;

import java.util.List;

@RestController
@RequestMapping(value = MenuAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuAdminRestController extends AbstractAdminRestController {

    static final String REST_URL = AbstractAdminRestController.REST_URL + "/menu";

    @Autowired
    private MenuService service;

    @GetMapping
    public List<Menu> getAll(){
        log.info("getAll");
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public Menu get(@PathVariable("id") int id) {
        log.info("get {}", id);

        return service.get(id);
    }
}
