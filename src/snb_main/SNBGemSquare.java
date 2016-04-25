
package snb_main;

import java.awt.Color;
import javax.swing.JLabel;

public class SNBGemSquare extends JLabel
{

	private int		colorCode;
	private int		id;
	/**
	 * Flag to allow for matched cells to be matched again in different directions and then cleared
	 * out later.
	 */
	private boolean	clear;

	public SNBGemSquare()
	{
		this.setBackground(Color.CYAN);
		this.setOpaque(true);
		this.colorCode = 0;
		this.id = 0;
		clear = false;
	}

	public SNBGemSquare(int colorCode, int id)
	{

		this.id = id;
		setColorCode(colorCode);
		clear = false;
	}

	public void setClearFlag(boolean flag)
	{
		clear = flag;
	}

	public void setColorCode(int colorCode)
	{

		this.colorCode = colorCode;
		Color c;

		switch (colorCode)
		{
			case 0:
				c = Color.CYAN;
				break;
			case 1:
				c = Color.RED;
				break;
			case 2:
				c = Color.YELLOW;
				break;
			case 3:
				c = Color.GREEN;
				break;
			case 4:
				c = Color.BLUE;
				break;
			case 5:
				c = Color.ORANGE;
				break;
			case 6:
				c = Color.MAGENTA;
				break;
			case 7:
				c = Color.GRAY;
				break;
			default:
				c = Color.BLACK;
				this.colorCode = -1;
				break;
		}

		this.setBackground(c);
		this.setOpaque(true);
	}

	public int getColorCode()
	{
		return this.colorCode;
	}

	public int getId()
	{
		return this.id;
	}

	public boolean getClearFlag()
	{
		return clear;
	}

}
