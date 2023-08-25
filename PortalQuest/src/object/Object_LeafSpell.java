package object;

import Main.GamePanel;
import entity.Projectile;

public class Object_LeafSpell extends Projectile{
	
	GamePanel gp;
	
	public Object_LeafSpell(GamePanel gp) { 
		super(gp);
		this.gp = gp;
		
		
		name = "Leaves";
		speed = 3;
		maxLife = 80;
		life = maxLife;
		stun = true;
		useCost = 2; 
		knockBackPower = 2;
		alive = false;
		isRanged = true;
		getImage();
		solidArea.x = 8;
		solidArea.y = 0;
		solidArea.width = 32;
		solidArea.height = 36;
		
		
	}
	
	public void getImage() {
		
		up1 = setUp("/spells/LeafSpellUp1", gp.tileSize, gp.tileSize);
		up2 = setUp("/spells/LeafSpellUp2", gp.tileSize, gp.tileSize);
		down1 = setUp("/spells/LeafSpellDown1", gp.tileSize, gp.tileSize);
		down2 = setUp("/spells/LeafSpellDown2", gp.tileSize, gp.tileSize);
		left1 = setUp("/spells/LeafSpellLeft1", gp.tileSize, gp.tileSize);
		left2 = setUp("/spells/LeafSpellLeft2", gp.tileSize, gp.tileSize);
		right1 = setUp("/spells/LeafSpellRight1", gp.tileSize, gp.tileSize);
		right2 = setUp("/spells/LeafSpellRight2", gp.tileSize, gp.tileSize);
		
		
		
		
	}

}
