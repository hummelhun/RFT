package things;

public class spellCard{
	
	private String cardName;
	private int manaCost;
	private String text;
	
	
	
	
	
	
	
	
	
	
	
	
	
	public spellCard(String cardName, int manaCost, String text) {
		this.cardName = cardName;
		this.manaCost = manaCost;
		this.text = text;
	}
	
	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public int getManaCost() {
		return manaCost;
	}

	public void setManaCost(int manaCost) {
		this.manaCost = manaCost;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
