package ru.valuyskiy.chooseyourlunch.to;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VotingStatisticsTo {

    @JsonProperty(value = "menu_id")
    private int menuId;
    private long votes;

    public VotingStatisticsTo() {
    }

    public VotingStatisticsTo(int menuId, long votes) {
        this.menuId = menuId;
        this.votes = votes;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public long getVotes() {
        return votes;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }
}
