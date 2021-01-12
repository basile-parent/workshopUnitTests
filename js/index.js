class BowlingGame {
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
    this.updateScoreTable();
  }

  updateScoreTable() {
    document.getElementById("score-table").innerHTML = this.frames.map((f, index) => f.render(index + 1)).join("");
  }
}

class Frame {
  constructor(firstShot = null, secondShot = null, score = null) {
    this.firstShot = firstShot;
    this.secondShot = secondShot;
    this.score = score;
  }

  render(index) {
    return `
    <li>
        <span class="frame-score">${ this.score || "-" }</span>
        
        <div class="frame-shots">
            <div class="frame-shot-index">${ index }</div>
            <div class="frame-shot-first">${ this.firstShot || "-" }</div>
            <div class="frame-shot-second">${ this.secondShot || "-" }</div>
        </div>
    </li>`;
  }
}