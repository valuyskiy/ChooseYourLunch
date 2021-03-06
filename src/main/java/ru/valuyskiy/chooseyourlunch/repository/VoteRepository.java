package ru.valuyskiy.chooseyourlunch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.valuyskiy.chooseyourlunch.model.Vote;
import ru.valuyskiy.chooseyourlunch.to.VotingStatisticsTo;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id")
    int delete(@Param("id") int id);

    Vote getByUser_IdAndDate(int userId, LocalDate date);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO voting (user_id, date, menu_id) VALUES " +
            "(?1, ?2, ?3)", nativeQuery = true)
    int create(int userId, LocalDate date, int menuId);

    @Query("SELECT new ru.valuyskiy.chooseyourlunch.to.VotingStatisticsTo(v.menu.id, count(v.id)) from Vote v WHERE v.date = ?1 GROUP BY v.menu.id")
    List<VotingStatisticsTo> getVotingStatistics(LocalDate date);
}
