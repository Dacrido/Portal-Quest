package tile;

import java.awt.Rectangle;
import java.awt.Graphics2D;
import Main.GamePanel;
import java.awt.image.BufferedImage;

public class TileMap {

	public BufferedImage image;
	public boolean collision = false;
	
	// trying partial collision work
	public Rectangle cactus1Solid = new Rectangle(0, 0, 40, 30);
	public Rectangle cactus1Solid2 = new Rectangle(0, 0, 40, 30);
	public int CactusSolidAreaDefaultX = 0;
	public int CactusSolidAreaDefaultY = 0;
	
	public boolean partialCollision = false;
	
	
	// other way
	public int height = 40;
	public int width = 30;
	
	
	
}
