package ru.valuyskiy.chooseyourlunch.service;

import ru.valuyskiy.chooseyourlunch.model.Vote;

import java.util.List;

public interface VotingService extends AbstractCrudService<Vote> {

    Vote votingById(int menuId);
}
