package snb_main;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;

public class SNBGameBoard extends JPanel{

	private static final int DEFAULT_BOARD_SIZE = 10;
	private static final int DEFAULT_NUM_GEMS = 8;
	
	private SNBGemSquare[][] board;

	private int numGemTypes;
	private int width;
	private int height;
	private Random rand;
	
	public SNBGameBoard(){
		
		this.numGemTypes = DEFAULT_NUM_GEMS;
		this.width = this.height = DEFAULT_BOARD_SIZE;
		this.rand = new Random();
		
		this.setLayout( new GridLayout( height, width, 2, 2 ));
		this.setBackground(Color.BLACK);
		board = new SNBGemSquare[height][width];
		
		for( int i = 0; i < height; i++ ){
			for( int j = 0; j < width; j++ ){
				
				int cCode = rand.nextInt(this.numGemTypes);
				
				while( checkIfThreeDuringInit(board, j, i, width, height, cCode)){
					cCode = rand.nextInt(this.numGemTypes);
				}
				
				board[i][j] = new SNBGemSquare(cCode, i*height + j);
				board[i][j].addMouseListener(new GemListener());
				this.add(board[i][j]);
			}
		}
	}
	
	public static boolean checkIfThreeDuringInit( SNBGemSquare[][] board, int checkX, int checkY, int width, int height, int codeToCheck ){
		
		if( checkX >= width || checkY >= height ){
			return false;
		}
		
		if( checkX > 1 ){
			
			if( (board[checkY][checkX-1].getColorCode() == codeToCheck) && 
					(board[checkY][checkX-2].getColorCode() == codeToCheck)){
				return true;
			}
		}
		
		if( checkY > 1 ){
			
			if( (board[checkY-1][checkX].getColorCode() == codeToCheck) && 
					(board[checkY-2][checkX].getColorCode() == codeToCheck)){
				return true;
			}
		}
		
		return false;
	}
	
	private class GemListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
		/*
			int boardId = ((SNBGemSquare)(arg0.getComponent())).getId();
			
			int i = 0;
			int j = 0;
			
			outerloop:
			for( i = 0; i < height; i++ ){
				for( j = 0; j < width; j++ ){
					
					if( board[i][j].getId() == boardId ){
						break outerloop;
					}
				}
			}
			
			board[i][j].setBackground(Color.BLACK);
			board[i][j].repaint();
		*/	
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {	
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		}

		@Override
		public void mousePressed(MouseEvent arg0) {	
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
		
			
			int boardId = ((SNBGemSquare)(arg0.getComponent())).getId();
			
			int i = 0;
			int j = 0;
			
			outerloop:
			for( i = 0; i < height; i++ ){
				for( j = 0; j < width; j++ ){
					if( board[i][j].getId() == boardId ){
						break outerloop;
					}
				}
			}
			
			int oldX = board[i][j].getLocationOnScreen().x;
			int oldY = board[i][j].getLocationOnScreen().y;
			
			int dx = arg0.getX();
			int dy = arg0.getY();
			
			int newX = oldX + dx;
			int newY = oldY + dy;
			
			int dimy = board[i][j].getSize().height;
			int dimx = board[i][j].getSize().width;
			
			SNBGemSquare tmp = board[i][j];
			
			if( Math.abs(dy) < Math.abs(dx)){
				
				if( dx > 0 && (newX > (oldX + dimx))){
					board[i][j] = board[i][j+1];
					board[i][j+1] = tmp;
					board[i][j].repaint();
					board[i][j+1].repaint();
				}
				else if( dx < 0 && (newX < (oldX))){
					board[i][j] = board[i][j-1];
					board[i][j-1] = tmp;	
					board[i][j].repaint();
					board[i][j-1].repaint();
				}
			}
			else{
				if( dy > 0 && (newY > (oldY + dimy))){
					board[i][j] = board[i+1][j];
					board[i+1][j] = tmp;
					board[i][j].repaint();
					board[i+1][j].repaint();
				}
				else if( dy < 0 && (newY < (oldY))){
					board[i][j] = board[i-1][j];
					board[i-1][j] = tmp;
					board[i][j].repaint();
					board[i-1][j].repaint();
				}				
			}
		}
		
	}
}
