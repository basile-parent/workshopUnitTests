import BowlingGame from "./bowlingGame";

describe('Dumb tests', () => {
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
});