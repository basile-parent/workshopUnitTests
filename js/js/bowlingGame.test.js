import BowlingGame from "./bowlingGame";

describe('Bowling game tests', () => {
  test('New bowling game should have 10 empty frames', () => {
    // Given
    const bowlingGame = new BowlingGame();

    // When (nothing)
    // Then
    expect(10).toEqual(bowlingGame.frames.length);
    expect(bowlingGame.frames[0].firstShot).toBeNull();
    expect(bowlingGame.frames[0].secondShot).toBeNull();
    expect(bowlingGame.frames[0].score).toBeNull();
  });

  test('addShoot should set first shot of first frame on first call', () => {
    // Given
    const bowlingGame = new BowlingGame();

    // When
    bowlingGame.addShot(2);

    // Then
    expect(2).toEqual(bowlingGame.frames[0].firstShot);
    expect(bowlingGame.frames[0].secondShot).toBeNull();
  });

});