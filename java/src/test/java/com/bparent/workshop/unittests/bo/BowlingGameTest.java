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
    public void addShoot_should_update_first_frame_score_with_shot_value_if_its_hole() {
        // Given
        BowlingGame game = new BowlingGame();

        // When
        game.addShoot(7);

        // Then
        assertEquals(7, game.getFrames().get(0).getScore());
    }

    @Test
    public void addShoot_should_update_first_frame_score_with_sum_of_two_first_shot_values_if_its_hole() {
        // Given
        BowlingGame game = new BowlingGame();

        // When
        game.addShoot(7);
        game.addShoot(1);

        // Then
        assertEquals(8, game.getFrames().get(0).getScore());
    }

    @Test
    public void addShoot_should_update_second_frame_score_with_the_sum_of_first_frame_and_second_if_its_a_hole() {
        // Given
        BowlingGame game = new BowlingGame();
        game.addShoot(7);
        game.addShoot(1);

        // When
        game.addShoot(4);

        // Then
        assertEquals(12, game.getFrames().get(1).getScore());
    }

    @Test
    public void addShoot_should_value_10_points_if_its_spare_and_no_shot_in_next_frame() {
        // Given
        BowlingGame game = new BowlingGame();
        game.addShoot(7);

        // When
        game.addShoot(3);

        // Then
        assertEquals(10, game.getFrames().get(0).getScore());
    }


    @Test
    public void addShoot_should_change_first_frame_score_with_next_shot_if_its_spare() {
        // Given
        BowlingGame game = new BowlingGame();
        game.addShoot(7);
        game.addShoot(3);

        // When
        game.addShoot(8);

        // Then
        assertEquals(18, game.getFrames().get(0).getScore());
        assertEquals(26, game.getFrames().get(1).getScore());
    }

    @Test
    public void addShoot_should_value_10_points_if_its_strike_and_no_shot_in_next_frame() {
        // Given
        BowlingGame game = new BowlingGame();

        // When
        game.addShoot(10);

        // Then
        assertEquals(10, game.getFrames().get(0).getScore());
    }

    @Test
    public void addShoot_should_change_first_frame_score_with_the_2_next_shots_if_its_strike() {
        // Given
        BowlingGame game = new BowlingGame();
        game.addShoot(10);

        // When
        game.addShoot(5);
        game.addShoot(3);

        // Then
        assertEquals(18, game.getFrames().get(0).getScore());
        assertEquals(26, game.getFrames().get(1).getScore());
    }

    @Test
    public void addShoot_should_change_first_frame_score_with_the_2_next_shots_including_strikes_if_its_strike() {
        // Given
        BowlingGame game = new BowlingGame();
        game.addShoot(10);

        // When
        game.addShoot(10);
        game.addShoot(10);

        // Then
        assertEquals(30, game.getFrames().get(0).getScore());
        assertEquals(50, game.getFrames().get(1).getScore());
        assertEquals(60, game.getFrames().get(2).getScore());

        assertEquals(10, game.getFrames().get(0).getFirstShot());
        assertNull(game.getFrames().get(0).getSecondShot());
        assertEquals(10, game.getFrames().get(1).getFirstShot());
        assertNull(game.getFrames().get(1).getSecondShot());
        assertEquals(10, game.getFrames().get(2).getFirstShot());
        assertNull(game.getFrames().get(2).getSecondShot());
    }

    @Test
    public void addShoot_should_calculate_300_points_for_a_perfect_game() {
        // Given
        BowlingGame game = new BowlingGame();
        game.addShoot(10); // Frame 1
        game.addShoot(10); // Frame 2
        game.addShoot(10); // Frame 3
        game.addShoot(10); // Frame 4
        game.addShoot(10); // Frame 5
        game.addShoot(10); // Frame 6
        game.addShoot(10); // Frame 7
        game.addShoot(10); // Frame 8
        game.addShoot(10); // Frame 9

        // When
        game.addShoot(10); // Frame 10
        game.addShoot(10); // Frame 10
        game.addShoot(10); // Frame 10

        // Then
        assertEquals(300, game.getFrames().get(9).getScore());
    }

}