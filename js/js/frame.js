export default class Frame {
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
