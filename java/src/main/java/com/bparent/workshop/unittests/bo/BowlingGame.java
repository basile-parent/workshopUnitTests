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
        for (int i = 0; i < this.frames.size(); i++) {
            Frame frame = this.frames.get(i);
            if (frame.getFirstShot() == null && frame.getSecondShot() == null) {
                break;
            }
            Integer score = frame.getFirstShot();
            if (frame.getSecondShot() != null) {
                score += frame.getSecondShot();
            }
            frame.setScore(score);
        }
    }

    public void clear() {
        this.frames = buildInitFrames();
    }

}
