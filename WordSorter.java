import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

/**
 * 
 * Write a Java application which takes any number of words as command line arguments
 * and outputs unique words (ignoring case) in ascending length and then alphabetical
 * order (where lengths are the same). The original case should be preserved and the
 * outputted words should be comma separated.
 * e.g.
 * java WordSorter Fish DOG banana fish cat a pineapple carrot dog
 * would produce:
 * a, cat, DOG, Fish, banana, carrot, pineapple
 * 
 * @author 	Oliver Woods
 * @version	1.0
 */
public class WordSorter {
	
	/**
	 * Returns a unique list of words, sorted into ascending length and then
	 * alphabetical order (where lengths are the same).
	 * 
	 * @param 	inputWordList a list of words
	 * @return	unique, sorted list
	 */
	public static List<String> getSortedWordList(List<String> inputWordsList) {
		HashMap<String, String> uniqueWordsList = new HashMap<String, String>();
		ArrayList<String> resultsWordsList = new ArrayList<String>();
		ArrayList<String> sortedWordsList = null;

		if(inputWordsList==null)
		{
			throw new IllegalArgumentException();
		}
		
		/*
		 *  Use a HashMap to get a unique set of words. The original case is stored as the value
		 *  and the lowercase version of the word is stored as the key.
		 */
		for (String originalWord : inputWordsList) {
			if (!uniqueWordsList.containsKey(originalWord.toLowerCase())) {
				uniqueWordsList.put(originalWord.toLowerCase(), originalWord);
			}
		}

		/*
		 * Convert the (lowercase) HashMap keys into a list and use a Comparator to sort them.
		 */
		sortedWordsList = new ArrayList<String>(uniqueWordsList.keySet());

		Collections.sort(sortedWordsList, new Comparator<String>() {
			@Override
			public int compare(String word1, String word2) {
				// If word length is the same, compare by alphabetical order
				if (word1.length() == word2.length()) {
					return word1.compareTo(word2);
				} else {					
					return word1.length() - word2.length();
				}
			}
		});

		/*
		 *  Copy sorted list into the result list, retrieving the correct case from
		 *  the HashMap values.
		 */
		for (String originalWord : sortedWordsList) {
			resultsWordsList.add(uniqueWordsList.get(originalWord));
		}

		return resultsWordsList;
	}

	/**
	 * Returns a List as a comma-separated String.
	 * 
	 * @param 	resultsList A List of words
	 * @return	commas separated String
	 */
	public static String ListToString(List<String> resultsList) {
		StringBuffer resultsString = new StringBuffer();

		if(resultsList==null)
		{
			throw new IllegalArgumentException();
		}
		
		for (int ndx = 0; ndx < resultsList.size(); ndx++) {
			if (ndx > 0) {
				resultsString.append(", ");
			}
			resultsString.append(resultsList.get(ndx));
		}

		return resultsString.toString();
	}

	/**
	 * Main method. 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
				
		if(args.length==0)
		{
			System.out.println("USAGE: WordSorter [list of words seperated by a space]");
		}
		else
		{
			List<String>sortedWordsList=getSortedWordList(Arrays
					.asList(args));
			System.out.println(ListToString(sortedWordsList));
		}
	}

	/*
	 * 
	 * JUnit Tests. Test the example given in the class description.
	 * 
	 */
	
	@Test
	/**
	 * Test GetSortedWordList
	 */
	public void testGetSortedWordList() {
		String[] testString = "Fish DOG banana fish cat a pineapple carrot dog"
				.split(" ");
		List<String> expectedResults = Arrays
				.asList("a cat DOG Fish banana carrot pineapple"
				.split(" "));
		
		List<String> testResults = getSortedWordList(Arrays
				.asList(testString));
		
		assertEquals(testResults, expectedResults);
	}

	@Test
	/**
	 * Test ListToString method
	 */
	public void testListToString() {
		String[] testString = "Fish DOG banana fish cat a pineapple carrot dog".split(" ");
		String expectedResult = "a, cat, DOG, Fish, banana, carrot, pineapple";
		String resultString="";
		
		List<String> testResults = getSortedWordList(Arrays
				.asList(testString));
		resultString=ListToString(testResults);
		assertEquals(resultString, expectedResult);
	}

}
