package entity;

import java.awt.AlphaComposite;
import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.UtilityTool;
import Main.keyHandler;
import object.Object_AttackPotion;
import object.Object_BubbleSpell;
import object.Object_BubbleWand;
import object.Object_DefensePotion;
import object.Object_FireSpell;
import object.Object_FireWand;
import object.Object_HealthPotion;
import object.Object_Key;
import object.Object_LeafSpell;
import object.Object_LeafWand;
import object.Object_LegendShield;
import object.Object_LegendSword;
import object.Object_PoisonPotion;
import object.Object_ShieldBasic;
import object.Object_SpeedPotion;
import object.Object_SwordBasic;
import object.Object_TreeShield;
import object.Object_TreeSword;

public class Player extends Entity {

	keyHandler keyH;

	public final int screenX;
	public final int screenY;
	public int npcIndex;
	public boolean attackCancel = false;

	// Inventory
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 20;
	public int keyCount = 0;

	public Player(GamePanel gp, keyHandler keyH) {

		super(gp); // passes gp into the super of player class, which is entity
					// Now, gamepanel is handled in entity, not just in the player class

		this.keyH = keyH; // Makes gp and keyH used in this class equal to those from the GamePanel, or
							// main class

		screenX = gp.screenWidth / 2 - (gp.tileSize / 2); // The substraction fixes the issue that halway og coords are
															// top left of image
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2); // Middle point of the screen

		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 18;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 28;
		solidArea.height = 26; // can adjust for whatever works better for my character

		// attackArea.width = 48; // Each weapon can have it's own attack range
		// attackArea.height = 48;

		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
		setItems();

	}

	public void setDefaultValues() {

		worldX = gp.tileSize * 10;
		worldY = gp.tileSize * 18; // x = 11, y = 18
		defaultSpeed = 4; // 4
		speed = defaultSpeed;
		direction = "DownNone";
		// tookDamage = false;

		// Player Life
		maxLife = 8; // 8

		life = maxLife; // 2 lives is equal to one heart

		// Stats
		level = 1;
		dexterity = 1;
		strength = 1;
		exp = 0;
		nextLevelExp = 10;
		maxMana = 10;
		mana = maxMana;

		currentWeapon = new Object_SwordBasic(gp);
		currentShield = new Object_ShieldBasic(gp);
		currentWand = null;
		attackValue = 0;
		defenseValue = 0; // For potions

		attack = getAttack();
		defense = getDefense();
		projectile = getProjectile();
		manaRechargeValue = 100;
		currentManaRechargeValue = manaRechargeValue;
		spellAttack = 0;

		// 3 Types of Projectiles for now
		// 1: A stun wand. Press e or something, and shoots a projectile that does no
		// damage, but locks a monster in place
		// for 5 seconds. Note: If you touch monster, you still get damaged
		// 2: Damage wand. Weaker than a sword, but has a much longer range. Has a
		// cooldown of 1 shot per 3 seconds, so cannot rely
		// solely on want. In addition, due to it's weak strength, will eventually be
		// useless in combat
		// 3: Super damage wand. A much higher damage wand that will deal the largest
		// amout of damage in the game. However, it has
		// a very low cooldown of 12 seconds. In addition, lower range than the damage
		// want. While useful, considering
		// monsters will chase the player often, sword will still be used more than this
		// want. In addition,

		// MANA Bar: Under the health bar, mana bar will exist. This mana bar decreases
		// with every cast of a spell. It increases on
		// it's own slowly with time. This is so that spells cannot be spammed and so
		// that swords must be used.

		// After projectiles, monster projectiles:

		// Similar concepts, with one monster stunning player, another doing weak
		// damage, and another very high damage, but slow shot.

		// After all projectiles:

		/*
		 * 
		 * -- Item drops from monsters
		 * -- Options Menu (to turn down soud, scroll bar messages, etc)
		 * -- Full screen mode
		 * -- Game over
		 * -- Monster pathfinding (compare mine to tutorial vid and see how I can
		 * improve)
		 * -- Cutting projectiles and knockback. Especially knockback! Important!
		 * -- Save files
		 * -- 4 Maps for now (Tutorial Map, main world, tree maze world, and dirt island
		 * world)
		 * -- 10 different monsters. 5 for each tree maze world and dirt island world.
		 * -- For the 5 for each map, 1 basic melee, 1 basic range, 1 wierd (camo/wierd
		 * properties), 1 tough enemy (range and melee)
		 * -- and finally, 1 boss for each. Each boss has 3 attacks. 1-2 melee, 1-2
		 * range.
		 * GAME COMPLETED!
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */

	}

	public void setDefaultPositions() {

		gp.currentMap = 0;
		worldX = gp.tileSize * 10;
		worldY = gp.tileSize * 18;
		direction = "DownNone";

	}

	public void restoreLife() {

		life = maxLife;
		mana = maxMana;
		tookDamage = false;

	}

	public void setItems() {
		inventory.clear();
		inventory.add(currentWeapon);
		inventory.add(currentShield);
		// inventory.add(new Object_BubbleWand(gp));
		// inventory.add(new Object_LeafWand(gp));
		// inventory.add(new Object_FireWand(gp));
		// inventory.add(new Object_PoisonPotion(gp));

	}

	public int getAttack() {
		attackArea = currentWeapon.attackArea;
		return attack = (strength * currentWeapon.attackValue) + attackValue;
	}

	public int getDefense() {
		return defense = (dexterity * currentShield.defenseValue) + defenseValue;
	}

	public Projectile getProjectile() {

		if (currentWand == null) {
			return null;
		} else if (currentWand.name == "Bubble Wand") {
			return new Object_BubbleSpell(gp);
		} else if (currentWand.name == "Leaf Wand") {
			return new Object_LeafSpell(gp);
		} else if (currentWand.name == "Fire Wand") {
			return new Object_FireSpell(gp);
		}
		return null;
	}

	public void getPlayerImage() {

		up1 = setUp("/player/PlayerUp1", gp.tileSize, gp.tileSize);
		up2 = setUp("/player/PlayerUp2", gp.tileSize, gp.tileSize);
		down1 = setUp("/player/PlayerDown1", gp.tileSize, gp.tileSize);
		down2 = setUp("/player/PlayerDown2", gp.tileSize, gp.tileSize);
		left1 = setUp("/player/PlayerLeft1", gp.tileSize, gp.tileSize);
		left2 = setUp("/player/PlayerLeft2", gp.tileSize, gp.tileSize);
		right1 = setUp("/player/PlayerRight1", gp.tileSize, gp.tileSize);
		right2 = setUp("/player/PlayerRight2", gp.tileSize, gp.tileSize);
		stand1 = setUp("/player/PlayerStanding1", gp.tileSize, gp.tileSize);
		stand2 = setUp("/player/PlayerStanding2", gp.tileSize, gp.tileSize);
		upStand1 = setUp("/player/UpStand1", gp.tileSize, gp.tileSize);
		upStand2 = setUp("/player/UpStand2", gp.tileSize, gp.tileSize);
		leftStand1 = setUp("/player/LeftStand1", gp.tileSize, gp.tileSize);
		leftStand2 = setUp("/player/LeftStand2", gp.tileSize, gp.tileSize);
		rightStand1 = setUp("/player/RightStand1", gp.tileSize, gp.tileSize);
		rightStand2 = setUp("/player/RightStand2", gp.tileSize, gp.tileSize);

	}

	public void getPlayerAttackImage() {

		if (currentWeapon.name == "Basic Sword") {
			attackUp1 = setUp("/player/UpAttack1", gp.tileSize, gp.tileSize);
			attackUp2 = setUp("/player/PlayerUpAttack2", gp.tileSize, gp.tileSize * 2);
			attackDown1 = setUp("/player/PlayerDownAttack1", gp.tileSize, gp.tileSize);
			attackDown2 = setUp("/player/PlayerDownAttack2", gp.tileSize, gp.tileSize * 2);
			attackLeft1 = setUp("/player/LeftAttack1", gp.tileSize, gp.tileSize);
			attackLeft2 = setUp("/player/LeftAttack2", gp.tileSize * 2, gp.tileSize);
			attackRight1 = setUp("/player/RightAttack1", gp.tileSize, gp.tileSize);
			attackRight2 = setUp("/player/RightAttack2", gp.tileSize * 2, gp.tileSize);

			// Player attack left and up need adjustment for screen X and Y. They have an
			// extra tile in a certain direction.
			// Down and right are not affected
		}

		if (currentWeapon.name == "Tree Sword") {
			attackUp1 = setUp("/player/UpAttack1", gp.tileSize, gp.tileSize);
			attackUp2 = setUp("/player/PlayerUpAttack2TreeSword", gp.tileSize, gp.tileSize * 2);
			attackDown1 = setUp("/player/PlayerDownAttackStartTreeSword", gp.tileSize, gp.tileSize);
			attackDown2 = setUp("/player/PlayerDownAttackEndTreeSword", gp.tileSize, gp.tileSize * 2);
			attackLeft1 = setUp("/player/PlayerLeftAttack1Tree", gp.tileSize, gp.tileSize);
			attackLeft2 = setUp("/player/PlayerLeftAttack2Tree", gp.tileSize * 2, gp.tileSize);
			attackRight1 = setUp("/player/PlayerRightAttack1Tree", gp.tileSize, gp.tileSize);
			attackRight2 = setUp("/player/PlayerRightAttack2Tree", gp.tileSize * 2, gp.tileSize);
		}

		if (currentWeapon.name == "Legendary Sword") {
			attackUp1 = setUp("/player/UpAttack1", gp.tileSize, gp.tileSize);
			attackUp2 = setUp("/player/PlayerUpAttack2LegendSword", gp.tileSize, gp.tileSize * 2);
			attackDown1 = setUp("/player/PlayerDownAttackStartLegendSword", gp.tileSize, gp.tileSize);
			attackDown2 = setUp("/player/PlayerDownAttackEndLegendSword", gp.tileSize, gp.tileSize * 2);
			attackLeft1 = setUp("/player/PlayerLeftAttack1Legend", gp.tileSize, gp.tileSize);
			attackLeft2 = setUp("/player/PlayerLeftAttack2Legend", gp.tileSize * 2, gp.tileSize);
			attackRight1 = setUp("/player/PlayerRightAttack1Legend", gp.tileSize, gp.tileSize);
			attackRight2 = setUp("/player/PlayerRightAttack2Legend", gp.tileSize * 2, gp.tileSize);
		}

	}

	public void dialogueUpdate() {
		attacking = false;
		if (direction == "down") {
			direction = "DownNone";
		} else if (direction == "up") {
			direction = "UpNone";
		} else if (direction == "left") {
			direction = "LeftNone";
		} else if (direction == "right") {
			direction = "RightNone";
		}

		spriteCounter++;
		if (spriteCounter > 12) {
			if (spriteNum == 1) {
				spriteNum = 2;
			} else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}

	public void update() {
		if (attacking == true) { // bypass keyInput while attacking
			attacking();

		} else if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
				|| keyH.rightPressed == true || keyH.enterPressed == true) {
			if (keyH.upPressed == true) {
				direction = "up";
			} else if (keyH.downPressed == true) {
				direction = "down";
			} else if (keyH.leftPressed == true) {
				direction = "left";
			} else if (keyH.rightPressed == true) {
				direction = "right";
			}

			// Player should be able to move with the code above, but there is something
			// missing.
			// Since there is no time interval of how often update and repaint repeats, the
			// computer update so fast
			// that y decreases millions of times, due to the number of updates. There must
			// be a time interval that must be added.
			// 60 times per second is normal (definition of FPS)

			// CHECKING FOR TILE COLLISIONS
			collisionOn = false;
			gp.cChecker.checkTile(this);

			// CHECKING FOR OBJECT COLLISIONS
			int objectIndex = gp.cChecker.checkObject(this, true); // this checks for collision, and returns the index
																	// of the object that is collided with
			pickUpObject(objectIndex);

			// CHECKING FOR ENTITY COLLISIONS
			npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);

			// Check Monster Collisions
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);

			// Check Event

			gp.eHandler.checkEvent();

			// If Collision is false, player can move, else, cannot
			if (collisionOn == false && keyH.enterPressed == false) {

				switch (direction) {

					case "up":
						worldY -= speed; // Remember, y decreases as you go up. (0, 0) is in top left corner!
						break;
					case "down":
						worldY += speed;
						break;
					case "left":
						worldX -= speed;
						break;
					case "right":
						worldX += speed;
						break;
				}
			}

			extraPlayerMethods();

		} else {

			if (direction == "down") {
				direction = "DownNone";
			} else if (direction == "up") {
				direction = "UpNone";
			} else if (direction == "left") {
				direction = "LeftNone";
			} else if (direction == "right") {
				direction = "RightNone";
			}
			extraPlayerMethods();

		}

	}

	public void extraPlayerMethods() {

		// POTIONS
		if (speedPotionActivated == true) {
			potionSpeedTimer++;
		}

		if (defensePotionActivated == true) {
			potionDefenseTimer++;
		}
		if (attackPotionActivated == true) {
			potionAttackTimer++;
		}

		if (poisonPotionActivated == true) {
			potionPoisonTimer++;
		}

		if (manaPotionActivated == true) {
			potionManaTimer++;
		}

		if (potionSpeedTimer >= 800) {
			potionSpeedTimer = 0;
			speedPotionActivated = false;
			speed = defaultSpeed;
		}

		if (potionDefenseTimer >= 800) {
			defenseValue = 0;
			defense = getDefense();
			potionDefenseTimer = 0;
			defensePotionActivated = false;
		}

		if (potionAttackTimer >= 800) {
			attackValue = 0;
			attack = getAttack();
			potionAttackTimer = 0;
			attackPotionActivated = false;
		}

		if (potionPoisonTimer >= 1200) {
			poisonValue = 0;
			potionPoisonTimer = 0;
			poisonPotionActivated = false;
		}

		if (potionManaTimer >= 800) {
			potionManaTimer = 0;
			currentManaRechargeValue = manaRechargeValue;
			manaPotionActivated = false;
		}

		if (spellCast == true) {
			spellCastTimer++;
		}

		if (spellCastTimer >= 30) { // Cannot recharge mana for a half second after casting a spell
			spellCastTimer = 0;
			spellCast = false;
		}

		if (mana != maxMana && spellCast == false) {
			rechargeManaTimer++;
		}

		if (rechargeManaTimer >= currentManaRechargeValue) {
			rechargeManaTimer = 0;
			mana++;
		}

		// PROJECTILES
		if (projectile != null) {
			if (gp.keyH.shotKeyPressed == true && projectile.alive == false && shotAvailableCounter == 45) {
				// Cannot shoot a projectile if the previous projectile is still alive
				if (mana - projectile.useCost >= 0) {
					// SETTING DEFAULT COORDS AND DIRECTION FOR THE PROJECTILE
					projectile.set(worldX, worldY, direction, true, this);
					mana -= projectile.useCost;
					shotAvailableCounter = 0;

					// Check Vacancy
					for (int i = 0; i < gp.projectile[1].length; i++) {

						if (gp.projectile[gp.currentMap][i] == null) {
							gp.projectile[gp.currentMap][i] = projectile;
							break;
						}

					}
					if (projectile.name == "Bubbles") {
						gp.playSoundEffect(12);
					} else if (projectile.name == "Fire") {
						gp.playSoundEffect(16);
					} else if (projectile.name == "Leaves") {
						gp.playSoundEffect(15);
					}

					spellCast = true;
				}

			}
		}

		// INVINCIBILITY

		if (tookDamage == true) {
			invincibilityTimer++;

			if (invincibilityTimer >= 60) {
				invincibilityTimer = 0;
				tookDamage = false;
			}
		}

		// ATTACKING
		if (gp.keyH.enterPressed == true && attackCancel == false) {
			gp.playSoundEffect(6);
			attacking = true;
			spriteCounter = 0;
		}

		attackCancel = false;
		gp.keyH.enterPressed = false;

		spriteCounter++;
		if (spriteCounter > 12) {
			if (spriteNum == 1) {
				spriteNum = 2;
			} else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}

		if (shotAvailableCounter < 45) {
			shotAvailableCounter++;
		}

		if (life <= 0) {
			gp.gameState = gp.gameOverState;
			gp.stopMusic();
			gp.playMusic(14);
			gp.ui.commandNum = -1;
		}

	}

	public void attacking() {
		attackSpriteCounter++;

		if (attackSpriteCounter <= 8) {
			spriteNum = 1;
		}
		if (attackSpriteCounter > 8 && attackSpriteCounter <= 25) {
			spriteNum = 2;

			// Save to result player position
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			// Adjust player worldX and Y for Attack Area
			switch (direction) {
				case "up":
					worldY -= attackArea.height;
					break;
				case "UpNone":
					worldY -= attackArea.height;
					break;
				case "down":
					worldY += attackArea.height;
					break;
				case "DownNone":
					worldY += attackArea.height;
					break;
				case "left":
					worldX -= attackArea.width;
					break;
				case "LeftNone":
					worldX -= attackArea.width;
					break;
				case "right":
					worldX += attackArea.width;
					break;
				case "RightNone":
					worldX += attackArea.width;
					break;
			}
			// Attack area becomes solidArea
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;

			// Checking monster collision with updated solid area
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			damageMonster(monsterIndex, attack, isRanged, stun, currentWeapon.knockBackPower);

			// Projectile Collision
			int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
			damageProjectile(projectileIndex);

			// Restoring values
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;

		}
		if (attackSpriteCounter > 32) {
			spriteNum = 1;
			attackSpriteCounter = 0;
			attacking = false;

		}

	}

	public void damageProjectile(int index) {

		if (index != 999) {

			Entity projectile = gp.projectile[gp.currentMap][index];
			projectile.alive = false;
			// Sound Effect
		}

	}

	public void pickUpObject(int index) {

		if (index != 999) { // only 998 index's total set. If more, can just increase the number. 999 is one
							// out of bounds.
			// We have touched an object, as index is defined to be 999 to start.

			// gp.object[index] = null; // deletes the object that was just touched
			String objectName = gp.object[gp.currentMap][index].name;
			String text = "";

			if (objectName == "ForestPortal") {
				switch (gp.currentMap) {

					case 0:
						// worldX = 5 * gp.tileSize; // same as object location
						// worldY = 5 * gp.tileSize;
						worldX = gp.tileSize * 10;
						worldY = gp.tileSize * 18;
						gp.currentMap = 1;

						break;
					case 1:
						// gp.currentMap = 0;
						// worldX = 29 * gp.tileSize; // same as object location
						// worldY = 27 * gp.tileSize;
						gp.currentMap = 2;
						worldX = gp.tileSize * 10;
						worldY = gp.tileSize * 18;
						break;
					case 2:
						gp.currentMap = 3;
						worldX = gp.tileSize * 10;
						worldY = gp.tileSize * 18;
						break;
					case 3:
						gp.currentMap = 4;
						worldX = gp.tileSize * 10;
						worldY = gp.tileSize * 18;
						break;
					case 4:
						gp.gameState = gp.gameWinState;
						gp.stopMusic();
						gp.playMusic(5);
						break;

				}
			} else if (objectName == "Grass") {
				gp.object[gp.currentMap][index].checkDrop();
				gp.object[gp.currentMap][index] = null;

			} else if (objectName == "Door") {

				if (keyCount > 0) {
					for (int i = 0; i < inventory.size(); i++) {
						if (inventory.get(i) != null) {
							if (inventory.get(i).name == "Key") {
								inventory.remove(i);
								break;
							}
						}

					}
					keyCount--;
					gp.object[gp.currentMap][index] = null;
					gp.playSoundEffect(1);
				}

			} else if (inventory.size() != maxInventorySize) {
				inventory.add(gp.object[gp.currentMap][index]);
				gp.playSoundEffect(11);
				text = "Got a "
						+ "" + objectName + "!";
				gp.object[gp.currentMap][index] = null;
				if (objectName == "Key") {
					keyCount++;
					gp.playSoundEffect(2);
				}
			} else {
				text = "Inventory full";
			}
			gp.ui.addMessage(text);

		}

	}

	public void interactNPC(int i) {
		if (i != 999) {
			if (gp.keyH.enterPressed == true || gp.npc[gp.currentMap][i].firstContact == true) {
				if (gp.npc[gp.currentMap][i].canDialogue == true) {
					attackCancel = true;
					gp.gameState = gp.dialogueState;
					gp.npc[gp.currentMap][i].speak();
				}
			}
		}

	}

	public void contactMonster(int i) {

		if (i != 999) {

			if (tookDamage == false && gp.monster[gp.currentMap][i].dying == false) {
				gp.playSoundEffect(7);

				int damage = gp.monster[gp.currentMap][i].attack - defense;
				if (damage < 0) {
					damage = 1;
				}

				life -= damage;
				tookDamage = true;
			}

		}

	}

	public void damageMonster(int i, int attack, boolean isRanged, boolean stun, int knockBackPower) {

		if (i != 999) {

			if (gp.monster[gp.currentMap][i].tookDamage == false && gp.monster[gp.currentMap][i].direction != "camo") {

				gp.playSoundEffect(8);

				if (knockBackPower > 0) {
					knockBack(gp.monster[gp.currentMap][i], knockBackPower);
				}
				int damage = attack - gp.monster[gp.currentMap][i].defense;
				if (damage < 0) {
					damage = 0;
				}
				if (poisonValue > 0 && isRanged == false) {
					gp.monster[gp.currentMap][i].monsterPoisoned = true;
				}

				if (stun == false) {
					gp.monster[gp.currentMap][i].life -= damage;
					gp.ui.addMessage(damage + " damage!");
					gp.monster[gp.currentMap][i].tookDamage = true;
					gp.monster[gp.currentMap][i].damageReaction();
				} else {
					gp.monster[gp.currentMap][i].monsterStun = true;
				}

				if (gp.monster[gp.currentMap][i].life <= 0) {
					gp.monster[gp.currentMap][i].dying = true;
					Random random = new Random();

					int value = random.nextInt(100) + 1;
					if (value <= 33) {
						gp.ui.addMessage("Taken Down!");
					} else if (value > 33 && value <= 66) {
						gp.ui.addMessage("BOOM! Dead!");
					} else if (value > 66 && value <= 95) {
						gp.ui.addMessage("Nice!");
					} else if (value > 95 && value <= 100) {
						gp.ui.addMessage("Who's next!?");
					}

					exp += gp.monster[gp.currentMap][i].exp;
					gp.ui.addMessage("You gained " + gp.monster[gp.currentMap][i].exp + " exp!");
					checkLevelUp();

				}

			}

		}

	}

	public void checkLevelUp() {

		if (exp >= nextLevelExp) {

			level++;
			nextLevelExp = nextLevelExp * 2;

			if (level % 2 == 0) {
				maxLife += 2;
				if (maxLife >= 14) {
					maxLife = 14; // No more than 7 hearts or 14 lives
				}
				maxMana++;
				gp.playSoundEffect(9);
				gp.gameState = gp.dialogueState;
				gp.ui.currentDialogue = "You are now level " + level
						+ "!\nYou feel stronger! Increased mana and health!";
				gp.eHandler.dialogueEvent = true;
			} else {
				strength++;
				dexterity++;
				spellAttack++;
				attack = getAttack();
				defense = getDefense();
				gp.playSoundEffect(9);
				gp.gameState = gp.dialogueState;
				gp.ui.currentDialogue = "You are now level " + level
						+ "!\nYou feel stronger! Increased attack and defense!";
				gp.eHandler.dialogueEvent = true;
			}

		}

	}

	public void selectItem() {

		int itemIndex = gp.ui.getItemIndex();

		if (itemIndex <= inventory.size() - 1) { // item, not a vacant slot

			Entity selectedItem = inventory.get(itemIndex);
			if (selectedItem.type == typeSword) {
				currentWeapon = selectedItem;
				attack = getAttack();
				getPlayerAttackImage();
			}
			if (selectedItem.type == typeShield) {
				currentShield = selectedItem;
				defense = getDefense();
			}
			if (selectedItem.type == consumable) {
				selectedItem.use(this);
				if (selectedItem.consumableUsed == true) {
					inventory.remove(itemIndex);
				}
			}
			if (selectedItem.type == typeWand) {
				currentWand = selectedItem;
				projectile = getProjectile();

			}

		}

	}

	public void knockBack(Entity entity, int knockBackPower) {

		entity.direction = direction;
		entity.speed += knockBackPower;
		entity.knockBack = true;
	}

	public void draw(Graphics2D g2) {

		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;

		switch (direction) {

			case "up":
				if (attacking == false) {
					if (spriteNum == 1) {
						image = up1;
					}
					if (spriteNum == 2) {
						image = up2;
					}
				}
				if (attacking == true) {
					if (spriteNum == 1) {
						image = attackUp1;
					}
					if (spriteNum == 2) {
						tempScreenY = screenY - gp.tileSize;
						image = attackUp2;
					}
				}
				break;
			case "UpNone":
				if (attacking == false) {
					if (spriteNum == 1) {
						image = upStand1;
					}
					if (spriteNum == 2) {
						image = upStand2;
					}
				}
				if (attacking == true) {
					if (spriteNum == 1) {
						image = attackUp1;
					}
					if (spriteNum == 2) {
						tempScreenY = screenY - gp.tileSize;
						image = attackUp2;
					}
				}
				break;
			case "down":
				if (attacking == false) {
					if (spriteNum == 1) {
						image = down1;
					}
					if (spriteNum == 2) {
						image = down2;
					}
				}
				if (attacking == true) {
					if (spriteNum == 1) {
						image = attackDown1;
					}
					if (spriteNum == 2) {
						image = attackDown2;
					}
				}
				break;
			case "DownNone":
				if (attacking == false) {
					if (spriteNum == 1) {
						image = stand1;
					}
					if (spriteNum == 2) {
						image = stand2;
					}
				}
				if (attacking == true) {
					if (spriteNum == 1) {
						image = attackDown1;
					}
					if (spriteNum == 2) {
						image = attackDown2;
					}
				}
				break;
			case "left":
				if (attacking == false) {
					if (spriteNum == 1) {
						image = left1;
					}
					if (spriteNum == 2) {
						image = left2;
					}
				}
				if (attacking == true) {
					if (spriteNum == 1) {
						image = attackLeft1;
					}
					if (spriteNum == 2) {
						tempScreenX = screenX - gp.tileSize;
						image = attackLeft2;
					}
				}
				break;
			case "LeftNone":
				if (attacking == false) {
					if (spriteNum == 1) {
						image = leftStand1;
					}
					if (spriteNum == 2) {
						image = leftStand2;
					}
				}
				if (attacking == true) {
					if (spriteNum == 1) {
						image = attackLeft1;
					}
					if (spriteNum == 2) {
						tempScreenX = screenX - gp.tileSize;
						image = attackLeft2;
					}
				}
				break;
			case "right":
				if (attacking == false) {
					if (spriteNum == 1) {
						image = right1;
					}
					if (spriteNum == 2) {
						image = right2;
					}
				}
				if (attacking == true) {
					if (spriteNum == 1) {
						image = attackRight1;
					}
					if (spriteNum == 2) {
						image = attackRight2;
					}
				}
				break;
			case "RightNone":
				if (attacking == false) {
					if (spriteNum == 1) {
						image = rightStand1;
					}
					if (spriteNum == 2) {
						image = rightStand2;
					}
				}
				if (attacking == true) {
					if (spriteNum == 1) {
						image = attackRight1;
					}
					if (spriteNum == 2) {
						image = attackRight2;
					}
				}
				break;

		}

		if (tookDamage == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f)); // Visual effect to invincible
																						// state
			// 0.3 is opacity level
		}

		g2.drawImage(image, tempScreenX, tempScreenY, null);
		// draw an image on the screen
		// not worldX and Y as image must be displayed at the screen center
		// 30, 14 start (30*50 + 14) = 1514th number
		// Note, screenX and Y are used to draw the image from the top left corner of
		// the screen. This must be fixed.

		// Reset Alpha
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

	}

}
