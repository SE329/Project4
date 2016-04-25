package snb_main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SNBWrapper {

	private JFrame f;
	private SNBGameBoard board;
	private SNBPanel top;
	private JPanel bottom;
	private JPanel left;
	private JPanel right;
	
	public SNBWrapper(){
		
		f = new JFrame("Swap'N'Break");
		f.setSize(700, 700);
		f.setLayout( new BorderLayout() );
		
		board = new SNBGameBoard();
		
		top = new SNBPanel(board);
		top.setNewGameListener(new NewGameListener());
		
		bottom = new JPanel();
		left = new JPanel();
		right = new JPanel();
		
		bottom.setPreferredSize( new Dimension(700, 50));
		left.setPreferredSize( new Dimension(50, 600));
		right.setPreferredSize( new Dimension(50, 600));
	
		setupFrame();
	}
	
	private void setupFrame(){
		f.add(board, BorderLayout.CENTER);
		f.add(top, BorderLayout.PAGE_START);
		f.add(bottom, BorderLayout.PAGE_END);
		f.add(left, BorderLayout.LINE_START);
		f.add(right, BorderLayout.LINE_END);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	private class NewGameListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			board = new SNBGameBoard();
			top = new SNBPanel(board);
			top.setNewGameListener(this);
			board.setScorePanel(top);
			setupFrame();
			//board.getParent().repaint();
			//System.out.println("New Game");
		}
	}
	
}
