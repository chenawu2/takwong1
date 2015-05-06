package bridge;
public class Card{
	private static String[] suits = {"S","H","C","D"};
	private static String[] faces = {"J","Q","K","A"};
	
	private int rank;
	private int suit;
	
	public String getSuit(){
		return suits[suit];
	}
	
	public int getRank(){
		return rank;
	}
	
	public boolean isFace(){
		if(rank == 12 || rank == 11 || rank == 10 || rank == 9){
			return true;
		}
		else{
			return false;
		}
	}
	
	public Card(int r, int s){
		rank = r;
		suit = s;
	}
	
	public String toString(){
		String Ranking = "";
		if (isFace()){
			Ranking = faces[(rank + 4) % 13];
		}
		else {
			Ranking = String.valueOf(rank + 1);
		}
		return Ranking + suits[suit];
		}
	
	public static void main(String args[]){
		for(int i = 0; i < 4; i ++){
			for(int j = 0; j < 13; j ++){
				Card cards = new Card(j,i);
				System.out.println(cards + "\t");
			}
		}
		
	}
}