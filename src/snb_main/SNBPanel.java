package snb_main;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SNBPanel extends JPanel{

	private SNBGameBoard board;
	private JButton newGame;
	private JLabel score;
	
	public SNBPanel(SNBGameBoard board){
		this.board = board;
		board.setScorePanel(this);
		
		newGame = new JButton("New Game");
		newGame.setPreferredSize(new Dimension(100, 40));
		newGame.setOpaque(true);
		this.add(newGame);
		
		score = new JLabel("Score:  " + board.getScore());
		score.setPreferredSize(new Dimension(100, 40));
		score.setOpaque(true);
		this.add(score);
	}
	
	public void updateScore(){
		score.setText("Score:  " + board.getScore());
	}
}
