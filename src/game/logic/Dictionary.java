package game.logic;

import java.io.BufferedReader;
import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * The class Dictionary contains the valid words used on the game, its main function is to serve as generator and container of the data structure
 * which will store the words in the provided dictionary
 */
public class Dictionary {//  This class 
	
	/** The attribute wordBook, which is an instance of DictionaryLinkedList */
private DictionaryLinkedList wordBook = new DictionaryLinkedList();
	
	/**
	 * Gets the word book.
	 *
	 * @return the word book
	 */
	public DictionaryLinkedList getWordBook() {
		return wordBook;
	}

	/**
	 * Sets the word book.
	 *
	 * @param wordBook the new word book
	 */
	public void setWordBook(DictionaryLinkedList wordBook) {
		this.wordBook = wordBook;
	}

	/**
	 * Generate dictionary book.
	 * this method parses the Dictionary.txt file containing the valid words to use and appends one DictionaryLinkedListNode
	 *  that contains a single word on its data attribute to the linked list on the attribute wordBook of this class.
	 */
	public void generateDictionaryBook(){
		BufferedReader reader = null;
		try {
			reader= new BufferedReader(new FileReader("Dictionary.txt"));

			String line;
			while((line = reader.readLine()) != null) {
				this.wordBook.append(line);
			}
			System.out.println("Dictionary Initialized");
		} catch (IOException error){
			System.out.println("Error de lectura");
		} finally {
			try {
				reader.close();
			} catch (IOException closingError) {
				System.out.println("Error al crear diccionario");

			}
		}
	}
	

}