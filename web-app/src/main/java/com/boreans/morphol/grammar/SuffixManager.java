package com.boreans.morphol.grammar;

import java.util.Scanner;



public class SuffixManager {
public static void conjugateIt(WordState word, int choice, char v, Scanner s) {
		
		switch(choice){
			case 1 : //accusative
				
				if(!TurkishRules.canAddCase(word)) {
				    System.out.println("Accusative cannot be added.");
				    return;
				}
				
				addAccusative(word, v, s);
				break;
				
			case 2 : // dative
				
				if(!TurkishRules.canAddCase(word)) {
				    System.out.println("Dative cannot be added.");
				    return;
				}
				
				addDative(word, v, s);
		        break;
		        
			case 3 : //locative
				
				if(!TurkishRules.canAddCase(word)) {
				    System.out.println("Locative cannot be added.");
				    return;
				}
				
				addLocative(word, v, s);
				break;
				
			case 4 : //ablative
				
				if(!TurkishRules.canAddCase(word)) {
				    System.out.println("Ablative cannot be added.");
				    return;
				}
				
				addAblative(word, v, s);
				break;
				
			case 5: //instrumental
				
				if(!TurkishRules.canAddCase(word)) {
				    System.out.println("Instrumental cannot be added.");
				    return;
				}
				
				addInstrumental(word, v, s);
				break;
				
			case 6: // genitive
				
				if(!TurkishRules.canAddGenitive(word)) {
				    System.out.println("Genitive cannot be added.");
				    return;
				}
				
				addGenitive(word, v, s);
				break;
				
			case 7 : // possessive possessive — handled separately via addPossessive(word, v, choice) directly from the Controller
				break;
				
			case 8 : //plural
				
				if(!TurkishRules.canAddPlural(word)) {
				    System.out.println("Plural cannot be added.");
				    return;
				}
				
				addPlural(word, v, s);
				break; 
				
			case 9 : //past
				
				if(!TurkishRules.canAddPast(word)) {
				    System.out.println("Past cannot be added.");
				    return;
				}
				
				addPast(word, v, s);
				break;
				
			case 10 : //copula // handled separately via addCopula(word, v, choice) directly from the Controller
				break;
				
		}
			
		
		System.out.println(word.text);
		
	}

static void addPlural(WordState word, char v, Scanner s){
	word.plural = true;
	
	if(TurkishHelper.isFront(v)) {
		word.text += "ler";
	}
	else {
		word.text += "lar"; 
	}
}

static void addGenitive(WordState word, char v, Scanner s){
	word.genitive = true;
	
	if(TurkishHelper.EndingWithVowel(word)) {
		word.text += "n";
	}
	if(TurkishHelper.isFront(v) && TurkishHelper.isRounded(v)) {
		word.text += "ün";
	}
	else if(!(TurkishHelper.isFront(v)) && TurkishHelper.isRounded(v)) {
		word.text += "un";
	}
	else if(!(TurkishHelper.isFront(v)) && !(TurkishHelper.isRounded(v))) {
		word.text += "ın";
	}
	else {
		word.text += "in";
		}
}

public static void addPossessive(WordState word, char v, int genChoice){
	
	switch(genChoice) {
		case 1 : 
			if(TurkishHelper.EndingWithVowel(word)) {
				word.text += "m";
			}
			if(TurkishHelper.isFront(v) && TurkishHelper.isRounded(v)) {
				word.text += "üm";
			}
			else if(!(TurkishHelper.isFront(v)) && TurkishHelper.isRounded(v)) {
				word.text += "um";
			}
			else if(!(TurkishHelper.isFront(v)) && !(TurkishHelper.isRounded(v))) {
				word.text += "ım";
			}
			else {
				word.text += "im";
			}
			break;
			
		case 2 : 
			if(!(TurkishHelper.EndingWithVowel(word))) {
				if(TurkishHelper.isFront(v) && TurkishHelper.isRounded(v)) {
					word.text += "ü";
				}
				else if(!(TurkishHelper.isFront(v)) && TurkishHelper.isRounded(v)) {
					word.text += "u";
				}
				else if(!(TurkishHelper.isFront(v)) && !(TurkishHelper.isRounded(v))) {
					word.text += "ı";
				}
				else {
					word.text += "i";
				}
			}
			if(TurkishHelper.isFront(v) && TurkishHelper.isRounded(v)) {
				word.text += "müz";
			}
			else if(!(TurkishHelper.isFront(v)) && TurkishHelper.isRounded(v)) {
				word.text += "muz";
			}
			else if(!(TurkishHelper.isFront(v)) && !(TurkishHelper.isRounded(v))) {
				word.text += "mız";
			}
			else {
				word.text += "miz";
			}
			break;
			
		case 3 : 
			if(TurkishHelper.EndingWithVowel(word)) {
				word.text += "n";
			}
			else if(TurkishHelper.isFront(v) && TurkishHelper.isRounded(v)) {
				word.text += "ün";
			}
			else if(!(TurkishHelper.isFront(v)) && TurkishHelper.isRounded(v)) {
				word.text += "un";
			}
			else if(!(TurkishHelper.isFront(v)) && !(TurkishHelper.isRounded(v))) {
				word.text += "ın";
			}
			else {
				word.text += "in";
			}
			break;
			
		case 4 : 
			if(!(TurkishHelper.EndingWithVowel(word))) {
				if(TurkishHelper.isFront(v) && TurkishHelper.isRounded(v)) {
					word.text += "ü";
				}
				else if(!(TurkishHelper.isFront(v)) && TurkishHelper.isRounded(v)) {
					word.text += "u";
				}
				else if(!(TurkishHelper.isFront(v)) && !(TurkishHelper.isRounded(v))) {
					word.text += "ı";
				}
				else {
					word.text += "i";
				}
			}
			if(TurkishHelper.isFront(v) && TurkishHelper.isRounded(v)) {
				word.text += "nüz";
			}
			else if(!(TurkishHelper.isFront(v)) && TurkishHelper.isRounded(v)) {
				word.text += "nuz";
			}
			else if(!(TurkishHelper.isFront(v)) && !(TurkishHelper.isRounded(v))) {
				word.text += "nız";
			}
			else {
				word.text += "niz";
			}
			break;
			
		case 5 : 
			
			word.thirdPossessive = true;
			
			if(TurkishHelper.EndingWithVowel(word)) {
					word.text += "s";
			}
			if(TurkishHelper.isFront(v) && TurkishHelper.isRounded(v)) {
				word.text += "ü";
			}
			else if(!(TurkishHelper.isFront(v)) && TurkishHelper.isRounded(v)) {
				word.text += "u";
			}
			else if(!(TurkishHelper.isFront(v)) && !(TurkishHelper.isRounded(v))) {
				word.text += "ı";
			}
			else {
				word.text += "i";
			}
			
			break;
			
		case 6 : 
			
			word.thirdPossessive = true;
			
			if(TurkishHelper.isFront(v)) {
				word.text += "leri";
			}
			else {
				word.text += "ları";
			}
			break;
	}
}

static void addPast(WordState word, char v, Scanner s){
	word.hasPast = true;
	
	if(TurkishHelper.EndingWithVowel(word)) {
		word.text += "y";
	}
	
	if(TurkishHelper.lastConsonantHard(word)) {
		word.text += "t";
	}
	else {
		word.text += "d";
	}
	
	if(TurkishHelper.isFront(v) && TurkishHelper.isRounded(v)) {
		word.text += "ü";
	}
	else if(!(TurkishHelper.isFront(v)) && TurkishHelper.isRounded(v)) {
		word.text += "u";
	}
	else if(!(TurkishHelper.isFront(v)) && !(TurkishHelper.isRounded(v))) {
		word.text += "ı";
	}
	else {
		word.text += "i";
	}
}

public static void addCopula(WordState word, char v, int copChoice) {
	word.hasCopula = true;
	
	
	if(!(word.hasPast)) {
	
	switch(copChoice) {
		case 1 :
			
			if(TurkishHelper.EndingWithVowel(word)) {
				word.text += "y";
			}
			
			if(TurkishHelper.isFront(v) && TurkishHelper.isRounded(v)) {
				word.text += "üm";
			}
			else if(!(TurkishHelper.isFront(v)) && TurkishHelper.isRounded(v)) {
				word.text += "um";
			}
			else if(!(TurkishHelper.isFront(v)) && !(TurkishHelper.isRounded(v))) {
				word.text += "ım";
			}
			else {
				word.text += "im";
			}
			break;
		case 2 :
			
			if(TurkishHelper.EndingWithVowel(word)) {
				word.text += "y";
			}
			
			if(TurkishHelper.isFront(v) && TurkishHelper.isRounded(v)) {
				word.text += "üz";
			}
			else if(!(TurkishHelper.isFront(v)) && TurkishHelper.isRounded(v)) {
				word.text += "uz";
			}
			else if(!(TurkishHelper.isFront(v)) && !(TurkishHelper.isRounded(v))) {
				word.text += "ız";
			}
			else {
				word.text += "iz";
			}
			break;
			
		case 3 :
				
			if(TurkishHelper.isFront(v) && TurkishHelper.isRounded(v)) {
				word.text += "sün";
			}
			else if(!(TurkishHelper.isFront(v)) && TurkishHelper.isRounded(v)) {
				word.text += "sun";
			}
			else if(!(TurkishHelper.isFront(v)) && !(TurkishHelper.isRounded(v))) {
				word.text += "sın";
			}
			else {
				word.text += "sin";
			}
			break;
			
		case 4 :
			
			if(TurkishHelper.isFront(v) && TurkishHelper.isRounded(v)) {
				word.text += "sünüz";
			}
			else if(!(TurkishHelper.isFront(v)) && TurkishHelper.isRounded(v)) {
				word.text += "sunuz";
			}
			else if(!(TurkishHelper.isFront(v)) && !(TurkishHelper.isRounded(v))) {
				word.text += "sınız";
			}
			else {
				word.text += "siniz";
			}
			break;
			
		case 5 : 
			
			if(TurkishHelper.lastConsonantHard(word)) {
				word.text += "t";
			}
			else {
				word.text += "d";
			}
			
			if(TurkishHelper.isFront(v) && TurkishHelper.isRounded(v)) {
				word.text += "ür";
			}
			else if(!(TurkishHelper.isFront(v)) && TurkishHelper.isRounded(v)) {
				word.text += "ur";
			}
			else if(!(TurkishHelper.isFront(v)) && !(TurkishHelper.isRounded(v))) {
				word.text += "ır";
			}
			else {
				word.text += "ir";
			}
			break;
			
		case 6 : 
			
			if(TurkishHelper.lastConsonantHard(word)) {
				word.text += "t";
			}
			else {
				word.text += "d";
			}
			
			if(TurkishHelper.isFront(v) && TurkishHelper.isRounded(v)) {
				word.text += "ürler";
			}
			else if(!(TurkishHelper.isFront(v)) && TurkishHelper.isRounded(v)) {
				word.text += "urlar";
			}
			else if(!(TurkishHelper.isFront(v)) && !(TurkishHelper.isRounded(v))) {
				word.text += "ırlar";
			}
			else {
				word.text += "irler";
			}
			break;
	
	}}
	else {
		switch(copChoice) {
		case 1 :	
			
			word.text += "m";
			break;
			
		case 2 :

			word.text += "k";
			break;
			
		case 3 :
			
			word.text += "n";
			break;
			
		case 4 :
			
			if(TurkishHelper.isFront(v) && TurkishHelper.isRounded(v)) {
				word.text += "nüz";
			}
			else if(!(TurkishHelper.isFront(v)) && TurkishHelper.isRounded(v)) {
				word.text += "nuz";
			}
			else if(!(TurkishHelper.isFront(v)) && !(TurkishHelper.isRounded(v))) {
				word.text += "nız";
			}
			else {
				word.text += "niz";
			}
			break;
			
		case 5 : 
			
			word.hasCopula = false;
			break;
			
		case 6 : 

			if(TurkishHelper.isFront(v)) {
				word.text += "ler";
				}
			else {
				word.text += "lar";
			}
			break;
		
		}
		
	}
	
}

static void addAccusative(WordState word, char v, Scanner s){
	word.hasHardCase = true;
	
	if((word.thirdPossessive)) {
		word.text += "n";
	}
	
	if(TurkishHelper.EndingWithVowel(word)) {
		word.text += "y";
	}
	if(TurkishHelper.isFront(v) && TurkishHelper.isRounded(v)) {
		word.text += "ü";
	}
	else if(!(TurkishHelper.isFront(v)) && TurkishHelper.isRounded(v)) {
		word.text += "u";
	}
	else if(!(TurkishHelper.isFront(v)) && !(TurkishHelper.isRounded(v))) {
		word.text += "ı";
	}
	else {
		word.text += "i";
	}
}

static void addInstrumental(WordState word, char v, Scanner s){
	word.hasCase = true;
	
	if(TurkishHelper.EndingWithVowel(word)) {
		word.text += "y";
	}
	if(TurkishHelper.isFront(v)) {
		word.text += "le";
	}
	else {
		word.text += "la";
	}
}

static void addDative(WordState word, char v, Scanner s){
	word.hasHardCase = true;
	
	if((word.thirdPossessive)) {
		word.text += "n";
	}
	
	if(TurkishHelper.EndingWithVowel(word)) {
		word.text += "y";
	}
	
	if(TurkishHelper.isFront(v)) {
		word.text += "e";
	}
	else {
		word.text += "a";
	}
	
}

static void addLocative(WordState word, char v, Scanner s){
	word.hasCase = true;
	
	if((word.thirdPossessive)) {
		word.text += "n";
	}
	
	if(TurkishHelper.lastConsonantHard(word)) {
		word.text += "t";
	}
	else {
		word.text += "d";
	}
	
	if(TurkishHelper.isFront(v)) {
		word.text += "e";
	}
	else {
		word.text += "a";
	}
}

static void addAblative(WordState word, char v, Scanner s){
	word.hasCase = true;
	
	if((word.thirdPossessive)) {
		word.text += "n";
	}
	
	if(TurkishHelper.lastConsonantHard(word)) {
		word.text += "t";
	}
	else {
		word.text += "d";
	}
	
	if(TurkishHelper.isFront(v)) {
		word.text += "en";
	}
	else {
		word.text += "an";
	}
}

}

