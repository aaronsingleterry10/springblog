package com.codeup.springblog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Random;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String diceRoll() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String guessedNumber(@PathVariable int n, Model model) {
        Random rand = new Random();
        int num = rand.nextInt(6) + 1;
        model.addAttribute("rolledNum", "The number rolled is: " + num);
        if (n == num) {
            model.addAttribute("guessedNum", "Congratulations! " + n + " is the correct number!");
        } else {
            model.addAttribute("guessedNum", "Sorry, " + n + " is not the correct number. Try again!");
        }
        return "roll-dice";
    }
}
