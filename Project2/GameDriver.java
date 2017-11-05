package game;
import java.util.Scanner;

public class GameDriver {

	public static void main(String[] args) {
		while(true) {
			Scanner input=new Scanner(System.in);
			System.out.println("Please input the number of players (3-6):");
			int n = input.nextInt();
			Game a =new Game(n);
			a.playAGame();
			System.out.println("\nPlay another game (y/n)?");
			char isContinue = input.next().charAt(0);
			if (isContinue == 'n')
			    return;
			if (isContinue == 'y')
				continue;
		}

	}

}
