import RegularFrame from "./regularFrame.js";

export default class LastFrame extends RegularFrame {
  constructor(firstShot = null, secondShot = null, thirdShot = null, score = null) {
    super(firstShot, secondShot, score);
    this.thirdShot = thirdShot;
  }

  canAcceptShot() {
    return this.firstShot === null ||
      this.secondShot === null ||
      (this.thirdShot === null && (this.firstShot === 10 || this.firstShot + this.secondShot === 10));
  }

  registerShot(shotValue) {
    const value = +shotValue;
    if (!this.canAcceptShot()) {
      throw new Error("This frame cannot accept shots anymore");
    }
    if (value > 10) {
      throw new Error("You cannot have a better score than 10 for one shot");
    }

    if (this.firstShot !== null) {
      if (this.firstShot !== 10) {
        if (this.secondShot === null && this.firstShot + value > 10) {
          throw new Error("The sum of 2 shots cannot be > 10. First shot = " + this.firstShot + ". New shot value : " + value);
        }
        if (this.secondShot !== null && this.firstShot + this.secondShot < 10) {
          throw new Error("You don't have a third shot if you didn't do a spare or a strike");
        }
      } else {
        if (this.secondShot !== null && this.secondShot !== 10 && this.secondShot + value > 10) {
          throw new Error("The sum of 2 shots cannot be > 10. Second shot = " + this.secondShot + ". New shot value : " + value);
        }
      }
    }

    if (this.firstShot === null) {
      this.firstShot = value;
    } else if (this.secondShot === null) {
      this.secondShot = value;
    } else {
      this.thirdShot = value;
    }
  }

  isSpare() {
    return false;
  }

  isStrike() {
    return false;
  }

  getAllShots() {
    return [ this.firstShot, this.secondShot, this.thirdShot ].filter(shot => shot !== null);
  }


  render(index) {
    return `
    <li>
        <span class="frame-score">${ this.score || "-" }</span>
        
        <div class="frame-shots">
            <div class="frame-shot-index">${ index }</div>
            <div class="frame-shot-first">${ this.firstShot || "-" }</div>
            <div class="frame-shot-second">${ this.secondShot || "-" }</div>
            <div class="frame-shot-third">${ this.thirdShot || "-" }</div>
        </div>
    </li>`;
  }
}
