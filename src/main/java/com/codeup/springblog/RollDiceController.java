package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String diceRoll() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String guessedNumber(@PathVariable int n, Model model) {
        int num = 4;
        if (n == num) {
            model.addAttribute("guessedNumber", "Congratulations! " + n + " is the correct number!");
        } else {
            model.addAttribute("guessedNumber", "Sorry, " + n + " is not the correct number. Try again!");
        }
        return "roll-dice";
    }
}
