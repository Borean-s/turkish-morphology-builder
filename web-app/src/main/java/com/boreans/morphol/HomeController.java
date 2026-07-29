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
import com.boreans.morphol.history.DeclensionSessionRepository;
import com.boreans.morphol.history.DeclensionSession;

@Controller
public class HomeController {
	
	private final DeclensionSessionRepository repository;

    public HomeController(DeclensionSessionRepository repository) {
        this.repository = repository;
    }
    
    private void syncSession(HttpSession session, WordState state) {
        Long sessionId = (Long) session.getAttribute("sessionId");
        DeclensionSession record = repository.findById(sessionId).orElseThrow();

        record.setCurrentWord(state.getText());
        record.setThirdPossessive(state.isThirdPossessive());
        record.setGenitive(state.isGenitive());
        record.setPlural(state.isPlural());
        record.setHasCase(state.isHasCase());
        record.setHasHardCase(state.isHasHardCase());
        record.setHasCopula(state.isHasCopula());
        record.setHasPast(state.isHasPast());

        repository.save(record);
    }
    
    private void pushUndo(HttpSession session, WordState state) {
        java.util.Deque<WordState> stack = (java.util.Deque<WordState>) session.getAttribute("undoStack");
        if (stack == null) {
            stack = new java.util.ArrayDeque<>();
        }
        stack.push(new WordState(state));
        session.setAttribute("undoStack", stack);
    }
    
    @PostMapping("/undo")
    public String undo(HttpSession session) {
        java.util.Deque<WordState> stack = (java.util.Deque<WordState>) session.getAttribute("undoStack");
        if (stack != null && !stack.isEmpty()) {
            WordState previous = stack.pop();
            session.setAttribute("wordState", previous);
            syncSession(session, previous);
        }
        return "redirect:/word";
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("history", repository.findAll());
        return "home";
    }

    @PostMapping("/start")
    public String start(@RequestParam String word, HttpSession session) {
        WordState state = new WordState(word);
        session.setAttribute("wordState", state);
        session.setAttribute("undoStack", new java.util.ArrayDeque<WordState>());

        DeclensionSession record = new DeclensionSession();
        record.setOriginalWord(word);
        record.setCurrentWord(word);
        record.setCreatedAt(java.time.LocalDateTime.now());
        repository.save(record);

        session.setAttribute("sessionId", record.getId());
        return "redirect:/word";
    }
    
    @GetMapping("/word")
    public String showWord(HttpSession session, Model model) {
        WordState state = (WordState) session.getAttribute("wordState");
        model.addAttribute("word", state.getText());

        model.addAttribute("canPlural", TurkishRules.canAddPlural(state));
        model.addAttribute("canAccusative", TurkishRules.canAddCase(state));
        model.addAttribute("canDative", TurkishRules.canAddCase(state));
        model.addAttribute("canLocative", TurkishRules.canAddCase(state));
        model.addAttribute("canAblative", TurkishRules.canAddCase(state));
        model.addAttribute("canInstrumental", TurkishRules.canAddCase(state));
        model.addAttribute("canGenitive", TurkishRules.canAddGenitive(state));
        model.addAttribute("canPast", TurkishRules.canAddPast(state));
        model.addAttribute("canPossessive", TurkishRules.canAddPossessive(state));
        model.addAttribute("canCopula", TurkishRules.canAddCopula(state));

        model.addAttribute("history", repository.findAll());

        return "word";
    }
    
    @GetMapping("/session/{id}")
    public String reopenSession(@PathVariable Long id, HttpSession session) {
        DeclensionSession record = repository.findById(id).orElseThrow();

        WordState state = new WordState(record.getCurrentWord());
        state.setThirdPossessive(record.isThirdPossessive());
        state.setGenitive(record.isGenitive());
        state.setPlural(record.isPlural());
        state.setHasCase(record.isHasCase());
        state.setHasHardCase(record.isHasHardCase());
        state.setHasCopula(record.isHasCopula());
        state.setHasPast(record.isHasPast());

        session.setAttribute("wordState", state);
        session.setAttribute("sessionId", record.getId());

        return "redirect:/word";
    }

    @PostMapping("/session/{id}/delete")
    public String deleteSession(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/word";
    }
    
    @PostMapping("/suffix/plural")
    public String addPlural(HttpSession session) {
        WordState state = (WordState) session.getAttribute("wordState");
        pushUndo(session, state);
        char lastVowel = TurkishHelper.findLastVowel(state);
        SuffixManager.conjugateIt(state, 8, lastVowel, null);
        syncSession(session, state);
        return "redirect:/word";
    }

    @PostMapping("/suffix/accusative")
    public String addAccusative(HttpSession session) {
        WordState state = (WordState) session.getAttribute("wordState");
        pushUndo(session, state);
        SuffixManager.conjugateIt(state, 1, TurkishHelper.findLastVowel(state), null);
        syncSession(session, state);
        return "redirect:/word";
    }

    @PostMapping("/suffix/dative")
    public String addDative(HttpSession session) {
        WordState state = (WordState) session.getAttribute("wordState");
        pushUndo(session, state);
        SuffixManager.conjugateIt(state, 2, TurkishHelper.findLastVowel(state), null);
        syncSession(session, state);
        return "redirect:/word";
    }

    @PostMapping("/suffix/locative")
    public String addLocative(HttpSession session) {
        WordState state = (WordState) session.getAttribute("wordState");
        pushUndo(session, state);
        SuffixManager.conjugateIt(state, 3, TurkishHelper.findLastVowel(state), null);
        syncSession(session, state);
        return "redirect:/word";
    }

    @PostMapping("/suffix/ablative")
    public String addAblative(HttpSession session) {
        WordState state = (WordState) session.getAttribute("wordState");
        pushUndo(session, state);
        SuffixManager.conjugateIt(state, 4, TurkishHelper.findLastVowel(state), null);
        syncSession(session, state);
        return "redirect:/word";
    }

    @PostMapping("/suffix/instrumental")
    public String addInstrumental(HttpSession session) {
        WordState state = (WordState) session.getAttribute("wordState");
        pushUndo(session, state);
        SuffixManager.conjugateIt(state, 5, TurkishHelper.findLastVowel(state), null);
        syncSession(session, state);
        return "redirect:/word";
    }

    @PostMapping("/suffix/genitive")
    public String addGenitive(HttpSession session) {
        WordState state = (WordState) session.getAttribute("wordState");
        pushUndo(session, state);
        SuffixManager.conjugateIt(state, 6, TurkishHelper.findLastVowel(state), null);
        syncSession(session, state);
        return "redirect:/word";
    }

    @PostMapping("/suffix/past")
    public String addPast(HttpSession session) {
        WordState state = (WordState) session.getAttribute("wordState");
        pushUndo(session, state);
        SuffixManager.conjugateIt(state, 9, TurkishHelper.findLastVowel(state), null);
        syncSession(session, state);
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
            pushUndo(session, state);
            SuffixManager.addPossessive(state, TurkishHelper.findLastVowel(state), choice);
            syncSession(session, state);
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
            pushUndo(session, state);
            SuffixManager.addCopula(state, TurkishHelper.findLastVowel(state), choice);
            syncSession(session, state);
        }
        return "redirect:/word";
    }
}