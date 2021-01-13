package com.bparent.workshop.unittests.controller;

import com.bparent.workshop.unittests.bean.Frame;
import com.bparent.workshop.unittests.bean.Shot;
import com.bparent.workshop.unittests.bo.BowlingGame;
import com.bparent.workshop.unittests.service.ScoreService;
import com.bparent.workshop.unittests.util.ScoreFormatter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/game")
public class BowlingController {

    private static final BowlingGame game = new BowlingGame();

    private final ScoreService scoreService;

    public BowlingController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping("/shoot")
    public ResponseEntity<String> addShootAndCalculateScore(@RequestBody Shot shot) {
        game.addShoot(shot.getShotValue());

        List<Frame> frames = scoreService.calculateScore(game);
        return ResponseEntity.ok(ScoreFormatter.formatScore(frames));
    }

    @PostMapping("/clear")
    public ResponseEntity<String> clearScore() {
        game.clear();
        return ResponseEntity.ok("Cleareddd");
    }

}
