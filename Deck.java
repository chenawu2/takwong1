package bridge;
import java.util.Random;
//asdf
public class Deck{
	private Card[] myDeck;
	private int next;
	
	public Deck(){
		myDeck = new Card[52];
		int k = 0;
		for(int i = 0; i < 4; i ++){
			for(int j = 0; j < 13; j ++){
				myDeck[k] = new Card(j,i);
				k ++;
			}
		}
		next = 0;
	}
	
	public int cardsInDeck(){
		return myDeck.length - next;
	}
	
	public boolean isEmpty(){
		if (cardsInDeck() == 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public Card draw(){
		Card a = null;
		if(isEmpty() == false){
			a = myDeck[next];
			next ++ ;
		}
		return a;
	}
	
	public void shuffle(){
		Random random = new Random();
		
		for(int i = 0; i < myDeck.length; i ++){
			int a = random.nextInt(myDeck.length);
			Card b = myDeck[a];
			myDeck[a] = myDeck[i];
			myDeck[i] = b;
		}
	}
	public int numberOfCards(){
		return myDeck.length - next;
	}
}
