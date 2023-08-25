package Main;

import java.awt.Rectangle;
import entity.Entity;
import tile.TileMap;


public class CollisionChecker {

	GamePanel gp;

	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}

	public void checkTile(Entity entity) {

		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

		int entityLeftCol = entityLeftWorldX / gp.tileSize;
		int entityRightCol = entityRightWorldX / gp.tileSize;
		int entityTopRow = entityTopWorldY / gp.tileSize;
		int entityBottomRow = entityBottomWorldY / gp.tileSize;

		int tileNum1 = 0, tileNum2 = 0; // must only check two tiles at any given time for collision (vertices of
		// rectangle)

		entity.solidArea.x = entity.worldX + entity.solidArea.x;
		entity.solidArea.y = entity.worldY + entity.solidArea.y;
		Rectangle one = new Rectangle(0, 0, 35, 28);
		Rectangle two = new Rectangle(0, 0, 35, 28);

		switch (entity.direction) {
		
		case "UpNone":
		case "up":
			
			// Normal Collision
			
			entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize; // finding out what tile player is trying to
			// step in

			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow]; // When going up, only the top two vertices of
			// the rectangle must be checked. Both
			// numbers are for finding the coordinates
			// of those points.
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];

			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				// Checks both tiles from their position in the overall map, and then checks
				// whether they collide with player.
				entity.collisionOn = true;
				break;
			} 
			// Partial Collision
			
			if (gp.tileM.tile[tileNum1].partialCollision == true || gp.tileM.tile[tileNum2].partialCollision == true) {

				entity.solidArea.y -= entity.speed;
				
				if (tileNum1 == 18) {
					one.x = entityLeftCol * gp.tileSize;
					one.y = entityTopRow*gp.tileSize;
				} else if (tileNum1 == 19) {
					one.x = entityLeftCol * gp.tileSize + (gp.tileSize/4);
					one.y = entityTopRow*gp.tileSize + (gp.tileSize/6);
				}
				
				if (tileNum2 == 18) {
					two.x = entityRightCol * gp.tileSize;
					two.y = entityTopRow * gp.tileSize;
				} else if (tileNum2 == 19) {
					two.x = entityRightCol * gp.tileSize + (gp.tileSize/4);
					two.y = entityTopRow * gp.tileSize + (gp.tileSize/6);
				}
				
				
				
				if (gp.tileM.tile[tileNum1].partialCollision == true) {
					if (entity.solidArea.intersects(one)) {
						entity.collisionOn = true;
					}
				}
				
				if (gp.tileM.tile[tileNum2].partialCollision == true) {
					if (entity.solidArea.intersects(two)) {
						entity.collisionOn = true;
					}
				}
			
			}


			break;
		case "DownNone":
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;

			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];

			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			
			if (gp.tileM.tile[tileNum1].partialCollision == true || gp.tileM.tile[tileNum2].partialCollision == true) {

				entity.solidArea.y += entity.speed;
				if (tileNum1 == 18) {
					one.x = entityLeftCol * gp.tileSize;
					one.y = entityBottomRow*gp.tileSize;
				} else if (tileNum1 == 19) {
					one.x = entityLeftCol * gp.tileSize + (gp.tileSize/4);
					one.y = entityBottomRow*gp.tileSize + (gp.tileSize/6);
				}
				
				if (tileNum2 == 18) {
					two.x = entityRightCol * gp.tileSize;
					two.y = entityBottomRow * gp.tileSize;
				} else if (tileNum2 == 19) {
					two.x = entityRightCol * gp.tileSize + (gp.tileSize/4);
					two.y = entityBottomRow * gp.tileSize + (gp.tileSize/6);
				}
				
				
				
				if (gp.tileM.tile[tileNum1].partialCollision == true) {
					if (entity.solidArea.intersects(one)) {
						entity.collisionOn = true;
					}
				}
				
				if (gp.tileM.tile[tileNum2].partialCollision == true) {
					if (entity.solidArea.intersects(two)) {
						entity.collisionOn = true;
					}
				}
			}
			

			break;
		case "LeftNone":
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;

			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];

			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			
			if (gp.tileM.tile[tileNum1].partialCollision == true || gp.tileM.tile[tileNum2].partialCollision == true) {

				entity.solidArea.x -= entity.speed;
				
				if (tileNum1 == 18) {
					one.x = entityLeftCol * gp.tileSize;
					one.y = entityTopRow*gp.tileSize;
				} else if (tileNum1 == 19) {
					one.x = entityLeftCol * gp.tileSize + (gp.tileSize/4);
					one.y = entityTopRow*gp.tileSize + (gp.tileSize/6);
				}
				
				if (tileNum2 == 18) {
					two.x = entityLeftCol * gp.tileSize;
					two.y = entityBottomRow * gp.tileSize;
				} else if (tileNum2 == 19) {
					two.x = entityLeftCol * gp.tileSize + (gp.tileSize/4);
					two.y = entityBottomRow * gp.tileSize + (gp.tileSize/6);
				}
				
				
				
				if (gp.tileM.tile[tileNum1].partialCollision == true) {
					if (entity.solidArea.intersects(one)) {
						entity.collisionOn = true;
					}
				}
				
				if (gp.tileM.tile[tileNum2].partialCollision == true) {
					if (entity.solidArea.intersects(two)) {
						entity.collisionOn = true;
					}
				}
			}
			

			break;
		case "RightNone":
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;

			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];

			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			
			if (gp.tileM.tile[tileNum1].partialCollision == true || gp.tileM.tile[tileNum2].partialCollision == true) {

				entity.solidArea.x += entity.speed;
				if (tileNum1 == 18) {
					one.x = entityRightCol * gp.tileSize;
					one.y = entityTopRow*gp.tileSize;
				} else if (tileNum1 == 19) {
					one.x = entityRightCol * gp.tileSize + (gp.tileSize/4);
					one.y = entityTopRow*gp.tileSize + (gp.tileSize/6);
				}
				
				if (tileNum2 == 18) {
					two.x = entityRightCol * gp.tileSize;
					two.y = entityBottomRow * gp.tileSize;
				} else if (tileNum2 == 19) {
					two.x = entityRightCol * gp.tileSize + (gp.tileSize/4);
					two.y = entityBottomRow * gp.tileSize + (gp.tileSize/6);
				}
				
				
				
				
				
				if (gp.tileM.tile[tileNum1].partialCollision == true) {
					if (entity.solidArea.intersects(one)) {
						entity.collisionOn = true;
					}
				}
				
				if (gp.tileM.tile[tileNum2].partialCollision == true) {
					if (entity.solidArea.intersects(two)) {
						entity.collisionOn = true;
					}
				}
			}
			

			break;
		// no need for case none, as player cannot collide with anything when moving.
		// When standing still, there are no collisions.
		}
		
		
		
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		gp.tileM.tile[tileNum1].cactus1Solid.x = gp.tileM.CactusSolidAreaDefaultX;
		gp.tileM.tile[tileNum1].cactus1Solid.y = gp.tileM.CactusSolidAreaDefaultY;
		gp.tileM.tile[tileNum2].cactus1Solid2.x = gp.tileM.CactusSolidAreaDefaultX;
		gp.tileM.tile[tileNum2].cactus1Solid2.y = gp.tileM.CactusSolidAreaDefaultY;

	}
	// both above and below are ways for collision. Below is more efficient,
	// however, works less well than above for large amount of checking collisions

	public int checkObject(Entity entity, boolean player) {

		int index = 999;
		// returns index of object hit, and checks whether the player hit it.

		for (int i = 0; i < gp.object[1].length; i++) {

			if (gp.object[gp.currentMap][i] != null) {
				// Get entity's solid area position
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				// Get object's solid area position
				gp.object[gp.currentMap][i].solidArea.x = gp.object[gp.currentMap][i].worldX + gp.object[gp.currentMap][i].solidArea.x;
				gp.object[gp.currentMap][i].solidArea.y = gp.object[gp.currentMap][i].worldY + gp.object[gp.currentMap][i].solidArea.y;

				switch (entity.direction) {
				
				case "UpNone":
				case "up":
					entity.solidArea.y -= entity.speed;
					break;
				case "DownNone":
				case "down":
					entity.solidArea.y += entity.speed;
					break;
				case "LeftNone":
				case "left":
					entity.solidArea.x -= entity.speed;
					break;
				case "RightNone":
				case "right":
					entity.solidArea.x += entity.speed;					
					break;
				}
				// Monsters will collide in objects and tiles that have collision.
				// However, they never collect anything, so no index is required except
				// for the player character.
				if (entity.solidArea.intersects(gp.object[gp.currentMap][i].solidArea)) { 
					if (gp.object[gp.currentMap][i].collision == true) {
						entity.collisionOn = true;
					}
					if (player == true) {
						index = i;
					}
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.object[gp.currentMap][i].solidArea.x = gp.object[gp.currentMap][i].solidAreaDefaultX;
				gp.object[gp.currentMap][i].solidArea.y = gp.object[gp.currentMap][i].solidAreaDefaultY;
			}
		}

		return index;
		
		
		
	}
	
	
	public int checkEntity(Entity entity, Entity[][] target) {
		int index = 999;


		for (int i = 0; i < target[1].length; i++) {

			if (target[gp.currentMap][i] != null && target[gp.currentMap][i].dying != true) {
				// Get entity's solid area position
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				// Get object's solid area position
				target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].worldX + target[gp.currentMap][i].solidArea.x;
				target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].worldY + target[gp.currentMap][i].solidArea.y;
				
				
				switch (entity.direction) {

				case "up":
				case "UpNone":
					entity.solidArea.y -= entity.speed;				
					break;
				case "down":
				case "DownNone":
					entity.solidArea.y += entity.speed;
					break;
				case "left":
				case "LeftNone":
					entity.solidArea.x -= entity.speed;
					break;
				case "right":
				case "RightNone":
					entity.solidArea.x += entity.speed;
					break;	
					
					
				
				
				}
				
				if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
					
					if (target[gp.currentMap][i] != entity) {
						entity.collisionOn = true;
						index = i;	
					}
									
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;
				target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;
			}
			
		}

		return index;
	}
	
	
	public boolean checkPlayer(Entity entity) {
		
		boolean contactPlayer = false;
		
		entity.solidArea.x = entity.worldX + entity.solidArea.x;
		entity.solidArea.y = entity.worldY + entity.solidArea.y;

		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

		switch (entity.direction) {
		
		case "UpNone":
		case "up":
			entity.solidArea.y -= entity.speed; 
			break;
		case "DownNone":
		case "down":
			entity.solidArea.y += entity.speed;
			break;
		case "LeftNone":
		case "left":
			entity.solidArea.x -= entity.speed;
			break;
		case "RightNone":
		case "right":
			entity.solidArea.x += entity.speed;
			break;
		}

		if (entity.solidArea.intersects(gp.player.solidArea)) {
			entity.collisionOn = true;
			contactPlayer = true;

		}
		
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		return contactPlayer;
		
	}
		
		
	
	
	

}
