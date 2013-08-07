package edu.hpu.csci4702;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;

import android.content.res.Resources;

public class WordMasterBrain {
	private ArrayList<String> usedWords;
	// getThing implies <String,HashMap> String is the LetterSet.....
	// HashMap is all the associated data regarding the String like the word
	// size
	// then all the words associated with the word size
	private HashMap<String, HashMap<Integer, ArrayList<String>>> getThings;

	private Object currentLetterSetBeingUsed;

	private Object getCurrentLetterSetBeingUsed() {
		if (this.currentLetterSetBeingUsed == null) {
			this.currentLetterSetBeingUsed = null;
		}
		return this.currentLetterSetBeingUsed;
	}

	private void setCurrentLetterSetBeingUsed(Object letterSet) {
		if (this.currentLetterSetBeingUsed == null) {
			this.currentLetterSetBeingUsed = letterSet;
		}
	}

	private HashMap<String, HashMap<Integer, ArrayList<String>>> getThings() {
		if (this.getThings == null) {
			this.getThings = new HashMap<String, HashMap<Integer, ArrayList<String>>>();
		}

		return this.getThings;
	}

	private ArrayList<String> getUsedWords() {
		if (this.usedWords == null) {
			this.usedWords = new ArrayList<String>();
		}
		return this.usedWords;
	}

	// returns LetterSet to ViewController
	Object getLetterSet(Resources resources) {
		// load up database
		accessDatabase(resources);
		Object[] letterSet = getThings().keySet().toArray();

		// create random number to choose random letterSet index
		Random generator = new Random();
		int randomIndex = generator.nextInt(letterSet.length);
		setCurrentLetterSetBeingUsed(letterSet[randomIndex]);
		return getCurrentLetterSetBeingUsed();
	}

	private void accessDatabase(Resources resources) {
		Scanner reader = new Scanner(new BufferedInputStream(
				resources.openRawResource(R.raw.lettersets)));
		while (reader.hasNextLine()) {
			HashMap<Integer, ArrayList<String>> things = new HashMap<Integer, ArrayList<String>>();
			for (int i = 3; i <= 6; i++) {
				ArrayList<String> values = new ArrayList<String>();
				things.put(i, values);
			}

			boolean newSet = false;

			while (!newSet) {
				String line = null;
				if (reader.hasNextLine()) {
					line = reader.nextLine();

					if (line.length() != 0) {
						ArrayList<String> list = new ArrayList<String>(
								things.get(line.length()));
						list.add(line);
						things.remove(line.length());
						things.put(line.length(), list);
					} else {
						newSet = true;
						// add to getThings
						getThings().put(things.get(6).get(0), things);
					}
				} else {
					newSet = true;
					getThings().put(things.get(6).get(0), things);
				}
			}
		}
	}

	void addToUsedWords(String word) {
		getUsedWords().add(word);
	}

	boolean checkIfWordAlreadyExists(String word) {
		// boolean regarding a word already used is returned
		if (getUsedWords().contains(word)) {
			return true;
		}
		return false;
	}

	boolean checkWord(String word) {
		// boolean regarding the existence of a word in the current letter set
		// is returned
		if (word.length() == 3) {
			return getThings().get(getCurrentLetterSetBeingUsed()).get(3)
					.contains(word);
		} else if (word.length() == 4) {
			return getThings().get(getCurrentLetterSetBeingUsed()).get(4)
					.contains(word);
		} else if (word.length() == 5) {
			return getThings().get(getCurrentLetterSetBeingUsed()).get(5)
					.contains(word);
		} else if (word.length() == 6) {
			return getThings().get(getCurrentLetterSetBeingUsed()).get(6)
					.contains(word);
		}
		return false;
	}
}
