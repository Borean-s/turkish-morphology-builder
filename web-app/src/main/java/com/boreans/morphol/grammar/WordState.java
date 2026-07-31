package com.boreans.morphol.grammar;

public class WordState {
	
	String text;

    boolean thirdPossessive = false; // sonrasında genitive ve başka possesive gelemez, plural gelemez
    boolean genitive = false; // only past and copula
    boolean plural = false; // sonrasında plural gelemez ve third person plural possessive gelemez
    boolean hasCase = false; // sonrasında past ve copula gelebilir
    boolean hasHardCase = false; //acc , dat: sonrasında hiçbişey gelemez
    boolean hasCopula = false; // sonrasında hiçbişey gelemez
    boolean hasPast = false; // sonrasında sadece copula gelebilir
    
  
    // ArrayList<String> suffixHistory; (TO BE ADDED)
    
    
    public WordState(String text) {
        this.text = text;
    }
    
    public String getText() {
        return text;
    }
    
    boolean hasPossessive;
    
    public boolean isThirdPossessive() { return thirdPossessive; }
    public boolean isGenitive() { return genitive; }
    public boolean isPlural() { return plural; }
    public boolean isHasCase() { return hasCase; }
    public boolean isHasHardCase() { return hasHardCase; }
    public boolean isHasCopula() { return hasCopula; }
    public boolean isHasPast() { return hasPast; }
    public boolean isHasPossessive() { return hasPossessive; }
    
    public void setThirdPossessive(boolean v) { this.thirdPossessive = v; }
    public void setGenitive(boolean v) { this.genitive = v; }
    public void setPlural(boolean v) { this.plural = v; }
    public void setHasCase(boolean v) { this.hasCase = v; }
    public void setHasHardCase(boolean v) { this.hasHardCase = v; }
    public void setHasCopula(boolean v) { this.hasCopula = v; }
    public void setHasPast(boolean v) { this.hasPast = v; }
    public void setText(String text) { this.text = text; }
    public void setHasPossessive(boolean v) { this.hasPossessive = v; }
    
    public WordState(WordState other) {
        this.text = other.text;
        this.thirdPossessive = other.thirdPossessive;
        this.genitive = other.genitive;
        this.plural = other.plural;
        this.hasCase = other.hasCase;
        this.hasHardCase = other.hasHardCase;
        this.hasCopula = other.hasCopula;
        this.hasPast = other.hasPast;
        this.hasPossessive = other.hasPossessive;
    }
    
}
