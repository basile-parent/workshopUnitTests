package com.bparent.workshop.unittests.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
public abstract class Frame {

    protected Integer firstShot;
    protected Integer secondShot;
    @Setter
    protected Integer score;

    protected String formatShot(Integer shotValue) {
        return shotValue == null ? "-" : String.valueOf(shotValue);
    }

    public String getDisplayScore() {
        return formatShot(this.score);
    }

    public abstract void registerShot(Integer shotValue);
    public abstract List<Integer> getAllShots();
    public abstract boolean canAcceptShot();
    public abstract boolean isSpare();
    public abstract boolean isStrike();


}
