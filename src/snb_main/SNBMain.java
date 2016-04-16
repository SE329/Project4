package snb_main;

import javax.swing.JFrame;

public class SNBMain {

	public static void main(String[] args) {
		
		JFrame f = new JFrame("Swap'N'Break");
		f.setSize(600, 600);
		
		SNBGameBoard board = new SNBGameBoard();
		f.add(board);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

}
