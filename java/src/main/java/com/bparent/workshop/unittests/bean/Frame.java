package com.bparent.workshop.unittests.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
public class Frame {

    private Integer firstShot;
    private Integer secondShot;
    @Setter
    private Integer score;

    public void registerShot(Integer shotValue) {
        if (!canAcceptShot()) {
            throw new IllegalStateException("This frame cannot accept shots anymore");
        }
        if (shotValue > 10) {
            throw new IllegalArgumentException("You cannot have a better score than 10 for one shot");
        }
        if (this.firstShot != null && this.firstShot + shotValue > 10) {
            throw new IllegalArgumentException("You cannot have a better score than 10 for one shot");
        }

        if (this.firstShot == null) {
            this.firstShot = shotValue;
        } else {
            this.secondShot = shotValue;
        }
    }

    private String formatShot(Integer shotValue) {
        return shotValue == null ? "-" : String.valueOf(shotValue);
    }

    public String toString() {
        return String.format("[%s | %s]", formatShot(this.firstShot), formatShot(this.secondShot));
    }

    public String getDisplayScore() {
        return formatShot(this.score);
    }

    public boolean canAcceptShot() {
        return this.firstShot == null ||
                (this.firstShot != 10 && this.secondShot == null);
    }

    public boolean isSpare() {
        return this.firstShot != null && this.secondShot != null &&
                this.firstShot + this.secondShot == 10;
    }

    public boolean isStrike() {
        return this.firstShot != null && this.firstShot == 10;
    }

}
