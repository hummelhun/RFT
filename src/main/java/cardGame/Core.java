package cardGame;

import java.util.ArrayList;
import java.util.List;

import things.MinionCard;
import things.Player;

public class Core {

	public Core() {	
	}
	
	public List<MinionCard> deck1 = new ArrayList<MinionCard>();
	public List<MinionCard> deck2 = new ArrayList<MinionCard>();
	public List<MinionCard> hand1 = new ArrayList<MinionCard>();
	public List<MinionCard> hand2 = new ArrayList<MinionCard>();
	public List<MinionCard> board1 = new ArrayList<MinionCard>();
	public List<MinionCard> board2 = new ArrayList<MinionCard>();
	public MinionCard bloodfenRaptor = new MinionCard("Bloodfen Raptor", 2, "", "Bloodfen_Raptor.png",3, 2, 0);
	public MinionCard murlocRaider = new MinionCard("Murloc Raider", 1, "", "Murloc_Raider.png", 2, 1, 0);
	public MinionCard riverCrocolisk = new MinionCard("River Crocolisk", 2, "", "River_Crocolisk.png", 2, 3, 0);		
	private Player player1;
	private Player player2;
	private int actualPlayer;
	
	
	public List<MinionCard> getDeck1() {
		return deck1;
	}

	public void setDeck1(List<MinionCard> deck1) {
		this.deck1 = deck1;
	}

	public List<MinionCard> getDeck2() {
		return deck2;
	}

	public void setDeck2(List<MinionCard> deck2) {
		this.deck2 = deck2;
	}

	public List<MinionCard> getHand1() {
		return hand1;
	}

	public void setHand1(List<MinionCard> hand1) {
		this.hand1 = hand1;
	}

	public List<MinionCard> getHand2() {
		return hand2;
	}

	public void setHand2(List<MinionCard> hand2) {
		this.hand2 = hand2;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public int getActualPlayer() {
		return actualPlayer;
	}

	public void setActualPlayer(int actualPlayer) {
		this.actualPlayer = actualPlayer;
	}

	
	public List<MinionCard> getBoard1() {
		return board1;
	}

	public void setBoard1(List<MinionCard> board1) {
		this.board1 = board1;
	}

	public List<MinionCard> getBoard2() {
		return board2;
	}

	public void setBoard2(List<MinionCard> board2) {
		this.board2 = board2;
	}

	public void startGame() {
		deck1.add(bloodfenRaptor);
		deck1.add(murlocRaider);
		deck1.add(bloodfenRaptor);
		deck1.add(riverCrocolisk);
		deck1.add(riverCrocolisk);
		deck1.add(murlocRaider);
		deck1.add(murlocRaider);
		deck1.add(bloodfenRaptor);
		deck1.add(bloodfenRaptor);
		deck1.add(riverCrocolisk);
		deck1.add(riverCrocolisk);
		
		deck2.add(bloodfenRaptor);
		deck2.add(murlocRaider);
		deck2.add(riverCrocolisk);
		deck2.add(murlocRaider);
		deck2.add(murlocRaider);
		deck2.add(bloodfenRaptor);
		deck2.add(bloodfenRaptor);
		deck2.add(riverCrocolisk);
		deck2.add(riverCrocolisk);
			
		player1 = new Player(0, "Bloody Steve", hand1, deck1, board1, 1, 1, 20);
		for (int i = 0; i < 3; i++) {		
	  	  player1.getHand().add(player1.getDeck().get(0));
		  player1.getDeck().remove(0);
		}
		player2 = new Player(1, "Noob Bob", hand2, deck2, board2, 0, 0, 25);
		for (int i = 0; i < 3; i++) {		
	  	  player2.getHand().add(player2.getDeck().get(0));
		  player2.getDeck().remove(0);
		}
		actualPlayer=0;
		
		
	}
}
