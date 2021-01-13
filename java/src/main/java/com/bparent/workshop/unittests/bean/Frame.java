package com.bparent.workshop.unittests.bean;

import lombok.Data;

@Data
public class Frame {

    private Integer firstShot;
    private Integer secondShot;
    private Integer score;

    private String formatShot(Integer shotValue) {
        return shotValue == null ? "-" : String.valueOf(shotValue);
    }

    public String toString() {
        return String.format("[%s | %s]", formatShot(this.firstShot), formatShot(this.secondShot));
    }

    public String getDisplayScore() {
        return formatShot(this.score);
    }
}
