package com.bparent.workshop.unittests.bean;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LastFrameTest {

    @Test
    public void registerShot_should_throw_exception_if_the_sum_of_the_2_first_shots_is_more_than_10() {
        // Given
        LastFrame frame = new LastFrame();
        frame.registerShot(7);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> frame.registerShot(7));
    }

    @Test
    public void registerShot_should_throw_exception_if_trying_to_add_a_third_shot_without_having_a_previous_strike_or_spare() {
        // Given
        LastFrame frame = new LastFrame();
        frame.registerShot(2);
        frame.registerShot(2);

        // When & Then
        assertThrows(IllegalStateException.class, () -> frame.registerShot(2));
    }

    @Test
    public void registerShot_should_accept_third_shot_if_have_done_a_spare() {
        // Given
        LastFrame frame = new LastFrame();
        frame.registerShot(8);
        frame.registerShot(2);

        // When
        frame.registerShot(3);

        // Then
        assertEquals(8, frame.getFirstShot());
        assertEquals(2, frame.getSecondShot());
        assertEquals(3, frame.getThirdShot());
    }

    @Test
    public void registerShot_should_accept_third_shot_if_have_done_a_strike() {
        // Given
        LastFrame frame = new LastFrame();
        frame.registerShot(10);
        frame.registerShot(2);

        // When
        frame.registerShot(3);

        // Then
        assertEquals(10, frame.getFirstShot());
        assertEquals(2, frame.getSecondShot());
        assertEquals(3, frame.getThirdShot());
    }

    @Test
    public void registerShot_should_accept_third_strikes_in_a_row() {
        // Given
        LastFrame frame = new LastFrame();

        // When
        frame.registerShot(10);
        frame.registerShot(10);
        frame.registerShot(10);

        // Then
        assertEquals(10, frame.getFirstShot());
        assertEquals(10, frame.getSecondShot());
        assertEquals(10, frame.getThirdShot());
    }

    @Test
    public void registerShot_should_throw_exception_if_do_a_strike_on_first_shot_and_male_more_than_10_points_on_the_2_remaining_shots() {
        // Given
        LastFrame frame = new LastFrame();
        frame.registerShot(10);
        frame.registerShot(7);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> frame.registerShot(7));
    }


}