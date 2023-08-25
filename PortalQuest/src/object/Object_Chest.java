package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import entity.Entity;

public class Object_Chest extends Entity{
	
	
	public Object_Chest(GamePanel gp) {
			super(gp);
			
			name = "Chest";
			obj_image = setUp("/objects/Chest", gp.tileSize, gp.tileSize);
			collision = true;
			isObject = true;
			
		}
	
}
