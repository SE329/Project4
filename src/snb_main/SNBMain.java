package snb_main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SNBMain {
	
	public static void main(String[] args) {
		
		JFrame f = new JFrame("Swap'N'Break");
		f.setSize(700, 700);
		f.setLayout( new BorderLayout() );
		
		SNBGameBoard board = new SNBGameBoard();
		board.setPreferredSize( new Dimension(600, 600));
		
		JPanel top = new SNBPanel(board);
		JPanel bottom = new JPanel();
		JPanel left = new JPanel();
		JPanel right = new JPanel();
		
		top.setPreferredSize( new Dimension(700, 50));
		bottom.setPreferredSize( new Dimension(700, 50));
		left.setPreferredSize( new Dimension(50, 600));
		right.setPreferredSize( new Dimension(50, 600));
		
		f.add(top, BorderLayout.PAGE_START);
		f.add(bottom, BorderLayout.PAGE_END);
		f.add(left, BorderLayout.LINE_START);
		f.add(right, BorderLayout.LINE_END);
		
		f.add(board, BorderLayout.CENTER);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
