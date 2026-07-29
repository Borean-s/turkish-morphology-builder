package tr_conjugation;

import java.util.Scanner;

// MorphoTR

public class Conj {
	Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		String state;
		int choice;
		//copula : nothing comes after
		// case : nothing comes after
		// kapının : past
		// kapım : everything , except another possessee
		// past : only copula comes after
		
		Scanner s = new Scanner(System.in);
		
	 System.out.println("Please enter the word you want to conjugate ");
	 WordState word = new WordState(s.next());
	 	
	 	char lastVowel = TurkishHelper.findLastVowel(word); 
	 	
	
	 	
	 	 
	 	 
	 		 
	 		while(!word.hasHardCase && !word.hasCopula) {

	 		    showMenu(word);

	 		    choice = s.nextInt();

	 		    SuffixManager.conjugateIt(word, choice, TurkishHelper.findLastVowel(word), s); //(WordState word, int choice, char v, Scanner s)
	 		   if(word.hasHardCase || word.hasCopula) {
	 			    break;
	 			}
	 		    
	 		   System.out.print("\nContinue adding suffixes? (yes/no): ");
	 		    String answer = s.next().toLowerCase();

	 		   if(answer.equals("no")) {
	 			    break;
	 			}	 		}
	 		
	 		System.out.printf("\nthe final word is : %s", word.text);
	 		
	 		 
	 		 
	 	 
	 	
	 
	 
	}

	
	
	static void conjugateIt(WordState word, int choice, char v, Scanner s) {
		
		switch(choice){
			case 1 : //accusative
				
				if(!TurkishRules.canAddCase(word)) {
				    System.out.println("Accusative cannot be added.");
				    return;
				}
				
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
				
				break;
				
			case 2 : // dative
				
				if(!TurkishRules.canAddCase(word)) {
				    System.out.println("Dative cannot be added.");
				    return;
				}
				
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
				
				
		        break;
			case 3 : //locative
				
				if(!TurkishRules.canAddCase(word)) {
				    System.out.println("Locative cannot be added.");
				    return;
				}
				
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
				break;
				
			case 4 : //ablative
				
				if(!TurkishRules.canAddCase(word)) {
				    System.out.println("Ablative cannot be added.");
				    return;
				}
				
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
				break;
				
			case 5: //instrumental
				
				if(!TurkishRules.canAddCase(word)) {
				    System.out.println("Instrumental cannot be added.");
				    return;
				}
				
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
				break;
				
			case 6: // genitive
				
				if(!TurkishRules.canAddGenitive(word)) {
				    System.out.println("Genitive cannot be added.");
				    return;
				}

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
				break;
			case 7 : // possessive
				
				if(!TurkishRules.canAddPossessive(word)) {
				    System.out.println("Possessive cannot be added.");
				    return;
				}

				System.out.printf("1)  1st sg      "); System.out.printf("2)  1st pl\n");
				System.out.printf("3)  2st sg      "); System.out.printf("4)  2st pl\n");
				System.out.printf("5)  3st sg      "); System.out.printf("6)  3st pl\n");
				int genChoice;
				genChoice = s.nextInt();
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
				break;
			case 8 : //plural
				
				if(!TurkishRules.canAddPlural(word)) {
				    System.out.println("Plural cannot be added.");
				    return;
				}
				
				word.plural = true;
				
				if(TurkishHelper.isFront(v)) {
					word.text += "ler";
				}
				else {
					word.text += "lar"; 
				}
				break; 
				
			case 9 : //past
				
				if(!TurkishRules.canAddPast(word)) {
				    System.out.println("Past cannot be added.");
				    return;
				}
				
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
				
				break;
				
			case 10 : //copula
				
				if(!TurkishRules.canAddCopula(word)) {
				    System.out.println("Copula cannot be added.");
				    return;
				}
				
				word.hasCopula = true;
				
				System.out.printf("1)  1st sg      "); System.out.printf("2)  1st pl\n");
				System.out.printf("3)  2st sg      "); System.out.printf("4)  2st pl\n");
				System.out.printf("5)  3st sg      "); System.out.printf("6)  3st pl\n");
				int copChoice;
				copChoice = s.nextInt();

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
			
		
		System.out.println(word.text);
		
	}
	
	
	
	
	static void showMenu(WordState word) {
		
		System.out.println();
		System.out.println("----------------------------");
		System.out.println("Current word: " + word.text);
		System.out.println("Choose a suffix:");

	    if(!word.hasHardCase && !word.hasCopula) {

	    	if(TurkishRules.canAddPlural(word))
	    	    System.out.println("8 - Plural");

	    	if(TurkishRules.canAddGenitive(word))
	    	    System.out.println("6 - Genitive");

	    	if(TurkishRules.canAddPossessive(word))
	    	    System.out.println("7 - Possessive");

	    	if(TurkishRules.canAddCase(word)) {

	    	    System.out.println("1 - Accusative");
	    	    System.out.println("2 - Dative");
	    	    System.out.println("3 - Locative");
	    	    System.out.println("4 - Ablative");
	    	    System.out.println("5 - Instrumental");

	    	}

	    	if(TurkishRules.canAddPast(word))
	    	    System.out.println("9 - Past");

	    	if(TurkishRules.canAddCopula(word))
	    	    System.out.println("10 - Copula");

	    	System.out.print("\nChoice: ");
	    }
		
	   
	    
		}
		
		
	   
	}
	
	

