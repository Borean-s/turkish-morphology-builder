package com.boreans.morphol;

import com.boreans.morphol.grammar.WordState;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        record.setHasPossessive(state.isHasPossessive());

        repository.save(record);
    }
    
    private static final java.util.Map<String, Integer> suffixCodes = java.util.Map.of(
            "plural", 8,
            "accusative", 1,
            "dative", 2,
            "locative", 3,
            "ablative", 4,
            "instrumental", 5,
            "genitive", 6,
            "past", 9
        );

        private WordStateResponse buildResponse(WordState state) {
            WordStateResponse response = new WordStateResponse();
            response.word = state.getText();
            response.canPlural = TurkishRules.canAddPlural(state);
            response.canAccusative = TurkishRules.canAddCase(state);
            response.canDative = TurkishRules.canAddCase(state);
            response.canLocative = TurkishRules.canAddCase(state);
            response.canAblative = TurkishRules.canAddCase(state);
            response.canInstrumental = TurkishRules.canAddCase(state);
            response.canGenitive = TurkishRules.canAddGenitive(state);
            response.canPast = TurkishRules.canAddPast(state);
            response.canPossessive = TurkishRules.canAddPossessive(state);
            response.canCopula = TurkishRules.canAddCopula(state);
            return response;
        }
        
        private WordState replay(String originalWord, String suffixHistory) {
            WordState state = new WordState(originalWord);
            if (suffixHistory == null || suffixHistory.isBlank()) {
                return state;
            }
            String[] steps = suffixHistory.split(",");
            for (String step : steps) {
                String[] parts = step.split(":");
                String name = parts[0];
                char lastVowel = TurkishHelper.findLastVowel(state);

                if (name.equals("possessive")) {
                    int choice = Integer.parseInt(parts[1]);
                    SuffixManager.addPossessive(state, lastVowel, choice);
                } else if (name.equals("copula")) {
                    int choice = Integer.parseInt(parts[1]);
                    SuffixManager.addCopula(state, lastVowel, choice);
                } else {
                    int code = suffixCodes.get(name);
                    SuffixManager.conjugateIt(state, code, lastVowel, null);
                }
            }
            return state;
        }
        
        private void applyStep(HttpSession session, String step) {
            Long sessionId = (Long) session.getAttribute("sessionId");
            DeclensionSession record = repository.findById(sessionId).orElseThrow();

            String history = record.getSuffixHistory();
            String newHistory = (history == null || history.isBlank()) ? step : history + "," + step;
            record.setSuffixHistory(newHistory);
            repository.save(record);

            WordState state = replay(record.getOriginalWord(), newHistory);
            session.setAttribute("wordState", state);
            syncSession(session, state);
        }
    
    
        @GetMapping("/api/word")
        @ResponseBody
        public WordStateResponse getWordJson(HttpSession session) {
            WordState state = (WordState) session.getAttribute("wordState");
            return buildResponse(state);
        }
        
        @PostMapping("/api/suffix/{name}")
        @ResponseBody
        public WordStateResponse applySuffixJson(@PathVariable String name, HttpSession session) {
            applyStep(session, name);
            WordState state = (WordState) session.getAttribute("wordState");
            return buildResponse(state);
        }
    
    
    
    @PostMapping("/undo")
    public String undo(HttpSession session) {
        Long sessionId = (Long) session.getAttribute("sessionId");
        DeclensionSession record = repository.findById(sessionId).orElseThrow();

        String history = record.getSuffixHistory();
        if (history != null && !history.isBlank()) {
            String[] steps = history.split(",");
            String newHistory = String.join(",", java.util.Arrays.copyOf(steps, steps.length - 1));
            record.setSuffixHistory(newHistory);
            repository.save(record);

            WordState state = replay(record.getOriginalWord(), newHistory);
            session.setAttribute("wordState", state);
            syncSession(session, state);
        }
        return "redirect:/word";
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("history", repository.findAllByOrderByIdDesc());
        return "home";
    }

    @PostMapping("/start")
    public String start(@RequestParam String word, HttpSession session) {
        WordState state = new WordState(word);
        session.setAttribute("wordState", state);
       

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

        model.addAttribute("history", repository.findAllByOrderByIdDesc());

        return "word";
    }
    
    @GetMapping("/session/{id}")
    public String reopenSession(@PathVariable Long id, HttpSession session) {
        DeclensionSession record = repository.findById(id).orElseThrow();

        WordState state = replay(record.getOriginalWord(), record.getSuffixHistory());

        session.setAttribute("wordState", state);
        session.setAttribute("sessionId", record.getId());

        return "redirect:/word";
    }

    @PostMapping("/session/{id}/delete")
    public String deleteSession(@PathVariable Long id, HttpSession session) {
        repository.deleteById(id);

        Long currentSessionId = (Long) session.getAttribute("sessionId");
        if (currentSessionId == null || currentSessionId.equals(id)) {
            session.removeAttribute("wordState");
            session.removeAttribute("sessionId");
            return "redirect:/";
        }
        return "redirect:/word";
    }
    

    @GetMapping("/suffix/possessive")
    public String choosePossessive(HttpSession session, Model model) {
        WordState state = (WordState) session.getAttribute("wordState");
        model.addAttribute("word", state.getText());
        model.addAttribute("history", repository.findAllByOrderByIdDesc());
        return "possessive";
    }

    @PostMapping("/suffix/possessive/{choice}")
    public String addPossessive(@PathVariable int choice, HttpSession session) {
        WordState state = (WordState) session.getAttribute("wordState");
        if (TurkishRules.canAddPossessive(state)) {
            applyStep(session, "possessive:" + choice);
        }
        return "redirect:/word";
    }

    @GetMapping("/suffix/copula")
    public String chooseCopula(HttpSession session, Model model) {
        WordState state = (WordState) session.getAttribute("wordState");
        model.addAttribute("word", state.getText());
        model.addAttribute("history", repository.findAllByOrderByIdDesc());
        return "copula";
    }

    @PostMapping("/suffix/copula/{choice}")
    public String addCopula(@PathVariable int choice, HttpSession session) {
        WordState state = (WordState) session.getAttribute("wordState");
        if (TurkishRules.canAddCopula(state)) {
            applyStep(session, "copula:" + choice);
        }
        return "redirect:/word";
    }
}