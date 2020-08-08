package com.codeup.springblog.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String diceRoll() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String guessedNumber(@PathVariable int n, Model model) {
        List<Integer> dice = new ArrayList<>();
        Random rand = new Random();
        Random rand2 = new Random();
        Random rand3 = new Random();
        int num = rand.nextInt(6) + 1;
        int num2 = rand2.nextInt(6) + 1;
        int num3 = rand3.nextInt(6) + 1;
        dice.add(num);
        dice.add(num2);
        dice.add(num3);

        model.addAttribute("rolledNum", "Your numbers you've rolled are: " + num + ", " + num2 + ", " + num3 + ".");
        model.addAttribute("userNum", "The number you have chosen is: " + n + ".");
        int howManyMatches = 0;
        for(Integer x : dice) {
            if(n == x) {
                howManyMatches += 1;
            }
        }

        if (howManyMatches == 1) {
            model.addAttribute("guessedNum", "You have " + howManyMatches + " matches.");
        } else if(howManyMatches == 2) {
            model.addAttribute("guessedNum", "You have " + howManyMatches + " matches.");
        } else if(howManyMatches == 3) {
            model.addAttribute("guessedNum", "You have " + howManyMatches + " matches.");
        } else {
            model.addAttribute("guessedNum", "You have " + howManyMatches + " matches.");
        }
        return "roll-dice";
    }
}
