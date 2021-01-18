import RegularFrame from "./regularFrame";

describe('Regular Frame tests - Register shot', () => {

  test('registerShot should set the first shot value on first call', () => {
    // Given
    const frame = new RegularFrame();

    // When
    frame.registerShot(1);

    // Then
    expect(1).toEqual(frame.firstShot);
    expect(frame.secondShot).toBeNull();
  });

  test('registerShot should set the second shot value on second call', () => {
    // Given
    const frame = new RegularFrame();
    frame.registerShot(1);

    // When
    frame.registerShot(2);

    // Then
    expect(1).toEqual(frame.firstShot);
    expect(2).toEqual(frame.secondShot);
  });

  test('registerShot should throw error if all shots are done', () => {
    // Given
    const frame = new RegularFrame();
    frame.registerShot(1);
    frame.registerShot(2);

    // When & Then
    expect(() => frame.registerShot(3)).toThrow(Error);
  });

  test('registerShot should throw error if shot value is more than 10', () => {
    // Given
    const frame = new RegularFrame();

    // When & Then
    expect(() => frame.registerShot(25)).toThrow(Error);
  });

  test('registerShot should throw error if shot sum value is more than 10', () => {
    // Given
    const frame = new RegularFrame();
    frame.registerShot(4);

    // When & Then
    expect(() => frame.registerShot(8)).toThrow(Error);
  });

  test('registerShot should throw error on second call if first shot was a strike', () => {
    // Given
    const frame = new RegularFrame();
    frame.registerShot(10);

    // When & Then
    expect(() => frame.registerShot(0)).toThrow(Error);
  });

});

describe('Regular Frame tests - Can accept', () => {

  test('canAcceptShot should return true if no shot is done', () => {
    // Given
    const frame = new RegularFrame();

    // When & Then
    expect(frame.canAcceptShot()).toBeTruthy();
  });

  test('canAcceptShot should return false if first shot is done and is not a strike', () => {
    // Given
    const frame = new RegularFrame();
    frame.registerShot(1);

    // When & Then
    expect(frame.canAcceptShot()).toBeTruthy();
  });

  test('canAcceptShot should return false if first shot is done and is a strike', () => {
    // Given
    const frame = new RegularFrame();
    frame.registerShot(10);

    // When & Then
    expect(frame.canAcceptShot()).toBeFalsy();
  });

  test('canAcceptShot should return false if both shots are done', () => {
    // Given
    const frame = new RegularFrame();
    frame.registerShot(1);
    frame.registerShot(2);

    // When & Then
    expect(frame.canAcceptShot()).toBeFalsy();
  });

});

describe('Regular Frame tests - isSpare', () => {

  test('isSpare should return false if sum of score is less than 10', () => {
    // Given
    const frame = new RegularFrame();
    frame.registerShot(1);
    frame.registerShot(2);

    // When & Then
    expect(frame.isSpare()).toBeFalsy();
  });

  test('isSpare should return false if sum of score is 10', () => {
    // Given
    const frame = new RegularFrame();
    frame.registerShot(4);
    frame.registerShot(6);

    // When & Then
    expect(frame.isSpare()).toBeTruthy();
  });

  test('isSpare should return false if the first shot is 10', () => {
    // Given
    const frame = new RegularFrame();
    frame.registerShot(10);

    // When & Then
    expect(frame.isSpare()).toBeFalsy();
  });

  test('isSpare should return true if the first shot is 0 and second shot is 10', () => {
    // Given
    const frame = new RegularFrame();
    frame.registerShot(0);
    frame.registerShot(10);

    // When & Then
    expect(frame.isSpare()).toBeTruthy();
  });

});

describe('Regular Frame tests - isStrike', () => {

  test('isStrike should return false if the first shot is 10', () => {
    // Given
    const frame = new RegularFrame();
    frame.registerShot(10);

    // When & Then
    expect(frame.isStrike()).toBeTruthy();
  });

  test('isStrike should return false if the first shot is not 10', () => {
    // Given
    const frame = new RegularFrame();
    frame.registerShot(9);

    // When & Then
    expect(frame.isStrike()).toBeFalsy();
  });

  test('isStrike should return false if is a spare', () => {
    // Given
    const frame = new RegularFrame();
    frame.registerShot(4);
    frame.registerShot(6);

    // When & Then
    expect(frame.isStrike()).toBeFalsy();
  });

});

describe('Regular Frame tests - get all shots', () => {

  test('getAllShots should return a list of 2 integers if the 2 shots are done', () => {
    // Given
    const frame = new RegularFrame();
    frame.registerShot(1);
    frame.registerShot(2);

    // When
    const allShots = frame.getAllShots();

    // Then
    expect(2).toEqual(allShots.length);
    expect(1).toEqual(allShots[0]);
    expect(2).toEqual(allShots[1]);
  });

  test('getAllShots should return a list of 2 integers if the 2 shots are done (and first is a hole)', () => {
    // Given
    const frame = new RegularFrame();
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
    const frame = new RegularFrame();
    frame.registerShot(1);

    // When
    const allShots = frame.getAllShots();

    // Then
    expect(1).toEqual(allShots.length);
    expect(1).toEqual(allShots[0]);
  });

  test('getAllShots should return a list of 1 integer if player has done a strike', () => {
    // Given
    const frame = new RegularFrame();
    frame.registerShot(10);

    // When
    const allShots = frame.getAllShots();

    // Then
    expect(1).toEqual(allShots.length);
    expect(10).toEqual(allShots[0]);
  });

  test('getAllShots should return a void list if no shot is done', () => {
    // Given
    const frame = new RegularFrame();

    // When
    const allShots = frame.getAllShots();

    // Then
    expect(0).toEqual(allShots.length);
  });

});


