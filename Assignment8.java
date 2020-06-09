package wordCount;

import java.util.*;
import java.io.*;

public class Assignment8{
	public static void main(String[] args) throws IOException{
		Scanner input = new Scanner(new File("/Users/sebiseb/eclipse-workspace/Assignments/src/wordCount/Assignment8in.txt"));
		PrintWriter output = new PrintWriter("Assignment8out.txt");
		
		final int max = 100;
		String [] word = new String[max];
		int [] wordCount = new int[max];
		String text;
	
		while(input.hasNext()) {
			
			text = input.nextLine();// Pulling the text from the file
			output.println("The text:\n");
			output.println(text);// Printing the the text from the file
			output.println("---------------------------------------------------");
			output.println("Displaying the sorted words with the number of unique words:\n");
			
			word = text.split(" ");// Splitting each word in the text to be in the array
			int size = word.length;
			countWords(word, wordCount, size);// returns the count for each individual word
			
			//Declaring the new arrays 
			int newSize = countUnique(word, size);// Size of the new array
			String [] newWord = new String[newSize];// Declaring the array with new array with no duplicates
			int [] newCount = new int [newSize];// Declaring the new array that holds the count of the words without the duplicates
			
			fill(newWord, word, wordCount, newCount, size);// Filling the array with no duplicates
			printArray(newWord, newCount, newSize, output);// Printing the new Array with the count of the unique words
		}// End of the while loop
		
		System.out.println("done");
		output.close();
	}// end of main
	
	public static void sortWords(String [] word, int size) {
		boolean swapped;
		String temp;
		
		do {
			swapped = false;
			
			for (int i = 0; i < size - 1; i++) {
				
				if (word[i].compareTo(word[i + 1]) > 0){// Compares the two indices next to each other
					temp = word[i]; // Putting the word in a empty place since it's alphabetically less
					word[i] = word[i + 1];// Assigning the word to the next index
					word[i + 1] = temp;// Second word is put in the first position
					swapped = true;// Swapped becomes true
				}// End of the if statement
			}// End of the for loop
		} while (swapped); //End of the do-while loop
		
	}// End of sortWords method
	
	public static void printArray(String [] word, int size) {
		for (int i = 0; i < size; i++) {
			System.out.println(word[i]); 
		}
	}// End of the printArray method
	
	public static int [] countWords(String [] word, int [] wordCount, int size) {
		for (int i = 0; i < size; i++) {
			word[i] = word[i].toLowerCase();
			sortWords(word, word.length);
			for(int j = i; j < size; j++) {
				if (word[i].equals(word[j]) == true) {
					wordCount[i]++;
				}//End of if statement
		
			}// End of for loop
		}// End of the top for loop
		
		return wordCount;
	}// End of the method
	
	public static int countUnique(String [] word, int size) {
		int cnt = 0;
		
		for (int i = 0; i < size; i++) {
			
			for(int j = i; j < size; j++) {
				if (word[i].equals(word[j]) == true) {// Checks if the words at the respective index are the same
					
					if (i == j) {// if the value of i and j are the same, this means that j would be a duplicate
						cnt++;// counts how many duplicates words their is
					} else {
						i++;// if the words has no more duplicates we go to the next word in the array
					}// End of the if-else statement
					
				}//End of if statement
			}// End of for loop
		}// End of the top for loop
	
		return cnt;
	}// End of the countUnique method
	
	public static void printArray(String [] word, int [] wordCount, int size, PrintWriter output) {

		for (int i = 0; i < size; i++) {
			output.println(word[i] + "  \t"+ wordCount[i]);
		}
	}
	
	public static void fill(String [] newArray, String [] word, int [] wordCount, int [] newWordCount, int size) {
		int cnt = 0;
		for (int i = 0; i < size; i++) {
			for(int j = i; j < size; j++) {
				if (word[i].equals(word[j]) == true) {
					if (i == j) {
						newArray[cnt] = word[i];
						newWordCount[cnt] = wordCount[i];
						cnt++;
					} else {
						i++;
					}
				}//End of if statement
			}// End of for loop
			
		}// End of the top for loop
	}
}// End of the class