package ru.valuyskiy.chooseyourlunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.valuyskiy.chooseyourlunch.model.Vote;
import ru.valuyskiy.chooseyourlunch.repository.VoteRepository;
import ru.valuyskiy.chooseyourlunch.util.exception.NotFoundException;

import java.util.List;

@Service("voteService")
public class VotingServiceImpl implements VotingService {

    @Autowired
    VoteRepository repository;

    @Override
    public Vote create(Vote vote) {
        Assert.notNull(vote, "Vote must not be null");
        return repository.save(vote);
    }

    @Override
    public Vote get(int id) throws NotFoundException {
        return null;
    }

    @Override
    public List<Vote> getAll() {
        return null;
    }

    @Override
    public void update(Vote vote) {

    }

    @Override
    public void delete(int id) throws NotFoundException {

    }
}
