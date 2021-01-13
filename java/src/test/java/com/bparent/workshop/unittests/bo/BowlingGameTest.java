package com.bparent.workshop.unittests.bo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BowlingGameTest {

    @Test
    public void new_object_should_have_10_empty_frames() {
        // Given
        BowlingGame game = new BowlingGame();

        // When (nothing)
        // Then
        assertEquals(10, game.getFrames().size());
        assertNull(game.getFrames().get(0).getFirstShot());
        assertNull(game.getFrames().get(0).getSecondShot());
    }

    @Test
    public void addShoot_should_set_first_shot_of_first_frame_on_first_call() {
        // Given
        BowlingGame game = new BowlingGame();

        // When
        game.addShoot(2);

        // Then
        assertEquals(2, game.getFrames().get(0).getFirstShot());
        assertNull(game.getFrames().get(0).getSecondShot());
    }

    @Test
    public void addShoot_should_set_second_shot_of_first_frame_on_second_call() {
        // Given
        BowlingGame game = new BowlingGame();
        game.addShoot(2);

        // When
        game.addShoot(3);

        // Then
        assertEquals(2, game.getFrames().get(0).getFirstShot());
        assertEquals(3, game.getFrames().get(0).getSecondShot());
    }

    @Test
    public void addShoot_should_set_first_shot_of_second_frame_on_third_call() {
        // Given
        BowlingGame game = new BowlingGame();
        game.addShoot(2);
        game.addShoot(3);

        // When
        game.addShoot(7);

        // Then
        assertEquals(2, game.getFrames().get(0).getFirstShot());
        assertEquals(3, game.getFrames().get(0).getSecondShot());
        assertEquals(7, game.getFrames().get(1).getFirstShot());
    }

    @Test
    public void addShoot_should_set_first_shot_of_second_frame_on_second_call_if_first_is_a_strike() {
        // Given
        BowlingGame game = new BowlingGame();
        game.addShoot(10);

        // When
        game.addShoot(7);

        // Then
        assertEquals(10, game.getFrames().get(0).getFirstShot());
        assertNull(game.getFrames().get(0).getSecondShot());
        assertEquals(7, game.getFrames().get(1).getFirstShot());
    }

    @Test
    public void addShoot_should_throw_exception_if_all_shot_are_done() {
        // Given
        BowlingGame game = new BowlingGame();
        game.addShoot(10);
        game.addShoot(10);
        game.addShoot(10);
        game.addShoot(10);
        game.addShoot(10);
        game.addShoot(10);
        game.addShoot(10);
        game.addShoot(10);
        game.addShoot(10);
        game.addShoot(10);

        // When & Then
        assertThrows(IllegalStateException.class, () -> game.addShoot(1));
    }

    @Test
    public void addShoot_should_update_first_frame_score_with_shot_value_if_its_regular_shot() {
        // Given
        BowlingGame game = new BowlingGame();

        // When
        game.addShoot(7);

        // Then
        assertEquals(7, game.getFrames().get(0).getScore());
    }

    @Test
    public void addShoot_should_update_first_frame_score_with_sum_of_two_first_shot_values_if_its_regular_shot() {
        // Given
        BowlingGame game = new BowlingGame();

        // When
        game.addShoot(7);
        game.addShoot(1);

        // Then
        assertEquals(8, game.getFrames().get(0).getScore());
    }

    @Test
    public void addShoot_should_update_second_frame_score_with_third_shot_value_if_its_regular_shot() {
        // Given
        BowlingGame game = new BowlingGame();
        game.addShoot(7);
        game.addShoot(1);

        // When
        game.addShoot(4);

        // Then
        assertEquals(4, game.getFrames().get(1).getScore());
    }

}