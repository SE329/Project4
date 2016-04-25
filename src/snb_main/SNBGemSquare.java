
package snb_main;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class SNBGemSquare extends JLabel
{

	private int		colorCode;
	private int		id;
	private ImageIcon gemIcon;
	
	/**
	 * Flag to allow for matched cells to be matched again in different directions and then cleared
	 * out later.
	 */
	private boolean	clear;

	public SNBGemSquare()
	{
		this.setBackground(Color.LIGHT_GRAY);
		this.setIcon(new ImageIcon("img/blue_gem.png"));
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
				gemIcon = new ImageIcon("img/blue_gem.png");
				break;
			case 1:
				c = Color.RED;
				gemIcon = new ImageIcon("img/red_gem.png");
				break;
			case 2:
				c = Color.YELLOW;
				gemIcon = new ImageIcon("img/yellow_gem.png");
				break;
			case 3:
				c = Color.GREEN;
				gemIcon = new ImageIcon("img/green_gem.png");
				break;
			case 4:
				c = Color.BLUE;
				gemIcon = new ImageIcon("img/gray_gem.png");
				break;
			case 5:
				c = Color.ORANGE;
				gemIcon = new ImageIcon("img/orange_gem.png");
				break;
			case 6:
				c = Color.MAGENTA;
				gemIcon = new ImageIcon("img/pink_gem.png");
				break;
			//case 7:
				//c = Color.GRAY;
				//break;
			default:
				c = Color.BLACK;
				this.colorCode = -1;
				break;
		}

		//this.setBackground(c);
		this.setIcon(new ImageIcon(gemIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
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
