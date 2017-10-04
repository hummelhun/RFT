package things;

public class Player {
	private String name;
	private MinionCard[] hand;
	private int mana;
	
	public Player(String name, MinionCard[] hand, int mana) {
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

	public MinionCard[] getHand() {
		return hand;
	}

	public void setHand(MinionCard[] hand) {
		this.hand = hand;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}
	
	

}
