package things;

public class minionCard {

	private String cardName;
	private int manaCost;
	private String text;
	private int attackPower;
	private int healthPower;
	
	
	
	
	
	
	
	
	
	
	

	public minionCard(String cardName, int manaCost, String text, int attackPower, int healthPower) {
		this.cardName = cardName;
		this.manaCost = manaCost;
		this.text = text;
		this.attackPower = attackPower;
		this.healthPower = healthPower;
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

	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}

	public int getHealthPower() {
		return healthPower;
	}

	public void setHealthPower(int healthPower) {
		this.healthPower = healthPower;
	}

}
