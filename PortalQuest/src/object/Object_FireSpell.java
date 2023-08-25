package object;

import Main.GamePanel;
import entity.Projectile;

public class Object_FireSpell extends Projectile{
	
	GamePanel gp;
	
	public Object_FireSpell(GamePanel gp) { // Projectile is a subclass of entity, and this is a subclass of projectile and entity
		super(gp);
		this.gp = gp;
		
		
		name = "Fire";
		knockBackPower = 4;
		speed = 3;
		maxLife = 120;
		life = maxLife;
		attack = 8 + gp.player.spellAttack;
		useCost = 5; 
		alive = false;
		isRanged = true;
		getImage();
		solidArea.x = 8;
		solidArea.y = 0;
		solidArea.width = 32;
		solidArea.height = 36;
		
		
	}
	
	public void getImage() {
		
		up1 = setUp("/spells/FireSpellUp1", gp.tileSize, gp.tileSize);
		up2 = setUp("/spells/FireSpellUp2", gp.tileSize, gp.tileSize);
		down1 = setUp("/spells/FireSpellDown1", gp.tileSize, gp.tileSize);
		down2 = setUp("/spells/FireSpellDown2", gp.tileSize, gp.tileSize);
		left1 = setUp("/spells/FireSpellLeft1", gp.tileSize, gp.tileSize);
		left2 = setUp("/spells/FireSpellLeft2", gp.tileSize, gp.tileSize);
		right1 = setUp("/spells/FireSpellRight1", gp.tileSize, gp.tileSize);
		right2 = setUp("/spells/FireSpellRight2", gp.tileSize, gp.tileSize);
		
		
		
		
	}

}
