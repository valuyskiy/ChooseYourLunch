package ru.valuyskiy.chooseyourlunch.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.valuyskiy.chooseyourlunch.model.Menu;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Menu m WHERE m.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=?1")
    List<Menu> getByRestaurant(int restaurantId);

//    List<Menu> getByDate(LocalDate date);
//
//    @EntityGraph(attributePaths = {"dishes"}, type = EntityGraph.EntityGraphType.LOAD)
//    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=?1 AND m.date=?2")
//    Menu getWithDishesByRestaurantAndDate(int restaurant, LocalDate date);

    @EntityGraph(attributePaths = {"dishes", "restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Menu m WHERE m.date=?1")
    List<Menu> getWithDishesByDate(LocalDate date);

//    @EntityGraph(attributePaths = {"dishes", "restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
//    @Query("SELECT m FROM Menu m WHERE m.id=?1")
//    Optional<Menu> getWithDishesById(int id);
}
