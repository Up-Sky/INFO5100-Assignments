package Assignment8;

import java.io.IOException;
import java.util.Scanner;

public class FileCounter {
	private int characterCount, wordCount, lineCount;
	
	public FileCounter() {
		characterCount = 0;
		wordCount = 0;
		lineCount = 0;
	}

	   /**
	      Processes an input source and adds its character, word, and line
	      counts to the respective variables.
	      @param in the scanner to process
	   */
	public void read(Scanner in) throws IOException {
		StringBuilder str = new StringBuilder();
		while(in.hasNextLine()) {
			lineCount ++;
			str.append(in.nextLine());
			str.append(" ");
		}
		String content = str.toString().trim();
		if(content.length() == 0)
			return;
		for(int i = 0; i < content.length(); i++) {
			if(content.charAt(i) != ' ')
				characterCount ++;
			else if(i < content.length() - 1 && content.charAt(i + 1) != ' ')
				wordCount ++;
		}
		wordCount ++;
	}
	
	public int getCharacterCount() {
		return this.characterCount;
	}
	
	public int getWordCount() {
		return this.wordCount;
	}
	public int getLineCount() {
		return this.lineCount;
	}
}
