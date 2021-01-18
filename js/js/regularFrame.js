export default class RegularFrame {
  constructor(firstShot = null, secondShot = null, score = null) {
    this.firstShot = firstShot;
    this.secondShot = secondShot;
    this.score = score;
  }

  canAcceptShot() {
    return !this.firstShot || (this.firstShot !== 10 && !this.secondShot);
  }

  registerShot(shotValue) {
    const value = +shotValue;
    if (!this.canAcceptShot()) {
      throw new Error("This frame cannot accept shots anymore");
    }
    if (value > 10) {
      throw new Error("You cannot have a better score than 10 for one shot");
    }
    if (this.firstShot !== null && this.firstShot + value > 10) {
      throw new Error("The sum of 2 shots cannot be > 10. First shot = " + this.firstShot + ". New shot value : " + value);
    }

    if (this.firstShot === null) {
      this.firstShot = value;
    } else {
      this.secondShot = value;
    }
  }

  isSpare() {
    return this.firstShot !== null && this.secondShot !== null &&
      this.firstShot + this.secondShot === 10;
  }

  isStrike() {
    return this.firstShot !== null && this.firstShot === 10;
  }

  getAllShots() {
    return [ this.firstShot, this.secondShot ].filter(shot => shot !== null);
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
