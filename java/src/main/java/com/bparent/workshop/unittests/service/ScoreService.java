package com.bparent.workshop.unittests.service;

import com.bparent.workshop.unittests.bean.FrameWithScore;
import com.bparent.workshop.unittests.bo.BowlingGame;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreService {

    public List<FrameWithScore> calculateScore(BowlingGame game) {
        return new ArrayList<>();
    }

}
