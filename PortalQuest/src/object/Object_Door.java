package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import entity.Entity;

public class Object_Door extends Entity{
	
	
	public Object_Door(GamePanel gp) {
			
			super(gp);
			name = "Door";
//			obj_image = setUp("/objects/Door", gp.tileSize, gp.tileSize);
			obj_image = setUp("/objects/Door 2", gp.tileSize, gp.tileSize);
			collision = true;
			isObject = true;
			
			
			solidArea.x = 0;
			solidArea.y = 16;
			solidArea.width = 48;
			solidArea.height = 32;
			solidAreaDefaultX = solidArea.x;
			solidAreaDefaultY = solidArea.y;			
			
		}
	

}
