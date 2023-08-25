package object;

import Main.GamePanel;
import entity.Projectile;

public class Object_BubbleSpell extends Projectile{
	
	GamePanel gp;
	
	public Object_BubbleSpell(GamePanel gp) { // Projectile is a subclass of entity, and this is a subclass of projectile and entity
		super(gp);
		this.gp = gp;
		
		
		name = "Bubbles";
		knockBackPower = 1;
		speed = 3;
		maxLife = 50;
		life = maxLife;
		attack = 2 + gp.player.spellAttack;
		useCost = 2; 
		alive = false;
		isRanged = true;
		getImage();
		solidArea.x = 8;
		solidArea.y = 0;
		solidArea.width = 32;
		solidArea.height = 36;
		
		
	}
	
	public void getImage() {
		
		up1 = setUp("/spells/BubbleSpellUp1", gp.tileSize, gp.tileSize);
		up2 = setUp("/spells/BubbleSpellUp2", gp.tileSize, gp.tileSize);
		down1 = setUp("/spells/BubbleSpellDown1", gp.tileSize, gp.tileSize);
		down2 = setUp("/spells/BubbleSpellDown2", gp.tileSize, gp.tileSize);
		left1 = setUp("/spells/BubbleSpellLeft1", gp.tileSize, gp.tileSize);
		left2 = setUp("/spells/BubbleSpellLeft2", gp.tileSize, gp.tileSize);
		right1 = setUp("/spells/BubbleSpellRight1", gp.tileSize, gp.tileSize);
		right2 = setUp("/spells/BubbleSpellRight2", gp.tileSize, gp.tileSize);
		
		
		
		
	}

}
