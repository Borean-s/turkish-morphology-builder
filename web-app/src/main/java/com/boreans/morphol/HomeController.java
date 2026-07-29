package com.boreans.morphol;

import com.boreans.morphol.grammar.WordState;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.boreans.morphol.grammar.TurkishHelper;
import com.boreans.morphol.grammar.SuffixManager;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/start")
    public String start(@RequestParam String word, HttpSession session) {
        WordState state = new WordState(word);
        session.setAttribute("wordState", state);
        return "redirect:/word";
    }
    
    @GetMapping("/word")
    public String showWord(HttpSession session, Model model) {
        WordState state = (WordState) session.getAttribute("wordState");
        model.addAttribute("word", state.getText());
        return "word";
    }
    
    @PostMapping("/suffix/plural")
    public String addPlural(HttpSession session) {
        WordState state = (WordState) session.getAttribute("wordState");
        char lastVowel = TurkishHelper.findLastVowel(state);
        SuffixManager.conjugateIt(state, 8, lastVowel, null);
        return "redirect:/word";
    }
    
}