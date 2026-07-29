package com.boreans.morphol.grammar;


public class TurkishRules {

    public static boolean canAddPlural(WordState word){
        if(word.plural) return false;
        if(word.thirdPossessive) return false;
        if(word.genitive) return false;
        if(word.hasCase) return false;
        if(word.hasHardCase) return false;
        if(word.hasPast) return false;
        if(word.hasCopula) return false;
        return true;
    }

    public static boolean canAddGenitive(WordState word){
        if(word.genitive) return false;
        if(word.thirdPossessive) return false;
        if(word.hasCase) return false;
        if(word.hasHardCase) return false;
        if(word.hasPast) return false;
        if(word.hasCopula) return false;
        return true;
    }

    public static boolean canAddPossessive(WordState word){
        if(word.thirdPossessive) return false;
        if(word.genitive) return false;
        if(word.hasCase) return false;
        if(word.hasHardCase) return false;
        if(word.hasPast) return false;
        if(word.hasCopula) return false;
        return true;
    }

    public static boolean canAddCase(WordState word){
        if(word.genitive) return false;
        if(word.hasCase) return false;
        if(word.hasHardCase) return false;
        if(word.hasPast) return false;
        if(word.hasCopula) return false;
        return true;
    }

    public static boolean canAddPast(WordState word){
        if(word.hasPast) return false;
        if(word.hasHardCase) return false;
        if(word.hasCopula) return false;
        return true;
    }

    public static boolean canAddCopula(WordState word){
        if(word.hasCopula) return false;
        if(word.hasHardCase) return false;
        return true;
    }
}