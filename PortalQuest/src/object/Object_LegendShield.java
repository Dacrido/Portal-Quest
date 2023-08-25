package object;

import Main.GamePanel;
import entity.Entity;

public class Object_LegendShield extends Entity{

	public Object_LegendShield(GamePanel gp) {
		super(gp);
		isObject = true;
		type = typeShield;
		name = "Legendary Shield";
		obj_image = setUp("/objects/LegendaryShield", gp.tileSize, gp.tileSize);
		defenseValue = 3;
		
		description = "[Legendary Shield]\nSaid to be able to \nwithstand huge storms \nand blows without a dent. \nTheir owners, not so much.";
		
		
		
		
	}

}
