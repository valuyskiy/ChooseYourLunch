package ru.valuyskiy.chooseyourlunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.valuyskiy.chooseyourlunch.AuthorizedUser;
import ru.valuyskiy.chooseyourlunch.model.Menu;
import ru.valuyskiy.chooseyourlunch.model.Vote;
import ru.valuyskiy.chooseyourlunch.repository.MenuRepository;
import ru.valuyskiy.chooseyourlunch.repository.UserRepository;
import ru.valuyskiy.chooseyourlunch.repository.VoteRepository;
import ru.valuyskiy.chooseyourlunch.to.VotingStatisticsTo;
import ru.valuyskiy.chooseyourlunch.util.exception.NotFoundException;

import java.time.*;
import java.util.List;

import static ru.valuyskiy.chooseyourlunch.util.ValidationUtil.checkNotFoundWithId;

@Service("voteService")
public class VotingServiceImpl implements VotingService {

    private Clock clock;
    private ZoneId zoneId;

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    UserRepository userRepository;

    public VotingServiceImpl() {
        clock = Clock.systemDefaultZone();
        zoneId = ZoneId.systemDefault();
    }

    public void setClock(LocalDateTime date) {
        clock = Clock.fixed(date.atZone(zoneId).toInstant(), zoneId);
    }

    @Override
    public Vote create(Vote vote) {
        Assert.notNull(vote, "Vote must not be null");
        return voteRepository.save(vote);
    }

    @Override
    public Vote get(int id) throws NotFoundException {
        return checkNotFoundWithId(voteRepository.findById(id).orElse(null), id);
    }

    @Override
    public List<Vote> getAll() {
        return voteRepository.findAll();
    }

    @Override
    public Vote update(Vote vote) {
        Assert.notNull(vote, "Vote must not be null");
        return checkNotFoundWithId(voteRepository.save(vote), vote.getId());
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(voteRepository.delete(id) != 0, id);
    }

    @Override
    public Vote voting(int menuId) {

        if (LocalTime.now(clock).isBefore(Vote.VOTING_TIME)) {

            Menu menu = checkNotFoundWithId(menuRepository.findById(menuId).orElse(null), menuId);
            int userId = AuthorizedUser.id();

            if (LocalDate.now().equals(menu.getDate())) {

                Vote todayVote = voteRepository.getByUser_IdAndDate(userId, LocalDate.now());

                if (todayVote == null) {
                    return voteRepository.save(new Vote(userRepository.getOne(userId), LocalDate.now(), menu));
                } else {
                    todayVote.setMenu(menu);
                    return voteRepository.save(todayVote);
                }
            }
        }
        return null;
    }

    @Override
    public List<VotingStatisticsTo> getVotingStatistics(LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        return voteRepository.getVotingStatistics(date);
    }
}
