import RegularFrame from "./regularFrame.js";
import LastFrame from "./lastFrame.js";

export default class BowlingGame {
  constructor() {
    this.initScores();
  }

  initScores() {
    this.frames = [];
    for (let i = 0; i < 9; i++) {
      this.frames.push(new RegularFrame());
    }
    this.frames.push(new LastFrame());
  }

  addShot(shotValue) {
    const currentFrame = this.frames.find(f => f.canAcceptShot());
    if (!currentFrame) {
      throw new Error("All tosses are done");
    }

    currentFrame.registerShot(shotValue);

    this._calculateScore();
  }

  _calculateScore() {
    let score = 0;
    for (let i = 0; i < this.frames.length; i++) {
      const frame = this.frames[i];
      const allShots = frame.getAllShots();
      if (!allShots.length) {
        break;
      }

      score += allShots.reduce((acc, item) => acc + item, 0);

      if (frame.isSpare()) {
        score += this._getNextTosses(i, 1);
      } else if (frame.isStrike()) {
        score += this._getNextTosses(i, 2);
      }

      frame.score = score;
    }
  }

  _getNextTosses(currentIndex, numberOfTossesToRetrieve) {
    let tossRemaining = numberOfTossesToRetrieve;
    let indexCursor = currentIndex + 1;
    let nextTossesSum = 0;

    while (tossRemaining > 0 && indexCursor < this.frames.length) {
      const nextFrame = this.frames[indexCursor];
      const allShots = nextFrame.getAllShots();
      if (!allShots.length) {
        break;
      }

      for (const shot of allShots) {
        nextTossesSum += shot;
        tossRemaining--;
        if (!tossRemaining) {
          break;
        }
      }

      indexCursor++;
    }

    return nextTossesSum;
  }

  updateScoreTable() {
    document.getElementById("score-table").innerHTML =
      this.frames.map((f, index) => f.render(index + 1)).join("");
  }
}
