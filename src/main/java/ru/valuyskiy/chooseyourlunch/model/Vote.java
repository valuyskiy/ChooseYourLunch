package ru.valuyskiy.chooseyourlunch.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "voting", uniqueConstraints = @UniqueConstraint(columnNames = {"menu_id", "user_id"}, name = "voting_unique_menu_user_idx"))
public class Vote extends AbstractBaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Menu menu;

    public Vote() {
    }

    public Vote(User user, Menu menu) {
        this.user = user;
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "user=" + user +
                ", menu=" + menu +
                ", id=" + id +
                '}';
    }
}
