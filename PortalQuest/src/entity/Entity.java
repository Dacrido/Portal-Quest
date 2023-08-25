package entity;

import java.awt.AlphaComposite;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.UtilityTool;

// Entity class --> Stores variables that will be used for the player, monster and NPC classes. (Super/parent class of other entity classes)
public class Entity {
	
	// Piskel --> For sprites and animation
	GamePanel gp;
	public int worldX, worldY; // Note: world X and world Y are not equal to screenX and screenY
	public int defaultSpeed; // Only for player atm
	public int speed;

	
	// IMAGES
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, stand1, stand2, camo, transition;
	public BufferedImage upStand1, upStand2, leftStand1, leftStand2, rightStand1, rightStand2;
	public BufferedImage attackUp1, attackUp2, attackLeft1, attackLeft2, attackRight1, attackRight2, attackDown1, attackDown2;
	// Describes an Image with an accessible buffer of image data (used to store image files)
	
	
	// STARTING DIRECTION
	public String direction = "DownNone"; // all objects will have this direction. As objects never change direction, this will
	// not affect at all the other subclass of entity, like player and npc. 
	
	
	// ANIMATION SWITCH VALUES
	public int spriteCounter = 0;
	public int spriteNum = 1;


	// COLLISION RECTANGLE
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48); // Invisible/abstract rectangle
	public int 	solidAreaDefaultX, solidAreaDefaultY = 0;
	public boolean collisionOn = false;
	
	// ATTACK
	boolean attacking = false; 
	int attackSpriteCounter = 0;
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0); // depends on entity and on what kind of attack
	
	
	// ACTION LOCK
	public int actionLockCounter = 0;
	public int actionLockCounter1 = 0;
	
	// DIALOGUES
	String dialogues[] = new String[20];
	public int dialogueIndex = 0;
	public boolean canDialogue = false;
	
	// TRANSFORM FUNCTION FOR SPECIAL CASES
	public boolean canTransform = false;
	public boolean transformed = false;
	public int transformLockCounter = 0;
	public int nonCamoCounter = 0;	
	
	// TYPE OF ENTITY
	public int type; // 0--> player, 1--> npc, 2--> monster, and so on
	public final int typePlayer = 0;
	public final int typeNPC = 1;
	public final int typeMonster = 2;
	public final int typeSword = 3;
	public final int typeShield = 4;
	public final int consumable = 5;
	public final int typeWand = 6;
	
	public boolean rangedMonster = false;
	public boolean lockedOn = false;
	public int lockCounter = 0;
	public int spawnCounter = 0; // Shaman summon time
	public boolean spawnAnimation = false;
	public int spawningTimer = 0; // Ghosts time before moving
	public boolean firstContact = true;
	
	// Character Status
	public int maxLife;
	public int life;
	public boolean tookDamage = false;
	int invincibilityTimer = 0;
	public boolean alive = true;
	public boolean dying = false;
	int dyingCounter = 0;
	boolean hpBarOn = false;
	int hpBarCounter = 0;
//	public boolean onPath = false; // Tracking
	public boolean knockBack = false;
	
	
	// OBJECTS
	
	public BufferedImage obj_image, obj_image2, obj_image3, obj_image4;
	public boolean isObject = false;
	public int portalNum = 1;
	public int portalWait = 50;
	public String name;
	public boolean collision = false;
	public boolean speedPotionActivated = false;
	public boolean defensePotionActivated = false;
	public boolean attackPotionActivated = false;
	public boolean poisonPotionActivated = false;
	public int potionSpeedTimer = 0;
	public int potionDefenseTimer = 0;
	public int potionAttackTimer = 0;
	public int potionPoisonTimer = 0;
	public boolean monsterPoisoned = false;
	public int poisonCounter = 0;
	public boolean consumableUsed = false;
	public boolean manaPotionActivated = false;
	public int potionManaTimer = 0;
	public int manaRechargeValue = 0;
	public int currentManaRechargeValue= 0;
	
	
	
	// Stats
	public int knockBackCounter = 0;
	public int level;
	public int strength;
	public int dexterity; // --> For defense
	public int attack;
	public int defense;
	public int exp; // the score system of the game
	public int nextLevelExp;
	public Entity currentWeapon; // 2 kinds of equipement
	public Entity currentShield;
	public Entity currentWand;
	public boolean isRanged = false; // Checking wheter projectile or melee. Only melee can poision, which is why this boolean exists
	public int shotAvailableCounter = 0; 
	public int knockBackPower = 0;
	
	// MANA SYSTEM AND SPELLS
	public int maxMana;
	public int mana;
	public Projectile projectile;
	public int useCost;
	public int rechargeManaTimer = 0;
	public boolean spellCast = false;
	public int spellCastTimer = 0;
	public boolean stun = false;
	public boolean monsterStun = false;
	public int stunCounter = 0;
	public int spellAttack = 0;
	
	
	// Item Stats
	public int attackValue;
	public int defenseValue;
	public int poisonValue;
	public String description = "";
	
	// Unique
	public boolean guarantee = false;
	public boolean ambush = false;
	public boolean startDialogue = false;
	
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setAction() {
		
	}
	
	public void damageReaction() {
		
	}
	
	public void setProjectile() {
		
	}
	
	public void speak() {
			
		
		if (dialogues[dialogueIndex] == null) {
			 dialogueIndex = 0;
			 gp.gameState = gp.playState;
			 gp.keyH.enterPressed = false;
			 return;
		} 
		
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;
		
		
		switch (gp.player.direction) {
		
		case "up":			
		case "UpNone":
			direction = "down";
			break;
		case "down":
		case "DownNone":
			direction = "up";
			break;
		case "left":
		case "LeftNone":
			direction = "right";
			break;
		case "right":
		case "RightNone":
			direction = "left";
			break;	
		
		
		}
		
		
	}
	
	public void dialogueUpdate() {
		
	}
	
	public void use(Entity entity) {
		
	}
	
	public void checkDrop() {
		
	}
	
	public void dropItem(Entity droppedItem) {
		
		for (int i = 0; i < gp.object[gp.currentMap].length; i++) {
			if (gp.object[gp.currentMap][i] == null) {
				gp.object[gp.currentMap][i] = droppedItem;
				gp.object[gp.currentMap][i].worldX = worldX;
				gp.object[gp.currentMap][i].worldY = worldY;
				break;				
			}
		}
		
	}
	
	public void checkCollision() {
		collisionOn = false;		
		
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc); // they include themselves, so this must be fixed
		gp.cChecker.checkEntity(this, gp.monster);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		
		if (this.type == typeMonster && contactPlayer == true && spawnAnimation == false && dying == false) {
			if (gp.player.tookDamage == false) {
				damagePlayer(attack);
			}
		} else if (this.type == typeNPC && contactPlayer == true && firstContact == true) {
			int npcIndex = gp.cChecker.checkEntity(gp.player, gp.npc);
			gp.player.interactNPC(npcIndex);
			firstContact = false;			
		}
		
		
	}
	
	public void update() {
		if (knockBack == true) {
			
			checkCollision();			
			if (collisionOn == true) {
				knockBackCounter = 0;
				knockBack = false;
				speed = defaultSpeed;
			} else if (collisionOn == false){
				switch(gp.player.direction) {
				
				case "up":
				case "UpNone": worldY-= speed; break;
				case "down":
				case "DownNone": worldY+= speed; break;
				case "left":
				case "LeftNone": worldX -= speed; break;
				case "right":
				case "RightNone": worldX += speed; break;			
				
				}
				 
				knockBackCounter++;
				if (knockBackCounter == 10) {
					knockBackCounter = 0;
					knockBack = false;
					speed = defaultSpeed;
				}
			}
			
			
			
			
		} else {
			if (monsterStun == true) {
				if (direction == "up") {
					direction = "UpNone";
				} else if (direction == "down") {
					direction = "DownNone";
				} else if (direction == "left") {
					direction = "LeftNone";
				} else if (direction == "right") {
					direction = "RightNone";
				}
				
				stunCounter++;
			} else {
				setAction();
			}
			if (stunCounter >= 300) {
				stunCounter = 0;
				monsterStun = false;
			}
			
			if (monsterPoisoned == true) {
				poison();
			}
			
			checkCollision();
			
			if (collisionOn == false) {
				
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
				case "camo":
					break;
				case "transition":
					break;
				}
			} else {
				
				if (direction == "up") {
					direction = "UpNone";
				} else if (direction == "down") {
					direction = "DownNone";
				} else if (direction == "left") {
					direction = "LeftNone";
				} else if (direction == "right") {
					direction = "RightNone";
				}
				
				
			}
		}
		
		

		spriteCounter++;
		if (spriteCounter > 12) {
			if (spriteNum ==  1) {
				spriteNum = 2;
			} else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		
		if (tookDamage == true) {
			invincibilityTimer++;
			
			if (direction == "up") {
				direction = "UpNone";
			} else if (direction == "down") {
				direction = "DownNone";
			} else if (direction == "left") {
				direction = "LeftNone";
			} else if (direction == "right") {
				direction = "RightNone";
			}
			
			if (invincibilityTimer >= 40) {
				invincibilityTimer = 0;
				tookDamage = false;
			}
		}
		
		if (shotAvailableCounter < 200) {
			shotAvailableCounter++;
		}
		
	}
	
	public void damagePlayer(int attack) {
		gp.playSoundEffect(7);
		
		int damage = attack - gp.player.defense;
		if (damage < 0) {
			damage = 1;
		}
		
		
		gp.player.life -= damage;
		gp.player.tookDamage = true;
	}
	
	public void poison() {
		
		poisonCounter++;
		
		if (poisonCounter == 80) {
			int damage = gp.player.poisonValue - defense;
			if (damage <= 0) {
				damage = 1;
			}
			gp.ui.addMessage(damage + " damage!");
			life -= damage;
			if (life <= 0 && dying == false) {
				dying = true;
				gp.player.exp += exp;
			}
		} 
		
		if (poisonCounter == 160) {
			int damage = gp.player.poisonValue - defense;
			if (damage <= 0) {
				damage = 1;
			}
			gp.ui.addMessage(damage + " damage!");
			life -= damage;
			poisonCounter = 0;
			monsterPoisoned = false;
			if (life <= 0 && dying == false) {
				dying = true;
				gp.player.exp += exp;
			}
		}		
		
	}
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX; 
		int screenY = worldY - gp.player.worldY + gp.player.screenY; 

		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize< gp.player.worldX + gp.player.screenX &&
				worldY + gp.tileSize> gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
			
			if (spawnAnimation == true) {
				spawningAnimation(g2);
			}
			
			if (transformed == true) {
				transformLockCounter++;
				direction = "transition";
				
				if (transformLockCounter >= 12) {
					transformed = false;
					transformLockCounter = 0;
					
				}
				
			}
			
				switch (direction) {
				
				
				case "up":
					if (spriteNum == 1) {image = up1;}					
					if (spriteNum == 2) {image = up2;}					
					break;
				case "down":
					if (spriteNum == 1) {image = down1;}
					if (spriteNum == 2) {image = down2;}
					break;
				case "left":
					if (spriteNum == 1) {image = left1;}					
					if (spriteNum == 2) {image = left2;}					
					break;
				case "right":
					if (spriteNum == 1) {image = right1;}				
					if (spriteNum == 2 ) {image = right2;}
					break;					
				case "DownNone":					
					if (isObject == true) {
						if (name != "ForestPortal") {
							image = obj_image;
						} else {
							portalWait++;
							if (portalWait > 24) {
								portalNum++;
								if (portalNum > 4) {
									portalNum = 1;
								}
								portalWait = 0;
							}
							
							if (portalNum == 1) {
								image = obj_image;
							} else if (portalNum == 2) {
								image = obj_image2;
							} else if (portalNum == 3) {
								image = obj_image3;
							} else if (portalNum == 4) {
								image = obj_image4;
							}
							
								
							
						}
						
					} else {
						if (spriteNum == 1) {
							image = stand1;
						}
						
						if (spriteNum == 2) {
							image = stand2;
						}
					}
					
					
					
					break;
				case "UpNone":
					if (spriteNum == 1) {
						image = upStand1;
					}
					
					if (spriteNum == 2) {
						image = upStand2;
					}
					
					break;
				case "LeftNone":
					if (spriteNum == 1) {
						image = leftStand1;
					}
					
					if (spriteNum == 2) {
						image = leftStand2;
					}
					
					break;
				case "RightNone":
					if (spriteNum == 1) {
						image = rightStand1;
					}
					
					if (spriteNum == 2) {
						image = rightStand2;
					}
					
					break;
					
				case "camo":
					image = camo;
					break;
				case "transition":
					image = transition;
					break;
				
				
				}
				
				
			// Health Bar
			if (type == 2 && direction != "camo" && direction != "transition" && hpBarOn == true) {
				
				double oneScale = (double)gp.tileSize/maxLife; // divide the bar's length by the monster's max life
				double hpBarValue = oneScale * life; // amount of bar that should be red
				
				// Outline
				g2.setColor(new Color(35, 35, 35));
				g2.fillRect(screenX - 1, screenY - 16, gp.tileSize+2, 12);
				
				// Health Bar
				g2.setColor(new Color(255, 0, 30));
				g2.fillRect(screenX, screenY - 15, (int) hpBarValue, 10); // slightly above entity
				
				hpBarCounter++;
				
				if (hpBarCounter > 600) {
					hpBarCounter = 0;
					hpBarOn = false;
				}
				
			}
			
			if (direction == "camo" || direction == "transition") {
				hpBarOn = false;
			}
			
			
			if (tookDamage == true) {
				hpBarOn = true;
				hpBarCounter = 0;	
				changeAlpha(g2, 0.4F); // Visual effect to invincible state
				// 0.3 is opacity level
			}
			if (dying == true) {
				dyingAnimation(g2);
			}
			
			g2.drawImage(image, screenX, screenY, null);	
			changeAlpha(g2, 1F);

		}
		
	}
	
	public void dyingAnimation(Graphics2D g2) {
		
		// Blinking effect
		
		
		dyingCounter++;
		int i = 5;
		
		
		
		if (dyingCounter <= i) {
			changeAlpha(g2, 0f);
		}
		if (dyingCounter > i && dyingCounter <= i*2) {
			changeAlpha(g2, 1f);
		}
		if (dyingCounter > i*2 && dyingCounter <= i*3) {
			changeAlpha(g2, 0f);
		}
		if (dyingCounter > i*3 && dyingCounter <= i*4) {
			changeAlpha(g2, 1f);
		}
		if (dyingCounter > i*4 && dyingCounter <= i*5) {
			changeAlpha(g2, 0f);
		}
		if (dyingCounter > i*5 && dyingCounter <= i*6) {
			changeAlpha(g2, 1f);
		}
		if (dyingCounter > i*6 && dyingCounter <= i*7) {
			changeAlpha(g2, 0f);
		}
		if (dyingCounter > i*7 && dyingCounter <= i*8) {
			changeAlpha(g2, 1f);
		}
		if (dyingCounter > i*8 ) {
			alive = false;
		}
		
		
		
	}
	
	public void spawningAnimation(Graphics2D g2) {
		spawningTimer++;
		int i = 5;
		
		
		
		if (spawningTimer <= i) {
			changeAlpha(g2, 0f);
		}
		if (spawningTimer > i && spawningTimer <= i*2) {
			changeAlpha(g2, 1f);
		}
		if (spawningTimer > i*2 && spawningTimer <= i*3) {
			changeAlpha(g2, 0f);
		}
		if (spawningTimer > i*3 && spawningTimer <= i*4) {
			changeAlpha(g2, 1f);
		}
		if (spawningTimer > i*4 && spawningTimer <= i*5) {
			changeAlpha(g2, 0f);
		}
		if (spawningTimer > i*5 && spawningTimer <= i*6) {
			changeAlpha(g2, 1f);
		}
		if (spawningTimer > i*6 && spawningTimer <= i*7) {
			changeAlpha(g2, 0f);
		}
		if (spawningTimer > i*7 && spawningTimer <= i*8) {
			changeAlpha(g2, 1f);
		}
		if (spawningTimer > i*8 && spawningTimer <= i*9) {
			changeAlpha(g2, 0f);
		}
		if (spawningTimer > i*9 && spawningTimer <= i*10) {
			changeAlpha(g2, 1f);
		}
		if (spawningTimer > i*10 && spawningTimer <= i*11) {
			changeAlpha(g2, 0f);
		}
		if (spawningTimer > i*11 && spawningTimer <= i*12) {
			changeAlpha(g2, 1f);
		}
		if (spawningTimer > i*12 && spawningTimer <= i*13) {
			changeAlpha(g2, 0f);
		}
		if (spawningTimer > i*13 && spawningTimer <= i*14) {
			changeAlpha(g2, 1f);
		}
		if (spawningTimer > i*14 ) {
			spawnAnimation = false;
		}
	}
	
	public void changeAlpha(Graphics2D g2, float AlphaValue) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, AlphaValue));
	}
	
	
	public BufferedImage setUp (String imagePath, int width, int height) {
		
		UtilityTool tool = new UtilityTool();
		BufferedImage scaledImage = null;
		
		try {
			
			scaledImage = ImageIO.read(getClass().getResourceAsStream(imagePath +".png"));
			scaledImage = tool.scaleImage(scaledImage, width, height);
			
			
		} catch(IOException e) {
			
		}
		return scaledImage;
		
		
	}
	
	public void searchPath(int goalCol, int goalRow, boolean rangedMonster) {
		int startCol = (worldX + solidArea.x) / gp.tileSize;
		int startRow = (worldY + solidArea.y) / gp.tileSize;
		
		gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);
		
		if (gp.pFinder.search() == true) {
			
			// Next worldX and worldY
			
			int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
			int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;
			
			// Entity's solid Area positions
			int entLeftX = worldX + solidArea.x;
			int entRightX = worldX + solidArea.x + solidArea.width;
			int entTopY = worldY + solidArea.y;
			int entBottomY = worldY + solidArea.y + solidArea.height;
			
			if (entTopY > nextY && entLeftX >= nextX && entRightX < nextX + gp.tileSize) {
				direction = "up";
			} else if (entTopY < nextY && entLeftX >= nextX && entRightX < nextX + gp.tileSize) {
				direction = "down";
			} else if (entTopY >= nextY && entBottomY < nextY + gp.tileSize) {
				
				// left or right
				
				if (entLeftX > nextX) {
					direction = "left";
				} else if (entLeftX < nextX) {
					direction = "right";
				}
				
				
			} else if (entTopY > nextY && entLeftX > nextX) {
				
				// up or left
				direction = "up";
				checkCollision();
				if (collisionOn == true) {
					direction = "left";
				}
				
				
			} else if (entTopY > nextY && entLeftX < nextX) {
				
				// up or right
				direction = "up";
				checkCollision();
				if (collisionOn == true) {
					direction = "right";
				}
				
			} else if (entTopY < nextY && entLeftX > nextX) {
				
				// down or left
				direction = "down";
				checkCollision();
				if (collisionOn == true) {
					direction = "left";
				}
				
				
			} else if (entTopY < nextY && entLeftX < nextX) {
				
				// down or right
				direction = "down";
				checkCollision();
				if (collisionOn == true) {
					direction = "right";
				}
				
			}

			int nextCol = gp.pFinder.pathList.get(0).col;
			int nextRow = gp.pFinder.pathList.get(0).row;	
			
			if (rangedMonster == true) {
				
				if (gp.pFinder.onlyXLeft == true) {
					
					if (direction == "right") {
						direction = "RightNone";
						if (projectile.alive == false && shotAvailableCounter == 200) {
							setProjectile();
						}
					} else if (direction == "left") {
						direction = "LeftNone";
						if (projectile.alive == false && shotAvailableCounter == 200) {
							setProjectile();
						}
					}
					
					
				} else if (gp.pFinder.onlyYLeft == true) {
					
					if (direction == "up") {
						direction = "UpNone";
						if (projectile.alive == false && shotAvailableCounter == 200) {
							setProjectile();
						}
					} else if (direction == "down") {
						direction = "DownNone";
						if (projectile.alive == false && shotAvailableCounter == 200) {
							setProjectile();
						}
					}
					
					
				}
				
				
				
				
			}			
			if (nextCol == goalCol && nextRow == goalRow) {
					
			}			
			
		}
		
		
		
		
	}
	
	
	
	
}
