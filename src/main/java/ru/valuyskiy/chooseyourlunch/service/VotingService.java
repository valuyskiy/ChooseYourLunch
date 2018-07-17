package ru.valuyskiy.chooseyourlunch.service;

import ru.valuyskiy.chooseyourlunch.model.Vote;
import ru.valuyskiy.chooseyourlunch.to.VotingStatisticsTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface VotingService extends AbstractCrudService<Vote> {

    Vote voting(int menuId);

    List<VotingStatisticsTo> getVotingStatistics(LocalDate date);

    void setClock(LocalDateTime date);
}
