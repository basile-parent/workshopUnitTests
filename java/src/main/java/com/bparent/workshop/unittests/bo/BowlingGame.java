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

    }

    public void clear() {
        this.frames = buildInitFrames();
    }

}
