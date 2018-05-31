package ru.valuyskiy.chooseyourlunch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.valuyskiy.chooseyourlunch.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id")
    int delete(@Param("id") int id);

    // TODO уточнить необходимость метода
    List<Vote> getByMenu_Id(int menuId);

    int countByMenu_Id(int menuId);

    int countByUser_IdAndMenu_Id(int userId, int MenuId);

    Vote getByUser_IdAndDate(int userId, LocalDate date);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO voting (user_id, date, menu_id) VALUES " +
            "(?1, ?2, ?3)", nativeQuery = true)
    int create(int userId, LocalDate date, int menuId);
}
