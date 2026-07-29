package tr_conjugation;

public class TurkishRules {
	static boolean canAddPlural(WordState word){

	    if(word.plural)
	        return false;

	    if(word.thirdPossessive)
	        return false;

	    if(word.genitive)
	        return false;

	    if(word.hasCase)
	        return false;

	    if(word.hasHardCase)
	        return false;

	    if(word.hasPast)
	        return false;

	    if(word.hasCopula)
	        return false;

	    return true;
	}
	
	static boolean canAddGenitive(WordState word){

	    if(word.genitive)
	        return false;

	    if(word.thirdPossessive)
	        return false;

	    if(word.hasCase)
	        return false;

	    if(word.hasHardCase)
	        return false;

	    if(word.hasPast)
	        return false;

	    if(word.hasCopula)
	        return false;

	    return true;
	}
	
	static boolean canAddPossessive(WordState word){

	    if(word.thirdPossessive)
	        return false;

	    if(word.genitive)
	        return false;

	    if(word.hasCase)
	        return false;

	    if(word.hasHardCase)
	        return false;

	    if(word.hasPast)
	        return false;

	    if(word.hasCopula)
	        return false;

	    return true;
	}
	
	static boolean canAddCase(WordState word){

	    if(word.genitive)
	        return false;

	    if(word.hasCase)
	        return false;

	    if(word.hasHardCase)
	        return false;

	    if(word.hasPast)
	        return false;

	    if(word.hasCopula)
	        return false;

	    return true;
	}
	
	static boolean canAddPast(WordState word){

	    if(word.hasPast)
	        return false;

	    if(word.hasHardCase)
	        return false;

	    if(word.hasCopula)
	        return false;

	    return true;
	}
	
	static boolean canAddCopula(WordState word){

	    if(word.hasCopula)
	        return false;

	    if(word.hasHardCase)
	        return false;

	    return true;
	}
}
