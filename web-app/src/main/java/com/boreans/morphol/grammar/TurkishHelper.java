package com.boreans.morphol.grammar;

public class TurkishHelper {

    static boolean EndingWithVowel(WordState word) {
        switch(word.text.charAt(word.text.length()-1)) {
        case 'a':
        case 'e':
        case 'i':
        case 'ü':
        case 'u':
        case 'ı':
        case 'ö':
        case 'o':
             return true;
        }
        return false;
    }

    public static char findLastVowel(WordState word){
        for (int t = word.text.length() - 1; t >= 0; t--) {
            switch(word.text.charAt(t)) {
            case 'a':
            case 'e':
            case 'ı':
            case 'i':
            case 'o':
            case 'ö':
            case 'u':
            case 'ü':
                return word.text.charAt(t);
            }
        }
        return '\0';
    }

    static boolean isFront(char v) {
        switch (v) {
            case 'e':
            case 'i':
            case 'ö':
            case 'ü':
                return true;
            default:
                return false;
        }
    }

    static boolean isRounded(char v) {
        switch (v) {
            case 'o':
            case 'ö':
            case 'u':
            case 'ü':
                return true;
            default:
                return false;
        }
    }

    static boolean lastConsonantHard(WordState word) {
        switch (word.text.charAt(word.text.length()-1)) {
        case 't':
        case 'k':
        case 's':
        case 'ş':
        case 'f':
        case 'ç':
        case 'p':
        case 'x':
        case 'h':
            return true;
        default:
            return false;
        }
    }
}