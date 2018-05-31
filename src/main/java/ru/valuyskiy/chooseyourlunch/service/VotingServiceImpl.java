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
import ru.valuyskiy.chooseyourlunch.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.valuyskiy.chooseyourlunch.util.ValidationUtil.checkNotFoundWithId;

@Service("voteService")
public class VotingServiceImpl implements VotingService {

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    UserRepository userRepository;

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
    public List<Vote> getByMenuId(int menuId) {
        return voteRepository.getByMenu_Id(menuId);
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
    public Vote votingById(int menuId) {

        Menu menu = checkNotFoundWithId(menuRepository.findById(menuId).orElse(null), menuId);

        int userId = AuthorizedUser.id();

        Vote vote = voteRepository.getByUser_IdAndDate(userId, LocalDate.now());

        if (LocalDate.now().equals(menu.getDate()) &&
                LocalTime.now().isBefore(Vote.votingTime)) {

            if (vote == null) {
                return voteRepository.save(new Vote(userRepository.getOne(userId), LocalDate.now(), menu));
            } else {
                vote.setMenu(menu);
                return voteRepository.save(vote);
            }
        }
        return null;
    }
}
