package ru.valuyskiy.chooseyourlunch.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "voting", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "date"}, name = "voting_unique_user_date_idx"))
public class Vote extends AbstractBaseEntity {

    public static final LocalTime votingTime = LocalTime.of(11, 0);

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Menu menu;

    public Vote() {
    }

    public Vote(User user, LocalDate date, Menu menu) {
        this.user = user;
        this.date = date;
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "userId=" + user.getId() +
                ", date=" + date +
                ", menuId=" + menu.getId() +
                ", id=" + id +
                '}';
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
