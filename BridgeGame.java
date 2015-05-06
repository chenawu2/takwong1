package bridge;
import java.util.Scanner;

public class BridgeGame {
	public static void main(String args[]){
		
		//create cards
		for(int i = 0; i < 4; i ++){
			for(int j = 0; j < 13; j ++){
				Card cards = new Card(j,i);
			}
		}
		
		//create deck
		Deck gameDeck = new Deck();
		
		//shuffle
		gameDeck.shuffle();
		
		//create hands
		Hands[] hands = new Hands[4];
		
		hands[0] = new Hands();
		hands[1] = new Hands();
		hands[2] = new Hands();
		hands[3] = new Hands();
		
		//create pile
		Hands pile = new Hands();
		
		//load deck up with cards
		while(gameDeck.isEmpty() == false){
			for(int i = 0; i < 4; i++){
				hands[i].add(gameDeck.draw());
			}
		}
		
		//deal and assign hands
		Hands[] allPlayerHands = new Hands[4];
		Hands playerHand = hands[0];
		Hands compOneHand = hands[1];
		Hands compTwoHand = hands[2];
		Hands compThreeHand = hands[3];
		allPlayerHands[0] = playerHand;
		allPlayerHands[1] = compOneHand;
		allPlayerHands[2] = compTwoHand;
		allPlayerHands[3] = compThreeHand;

		Card lastPlayedCard = null;
		Card[] roundCards = new Card[4];
		int playerTeamScore = 0;
		int compTeamScore = 0;
		int roundWinner = 0;
		boolean hasFirstPlayed = false;
		while(playerHand.getCardsInHand() > 0){
			System.out.println("###### NEW ROUND START #######");
			resetRoundCards(roundCards);
			System.out.println("Player hand = " + playerHand);
			System.out.println("computer 1 hand = " + compOneHand);
			System.out.println("computer 2 hand = " + compTwoHand);
			System.out.println("computer 3 hand = " + compThreeHand);
			int player = roundWinner;
			while (!isFull(roundCards)) {
				if (player > 3)
					player = 0;
				if (!hasFirstPlayed) {
					if (player == 0)
						System.out.println("You go first!");
					else
						System.out.println("Computer " + player + " goes first!");
					lastPlayedCard = playerTurn(player,roundCards,null,allPlayerHands);
					hasFirstPlayed = true;
					player++;
				}
				else {
					playerTurn(player,roundCards,lastPlayedCard,allPlayerHands);
					player++;
				}
			}
			for (int i = 0; i < roundCards.length; i++)
				pile.add(roundCards[i]);
			System.out.println("Cards played are " + pile);
			roundWinner = winner(roundCards,lastPlayedCard);
			System.out.println("This is the highest card on the table:" + roundCards[roundWinner]);
			if (roundWinner == 0 || roundWinner == 2) 
				playerTeamScore++;
			else 
				compTeamScore++;
			System.out.println("Team You and Computer 2 Points:" + playerTeamScore);
			System.out.println("Team Computer 1 and Computer 3 Points:" + compTeamScore);

			pile = new Hands();
			
			lastPlayedCard = null;
			resetRoundCards(roundCards);
			hasFirstPlayed = false;
		}
		if (playerTeamScore > compTeamScore)
			System.out.println("Team You and Computer 2 win");
		else if (playerTeamScore < compTeamScore) 
			System.out.println("Team Computer 1 and Computer 3 win");
		else
			System.out.println("Tie!");
	}

	public static Card playerTurn(int i, Card[] roundCards, Card lastPlayedCard, Hands[] playerHands) {
		Card c = null;
		if (i == 0) {
			if (roundCards[0] == null) {				
				System.out.println("Which card would you like to play?");
				Scanner input = new Scanner(System.in);
				int z = input.nextInt();
				c = playerHands[0].getCard(z);
				playerHands[0].removeCard(z);
				roundCards[0] = c;
				System.out.println("You played " + c);
			}
		}
		else {
			if (roundCards[i] == null) {
				int potentialIndex = playerHands[i].cardAI(lastPlayedCard);
				c = playerHands[i].getCard(potentialIndex);
				playerHands[i].removeCard(potentialIndex);
				roundCards[i] = c;
				System.out.println("Computer "+i+" played " + c);
			}
		}
		return c;
	}

	public static void resetRoundCards(Card[] cards) {
		for (int i = 0; i < cards.length; i++)
			cards[i] = null;
	}
	public static boolean isFull(Card[] cards) {
		for (int i = 0; i < cards.length; i++) {
			if (cards[i] == null)
				return false;
		}
		return true;
	}
	public static int winner(Card[] cards, Card lastPlayedCard) {
		String suit = lastPlayedCard.getSuit();
		int winnerRank = -1;
		int winnerIndex = -1;
		for (int i = 0; i < cards.length; i++) {
			if (cards[i].getRank() > winnerRank && cards[i].getSuit().equals(suit)) {
				winnerRank = cards[i].getRank();
				winnerIndex = i;
			}
		}
		return winnerIndex;
	}
}

