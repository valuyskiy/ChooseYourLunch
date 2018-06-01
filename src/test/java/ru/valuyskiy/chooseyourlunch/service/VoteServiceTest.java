package ru.valuyskiy.chooseyourlunch.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.valuyskiy.chooseyourlunch.model.Menu;

import java.time.LocalDate;
import java.util.List;

public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    protected VotingService service;

//    @Test
//
//    public void testGetByMenuId() {
//        System.out.println(service.getByMenuId(100015));
//    }
}