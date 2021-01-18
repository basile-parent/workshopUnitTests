import LastFrame from "./lastFrame";

describe('Last Frame tests - Register shot', () => {

  test('registerShot should set the first shot value on first call', () => {
    // Given
    const frame = new LastFrame();

    // When
    frame.registerShot(1);

    // Then
    expect(1).toEqual(frame.firstShot);
    expect(frame.secondShot).toBeNull();
  });

  test('registerShot should set the second shot value on second call', () => {
    // Given
    const frame = new LastFrame();
    frame.registerShot(1);

    // When
    frame.registerShot(2);

    // Then
    expect(1).toEqual(frame.firstShot);
    expect(2).toEqual(frame.secondShot);
  });

  test('registerShot should throw error if trying to add a third shot without spare or strike on the 2 firsts', () => {
    // Given
    const frame = new LastFrame();
    frame.registerShot(1);
    frame.registerShot(2);

    // When & Then
    expect(() => frame.registerShot(3)).toThrow(Error);
  });

  test('registerShot should throw error if shot value is more than 10', () => {
    // Given
    const frame = new LastFrame();

    // When & Then
    expect(() => frame.registerShot(25)).toThrow(Error);
  });

  test('registerShot should throw error if shot sum value is more than 10', () => {
    // Given
    const frame = new LastFrame();
    frame.registerShot(4);

    // When & Then
    expect(() => frame.registerShot(8)).toThrow(Error);
  });

  test('registerShot should accept third shot if player have done a spare', () => {
    // Given
    const frame = new LastFrame();
    frame.registerShot(4);
    frame.registerShot(6);

    // When
    frame.registerShot(2)

    // Then
    expect(4).toEqual(frame.firstShot);
    expect(6).toEqual(frame.secondShot);
    expect(2).toEqual(frame.thirdShot);
  });


  test('registerShot should accept third shot if player have done a strike', () => {
    // Given
    const frame = new LastFrame();
    frame.registerShot(10);
    frame.registerShot(0);

    // When
    frame.registerShot(2)

    // Then
    expect(10).toEqual(frame.firstShot);
    expect(0).toEqual(frame.secondShot);
    expect(2).toEqual(frame.thirdShot);
  });

  test('registerShot should accept three strikes in a row', () => {
    // Given
    const frame = new LastFrame();

    // When
    frame.registerShot(10);
    frame.registerShot(10);
    frame.registerShot(10)

    // Then
    expect(10).toEqual(frame.firstShot);
    expect(10).toEqual(frame.secondShot);
    expect(10).toEqual(frame.thirdShot);
  });

  test('registerShot should throw error if first shot was a strike and sum of the 2 remaining shot is more than 10', () => {
    // Given
    const frame = new LastFrame();
    frame.registerShot(10);
    frame.registerShot(7);

    // When & Then
    expect(() => frame.registerShot(7)).toThrow(Error);
  });

});

describe('Last Frame tests - Can accept', () => {

  test('canAcceptShot should return true if no shot is done', () => {
    // Given
    const frame = new LastFrame();

    // When & Then
    expect(frame.canAcceptShot()).toBeTruthy();
  });

  test('canAcceptShot should return false if first shot is done and is not a strike', () => {
    // Given
    const frame = new LastFrame();
    frame.registerShot(1);

    // When & Then
    expect(frame.canAcceptShot()).toBeTruthy();
  });

  test('canAcceptShot should return false if first shot is done and is a strike', () => {
    // Given
    const frame = new LastFrame();
    frame.registerShot(10);

    // When & Then
    expect(frame.canAcceptShot()).toBeTruthy();
  });

  test('canAcceptShot should return false if 2 shots are done with no spare or strike', () => {
    // Given
    const frame = new LastFrame();
    frame.registerShot(1);
    frame.registerShot(2);

    // When & Then
    expect(frame.canAcceptShot()).toBeFalsy();
  });

  test('canAcceptShot should return true if 2 shots are done with a spare', () => {
    // Given
    const frame = new LastFrame();
    frame.registerShot(4);
    frame.registerShot(6);

    // When & Then
    expect(frame.canAcceptShot()).toBeTruthy();
  });

  test('canAcceptShot should return true if 2 shots are done with a strike', () => {
    // Given
    const frame = new LastFrame();
    frame.registerShot(10);
    frame.registerShot(0);

    // When & Then
    expect(frame.canAcceptShot()).toBeTruthy();
  });

});

describe('Last Frame tests - get all shots', () => {

  test('getAllShots should return a list of 3 integers if the all shots are done', () => {
    // Given
    const frame = new LastFrame();
    frame.registerShot(10);
    frame.registerShot(10);
    frame.registerShot(10);

    // When
    const allShots = frame.getAllShots();

    // Then
    expect(3).toEqual(allShots.length);
    expect(10).toEqual(allShots[0]);
    expect(10).toEqual(allShots[1]);
    expect(10).toEqual(allShots[2]);
  });

  test('getAllShots should return a list of 2 integers if 2 shots are done (and first is a hole)', () => {
    // Given
    const frame = new LastFrame();
    frame.registerShot(0);
    frame.registerShot(2);

    // When
    const allShots = frame.getAllShots();

    // Then
    expect(2).toEqual(allShots.length);
    expect(0).toEqual(allShots[0]);
    expect(2).toEqual(allShots[1]);
  });

  test('getAllShots should return a list of 1 integer if only 1 shot is done', () => {
    // Given
    const frame = new LastFrame();
    frame.registerShot(1);

    // When
    const allShots = frame.getAllShots();

    // Then
    expect(1).toEqual(allShots.length);
    expect(1).toEqual(allShots[0]);
  });

  test('getAllShots should return a list of 1 integer if player has done a strike', () => {
    // Given
    const frame = new LastFrame();
    frame.registerShot(10);

    // When
    const allShots = frame.getAllShots();

    // Then
    expect(1).toEqual(allShots.length);
    expect(10).toEqual(allShots[0]);
  });

  test('getAllShots should return a void list if no shot is done', () => {
    // Given
    const frame = new LastFrame();

    // When
    const allShots = frame.getAllShots();

    // Then
    expect(0).toEqual(allShots.length);
  });

});


