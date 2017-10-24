package things;

import java.util.List;

public class Player {
	private String name;
	private List<MinionCard> hand;
	private List<MinionCard> deck;	
	private int mana;
	private int actualMana;
	private int healtPoint;
	
	public Player(String name, List<MinionCard> hand, List<MinionCard> deck, int mana, int actualMana, int healthPoint) {
		super();
		this.name = name;
		this.hand = hand;
		this.deck = deck;
		this.mana = mana;
		this.actualMana = actualMana;
		this.healtPoint = healthPoint;
	}
	
	public void addCardToHand(MinionCard card) {
		hand.add(card);
	}
	public void removeCardFromDeck(int index) {
		deck.remove(index);
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

	public int getHealtPoint() {
		return healtPoint;
	}

	public void setHealtPoint(int healtPoint) {
		this.healtPoint = healtPoint;
	}

	public int getActualMana() {
		return actualMana;
	}

	public void setActualMana(int actualMana) {
		this.actualMana = actualMana;
	}
	
	

}
