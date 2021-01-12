package com.bparent.workshop.unittests.controller;

import com.bparent.workshop.unittests.bean.FrameWithScore;
import com.bparent.workshop.unittests.bean.Shot;
import com.bparent.workshop.unittests.bo.BowlingGame;
import com.bparent.workshop.unittests.util.ScoreFormatter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BowlingController {

    private static final BowlingGame game = new BowlingGame();

    @PostMapping("/shoot")
    public ResponseEntity<String> addShootAndCalculateScore(@RequestBody Shot shot) {
        game.addShoot(shot.getShotValue());

        List<FrameWithScore> frames = new ArrayList<>();
        return ResponseEntity.ok(ScoreFormatter.formatScore(frames));
    }

    @PostMapping("/clear")
    public ResponseEntity<String> clearScore() {
        game.clear();
        return ResponseEntity.ok("Cleared");
    }

}
