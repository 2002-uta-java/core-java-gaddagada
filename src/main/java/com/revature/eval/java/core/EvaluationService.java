package com.revature.eval.java.core;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EvaluationService {

	private static final int[] digits = null;

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {

		// getBytes() method to convert string into bytes[].
		byte[] result = null;
		if (!(string == null)) {
			byte[] strAsByteArray = string.getBytes();
			result = new byte[strAsByteArray.length];
			// Store result in reverse order into the result byte[]
			for (int i = 0; i < strAsByteArray.length; i++)
				result[i] = strAsByteArray[strAsByteArray.length - i - 1];
		}

		return new String(result);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		String[] inputString = phrase.split("[\\s\\-]");
		// String[] inputString = phrase.split("[\\s\\-\\.\\'\\?\\,\\_\\@]+\"");

		String acronym = "";
		for (int i = 0; i < inputString.length; i++) {
			// Portable Network Graphics to its acronym
			String str = inputString[i];
			String firstletter = str.substring(0, 1).toUpperCase();
			acronym = (new StringBuilder().append(acronym).append(firstletter)).toString();
		}

		return acronym;
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			if (sideOne == sideTwo && sideTwo == sideThree) {
				return true;
			}
			return false;
		}

		public boolean isIsosceles() {
			if (sideOne == sideTwo || sideTwo == sideThree || sideOne == sideThree) {
				return true;
			}
			return false;
		}

		public boolean isScalene() {
			if (sideOne != sideTwo && sideTwo != sideThree && sideOne != sideThree) {
				return true;
			}
			return false;
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */

	/*
	 * Table of key and values ------------------------ Letter Value A, E, I, O, U,
	 * L, N, R, S, T 1 D, G 2 B, C, M, P 3 F, H, V, W, Y 4 K 5 J, X 8 Q, Z 10
	 */
	public int getScrabbleScore(String string) {
		int scores[] = { 1, 2, 3, 4, 5, 8, 10 };
		String letters[] = { "AEIOULNRST", "DG", "BCMP", "FHVWY", "K", "JX", "QZ" };
		int sum = 0;
		String stringUpper = string.toUpperCase();

		for (int i = 0; i < stringUpper.length(); i++) {
			for (int j = 0; j < letters.length; j++) {
				if (letters[j].contains("" + stringUpper.charAt(i))) {
					sum += scores[j];
				}
			}
		}
		return sum;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number. ` The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		// removing spaces and extra characters
		String retval = string.replaceAll("[()-.\\D]", "");
		// validating if number is 10 digits if not throw exception
		if ((retval.length() != 10) || (retval.length() < 10)) {
			throw new IllegalArgumentException(string);
		} else {
			// If there is an area code remove it
			while (retval.length() > 10) {
				retval.replaceFirst("\\d", "");
			}

		}
		return retval;
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		Map<String, Integer> countingWords = new HashMap<String, Integer>();
		string = string.replace(",", " ");
		string = string.replace("\n", "");
		for (String word : string.split(" |\\,|\\[\\n]")) {
			if (countingWords.containsKey(word)) {
				int count = countingWords.get(word);
				countingWords.put(word, ++count);
			} else {
				countingWords.put(word, 1);
			}
		}
		return countingWords;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T> {
		private List<T> sortedList;

		public int indexOf(T t) {
			int startIdx = 0;
			int endIdx = sortedList.size() - 1;
			String[] origStringList = null;
			Integer[] origNumberList = null;

			int mid = (startIdx + endIdx) / 2;

			// There may be a better way to dynamically create the array based on
			// type of the object added to the array
			if (t instanceof String) {
				origStringList = (String[]) (sortedList).toArray();
				while (startIdx <= endIdx) {

					int res = ((String) t).compareTo(origStringList[mid]);
					// Check if search string is present in the middle return index of the middle
					// string
					if (res == 0)
						return mid;
					else if (res > 0) {// Means the search string is in the 2nd half of the array. Reset the start idx
						startIdx = mid + 1;

					} else {
						endIdx = mid - 1;// Means the search strings in the first half of the array. Reset the end index
					}
					mid = (startIdx + endIdx) / 2;// Reset the mid to the first of 2nd half of the array
					// Continue searching with new startidx,endidx and mid
				}

			}
			if (t instanceof Number) {
				origNumberList = (Integer[]) (sortedList).toArray();
				while (startIdx <= endIdx) {
					if (origNumberList[mid] == (Integer) t) {
						return mid;
					} else if (origNumberList[mid] < (Integer) t) {
						startIdx = mid + 1;
					} else {
						endIdx = mid - 1;
					}
					mid = (startIdx + endIdx) / 2;
				}

			}

			return -1;

		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */

	/**
	 * Method to translate a sentence word by word.
	 * 
	 * @param s The sentence in English
	 * @return The pig latin version
	 */
	public String toPigLatin(String s) {
		Pattern vowel = Pattern.compile("^([aeiou]|y[^aeiou]|xr)");
		Pattern consone = Pattern.compile("^([^aeiou]?qu|[^aeiouy]+|y(?=[aeiou]))");

		String res = "";

		for (String word : s.split(" ")) {
			if (vowel.matcher(word).find())
				res += word;
			else {
				Matcher z = consone.matcher(word);
				if (z.find())
					res += word.substring(z.end()) + z.group();
			}
			res += "ay ";
		}

		return res.substring(0, res.length() - 1);
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		String inputAsString = input + "";
		int numberOfDigits = inputAsString.length();
		int copyOfInput = input;
		int sum = 0;
		while (copyOfInput != 0) {
			int lastDigit = copyOfInput % 10;
			sum = sum + (int) Math.pow(lastDigit, numberOfDigits);
			copyOfInput = copyOfInput / 10;
		}
		if (sum == input) {
			return true;
		} else {
			return false;

		}
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long number) {
		ArrayList<Long> primeNumbers = new ArrayList<Long>(
				Arrays.asList(1L, 2L, 3L, 5L, 7L, 11L, 13L, 17L, 19L, 23L, 461L));

		List<Long> primeFactors = new ArrayList<Long>();

//		do {
//			if (number % 2 == 0) {// even
//				number = number / 2;
//				primeFactors.add(new Long(2L));
//			} else {
//
//				if (number % 3 == 0) {
//					number = number / 3;
//					primeFactors.add(new Long(3L));
//
//				}
//
//				if (number % 5 == 0) {
//					number = number / 5;
//					primeFactors.add(new Long(5L));
//
//				}
//				if (number % 7 == 0) {
//					number = number / 7;
//					primeFactors.add(new Long(7L));
//
//				}
//				if (number % 11 == 0) {
//					number = number / 11;
//					primeFactors.add(new Long(11L));
//
//				}
//				
//				if (number % 17 == 0) {
//					number = number / 17;
//					primeFactors.add(new Long(17L));
//
//				}
//				
//				if (number % 23 == 0) {
//					number = number / 23;
//					primeFactors.add(new Long(23L));
//
//				}
//				
//				if (number % 461 == 0) {
//					number = number / 461;
//					primeFactors.add(new Long(461L));
//
//				}
//				
//			}
//			
//			//If the 1st factor is prime just add it
//			if (primeNumbers.contains(number) && (number != 1)) {
//				primeFactors.add(new Long(number));
//			}
//
//		} while (!primeNumbers.contains(number));

		for (int i = 2; i < number; i++) {
			while (number % i == 0) {
				primeFactors.add(new Long(i));
				number = number / i;
			}
		}
		if (number >= 2) {
			primeFactors.add(new Long(number));
		}

		return primeFactors;

	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private static final int ALPHABET_SIZE = 26;
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String message) {
			key %= ALPHABET_SIZE;
			char[] chars = message.toCharArray();
			for (int i = 0; i < chars.length; ++i) {
				if (isLowerCase(chars[i])) {
					chars[i] = rotateChar(chars[i], key, 'a', 'z');
				} else if (isUpperCase(chars[i])) {
					chars[i] = rotateChar(chars[i], key, 'A', 'Z');
				}
			}
			return new String(chars);
		}

		private static char rotateChar(char c, int rotateBy, char firstChar, char lastChar) {
			c += rotateBy;
			if (c < firstChar) {
				return (char) (c + ALPHABET_SIZE);
			}
			if (c > lastChar) {
				return (char) (c - ALPHABET_SIZE);
			}
			return c;
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int nthPosition) {
		// Throw exception if the position is zero
		if (nthPosition == 0)
			throw new IllegalArgumentException();
		// Define local variables for counters
		int nthPrime, count, i;
		nthPrime = 1;
		count = 0;
		// Identify prime until the nth position is reached
		while (count < nthPosition) {
			nthPrime = nthPrime + 1;
			// Loop through from 2 to nthprime
			for (i = 2; i <= nthPrime; i++) {
				if (nthPrime % i == 0) {
					break;
				}
			}
			// Increment count if it is a prime number
			if (i == nthPrime) {
				count = count + 1;
			}
		}

		return nthPrime;
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String paramPlainText) {
			String cipherText = "";
			String cleanPlainText = "";

			for (char c : paramPlainText.toLowerCase().toCharArray()) {
				if (Character.isLetterOrDigit(c)) {
					cleanPlainText += c;
				}
			}

			char[] alphabetsArray = new String("abcdefghijklmnopqrstuvwxyz").toCharArray();
			// Build a Hashmap to store key value pairs for alphabet reverse order A ->Z ,
			// B->Y etc.,
			// ABCDEFGHIJKLMNOPQRSTUVWXYZ
			// ZYXWVUTSRQPONMLKJIHGFEDCBA
			HashMap<String, String> hmap = new HashMap<String, String>();
			for (int i = 0; i < alphabetsArray.length; i++) {
				hmap.put(String.valueOf(alphabetsArray[i]),
						String.valueOf(alphabetsArray[alphabetsArray.length - (i + 1)]));
			}
			// Loop through each char, check if it is char and get mapping char from hashmap
			for (int i = 0; i < cleanPlainText.length(); i++) {
				char c = cleanPlainText.substring(i, i + 1).charAt(0);
				if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
					cipherText += hmap.get(String.valueOf(c).toLowerCase());
				} else {
					cipherText += String.valueOf(c);
				}
			}

			return parseString(cipherText).trim();
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String paramCipherText) {
			String plainText = "";
			String cleanCipherText = "";

			for (char c : paramCipherText.toLowerCase().toCharArray()) {
				if (Character.isLetterOrDigit(c)) {
					cleanCipherText += c;
				}
			}

			char[] alphabetsArray = new String("abcdefghijklmnopqrstuvwxyz").toCharArray();
			// Build a Hashmap to store key value pairs for alphabet reverse order A ->Z ,
			// B->Y etc.,
			// ABCDEFGHIJKLMNOPQRSTUVWXYZ
			// ZYXWVUTSRQPONMLKJIHGFEDCBA
			HashMap<String, String> hmap = new HashMap<String, String>();
			for (int i = 0; i < alphabetsArray.length; i++) {
				hmap.put(String.valueOf(alphabetsArray[i]),
						String.valueOf(alphabetsArray[alphabetsArray.length - (i + 1)]));
			}
			// Loop through each char, check if it is char and get mapping char from hashmap
			for (int i = 0; i < cleanCipherText.length(); i++) {
				char c = cleanCipherText.substring(i, i + 1).charAt(0);
				if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
					plainText += hmap.get(String.valueOf(c).toLowerCase());
				} else {
					plainText += String.valueOf(c);
				}
			}

			return (plainText).trim();
		}

		private static String parseString(String input) {
			String out = "";
			for (int i = 0; i < input.length(); i += 5) {
				if (i + 5 <= input.length()) {
					out += (input.substring(i, i + 5) + " ");
				} else {
					out += (input.substring(i) + " ");
				}
			}
			return out;
		}

	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String isbn) {

		if (isbn == null) {
			return false;
		}

		// remove any hyphens
		isbn = isbn.replaceAll("-", "");

		// must be a 10 digit ISBN
		if (isbn.length() != 10) {
			return false;
		}

		try {
			int tot = 0;
			for (int i = 0; i < 9; i++) {
				int digit = Integer.parseInt(isbn.substring(i, i + 1));
				tot += ((10 - i) * digit);
			}

			String checksum = Integer.toString((11 - (tot % 11)) % 11);
			if ("10".equals(checksum)) {
				checksum = "X";
			}

			return checksum.equals(isbn.substring(9));
		} catch (NumberFormatException nfe) {
			// to catch invalid ISBNs that have non-numeric characters in them
			return false;
		}

	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		boolean[] alphaList = new boolean[26];
		int index = 0;
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) >= 'A' && string.charAt(i) <= 'Z') {
				index = string.charAt(i) - 'A';
			} else if (string.charAt(i) >= 'a' && string.charAt(i) <= 'z') {
				index = string.charAt(i) - 'a';
			}
			alphaList[index] = true;
		}
		for (int i = 0; i <= 25; i++) {
			if (alphaList[i] == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		final long GIGASECOND = 1000000000;
		LocalDateTime beginning = null;
		if (given instanceof LocalDateTime) {
			return given.plus(GIGASECOND, ChronoUnit.SECONDS);
		} else {
			LocalDate give = (LocalDate) given;
			beginning = give.atStartOfDay();
			return beginning.plus(GIGASECOND, ChronoUnit.SECONDS);
		}
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		HashSet<Integer> multiples = new HashSet<Integer>();
		int result = 0;
		for (int k = 1; k < i; k++) {
			for (int num : set) {
				if (k % num == 0) {
					multiples.add(k);
				}
			}
		}
		for (int num : multiples) {
			result += num;
		}
		return result;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String paramStr) {
		String str = paramStr.replaceAll(" ", "");
		str = str.replaceAll("-", "");
		int[] ints = new int[str.length()];
		try {
			for (int i = 0; i < str.length(); i++) {
				ints[i] = Integer.parseInt(str.substring(i, i + 1));
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			return false;
		}
		for (int i = ints.length - 2; i >= 0; i = i - 2) {
			int j = ints[i];
			j = j * 2;
			if (j > 9) {
				j = j % 10 + 1;
			}
			ints[i] = j;
		}
		int sum = 0;
		for (int i = 0; i < ints.length; i++) {
			sum += ints[i];
		}
		return (sum % 10 == 0);
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		// Taking a word problem and parsing the operation needed to be performed
		String[] parsedNumber = string.split(" ");
		// Declaring a variable for the first number passed in the word problem to be
		// used to compute the operation declared.
		Integer firstNum = Integer.parseInt(parsedNumber[2]);
		String lastItem = parsedNumber[parsedNumber.length - 1];
		String trimedLastItem = lastItem.substring(0, lastItem.length() - 1);
		// Declaring a variable for the seconds number passed in the word problem to be
		// used to compute the operation declared.

		Integer secondNum = Integer.parseInt(trimedLastItem);

		switch (parsedNumber[3]) {
		case "plus":
			return (int) firstNum + secondNum;
		case "minus":
			return (int) firstNum - secondNum;
		case "multiplied":
			return (int) firstNum * secondNum;
		case "divided":
			return (int) firstNum / secondNum;
		}
		return 0;
	}

}
