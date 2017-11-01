package things;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private int id;
	private String name;
	private List<MinionCard> hand;
	private List<MinionCard> deck;
	public  List<MinionCard> board = new ArrayList<MinionCard>();
	private int mana;
	private int actualMana;
	private int healtPoint;
	
	public Player(int id, String name, List<MinionCard> hand, List<MinionCard> deck, List<MinionCard> board, int mana, int actualMana, int healthPoint) {
		super();
		this.id= id;
		this.name = name;
		this.hand = hand;
		this.deck = deck;
		this.board = board;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<MinionCard> getBoard() {
		return board;
	}

	public void setBoard(List<MinionCard> board) {
		this.board = board;
	}
	
	

}
