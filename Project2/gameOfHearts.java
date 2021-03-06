package game;

class Card {
	private int num, suit;
	Card(int num, int suit) {
		this.num = num;
		this.suit = suit;
	}
	int getNum() {
		return this.num;
	}
	int getSuit() {
		return this.suit;
	}
	void display() {
		String num, suit;
		switch(this.suit) {
		case 0:
			suit = "clubs";
			break;
		case 1:
			suit = "diamonds";
			break;
		case 2:
			suit = "hearts";
			break;
		case 3:
			suit = "spades";
			break;
		default:
			suit = "clubs";
			break;
		}
		switch(this.num) {
		default:
			num = String.valueOf(this.num);
			break;
		case 11:
			num = "Jack";
			break;
		case 12:
			num = "Queen";
			break;
		case 13:
			num = "King";
			break;
		case 14:
			num = "Ace";
			break;
		}
		System.out.println(num + " of " + suit);
	}
}

class groupOfCards {
	private Card[] cards;
	private int currentSize;
	groupOfCards (int num) {
		this.cards = new Card[num];
		this.currentSize = 0;
	}
	public int getCurrentSize() {
		return this.currentSize;
	}
	public Card getCard(int i) {
		if(i >= this.getCurrentSize())
			return null;
		return this.cards[i];
	}
	public void addCard(Card card) {
		if(this.getCurrentSize() == this.cards.length)
			return;
		this.cards[this.getCurrentSize()] = card;
		this.currentSize += 1;
	}
	public Card removeCard(int index) {
		if(index >= this.getCurrentSize())
			throw new IllegalArgumentException("Index not available");
		int suit = this.cards[index].getSuit();
		int num = this.cards[index].getNum();
		for(int i = index; i < this.getCurrentSize() - 1; i ++)
			this.cards[i] = this.cards[i + 1];
		this.cards[this.getCurrentSize() - 1] = null;
		this.currentSize -= 1;
		return new Card(num, suit);
	}
	public void display() {
		for(int i = 0; i < this.getCurrentSize(); i++) {
			this.cards[i].display();
		}
	}
}

class Deck extends groupOfCards {
	final int TOTAL_CARDS = 52;
	public Deck() {
		super(52);
		for(int suit = 0; suit < 4; suit ++)
			for(int num = 2; num < 15; num ++)
				this.addCard(new Card(num, suit));
	}
	public void shuffle() {
		int unshuffled = this.getCurrentSize();
		while(unshuffled > 0) {
			int index = (int)(Math.random() * (this.getCurrentSize() - 1));
			this.addCard(this.removeCard(index));
			unshuffled --;
		}
	}
	public Card dealCard() {
		if(this.getCurrentSize() > 0)
			return this.removeCard(0);
		throw new IllegalArgumentException("There are no cards");
	}
}

class Trick extends groupOfCards {
	private int winner;
	private boolean heart;
	private boolean queen;
	private Card winningCard;

	public Trick(int players) {
		super(2 * players - 1);
		this.heart = false;
		this.queen = false;
    }

	public int getWinner() {
		return this.winner;
	}

	public Card getWinningCard() {
		return this.winningCard;
	}

	public boolean getHearts() {
		return this.heart;
	}

	public boolean getQueen() {
		return this.queen;
	}

	public void setWinningCard(int num, int suit) {
		this.winningCard = new Card(num, suit);
	}

	private boolean isWinner(Card card) {
		if (this.winningCard == null)
			return true;
		else if (this.winningCard.getSuit() == card.getSuit() && card.getNum() > this.winningCard.getNum()) {
			return true;
		}
		return false;
	}

	public void update(Card card, int index) {
		if(isWinner(card)) {
			setWinningCard(card.getNum(), card.getSuit());
			this.winner = index;
		}
		if(card.getSuit() == 2) {
			this.heart = true;
		}
		if(card.getSuit() == 3 && card.getNum() == 12) {
			this.queen = true;
		}
	}
}

class Hand extends groupOfCards {
	int NUM;
	int shortest;
	
	public Hand(int playerNum, int numberOfCards) {
		super(numberOfCards);
		this.NUM = playerNum;
	}
	
	public void sort() {
		if(this.getCurrentSize() == 0)
			return;
		int unsorted = this.getCurrentSize() - 1;
		while(unsorted >= 0) {
			int min_value = Integer.MAX_VALUE;
			int index = 0;
			for(int i = 0; i <= unsorted; i ++) {
				int curr_value = this.getCard(i).getSuit() * 13 + this.getCard(i).getNum();
				if(curr_value < min_value) {
					min_value = curr_value;
					index = i;
				}
			}
			this.addCard(this.removeCard(index));
			unsorted --;
		}
	}
	
	public void setShortest() {
		int Clubs = 0, Diamonds = 0, Hearts = 0, Blades = 0;
		for(int i = 0; i < getCurrentSize(); i++) {
			Card card = getCard(i);
			switch(card.getSuit()) {
				case(0): 
					Clubs++;
					break;
				case(1): 
					Diamonds++;
					break;
				case(2): 
					Hearts++;
					break;
				case (3):
					Blades++;
					break;
				default: 
					break;
			}
		}
		this.shortest = 0;

		if ((Diamonds <= Clubs || Clubs == 0))
			this.shortest = 1;
		if ((Blades <= Diamonds || Diamonds == 0) && find(12, 3) == -1 && find(13, 3) == -1 && find(14, 3) == -1)
			this.shortest = 3;
		if (Blades == 0)
			this.shortest = 2;
	}
	
	public int find(int num, int suit) {
		for(int i = 0; i < getCurrentSize(); i++) {
			if(getCard(i).getNum() == num && getCard(i).getSuit() == suit)
				return i;
		}
		return -1;
	}
	
	public int getShortest() {
		return this.shortest;
	}

	public Card playACard(Game game, Trick trick) {
		int index;
		this.setShortest();
		if (trick.getCurrentSize() == 0 && (index = findHighest(shortest)) >= 0);
		else if (trick.getCurrentSize() == game.PLAYERS - 1 && !trick.getHearts() && !trick.getQueen() && (index = findHighest(shortest)) >= 0);
		else if (trick.getWinningCard() != null && (index = findHighestBelow(trick.getWinningCard())) >= 0);
		else if (trick.getWinningCard() != null && (index = findMiddleHigh(game, trick.getWinningCard().getSuit())) >= 0);
		else if ((index = getIndex(12, 3)) >= 0); // queen of Spades
		else if ((index = getIndex(14, 3)) >= 0); // Ace of Spades
		else if ((index = getIndex(13, 3)) >= 0); // King of Spades
		else if ((index = findHighest(2)) >= 0); // heart
		else {
			index = findHighest();
		}
		Card removed_card = removeCard(index);
		trick.update(removed_card, NUM);
		game.updateHeartsAndQueen(removed_card);

		return removed_card;
	}

	public int findLowest(int suit) {
		if (count(suit) == 0)
			return -1;
		int lowest = 20;
		for (int i = 0; i < getCurrentSize(); i++) {
			if (getCard(i).getSuit() == suit) {
				lowest = Math.min(lowest, getCard(i).getNum());
			}
		}
		return lowest;

	}

	public int count(int suit) {
		int count = 0;
		for(int i = 0; i < getCurrentSize(); i++) {
			if (getCard(i).getSuit() == suit) {
				count++;
			}
		}
		return count;
	}

	public int getIndex(int num, int suit) {
		for (int i = 0; i < getCurrentSize(); i++) {
			if (getCard(i).getNum() == num && getCard(i).getSuit() == suit) {
				return i;
			}
		}
		return -1;
	}

	public int findHighest(int suit) {
		if (count(suit) == 0) return -1;

		int highest = 0;
		int index = -1;
		for (int i = 0; i < getCurrentSize(); i++) {
			if (getCard(i).getSuit() == suit) {
				highest = Math.max(highest, getCard(i).getNum());
				index = i;
			}
		}
		return index;
	}

	public int findLowest(Game game) {
		int lowest = 20;
		for (int i = 0; i < getCurrentSize(); i++) {
			Card temp = getCard(i);
			if (game.getHearts() || temp.getSuit() != 2) {
				lowest = Math.min(temp.getNum(), lowest);
			}
		}
		return lowest;
	}

	public int findLastHigh(int suit) {
		int highest = findHighest(suit);
		if (highest == -1)
			return -1;
		if (suit == 3 && getCard(highest).getNum() == 12) {
			int index = getIndex(12, 3);
			if (index == 0) return highest;
			if (getCard(index - 1).getSuit() == suit)
				return index - 1;
			else
				return highest;
		}
		return highest;
	}

	public int findHighestBelow(Card winningCard) {
		int suit = winningCard.getSuit();
		int highest = findHighest(suit);
		int i = getIndex(highest, suit);
		while (highest > winningCard.getNum()) {
			i--;
			if (i < 0) return -1;
			if (getCard(i).getSuit() != suit)
				return -1;
			highest = getCard(i).getNum();
		}
		return i;
	}

	public int findMiddleHigh(Game game, int suit) {
		if(suit == 3 && !game.getQueenOfSpades()) {
			return findHighestBelow(new Card(11,3));
		}
		int max= Integer.MIN_VALUE;
		for (int i = 0; i < getCurrentSize(); i++) {
			if (getCard(i) == null) {
				break;
			}
			if (getCard(i).getSuit() != suit) {
				continue;
			}
			max=Math.max(max, getCard(i).getNum());
		}
		if(max > Integer.MIN_VALUE)
			return this.find(max, suit);
		return -1;
	}


	public int findHighest() {
		int index = -1;
		int max = 0;
		for (int i = 0; i < getCurrentSize(); i++) {
			if (getCard(i).getNum() > max) {
				max = getCard(i).getNum();
				index = i;
			}
		}
		return index;
	}

}

class Game {
	public final int PLAYERS;
	private Deck deck;
	private Hand[] players;
	private Trick[] tricks;
	private int numOfTricks;
	private boolean hearts = false;
	private boolean queenOfSpades = false;

	Game(int numOfPlayers) {
		deck = new Deck();
		this.PLAYERS = numOfPlayers;
		if (numOfPlayers < 3 || numOfPlayers > 6)
			throw new IllegalArgumentException("Number of players should be between 3 and 6");
		players = new Hand[numOfPlayers];
		int numOfCards = 52 / numOfPlayers;
		for(int i = 0; i < numOfPlayers; i++) {
			players[i] = new Hand(i, numOfCards);
		}
		tricks = new Trick[numOfCards];

	}

	public int getNumOfTricks() {
		return numOfTricks;
	}

	public boolean getHearts() {
		return hearts;
	}

	public boolean getQueenOfSpades() {
		return queenOfSpades;
	}

	public void playAGame(){
		deck.shuffle();
		int cardsLeft = 52 % PLAYERS;
		for (int i = 0; i < 52 / PLAYERS; i++) {
			for (int j = 0; j < PLAYERS; j++) {
				players[j].addCard(deck.dealCard());
			}
		}
		for (Hand player : players) {
			player.sort();
			player.setShortest();
		}

		int lowestClub = 20;
		int playNum = -1;
		for (int i = 0; i < PLAYERS; i++) {
			System.out.println();
			System.out.println("        Player " + i + " shortest = " + players[i].getShortest());
			for (int j = 0; j <players[i].getCurrentSize(); j++) {
				Card card = players[i].getCard(j);
				card.display();
				if (card.getSuit() == 0) {
					if (card.getNum() < lowestClub) {
						playNum = i;
						lowestClub = card.getNum();
					}
				}
			}
		}
		System.out.println();
		for (int i = 0; i < tricks.length; i++) {
			System.out.println();
			System.out.println("	       Round " + (i+1));
			tricks[i] = new Trick(PLAYERS);
			int index = playNum;
			for (int j = 0; j < PLAYERS; j++) {
				Card card = new Card(0, 0);
				if (i == 0 && j == 0) {
					card = players[index].removeCard(0);
				} else {
					card = players[index].playACard(this, tricks[i]);
				}
				tricks[i].addCard(card);
				tricks[i].update(card, index);
				System.out.print("Player " + index + "		  ");
				card.display();
				updateHeartsAndQueen(card);

				if (++index == PLAYERS) index = 0;
			}
			if (i == 0) {
				for (int n = 0; n < cardsLeft; n++ ) {
					Card c = deck.dealCard();
					tricks[i].addCard(c);
					System.out.print("Undelt Card " + index + "	 ");
					c.display();
					updateHeartsAndQueen(c);
				}
			}
			playNum = tricks[i].getWinner();

		}
		System.out.println();
		for (int i = 0; i < PLAYERS; i ++) {
			System.out.println("Player " + i + ": score = " + computePoints(i));
		}
	}

	public void updateHeartsAndQueen(Card card) {
		if (hearts == false) {
			if (card.getSuit() == 2) {
				hearts = true;
				System.out.println("Hearts is now broken.");
			}
		}
		if (card.getSuit() == 3 && card.getNum() == 12) {
			queenOfSpades = true;
		}

	}

	public int computePoints(int playerNum) {
		int points = 0;
		for(Trick t : tricks) {
			if (t.getWinner() == playerNum) {
				for (int i = 0; i < t.getCurrentSize(); i++) {
				   if (t.getCard(i).getSuit() == 2) points++;
				   if (t.getCard(i).getSuit() == 3 && t.getCard(i).getNum() == 12) points += 13;
				}
			}
		}
		return points;
	}
}

