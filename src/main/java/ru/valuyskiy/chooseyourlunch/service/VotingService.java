package ru.valuyskiy.chooseyourlunch.service;

import ru.valuyskiy.chooseyourlunch.model.Vote;

import java.util.List;

public interface VotingService extends BaseCrudService<Vote> {

    List<Vote> getByMenuId(int menuId);

    Vote votingById(int menuId);
}
