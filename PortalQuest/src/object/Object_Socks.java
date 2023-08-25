package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import entity.Entity;

public class Object_Socks extends Entity{
	
	
	
	public Object_Socks(GamePanel gp) {
		super(gp);
		name = "Sock";
		obj_image = setUp("/objects/Sock", gp.tileSize, gp.tileSize);
		isObject = true;
		
	}
	
	
}
