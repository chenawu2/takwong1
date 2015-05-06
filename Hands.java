package bridge;
import java.util.Random;

public class Hands {
	
	private Card[] hand;
	private int cardsInHand;
	
	public Hands(){
		hand = new Card[13];
		cardsInHand = 0;
	}
	
	public Card getCard(int a){
		//System.out.println("returning:"+hand[a]);
		return hand[a];
	}
	
	public int getCardsInHand(){
		return cardsInHand;
	}
	
	public void add(Card a){
		hand[cardsInHand] = a;
		cardsInHand ++;
	}

	public void removeCard(int a) {
		hand[a] = null;
		Card[] newHand = new Card[cardsInHand-1];
		int newIndex = 0;
		for (int i = 0; i < cardsInHand; i++) {
			if (hand[i] != null) {
				newHand[newIndex] = hand[i];
				newIndex++;
			}
		}
		hand = newHand;
		cardsInHand--;
	}

	public int cardAI(Card c) {
		int highestIndex = -1;
		int highestRank = -1;
		if (c != null) {
			String suite = c.getSuit();
			for (int i = 0; i < cardsInHand; i++) {
				if (hand[i] != null) {
					//System.out.println(hand[i].getSuit()+" "+suite);
					if (hand[i].getSuit().equals(suite)) {
						//System.out.println("eq:"+hand[i].getSuit()+" "+suite);
						if (hand[i].getRank() > highestRank) {
							highestIndex = i;
							highestRank = hand[i].getRank();
						}
					}
				}
			}
		}
		if (highestIndex == -1 ) {
			while (true) {
				Random random = new Random();
				int ranCard = random.nextInt(getCardsInHand());
				if (hand[ranCard] != null) {
					highestIndex = ranCard;
					break;
				}
			}
		}
		return highestIndex;
	}
	
	public String toString(){
		StringBuffer a = new StringBuffer();
		for(int i = 0; i < cardsInHand; i ++){
			if (hand[i] != null)
				a.append(hand[i].toString() + " ");
		}
		return a.toString();
	}

}
