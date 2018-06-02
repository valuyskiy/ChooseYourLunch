package ru.valuyskiy.chooseyourlunch.service;

import ru.valuyskiy.chooseyourlunch.model.Vote;

public interface VotingService extends AbstractCrudService<Vote> {

    Vote voting(int menuId);
}
