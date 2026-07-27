package tr_conjugation;

import java.util.ArrayList;

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
}
