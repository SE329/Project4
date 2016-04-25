
package snb_main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

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
		tmp1.setOpaque(false);
		this.add(tmp1);

		newGame = new JButton("New Game");
		newGame.setPreferredSize(new Dimension(200, 40));
		newGame.setOpaque(true);
		newGame.setFont(new Font("Arial", Font.BOLD, 20));
		newGame.setBackground(Color.DARK_GRAY);
		newGame.setForeground(Color.YELLOW);
		newGame.setBorder( new LineBorder(Color.LIGHT_GRAY, 3));
		newGame.setFocusPainted(false);
		this.add(newGame);

		JPanel tmp2 = new JPanel();
		tmp2.setPreferredSize(new Dimension(200, 20));
		tmp2.setOpaque(false);
		this.add(tmp2);

		score = new JLabel("Score:  " + board.getScore());
		score.setPreferredSize(new Dimension(200, 40));
		score.setOpaque(false);
		score.setFont(new Font("Arial", Font.BOLD, 20));
		score.setForeground(Color.YELLOW);
		this.add(score);

		JPanel tmp3 = new JPanel();
		tmp3.setPreferredSize(new Dimension(50, 40));
		tmp3.setOpaque(false);
		this.add(tmp3);
		
		this.setOpaque(false);
		
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
