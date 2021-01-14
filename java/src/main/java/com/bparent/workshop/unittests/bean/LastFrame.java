package com.bparent.workshop.unittests.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor
@Getter
public class LastFrame extends Frame {

    protected Integer thirdShot;

    public void registerShot(Integer shotValue) {
        if (!canAcceptShot()) {
            throw new IllegalStateException("This frame cannot accept shots anymore");
        }
        if (shotValue > 10) {
            throw new IllegalArgumentException("You cannot have a better score than 10 for one shot");
        }

        if (this.firstShot != null) {
            if (this.firstShot != 10) {
                if (this.secondShot == null && this.firstShot + shotValue > 10) {
                    throw new IllegalArgumentException("The sum of 2 shots cannot be > 10");
                }
                if (this.secondShot != null && this.firstShot + this.secondShot < 10) {
                    throw new IllegalArgumentException("You don't have a third shot if you didn't do a spare or a strike");
                }
            }
        }

        if (this.firstShot == null) {
            this.firstShot = shotValue;
        } else if (this.secondShot == null) {
            this.secondShot = shotValue;
        } else {
            this.thirdShot = shotValue;
        }
    }

    public List<Integer> getAllShots() {
        return Stream.of(
                this.firstShot,
                this.secondShot,
                this.thirdShot
        ).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public String toString() {
        return String.format("[%s | %s | %s]", formatShot(this.firstShot), formatShot(this.secondShot), formatShot(this.thirdShot));
    }

    public boolean canAcceptShot() {
        return this.firstShot == null ||
                this.secondShot == null ||
                (this.thirdShot == null && (this.firstShot == 10 || this.firstShot + this.secondShot == 10));
    }

    public boolean isSpare() {
        return false;
    }

    public boolean isStrike() {
        return false;
    }

}
