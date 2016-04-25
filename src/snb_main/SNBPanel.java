
package snb_main;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SNBPanel extends JPanel
{

	private SNBGameBoard	board;
	private JButton			newGame;
	private JLabel			score;

	public SNBPanel(SNBGameBoard board)
	{
		this.board = board;
		board.setScorePanel(this);

		// this.setLayout(new GridLayout(1,5));

		JPanel tmp1 = new JPanel();
		tmp1.setPreferredSize(new Dimension(50, 40));
		this.add(tmp1);

		newGame = new JButton("New Game");
		newGame.setPreferredSize(new Dimension(100, 40));
		newGame.setOpaque(true);
		this.add(newGame);

		JPanel tmp2 = new JPanel();
		tmp2.setPreferredSize(new Dimension(350, 20));
		this.add(tmp2);

		score = new JLabel("Score:  " + board.getScore());
		score.setPreferredSize(new Dimension(100, 40));
		score.setOpaque(true);
		this.add(score);

		JPanel tmp3 = new JPanel();
		tmp3.setPreferredSize(new Dimension(50, 40));
		this.add(tmp3);

		this.setPreferredSize(new Dimension(700, 50));
	}

	public void updateScore()
	{
		score.setText("Score:  " + board.getScore());
	}

	public void setNewGameListener(ActionListener l)
	{
		newGame.addActionListener(l);
	}

	public void setBoard(SNBGameBoard board)
	{
		this.board = board;
		updateScore();
	}
}
