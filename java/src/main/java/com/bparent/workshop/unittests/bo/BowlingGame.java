package com.bparent.workshop.unittests.bo;

import com.bparent.workshop.unittests.bean.Frame;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@NoArgsConstructor
@Getter
public class BowlingGame {

    private List<Frame> frames = buildInitFrames();

    private List<Frame> buildInitFrames() {
        return IntStream.range(0,10)
                .mapToObj(id -> new Frame())
                .collect(Collectors.toList());
    }

    public void addShoot(Integer shotValue) {
        Frame firstAvailableFrame = frames.stream()
                .filter(Frame::canAcceptShot)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("All shots are done"));
        firstAvailableFrame.registerShot(shotValue);

        this.calculateScore();
    }

    private void calculateScore() {
        Integer score = 0;
        for (int i = 0; i < this.frames.size(); i++) {
            Frame frame = this.frames.get(i);
            if (frame.getFirstShot() == null && frame.getSecondShot() == null) {
                break;
            }
            if (frame.getFirstShot() != null) {
                score += frame.getFirstShot();
            }
            if (frame.getSecondShot() != null) {
                score += frame.getSecondShot();
            }

            if (frame.isSpare()) {
                score += this.getNextTosses(i, 1);
            } else if (frame.isStrike()) {
                score += this.getNextTosses(i, 2);
            }

            frame.setScore(score);
        }
    }

    private Integer getNextTosses(int currentIndex, int numberOfTossesToRetrieve) {
        int tossRemaining = numberOfTossesToRetrieve;
        int indexCursor = currentIndex + 1;
        int nextTossesSum = 0;
        while (tossRemaining > 0 && indexCursor < this.frames.size()) {
            Frame nextFrame = this.getFrames().get(indexCursor);
            if (nextFrame.getFirstShot() == null) {
                break;
            }
            nextTossesSum += nextFrame.getFirstShot();
            tossRemaining--;
            if (tossRemaining == 0) {
                break;
            }
            if (nextFrame.getSecondShot() != null) {
                nextTossesSum += nextFrame.getSecondShot();
                tossRemaining--;
            }

            indexCursor++;
        }

        return nextTossesSum;
    }

    public void clear() {
        this.frames = buildInitFrames();
    }

}
