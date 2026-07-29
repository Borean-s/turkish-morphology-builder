package com.boreans.morphol.history;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class DeclensionSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalWord;
    private String currentWord;

    private boolean thirdPossessive;
    private boolean genitive;
    private boolean plural;
    private boolean hasCase;
    private boolean hasHardCase;
    private boolean hasCopula;
    private boolean hasPast;

    private LocalDateTime createdAt;

    public DeclensionSession() {
    }

    public Long getId() { return id; }
    public String getOriginalWord() { return originalWord; }
    public void setOriginalWord(String originalWord) { this.originalWord = originalWord; }
    public String getCurrentWord() { return currentWord; }
    public void setCurrentWord(String currentWord) { this.currentWord = currentWord; }
    public boolean isThirdPossessive() { return thirdPossessive; }
    public void setThirdPossessive(boolean thirdPossessive) { this.thirdPossessive = thirdPossessive; }
    public boolean isGenitive() { return genitive; }
    public void setGenitive(boolean genitive) { this.genitive = genitive; }
    public boolean isPlural() { return plural; }
    public void setPlural(boolean plural) { this.plural = plural; }
    public boolean isHasCase() { return hasCase; }
    public void setHasCase(boolean hasCase) { this.hasCase = hasCase; }
    public boolean isHasHardCase() { return hasHardCase; }
    public void setHasHardCase(boolean hasHardCase) { this.hasHardCase = hasHardCase; }
    public boolean isHasCopula() { return hasCopula; }
    public void setHasCopula(boolean hasCopula) { this.hasCopula = hasCopula; }
    public boolean isHasPast() { return hasPast; }
    public void setHasPast(boolean hasPast) { this.hasPast = hasPast; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}