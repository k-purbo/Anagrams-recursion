import java.util.*;

public class Anagrams {

	private Set<String> dictionary;
	
	public Anagrams(Set<String> dictionary) {
		if (dictionary == null) throw new IllegalArgumentException();	//if dict passed is null
		this.dictionary = dictionary;	
	}
	
	public Set<String> getWords(String phrase) {
		if (phrase == null) throw new IllegalArgumentException();	//exception if phrase passed is null
		
		LetterInventory phraseInv = new LetterInventory(phrase);	//keeps track of letters in word
		Set<String> words = new TreeSet<String>();	//words that will be returned
		for (String word : dictionary) {	//for every word of the dictionary
			if (phraseInv.contains(word)) {		//if the word is made up only of letters in the phrase
				words.add(word);
			}
		}
		return words;
	}
	
	public void print(String phrase) {
		print(phrase, 0);		//equivalent to print method with max = 0
	}
	
	public void print(String phrase, int max) {
		if (phrase == null || max < 0) throw new IllegalArgumentException();
		
		LetterInventory phraseInv = new LetterInventory(phrase);	//will be used in recursion method for keeping track of letters in phrase
		Set<String> options = getWords(phrase);		//words that can be made with letters from phrase
		Stack<String> chosen = new Stack<String>();		//used 
		print(chosen, options, phraseInv, max);
	}
	
	private void print(Stack<String> chosen, Set<String> options, LetterInventory phraseInv, int max) {
		if ((phraseInv.size() != 0 || chosen.size() > max) && (phraseInv.size() != 0 || max != 0)) {
			for (String word : options) {
				if (phraseInv.contains(word)) {
					
					chosen.push(word);		//choose word
					phraseInv.subtract(word);	//remove that word's letters from phraseInv
					
					print(chosen, options, phraseInv, max);		//explore other options (recursive call)
					
					phraseInv.add(word);	//re-add word's letters to phraseInv
					chosen.pop();	//unchoose word
				}
			}
		}
		else {
			System.out.println(chosen);		//base case - prints chosen
		}
	}
	
}
