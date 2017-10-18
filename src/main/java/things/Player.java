package things;

import java.util.List;

public class Player {
	private String name;
	private List<MinionCard> hand;
	private List<MinionCard> deck;	
	private int mana;
	
	public Player(String name, List<MinionCard> hand, int mana) {
		super();
		this.name = name;
		this.hand = hand;
		this.mana = mana;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MinionCard> getHand() {
		return hand;
	}

	public void setHand(List<MinionCard> hand) {
		this.hand = hand;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public List<MinionCard> getDeck() {
		return deck;
	}

	public void setDeck(List<MinionCard> deck) {
		this.deck = deck;
	}
	
	

}
