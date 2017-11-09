import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.*;
import java.util.*;

public class Hangman {
	ArrayList<String> words;
	ArrayList<Character> correctList, wrongList, availableList;
	String finalWord, nowWord;
	int count;
	char[] nowWordList;
	
	Hangman(ArrayList<String> words) {
		this.words = new ArrayList<String>();
		for(int i = 0; i < words.size(); i++)
			this.words.add(words.get(i).toUpperCase());
		this.count = 0;
		this.correctList = new ArrayList<Character>();
		this.wrongList = new ArrayList<Character>();
		this.availableList = new ArrayList<Character>();
		for(int i = 0; i < 26; i++) {
			char letter = (char)((int)'A' + i);
			this.availableList.add(letter); 
		}
	}
	
	String chooseWord() {
		int lenth = this.words.size();
		Random random = new Random();
		int index = random.nextInt(lenth - 1);
		this.finalWord = this.words.get(index);
		this.nowWordList = new char[this.finalWord.length()];
		Arrays.fill(this.nowWordList, '-');
		return this.finalWord;
	}
	
	void handleGuess(char letter) {
		if(letter >= 'a' && letter <= 'z')
			letter -= 32;
		if(!this.availableList.contains(letter)) {
			System.out.println("This character is not available or already guessed");
			return;
		}
		for(int i = 0; i < this.finalWord.length(); i++) {
			if(letter == this.finalWord.charAt(i)) {
				this.correctList.add(letter);
				break;
			}
			if(i == this.finalWord.length() - 1) {
				this.wrongList.add(letter);
				this.count += 1;
			}
		}
		this.availableList.remove(this.availableList.indexOf(letter));
	}
	
	boolean gameWon() {
		return this.finalWord.equals(this.nowWord);
	}
	
	void gameOver() {
		this.count = 0;
		this.correctList.clear();
		this.wrongList.clear();
		this.availableList.clear();
		for(int i = 0; i < 26; i++) {
			char letter = (char)((int)'A' + i);
			this.availableList.add(letter); 
		}
		System.out.println("Do you want to restart game?(Please Input Number)\n1. Yes\n0. No");
		Scanner in = new Scanner(System.in);
		int choose = in.nextInt();
		if(choose == 1)
			this.playGame();
		return;
	}
	
	void printHangman() {
		String[] lines = new String[9];
		Arrays.fill(lines, "|");
		lines[0] = " ________________";
		lines[1] = "|       |";
		lines[8] = "|________________";
		while(true) {
			if(this.count == 0)
				break;
			lines[2] = "|       O";
			if(this.count == 1)
				break;
			lines[3] = "|       |";
			if(this.count == 2)
				break;
			lines[4] = "|    ---|";
			if(this.count == 3)
				break;
			lines[4] = "|    ---|---";
			if(this.count == 4)
				break;
			lines[5] = "|      /";
			lines[6] = "|     /";
			if(this.count == 5)
				break;
			lines[5] = "|      / \\";
			lines[6] = "|     /   \\";
			if(this.count == 6)
				break;
			lines[7] = "|    --";
			if(this.count == 7)
				break;
			lines[7] = "|    --   --";
			lines[8] = "|___Game_Over!___";
			break;
		}
		for(int i = 0; i < lines.length; i++)
			System.out.println(lines[i]);
	}
	
	void playGame() {
		this.clearScreen();
		System.out.println("Welcome to Hangman Game!");
		System.out.println("Please Input Number:\n1. Start Game\n0.Exit");
		Scanner in = new Scanner(System.in);
		int choose = in.nextInt();
		if(choose == 0)
			return;
		
		this.chooseWord();
		while(true) {
			this.clearScreen();
			try {
				Thread.currentThread().sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.printHangman();
			this.displayWord();
			if(this.gameWon()) {
				System.out.println("Congratulation, you won the game!");
				this.gameOver();
				break;
			}
			else if(this.count == 8) {
				System.out.println("Sorry, you died.");
				this.gameOver();
				break;
			}
			System.out.println("Available Letters:" + this.availableList.toString());
			System.out.println("Correct Letters:" + this.correctList.toString());
			System.out.println("Wrong Letters:" + this.wrongList.toString());
			System.out.println("Please enter one letter from available letters:");
			char guess = in.next().charAt(0);
			this.handleGuess(guess);
		}
	}
	
	void displayWord() {
		for(int i = 0; i < this.finalWord.length(); i++) {
			char letter = this.finalWord.charAt(i);
			if(this.correctList.contains(letter))
				this.nowWordList[i] = letter;
		}
		this.nowWord = String.valueOf(this.nowWordList);
		System.out.println("Current Word Status: " + this.nowWord);
	}
	
	public static void clearScreen() {  
		Robot r;
		try {
			r = new Robot();
			r.mousePress(InputEvent.BUTTON3_MASK);
	        r.mouseRelease(InputEvent.BUTTON3_MASK);
	        r.keyPress(KeyEvent.VK_CONTROL); 
	        r.keyPress(KeyEvent.VK_R); 
	        r.keyRelease(KeyEvent.VK_R);
	        r.keyRelease(KeyEvent.VK_CONTROL); 
	        //r.delay(100);  
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ArrayList<String> words = new ArrayList<String>();
		words.add("Hello");
		words.add("World");
		words.add("Java");
		Hangman hangman = new Hangman(words);
		hangman.playGame();
	}
} 
