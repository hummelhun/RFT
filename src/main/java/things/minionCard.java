package things;

public class MinionCard extends Card{
	private int attackPower;
	private int healthPoint;
	
	public MinionCard(String cardName, int manaCost, String text, String fileName, int attackPower, int healthPoint) {
		super(cardName, manaCost, text, fileName);
		this.attackPower = attackPower;
		this.healthPoint = healthPoint;
	}


	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}

	public int getHealthPoint() {
		return healthPoint;
	}

	public void setHealthPoint(int healthPoint) {
		this.healthPoint = healthPoint;
	}
	
	

}
