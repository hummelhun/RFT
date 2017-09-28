package things;

public class DummyMain {

	public static void main(String[] args) {
		MinionCard card1 = new MinionCard("Novice Enginier", 2, "Draw a card", 1, 1);
		System.out.println(card1.getCardName());
		SpellCard card2 = new SpellCard("Arcane Intellect", 3, "Draw 2 card");
		System.out.println(card2.getCardName());
		
	}

}
