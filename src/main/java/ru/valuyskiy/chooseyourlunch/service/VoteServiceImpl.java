package ru.valuyskiy.chooseyourlunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.valuyskiy.chooseyourlunch.repository.VoteRepository;

@Service("voteService")
public class VoteServiceImpl {

    @Autowired
    VoteRepository repository;
}
