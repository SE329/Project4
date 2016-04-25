package snb_main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SNBWrapper {

	private JFrame gameFrame;
	private JLabel gamePanel;
	private SNBGameBoard board;
	private SNBPanel top;
	private JPanel bottom;
	private JPanel left;
	private JPanel right;
	
	public SNBWrapper(){
	
		gameFrame = new JFrame("Swap'N'Break");
		gameFrame.setSize(700, 700);
		
		setupGamePanel();
		
		gameFrame.add(gamePanel);
		
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
	}
	
	private void setupGamePanel(){
		
		gamePanel = new JLabel();
		
		ImageIcon icon = new ImageIcon("img/bg1.png");
		gamePanel.setIcon(icon);
		
		gamePanel.setSize(700, 700);
		gamePanel.setLayout( new BorderLayout() );
		
		gamePanel.setBackground(Color.MAGENTA);
		
		board = new SNBGameBoard();
		
		top = new SNBPanel(board);
		top.setNewGameListener(new NewGameListener());
		
		bottom = new JPanel();
		left = new JPanel();
		right = new JPanel();
		
		bottom.setOpaque(false);
		left.setOpaque(false);
		right.setOpaque(false);
		
		bottom.setPreferredSize( new Dimension(700, 50));
		left.setPreferredSize( new Dimension(50, 600));
		right.setPreferredSize( new Dimension(50, 600));
		
		gamePanel.add(board, BorderLayout.CENTER);
		gamePanel.add(top, BorderLayout.PAGE_START);
		gamePanel.add(bottom, BorderLayout.PAGE_END);
		gamePanel.add(left, BorderLayout.LINE_START);
		gamePanel.add(right, BorderLayout.LINE_END);
	}
	
	private class NewGameListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			gamePanel.remove(board);
			board = new SNBGameBoard();
			board.setScorePanel(top);
			top.setBoard(board);
			gamePanel.add(board, BorderLayout.CENTER);
			
			gameFrame.getContentPane().validate();
			gameFrame.getContentPane().repaint();
			//gamePanel.repaint();
			System.out.println("New Game");
		}
	}
	
}
