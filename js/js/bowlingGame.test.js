import BowlingGame from "./bowlingGame";

describe('Bowling game tests', () => {
  test('New bowling game should have 10 empty frames', () => {
    // Given
    const game = new BowlingGame();

    // When (nothing)
    // Then
    expect(10).toEqual(game.frames.length);
    expect(game.frames[0].firstShot).toBeNull();
    expect(game.frames[0].secondShot).toBeNull();
    expect(game.frames[0].score).toBeNull();
  });

  test('addShoot should set first shot of first frame on first call', () => {
    // Given
    const game = new BowlingGame();

    // When
    game.addShot(2);

    // Then
    expect(2).toEqual(game.frames[0].firstShot);
    expect(game.frames[0].secondShot).toBeNull();
  });

  test('addShoot should set second shot of first frame on second call if no strike on first shot', () => {
    // Given
    const game = new BowlingGame();
    game.addShot(2);

    // When
    game.addShot(3);

    // Then
    expect(2).toEqual(game.frames[0].firstShot);
    expect(3).toEqual(game.frames[0].secondShot);
  });

  test('addShoot should set first shot of second frame on third call if no strike on first frame', () => {
    // Given
    const game = new BowlingGame();
    game.addShot(2);
    game.addShot(3);

    // When
    game.addShot(4);

    // Then
    expect(4).toEqual(game.frames[1].firstShot);
    expect(game.frames[1].secondShot).toBeNull();
  });

  test('addShoot should set first shot of second frame on second call if strike on first frame', () => {
    // Given
    const game = new BowlingGame();
    game.addShot(10);

    // When
    game.addShot(4);

    // Then
    expect(10).toEqual(game.frames[0].firstShot);
    expect(game.frames[0].secondShot).toBeNull();
    expect(4).toEqual(game.frames[1].firstShot);
    expect(game.frames[1].secondShot).toBeNull();
  });

  test('addShoot should throw error if all shots are done', () => {
    // Given
    const game = new BowlingGame();
    game.addShot(10); // Frame 1
    game.addShot(10); // Frame 2
    game.addShot(10); // Frame 3
    game.addShot(10); // Frame 4
    game.addShot(10); // Frame 5
    game.addShot(10); // Frame 6
    game.addShot(10); // Frame 7
    game.addShot(10); // Frame 8
    game.addShot(10); // Frame 9
    game.addShot(10); // Frame 10
    game.addShot(10); // Frame 10
    game.addShot(10); // Frame 10

    // When & Then
    expect(() => game.addShot(10)).toThrow(Error);
  });

  test('addShoot should update the first frame score with shot value if its a hole', () => {
    // Given
    const game = new BowlingGame();

    // When
    game.addShot(7);

    // Then
    expect(7).toEqual(game.frames[0].score);
  });

  test('addShoot should update the first frame score with the sum of the two shot values if its a hole', () => {
    // Given
    const game = new BowlingGame();
    game.addShot(7);

    // When
    game.addShot(1);

    // Then
    expect(8).toEqual(game.frames[0].score);
  });

  test('addShoot should update the second frame score with the sum of the all shot values if all are holes', () => {
    // Given
    const game = new BowlingGame();
    game.addShot(7);
    game.addShot(1);

    // When
    game.addShot(4);

    // Then
    expect(12).toEqual(game.frames[1].score);
  });

  test('addShoot should value 10 points on first frame if is a spare and no shot in next frame', () => {
    // Given
    const game = new BowlingGame();
    game.addShot(7);

    // When
    game.addShot(3);

    // Then
    expect(10).toEqual(game.frames[0].score);
  });

  test('addShoot should update first frame value with next shot if is a spare', () => {
    // Given
    const game = new BowlingGame();
    game.addShot(7);
    game.addShot(3);

    // When
    game.addShot(8);

    // Then
    expect(18).toEqual(game.frames[0].score);
    expect(26).toEqual(game.frames[1].score);
  });

  test('addShoot should value 10 points on first frame if is a strike and no shot in next frame', () => {
    // Given
    const game = new BowlingGame();

    // When
    game.addShot(10);

    // Then
    expect(10).toEqual(game.frames[0].score);
  });

  test('addShoot should change first frame score with the 2 next shots if its strike', () => {
    // Given
    const game = new BowlingGame();
    game.addShot(10);

    // When
    game.addShot(5);
    game.addShot(3);

    // Then
    expect(18).toEqual(game.frames[0].score);
    expect(26).toEqual(game.frames[1].score);
  });

  test('addShoot should change first frame score with the 2 next shots (including strikes) if its strike', () => {
    // Given
    const game = new BowlingGame();
    game.addShot(10);

    // When
    game.addShot(10);
    game.addShot(10);

    // Then
    expect(30).toEqual(game.frames[0].score);
    expect(50).toEqual(game.frames[1].score);
    expect(60).toEqual(game.frames[2].score);
  });

  test('addShoot should calculate 300 points for a perfect game', () => {
    // Given
    const game = new BowlingGame();
    game.addShot(10); // Frame 1
    game.addShot(10); // Frame 2
    game.addShot(10); // Frame 3
    game.addShot(10); // Frame 4
    game.addShot(10); // Frame 5
    game.addShot(10); // Frame 6
    game.addShot(10); // Frame 7
    game.addShot(10); // Frame 8
    game.addShot(10); // Frame 9

    // When
    game.addShot(10); // Frame 10
    game.addShot(10); // Frame 10
    game.addShot(10); // Frame 10

    // Then
    expect(300).toEqual(game.frames[9].score);
  });

});