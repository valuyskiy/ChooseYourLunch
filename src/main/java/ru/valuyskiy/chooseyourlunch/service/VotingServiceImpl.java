package ru.valuyskiy.chooseyourlunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.valuyskiy.chooseyourlunch.model.Vote;
import ru.valuyskiy.chooseyourlunch.repository.VoteRepository;
import ru.valuyskiy.chooseyourlunch.util.exception.NotFoundException;

import java.util.List;

import static ru.valuyskiy.chooseyourlunch.util.ValidationUtil.checkNotFoundWithId;

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
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @Override
    public List<Vote> getAll() {
        return repository.findAll();
    }

    @Override
    public void update(Vote vote) {
        Assert.notNull(vote, "Vote must not be null");
        checkNotFoundWithId(repository.save(vote), vote.getId());
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }
}
