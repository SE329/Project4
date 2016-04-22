package snb_main;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.JPanel;

public class SNBGameBoard extends JPanel {

	private static final int DEFAULT_BOARD_SIZE = 10;
	private static final int DEFAULT_NUM_GEMS = 8;

	private SNBGemSquare[][] board;

	private int numGemTypes;
	private int width;
	private int height;
	private Random rand;

	public SNBGameBoard() {

		this.numGemTypes = DEFAULT_NUM_GEMS;
		this.width = this.height = DEFAULT_BOARD_SIZE;
		this.rand = new Random();

		this.setLayout(new GridLayout(height, width, 2, 2));
		this.setBackground(Color.BLACK);
		board = new SNBGemSquare[height][width];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

				int cCode = rand.nextInt(this.numGemTypes);

				while (checkIfThreeDuringInit(board, j, i, width, height, cCode)) {
					cCode = rand.nextInt(this.numGemTypes);
				}

				board[i][j] = new SNBGemSquare(cCode, i * height + j);

				this.add(board[i][j]);
			}
		}
		
		this.addMouseListener(new BoardMouseListener());
	}

	/**
	 * During initialization, board squares are added from top left to bottom
	 * right. So, we just need to check the two squares above and two squares to
	 * the left to ensure that we do not initialize the board with a set of
	 * three.
	 * 
	 * @param board
	 *            The board to check.
	 * @param checkX
	 *            The x-coordinate to check.
	 * @param checkY
	 *            The y-coordinate to check.
	 * @param width
	 *            The width of the game board.
	 * @param height
	 *            The height of the game board.
	 * @param codeToCheck
	 *            The color code to check for.
	 * @return
	 */
	public static boolean checkIfThreeDuringInit(SNBGemSquare[][] board, int checkX, int checkY, int width, int height,
			int codeToCheck) {

		if (checkX >= width || checkY >= height) {
			return false;
		}

		if (checkX > 1) {

			if ((board[checkY][checkX - 1].getColorCode() == codeToCheck)
					&& (board[checkY][checkX - 2].getColorCode() == codeToCheck)) {
				return true;
			}
		}

		if (checkY > 1) {

			if ((board[checkY - 1][checkX].getColorCode() == codeToCheck)
					&& (board[checkY - 2][checkX].getColorCode() == codeToCheck)) {
				return true;
			}
		}

		return false;
	}
	
	public int removeAdjacent3sAndGetScore(){
		
		
		return 0;
	}

	private class BoardMouseListener implements MouseListener{

		private boolean isDrag;
		private int startX;
		private int startY;
		
		public BoardMouseListener(){
			this.isDrag = false;
		}
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			
			
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mousePressed(MouseEvent arg0) {
			
			if( !isDrag ){
				this.startX = arg0.getX();
				this.startY = arg0.getY();
				System.out.println("Start:  " + startX + ", " + startY);
				isDrag = true;
			}
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {
			
			if( isDrag ){
			
				int endX = arg0.getX();
				int endY = arg0.getY();
				
				System.out.println("End:  " + endX + ", " + endY);
				
				int gemWidth = board[0][0].getWidth();
				int gemHeight = board[0][0].getHeight();
				
				System.out.println("Gem Dimensions:  " + gemWidth + ", " + gemHeight);
				
				//int startIndX = (int) Math.floor(this.startX / gemWidth);
				//int startIndY = (int) Math.floor(this.startY / gemHeight);
				
				int startIndX = getBoardArrayIndex(startX, true);
				int startIndY = getBoardArrayIndex(startY, false);
				
				System.out.println("Start Index:  " + startIndX + ", " + startIndY);
				
				//int endIndX = (int) Math.floor(endX / gemWidth);
				//int endIndY = (int) Math.floor(endY / gemHeight);
				
				int endIndX = getBoardArrayIndex(endX, true);
				int endIndY = getBoardArrayIndex(endY, false);
				
				System.out.println("End Index:  " + endIndX + ", " + endIndY);
				
				if( startIndX < 0 || startIndY < 0 || endIndX < 0 || endIndY < 0 ){
					isDrag = false;
					System.out.println("Index out of bounds, Do Nothing");
					return;
				}
				
				if( (Math.abs( endIndX - startIndX ) + Math.abs( endIndY - startIndY )) != 1 ){
					isDrag = false;
					System.out.println("Non-Adjacent Selections");
					return;
				}
				
				int startCode = board[startIndY][startIndX].getColorCode();
				int endCode = board[endIndY][endIndX].getColorCode();
				
				board[startIndY][startIndX].setColorCode(endCode);
				board[endIndY][endIndX].setColorCode(startCode);
				
				isDrag = false;
			}
		}
		
		private int getBoardArrayIndex(int pixel, boolean isX){
			
			if( isX ){
				
				for( int i = 0, total = 0; i < width; i++ ){
					
					int next = total + board[0][i].getWidth();
					
					if( pixel >= total && pixel < next ){
						return i;
					}
					
					total = next;
				}
			}
			else{
				
				for( int i = 0, total = 0; i < height; i++ ){
					
					int next = total + board[i][0].getHeight();
					
					if( pixel >= total && pixel < next ){
						return i;
					}
					
					total = next;
				}
			}
			
			return -1;
			
		}
		
	}
}
