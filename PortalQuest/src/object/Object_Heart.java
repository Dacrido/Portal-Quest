package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import entity.Entity;

public class Object_Heart extends Entity{


	
	
	public Object_Heart(GamePanel gp) {
		super(gp);
		name = "Heart";
		obj_image = setUp("/objects/FullHeart", gp.tileSize, gp.tileSize);
		obj_image2 = setUp("/objects/HalfHeart", gp.tileSize, gp.tileSize);
		obj_image3 = setUp("/objects/EmptyHeart", gp.tileSize, gp.tileSize);
		isObject = true;
		
	}
	
	
}
