package com.bparent.workshop.unittests.bean;

import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor
public class RegularFrame extends Frame {

    public void registerShot(Integer shotValue) {
        if (!canAcceptShot()) {
            throw new IllegalStateException("This frame cannot accept shots anymore");
        }
        if (shotValue > 10) {
            throw new IllegalArgumentException("You cannot have a better score than 10 for one shot");
        }
        if (this.firstShot != null && this.firstShot + shotValue > 10) {
            throw new IllegalArgumentException("The sum of 2 shots cannot be > 10");
        }

        if (this.firstShot == null) {
            this.firstShot = shotValue;
        } else {
            this.secondShot = shotValue;
        }
    }

    public List<Integer> getAllShots() {
        return Stream.of(
                this.firstShot,
                this.secondShot
        ).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public String toString() {
        return String.format("[%s | %s]", formatShot(this.firstShot), formatShot(this.secondShot));
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
