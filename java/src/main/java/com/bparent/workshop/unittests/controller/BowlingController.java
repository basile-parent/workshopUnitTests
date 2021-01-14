package com.bparent.workshop.unittests.controller;

import com.bparent.workshop.unittests.bean.Shot;
import com.bparent.workshop.unittests.bo.BowlingGame;
import com.bparent.workshop.unittests.util.ScoreFormatter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class BowlingController {

    private static final BowlingGame game = new BowlingGame();

    @PostMapping("/shoot")
    public ResponseEntity<String> addShootAndCalculateScore(@RequestBody Shot shot) {
        game.addShoot(shot.getShotValue());
        return ResponseEntity.ok(ScoreFormatter.formatScore(game.getFrames()));
    }

    @PostMapping("/clear")
    public ResponseEntity<String> clearScore() {
        game.clear();
        return ResponseEntity.ok("Cleared");
    }

}
