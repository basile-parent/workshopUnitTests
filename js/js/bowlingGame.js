import RegularFrame from "./regularFrame";

export default class BowlingGame {
  constructor() {
    this.initScores();
  }

  initScores() {
    this.frames = [];
    for (let i = 0; i < 10; i++) {
      this.frames.push(new RegularFrame());
    }
  }

  addShot(shotValue) {
    const currentFrame = this.frames.find(f => f.canAcceptShot());
    if (!currentFrame) {
      throw new Error("All tosses are done");
    }

    currentFrame.registerShot(shotValue);
  }

  updateScoreTable() {
    document.getElementById("score-table").innerHTML = this.frames.map((f, index) => f.render(index + 1)).join("");
  }
}
