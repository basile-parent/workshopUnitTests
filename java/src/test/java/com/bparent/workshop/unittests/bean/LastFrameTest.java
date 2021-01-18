package com.bparent.workshop.unittests.bean;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    public void registerShot_should_accept_three_strikes_in_a_row() {
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
    public void registerShot_should_throw_exception_if_do_a_strike_on_first_shot_and_the_sum_of_the_2_remaining_shots_is_more_than_10() {
        // Given
        LastFrame frame = new LastFrame();
        frame.registerShot(10);
        frame.registerShot(7);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> frame.registerShot(7));
    }

    @Test
    public void getAllShots_should_return_a_list_of_3_integers_if_all_shot_are_done() {
        // Given
        LastFrame frame = new LastFrame();

        // When
        frame.registerShot(10);
        frame.registerShot(10);
        frame.registerShot(10);

        // Then
        assertEquals(3, frame.getAllShots().size());
        assertEquals(10, frame.getAllShots().get(0));
        assertEquals(10, frame.getAllShots().get(1));
        assertEquals(10, frame.getAllShots().get(2));
    }

    @Test
    public void getAllShots_should_return_a_list_of_1_integer_if_only_first_shot_is_done() {
        // Given
        LastFrame frame = new LastFrame();

        // When
        frame.registerShot(10);

        // Then
        assertEquals(1, frame.getAllShots().size());
        assertEquals(10, frame.getAllShots().get(0));
    }

    @Test
    public void canAcceptShot_should_return_true_if_no_shot_is_done() {
        // Given
        LastFrame frame = new LastFrame();

        // When
        boolean canAcceptShot = frame.canAcceptShot();

        // Then
        assertTrue(canAcceptShot);
    }

    @Test
    public void canAcceptShot_should_return_true_if_1_shot_is_done() {
        // Given
        LastFrame frame = new LastFrame();
        frame.registerShot(5);

        // When
        boolean canAcceptShot = frame.canAcceptShot();

        // Then
        assertTrue(canAcceptShot);
    }

    @Test
    public void canAcceptShot_should_return_true_if_1_shot_is_done_and_is_a_strike() {
        // Given
        LastFrame frame = new LastFrame();
        frame.registerShot(10);

        // When
        boolean canAcceptShot = frame.canAcceptShot();

        // Then
        assertTrue(canAcceptShot);
    }

    @Test
    public void canAcceptShot_should_return_false_if_2_shots_are_done_with_no_spare_or_strike() {
        // Given
        LastFrame frame = new LastFrame();
        frame.registerShot(5);
        frame.registerShot(3);

        // When
        boolean canAcceptShot = frame.canAcceptShot();

        // Then
        assertFalse(canAcceptShot);
    }

    @Test
    public void canAcceptShot_should_return_true_if_2_shots_are_done_with_a_spare() {
        // Given
        LastFrame frame = new LastFrame();
        frame.registerShot(4);
        frame.registerShot(6);

        // When
        boolean canAcceptShot = frame.canAcceptShot();

        // Then
        assertTrue(canAcceptShot);
    }

    @Test
    public void canAcceptShot_should_return_true_if_2_shots_are_done_with_a_strike() {
        // Given
        LastFrame frame = new LastFrame();
        frame.registerShot(10);
        frame.registerShot(4);

        // When
        boolean canAcceptShot = frame.canAcceptShot();

        // Then
        assertTrue(canAcceptShot);
    }

}