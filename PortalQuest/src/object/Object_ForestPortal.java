package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import entity.Entity;

public class Object_ForestPortal extends Entity{
	
	
	public Object_ForestPortal(GamePanel gp) {
		
		super(gp);
		name = "ForestPortal";
		obj_image = setUp("/objects/ForestPortal1", gp.tileSize, gp.tileSize);
		obj_image2 = setUp("/objects/ForestPortal2", gp.tileSize, gp.tileSize);
		obj_image3 = setUp("/objects/ForestPortal3", gp.tileSize, gp.tileSize);
		obj_image4 = setUp("/objects/ForestPortal4", gp.tileSize, gp.tileSize);
		isObject = true;
		
		
	}
	
	
}
