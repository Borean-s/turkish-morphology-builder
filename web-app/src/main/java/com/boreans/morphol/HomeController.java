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
import org.springframework.web.bind.annotation.PathVariable;
import com.boreans.morphol.grammar.TurkishRules;

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
    
    @PostMapping("/suffix/accusative")
    public String addAccusative(HttpSession session) {
        WordState state = (WordState) session.getAttribute("wordState");
        SuffixManager.conjugateIt(state, 1, TurkishHelper.findLastVowel(state), null);
        return "redirect:/word";
    }

    @PostMapping("/suffix/dative")
    public String addDative(HttpSession session) {
        WordState state = (WordState) session.getAttribute("wordState");
        SuffixManager.conjugateIt(state, 2, TurkishHelper.findLastVowel(state), null);
        return "redirect:/word";
    }

    @PostMapping("/suffix/locative")
    public String addLocative(HttpSession session) {
        WordState state = (WordState) session.getAttribute("wordState");
        SuffixManager.conjugateIt(state, 3, TurkishHelper.findLastVowel(state), null);
        return "redirect:/word";
    }

    @PostMapping("/suffix/ablative")
    public String addAblative(HttpSession session) {
        WordState state = (WordState) session.getAttribute("wordState");
        SuffixManager.conjugateIt(state, 4, TurkishHelper.findLastVowel(state), null);
        return "redirect:/word";
    }

    @PostMapping("/suffix/instrumental")
    public String addInstrumental(HttpSession session) {
        WordState state = (WordState) session.getAttribute("wordState");
        SuffixManager.conjugateIt(state, 5, TurkishHelper.findLastVowel(state), null);
        return "redirect:/word";
    }

    @PostMapping("/suffix/genitive")
    public String addGenitive(HttpSession session) {
        WordState state = (WordState) session.getAttribute("wordState");
        SuffixManager.conjugateIt(state, 6, TurkishHelper.findLastVowel(state), null);
        return "redirect:/word";
    }

    @PostMapping("/suffix/past")
    public String addPast(HttpSession session) {
        WordState state = (WordState) session.getAttribute("wordState");
        SuffixManager.conjugateIt(state, 9, TurkishHelper.findLastVowel(state), null);
        return "redirect:/word";
    }
    
    @GetMapping("/suffix/possessive")
    public String choosePossessive(HttpSession session, Model model) {
        WordState state = (WordState) session.getAttribute("wordState");
        model.addAttribute("word", state.getText());
        return "possessive";
    }

    @PostMapping("/suffix/possessive/{choice}")
    public String addPossessive(@PathVariable int choice, HttpSession session) {
        WordState state = (WordState) session.getAttribute("wordState");
        if (TurkishRules.canAddPossessive(state)) {
            SuffixManager.addPossessive(state, TurkishHelper.findLastVowel(state), choice);
        }
        return "redirect:/word";
    }

    @GetMapping("/suffix/copula")
    public String chooseCopula(HttpSession session, Model model) {
        WordState state = (WordState) session.getAttribute("wordState");
        model.addAttribute("word", state.getText());
        return "copula";
    }

    @PostMapping("/suffix/copula/{choice}")
    public String addCopula(@PathVariable int choice, HttpSession session) {
        WordState state = (WordState) session.getAttribute("wordState");
        if (TurkishRules.canAddCopula(state)) {
            SuffixManager.addCopula(state, TurkishHelper.findLastVowel(state), choice);
        }
        return "redirect:/word";
    }
}