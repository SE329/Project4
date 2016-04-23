
package snb_main;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.JPanel;

public class SNBGameBoard extends JPanel
{

	private static final int	DEFAULT_BOARD_SIZE	= 10;
	private static final int	DEFAULT_NUM_GEMS	= 8;

	private SNBGemSquare[][]	board;

	private int					numGemTypes;
	private int					width;
	private int					height;
	private Random				rand;
	private int					score;

	public SNBGameBoard()
	{

		this.numGemTypes = DEFAULT_NUM_GEMS;
		this.width = this.height = DEFAULT_BOARD_SIZE;
		this.rand = new Random();

		this.setLayout(new GridLayout(height, width, 2, 2));
		this.setBackground(Color.BLACK);
		board = new SNBGemSquare[height][width];

		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{

				int cCode = rand.nextInt(this.numGemTypes);

				while (checkIfThreeDuringInit(board, j, i, width, height, cCode))
				{
					cCode = rand.nextInt(this.numGemTypes);
				}

				board[i][j] = new SNBGemSquare(cCode, i * height + j);

				this.add(board[i][j]);
			}
		}

		this.addMouseListener(new BoardMouseListener());

		// printColorCodeArray();
	}

	public boolean hasLineAroundPoint(int x, int y)
	{

		if (x < 0 || x >= this.width || y < 0 || y >= this.height)
			return false;

		if (x - 2 >= 0 && board[y][x - 2].getColorCode() == board[y][x - 1].getColorCode()
				&& board[y][x - 2].getColorCode() == board[y][x].getColorCode())
			return true;
		else if (x - 1 >= 0 && x + 1 < this.width
				&& board[y][x - 1].getColorCode() == board[y][x].getColorCode()
				&& board[y][x - 1].getColorCode() == board[y][x + 1].getColorCode())
			return true;
		else if (x + 2 < this.width && board[y][x].getColorCode() == board[y][x + 2].getColorCode()
				&& board[y][x].getColorCode() == board[y][x + 1].getColorCode())
			return true;

		if (y - 2 >= 0 && board[y][x].getColorCode() == board[y - 1][x].getColorCode()
				&& board[y][x].getColorCode() == board[y - 2][x].getColorCode())
			return true;
		else if (y - 1 >= 0 && y + 1 < this.height
				&& board[y - 1][x].getColorCode() == board[y][x].getColorCode()
				&& board[y + 1][x].getColorCode() == board[y][x].getColorCode())
			return true;
		else if (y + 2 < this.height && board[y][x].getColorCode() == board[y + 1][x].getColorCode()
				&& board[y][x].getColorCode() == board[y + 2][x].getColorCode())
			return true;

		return false;
	}

	/**
	 * During initialization, board squares are added from top left to bottom right. So, we just
	 * need to check the two squares above and two squares to the left to ensure that we do not
	 * initialize the board with a set of three.
	 * 
	 * @param board
	 * The board to check.
	 * @param checkX
	 * The x-coordinate to check.
	 * @param checkY
	 * The y-coordinate to check.
	 * @param width
	 * The width of the game board.
	 * @param height
	 * The height of the game board.
	 * @param codeToCheck
	 * The color code to check for.
	 * @return
	 */
	public static boolean checkIfThreeDuringInit(SNBGemSquare[][] board, int checkX, int checkY,
			int width, int height, int codeToCheck)
	{

		if (checkX >= width || checkY >= height)
			return false;

		if (checkX > 1)
		{

			if ((board[checkY][checkX - 1].getColorCode() == codeToCheck)
					&& (board[checkY][checkX - 2].getColorCode() == codeToCheck))
				return true;
		}

		if (checkY > 1)
		{

			if ((board[checkY - 1][checkX].getColorCode() == codeToCheck)
					&& (board[checkY - 2][checkX].getColorCode() == codeToCheck))
				return true;
		}

		return false;
	}

	public int removeLinesAndGetScore()
	{

		// removeLinesAndGetScoreHorizontal( getColorCodeArray() );

		return 0;
	}

	private void collapseAndFill()
	{

		for (int i = 0; i < width; i++)
		{
			for (int j = height - 1; j > 0; j--)
			{

				if (board[j][i].getColorCode() == -1)
				{

					int k = 1;
					while (k <= j && board[j - k][i].getColorCode() == -1)
					{
						k++;
					}

					if (j - k == -1)
					{
						k--;
					}

					board[j][i].setColorCode(board[j - k][i].getColorCode());
					board[j - k][i].setColorCode(-1);
				}
			}
		}

		for (int row = 0; row < board.length; ++row)
		{
			for (int col = 0; col < board[0].length; ++col)
			{
				if (board[row][col].getColorCode() == -1)
				{
					int cCode = rand.nextInt(numGemTypes);
					board[row][col].setColorCode(cCode);
				}
			}
		}

	}

	private int removeLinesAndGetScoreAroundPoint(int x, int y)
	{

		if (x < 0 || y < 0 || x >= width || y >= height)
			return 0;

		int score = removeLinesAndGetScoreAroundPointHorizontal(x, y);
		score += removeLinesAndGetScoreAroundPointVertical(x, y);

		if (score > 0)
		{
			board[y][x].setColorCode(-1);
		}

		collapseAndFill();

		return score;
	}

	private int removeLinesAndGetScoreAroundPointHorizontal(int x, int y)
	{

		int score = 0;
		int colorCode = board[y][x].getColorCode();
		int i, j;

		// Negative direction X
		for (i = 1; x - i >= 0; i++)
		{
			if (board[y][x - i].getColorCode() != board[y][x].getColorCode())
			{
				i--;
				break;
			}
		}

		// Correct for lower bound
		if (x - i == -1)
		{
			i--;
		}

		j = i;
		// Positive Direction X
		for (i = 1; x + i < width; i++)
		{
			if (board[y][x + i].getColorCode() != board[y][x].getColorCode())
			{
				i--;
				break;
			}
		}

		// Correct for upper bound
		if (x + i == width)
		{
			i--;
		}

		// If more than three in a row, then get the score and delete the line
		if (i + j + 1 >= 3)
		{
			for (int ind = x - j; ind < x + i + 1; ind++)
			{

				if (ind != x)
				{
					board[y][ind].setColorCode(-1);
				}
			}

			score += getScoreForLineOfLengthN(colorCode, i + j);
		}
		return score;
	}

	private int removeLinesAndGetScoreAroundPointVertical(int x, int y)
	{

		int score = 0;
		int colorCode = board[y][x].getColorCode();
		int i, j;

		// Negative direction Y
		for (i = 1; y - i >= 0; i++)
		{
			if (board[y - i][x].getColorCode() != board[y][x].getColorCode())
			{
				i--;
				break;
			}
		}

		// Correct for lower bound
		if (y - i == -1)
		{
			i--;
		}

		j = i;
		// Positive Direction X
		for (i = 1; y + i < height; i++)
		{
			if (board[y + i][x].getColorCode() != board[y][x].getColorCode())
			{
				i--;
				break;
			}
		}

		// Correct for upper bound
		if (y + i == height)
		{
			i--;
		}

		// If more than three in a row, then get the score and delete the line
		if (i + j + 1 >= 3)
		{
			for (int ind = y - j; ind < y + i + 1; ind++)
			{
				if (ind != y)
				{
					board[ind][x].setColorCode(-1);
				}
			}

			score += getScoreForLineOfLengthN(colorCode, i + j);
		}
		return score;
	}

	private static final int removeLinesAndGetScoreHorizontal(int[][] arr)
	{

		int width = arr[0].length;
		int height = arr.length;
		int score = 0;

		for (int i = 0; i < height; i++)
		{

			int index = 0;
			int j = 1;
			while (index < width && index + j < width)
			{

				if (arr[i][index] == arr[i][index + j])
				{
					j++;
				}
				else
				{
					score += getScoreForLineOfLengthN(arr[i][index], j);
					int k = 0;
					while (k < j)
					{
						arr[i][index + k] = -1;
					}
					index = index + j;
					j = 1;
				}
			}
		}

		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				System.out.print(arr[i][j] + " ");
				if (j == width - 1)
				{
					System.out.println();
				}
			}
		}
		return score;
	}

	private static final int getScoreForLineOfLengthN(int colorCode, int n)
	{
		return n;
	}

	private void printColorCodeArray()
	{

		for (int i = 0; i < this.height; i++)
		{
			for (int j = 0; j < this.width; j++)
			{
				System.out.print(board[i][j].getColorCode() + " ");
				if (j == this.width - 1)
				{
					System.out.println();
				}
			}
		}
	}

	private int[][] getColorCodeArray()
	{

		int arr[][] = new int[this.height][this.width];

		for (int i = 0; i < this.height; i++)
		{
			for (int j = 0; j < this.width; j++)
			{
				arr[i][j] = board[i][j].getColorCode();
			}
		}

		return arr;
	}

	private class BoardMouseListener implements MouseListener
	{

		private boolean	isDrag;
		private int		startX;
		private int		startY;

		public BoardMouseListener()
		{
			this.isDrag = false;
		}

		@Override
		public void mouseClicked(MouseEvent arg0)
		{

		}

		@Override
		public void mouseEntered(MouseEvent arg0)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0)
		{

			if (!isDrag)
			{
				this.startX = arg0.getX();
				this.startY = arg0.getY();
				// System.out.println("Start: " + startX + ", " + startY);
				isDrag = true;
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0)
		{

			if (isDrag)
			{

				int endX = arg0.getX();
				int endY = arg0.getY();

				// System.out.println("End: " + endX + ", " + endY);

				int gemWidth = board[0][0].getWidth();
				int gemHeight = board[0][0].getHeight();

				// System.out.println("Gem Dimensions: " + gemWidth + ", " +
				// gemHeight);

				// int startIndX = (int) Math.floor(this.startX / gemWidth);
				// int startIndY = (int) Math.floor(this.startY / gemHeight);

				int startIndX = getBoardArrayIndex(startX, true);
				int startIndY = getBoardArrayIndex(startY, false);

				// System.out.println("Start Index: " + startIndX + ", " +
				// startIndY);

				// int endIndX = (int) Math.floor(endX / gemWidth);
				// int endIndY = (int) Math.floor(endY / gemHeight);

				int endIndX = getBoardArrayIndex(endX, true);
				int endIndY = getBoardArrayIndex(endY, false);

				// System.out.println("End Index: " + endIndX + ", " + endIndY);

				if (startIndX < 0 || startIndY < 0 || endIndX < 0 || endIndY < 0)
				{
					isDrag = false;
					// System.out.println("Index out of bounds, Do Nothing");
					return;
				}

				if ((Math.abs(endIndX - startIndX) + Math.abs(endIndY - startIndY)) != 1)
				{
					isDrag = false;
					// System.out.println("Non-Adjacent Selections");
					return;
				}

				int startCode = board[startIndY][startIndX].getColorCode();
				int endCode = board[endIndY][endIndX].getColorCode();

				board[startIndY][startIndX].setColorCode(endCode);
				board[endIndY][endIndX].setColorCode(startCode);

				if (!hasLineAroundPoint(endIndX, endIndY)
						&& !hasLineAroundPoint(startIndX, startIndY))
				{
					board[startIndY][startIndX].setColorCode(startCode);
					board[endIndY][endIndX].setColorCode(endCode);
				}

				isDrag = false;

				removeLinesAndGetScoreAroundPoint(startIndX, startIndY);
				removeLinesAndGetScoreAroundPoint(endIndX, endIndY);
				// removeLinesAndGetScore();
			}
		}

		private int getBoardArrayIndex(int pixel, boolean isX)
		{

			if (isX)
			{

				for (int i = 0, total = 0; i < width; i++)
				{

					int next = total + board[0][i].getWidth();

					if (pixel >= total && pixel < next)
						return i;

					total = next;
				}
			}
			else
			{

				for (int i = 0, total = 0; i < height; i++)
				{

					int next = total + board[i][0].getHeight();

					if (pixel >= total && pixel < next)
						return i;

					total = next;
				}
			}

			return -1;

		}

	}
}
