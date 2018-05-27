package ru.valuyskiy.chooseyourlunch.web.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;


public abstract class AbstractAdminRestController {
    protected static final Logger log = LoggerFactory.getLogger(UserAdminRestController.class);

    static final String REST_URL = "/rest/admin";
}
