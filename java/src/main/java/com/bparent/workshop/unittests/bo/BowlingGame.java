package com.bparent.workshop.unittests.bo;

import com.bparent.workshop.unittests.bean.Frame;
import com.bparent.workshop.unittests.bean.LastFrame;
import com.bparent.workshop.unittests.bean.RegularFrame;
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
        List<Frame> initFrames = IntStream.range(0, 9)
                .mapToObj(id -> new RegularFrame())
                .collect(Collectors.toList());
        initFrames.add(new LastFrame());
        return initFrames;
    }

    public void addShoot(Integer shotValue) {
        Frame firstAvailableFrame = frames.stream()
                .filter(Frame::canAcceptShot)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("All tosses are done"));
        firstAvailableFrame.registerShot(shotValue);

        this.calculateScore();
    }

    private void calculateScore() {
        Integer score = 0;
        for (int i = 0; i < this.frames.size(); i++) {
            Frame frame = this.frames.get(i);
            List<Integer> allShots = frame.getAllShots();
            if (allShots.size() == 0) {
                break;
            }

            score += allShots.stream().reduce(0, Integer::sum);

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
            List<Integer> allShots = nextFrame.getAllShots();
            if (allShots.size() == 0) {
                break;
            }

            for (Integer shot : allShots) {
                nextTossesSum += shot;
                tossRemaining--;
                if (tossRemaining == 0) {
                    break;
                }
            }

            indexCursor++;
        }

        return nextTossesSum;
    }

    public void clear() {
        this.frames = buildInitFrames();
    }

}
