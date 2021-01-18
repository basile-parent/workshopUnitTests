import Frame from "./frame.js";

export default class BowlingGame {
  constructor() {
    this.initScores();
  }

  initScores() {
    this.frames = [];
    for (let i = 0; i < 10; i++) {
      this.frames.push(new Frame());
    }
  }

  addShot(shotValue) {

  }

  updateScoreTable() {
    document.getElementById("score-table").innerHTML = this.frames.map((f, index) => f.render(index + 1)).join("");
  }
}
