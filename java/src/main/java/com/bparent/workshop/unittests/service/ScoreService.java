package com.bparent.workshop.unittests.service;

import com.bparent.workshop.unittests.bean.Frame;
import com.bparent.workshop.unittests.bo.BowlingGame;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreService {

    public List<Frame> calculateScore(BowlingGame game) {
        return new ArrayList<>();
    }

}
