package com.bparent.workshop.unittests.bean;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FrameTest {

    @Test
    public void registerShot_should_set_the_first_shot_value_on_first_call() {
        // Given
        Frame frame = new Frame();

        // When
        frame.registerShot(2);

        // Then
        assertEquals(2, frame.getFirstShot());
        assertNull(frame.getSecondShot());
    }

    @Test
    public void registerShot_should_set_the_second_shot_value_on_second_call() {
        // Given
        Frame frame = new Frame();

        // When
        frame.registerShot(2);
        frame.registerShot(3);

        // Then
        assertEquals(2, frame.getFirstShot());
        assertEquals(3, frame.getSecondShot());
    }

    @Test
    public void registerShot_should_throw_exception_if_all_shot_are_done() {
        // Given
        Frame frame = new Frame();
        frame.registerShot(5 );
        frame.registerShot(3 );

        // When & Then
        assertThrows(IllegalStateException.class, () -> frame.registerShot(0 ));
    }

    @Test
    public void registerShot_should_throw_exception_if_shot_value_is_more_than_10() {
        // Given
        Frame frame = new Frame();

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> frame.registerShot(25 ));
    }

    @Test
    public void registerShot_should_throw_exception_if_shot_sum_value_is_more_than_10() {
        // Given
        Frame frame = new Frame();
        frame.registerShot(5 );

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> frame.registerShot(25 ));
    }

    @Test
    public void canAcceptShot_should_return_true_if_not_shot_done() {
        // Given
        Frame frame = new Frame();

        // When
        boolean canAcceptShot = frame.canAcceptShot();

        // Then
        assertTrue(canAcceptShot);
    }

    @Test
    public void canAcceptShot_should_return_true_if_first_shot_done_and_not_strike() {
        // Given
        Frame frame = new Frame();
        frame.registerShot(5);

        // When
        boolean canAcceptShot = frame.canAcceptShot();

        // Then
        assertTrue(canAcceptShot);
    }

    @Test
    public void canAcceptShot_should_return_false_if_first_shot_done_and_is_a_strike() {
        // Given
        Frame frame = new Frame();
        frame.registerShot(10);

        // When
        boolean canAcceptShot = frame.canAcceptShot();

        // Then
        assertFalse(canAcceptShot);
    }

    @Test
    public void canAcceptShot_should_return_false_if_both_shot_are_done() {
        // Given
        Frame frame = new Frame();
        frame.registerShot(1);
        frame.registerShot(1);

        // When
        boolean canAcceptShot = frame.canAcceptShot();

        // Then
        assertFalse(canAcceptShot);
    }

    @Test
    public void isSpare_should_return_false_if_sum_of_score_is_less_than_10() {
        // Given
        Frame frame = new Frame();
        frame.registerShot(4);
        frame.registerShot(3);

        // When
        boolean isSpare = frame.isSpare();

        // Then
        assertFalse(isSpare);
    }

    @Test
    public void isSpare_should_return_true_if_sum_of_score_is_10() {
        // Given
        Frame frame = new Frame();
        frame.registerShot(7);
        frame.registerShot(3);

        // When
        boolean isSpare = frame.isSpare();

        // Then
        assertTrue(isSpare);
    }

    @Test
    public void isSpare_should_return_false_if_first_shot_is_10() {
        // Given
        Frame frame = new Frame();
        frame.registerShot(10);

        // When
        boolean isSpare = frame.isSpare();

        // Then
        assertFalse(isSpare);
    }

    @Test
    public void isStrike_should_return_false_if_first_shot_is_not_10() {
        // Given
        Frame frame = new Frame();
        frame.registerShot(4);
        frame.registerShot(3);

        // When
        boolean isStrike = frame.isStrike();

        // Then
        assertFalse(isStrike);
    }

    @Test
    public void isStrike_should_return_false_if_is_a_spare() {
        // Given
        Frame frame = new Frame();
        frame.registerShot(7);
        frame.registerShot(3);

        // When
        boolean isStrike = frame.isStrike();

        // Then
        assertFalse(isStrike);
    }


    @Test
    public void isStrike_should_return_true_if_first_shot_is_10() {
        // Given
        Frame frame = new Frame();
        frame.registerShot(10);

        // When
        boolean isStrike = frame.isStrike();

        // Then
        assertTrue(isStrike);
    }

    @Test
    public void getAllShots_should_return_list_of_2_integers_if_the_2_shots_are_done() {
        // Given
        Frame frame = new Frame();
        frame.registerShot(1);
        frame.registerShot(2);

        // When
        List<Integer> allShots = frame.getAllShots();

        // Then
        assertEquals(2, allShots.size());
        assertEquals(1, allShots.get(0));
        assertEquals(2, allShots.get(1));
    }

    @Test
    public void getAllShots_should_return_list_of_1_integers_if_1_shot_is_done() {
        // Given
        Frame frame = new Frame();
        frame.registerShot(1);

        // When
        List<Integer> allShots = frame.getAllShots();

        // Then
        assertEquals(1, allShots.size());
        assertEquals(1, allShots.get(0));
    }

    @Test
    public void getAllShots_should_return_list_of_1_integers_if_player_has_done_a_strike() {
        // Given
        Frame frame = new Frame();
        frame.registerShot(10);

        // When
        List<Integer> allShots = frame.getAllShots();

        // Then
        assertEquals(1, allShots.size());
        assertEquals(10, allShots.get(0));
    }

    @Test
    public void getAllShots_should_return_a_void_list_if_no_shot_is_done() {
        // Given
        Frame frame = new Frame();

        // When
        List<Integer> allShots = frame.getAllShots();

        // Then
        assertEquals(0, allShots.size());
    }

}