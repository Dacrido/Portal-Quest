package tile;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.UtilityTool;

public class TileManager extends TileMap {

	GamePanel gp;
	public TileMap[] tile;
	public int mapTileNum[][][];
	boolean drawPath = true;

	public TileManager(GamePanel gp) {

		this.gp = gp;

		tile = new TileMap[70]; // 20 types of tiles. This can be edited and changed as needed
		mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

		getTileImage();
		
		
		
	
		//loadMap("/maps/UpdatedMainWorld.txt", 0);
		//loadMap("/maps/NewWorldMap.txt", 1);
		
		loadMap("/maps/FirstMap - Easy.txt", 0);
		loadMap("/maps/SecondMap - Medium.txt", 1);
		loadMap("/maps/ThirdMap - Medium.txt", 2);
		loadMap("/maps/FourthMap - Hard.txt", 3);
		loadMap("/maps/FifthMap - Hard.txt", 4);
		
		gp.maxWorldCol = 40;
		gp.maxWorldRow = 40;
	}

	public void getTileImage() {
		
		// PlaceHolds for no null pointer errors
		
		setUp(0, "Grass1", false, false);
		setUp(1, "Grass1", false, false);
		setUp(2, "Grass1", false, false);
		setUp(3, "Grass1", false, false);
		setUp(4, "Grass1", false, false);
		setUp(5, "Grass1", false, false);
		setUp(6, "Grass1", false, false);
		setUp(7, "Grass1", false, false);
		setUp(8, "Grass1", false, false);
		setUp(9, "Grass1", false, false);
//		setUp(10, "Grass1", false, false); 
//		setUp(11, "Grass2", false, false);
//		setUp(12, "Grass3", false, false);
//		setUp(13, "Wall", true, false);
//		setUp(14, "Water", true, false);
//		setUp(15, "Dirt", false, false);
//		setUp(16, "Sand", false, false);
//		setUp(17, "Cactus1", false, true);
//		setUp(18, "Cactus2", false, true);
//		setUp(19, "PierVertical", false, false);
//		setUp(20, "PierHorizontal", false, false);
//		setUp(21, "Tree", true, false);
//		setUp(22, "Palm Tree", true, false);
		
		setUp(10, "GrassBlank", false, false);
		setUp(11, "Grass1", false, false);
		setUp(12, "Grass2", false, false);
		setUp(13, "Flower1", false, false);
		setUp(14, "Flower2", false, false);
		setUp(15, "Dirt", false, false);
		setUp(16, "Brick", true, false);
		setUp(17, "BeachSand", false, false);
		setUp(18, "Cactus1", false, true);
		setUp(19, "Cactus2", false, true);
		setUp(20, "Water", true, false);
		setUp(21, "WaterCurrent", true, false);
		setUp(22, "Tree", true, false);
		setUp(23, "PalmTree", true, false);
		setUp(24, "GrassBottomLeftCornerOfWater", true, false);
		setUp(25, "GrassBottomRightCornerOfWater", true, false);
		setUp(26, "GrassTopRightCornerOfWater", true, false);
		setUp(27, "GrassTopLeftCornerOfWater", true, false);
		setUp(28, "GrassBottomLeftOfWater", true, false);
		setUp(29, "GrassBottomOfWater", true, false);
		setUp(30, "GrassBottomRightOfWater", true, false);
		setUp(31, "GrassRightOfWater", true, false);
		setUp(32, "GrassTopRightOfWater", true, false);
		setUp(33, "GrassTopOfWater", true, false);
		setUp(34, "GrassTopLeftOfWater", true, false);
		setUp(35, "GrassLeftOfWater", true, false);
		setUp(36, "SandBottomLeftCornerOfWater", true, false);
		setUp(37, "SandBottomRightCornerOfWater", true, false);
		setUp(38, "SandTopRightCornerOfWater", true, false);
		setUp(39, "SandTopLeftCornerOfWater", true, false);
		setUp(40, "SandLeftOfWater", true, false);
		setUp(41, "SandBottomLeftOfWater", true, false);
		setUp(42, "SandBottomOfWater", true, false);
		setUp(43, "SandBottomRightOfWater", true, false);
		setUp(44, "SandRightOfWater", true, false);
		setUp(45, "SandTopRightOfWater", true, false);
		setUp(46, "SandTopOfWater", true, false);
		setUp(47, "SandTopLeftOfWater", true, false);
		setUp(48, "SandCornerBottomLeftOfGrass", false, false);
		setUp(49, "SandCornerBottomRightOfGrass", false, false);
		setUp(50, "SandCornerTopRightOfGrass", false, false);
		setUp(51, "SandCornerTopLeftOfGrass", false, false);
		setUp(52, "SandBottomLeftOfGrass", false, false);
		setUp(53, "SandBottomOfGrass", false, false);
		setUp(54, "SandBottomRightOfGrass", false, false);
		setUp(55, "SandRightOfGrass", false, false);
		setUp(56, "SandTopRightOfGrass", false, false);
		setUp(57, "SandTopOfGrass", false, false);
		setUp(58, "SandTopLeftOfGrass", false, false);
		setUp(59, "SandLeftOfGrass", false, false);
		
		
		
		
		// Change index's so that they are all double digits
		// Still initialize 0 - 9 so no null pointer error
		// draw better tiles. Look at video maybe for inspiration, but it'll be my own work
		
		
	}

	public void setUp(int index, String imageName, boolean collision, boolean partialCollision) {

		UtilityTool tool = new UtilityTool();
		
		try {

			tile[index] = new TileMap();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
			tile[index].image = tool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
			tile[index].partialCollision = partialCollision;
		} catch (IOException e) {

		}

	}

	public void loadMap(String fileMap, int map) {
		
		if (map == 0) {
			gp.maxWorldCol = 40;
			gp.maxWorldRow = 40; // 40 by 40 for 1, 2,3. 40 by 50 for 4th. 40 by 30 for last		
		} else if (map == 1) {
			gp.maxWorldCol = 40;
			gp.maxWorldRow = 40;
		}
		else if (map == 2) {
			gp.maxWorldCol = 40;
			gp.maxWorldRow = 40;
		}
		else if (map == 3) {
			gp.maxWorldCol = 40;
			gp.maxWorldRow = 40;
		}
		else if (map == 4) {
			gp.maxWorldCol = 40;
			gp.maxWorldRow = 30;
		}
		try {
			
			InputStream first = getClass().getResourceAsStream(fileMap); // The way to import a text file and use it
			BufferedReader br = new BufferedReader(new InputStreamReader(first)); // reads the content of textFile
			
			int col = 0;
			int row = 0;
			
			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

				String line = br.readLine(); // reads a line of text (a single line)
				while (col < gp.maxWorldCol) {

					String numbers[] = line.split(" "); // Splits the string around matches of a given regular
														// expression, in this case, a space

					int num = Integer.parseInt(numbers[col]);
					// changes from string to integers
					
					if (num == 10) {
						Random random = new Random();
						int q = random.nextInt(100) + 1;
						
						if (q <= 60) {
							num = 10;
						} else if (q > 60 && q <= 70) {
							num = 11;
						} else if (q > 70 && q <= 80) {
							num = 12;
						} else if (q > 80 && q <= 90) {
							num = 13;
						} else if (q > 90 && q <= 100) {
							num = 14;
						}
						
						
					}
					
					mapTileNum[map][col][row] = num;

					col++;
				}

				if (col == gp.maxWorldCol) {
					col = 0;
					row++;

				}
			}
			br.close(); // stop the process
		} catch (Exception e) {

		}

	}

	public void draw(Graphics2D g2) {

		// g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
		// The proccess above is too slow as one must manually set all the tiles.
		// Much easier to automate.

		// TileSize is 48 x 48
		int worldCol = 0;
		int worldRow = 0;
		// However, map data must be created first, then the loop reads the data and
		// pastes the tiles.
		// Map data is stored in a text file
		
		if (gp.currentMap == 0) {
			gp.maxWorldCol = 40;
			gp.maxWorldRow = 40; // 40 by 40 for 1, 2,3. 40 by 50 for 4th. 40 by 30 for last		
		} else if (gp.currentMap == 1) {
			gp.maxWorldCol = 40;
			gp.maxWorldRow = 40;
		}
		else if (gp.currentMap == 2) {
			gp.maxWorldCol = 40;
			gp.maxWorldRow = 40;
		}
		else if (gp.currentMap == 3) {
			gp.maxWorldCol = 40;
			gp.maxWorldRow = 40;
		}
		else if (gp.currentMap == 4) {
			gp.maxWorldCol = 40;
			gp.maxWorldRow = 30;
		}
		
		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

			int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX; // added as player position is at center of the
																			// screen
			int screenY = worldY - gp.player.worldY + gp.player.screenY; // Relook ep 5 and try to understand this
																			// concept
			// works, but must imporve render efficiency --> only draw tiles that we can see
			// The plus tileSize adds one tile (too small otherwise)
			if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
					&& worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
					&& worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
					&& worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
				
		
				

			}

			// If statement creates a boundary from the screen, with +- screen X and Y.
			// If outside bound, doesn't draw.
			worldCol++;

			if (worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			} 

		}
		
//		if (drawPath == true) {
//			
//			g2.setColor(new Color(255, 0, 0, 70));
//			
//			for (int i = 0; i < gp.pFinder.pathList.size(); i++) {
//				
//				int worldX = gp.pFinder.pathList.get(i).col * gp.tileSize;
//				int worldY = gp.pFinder.pathList.get(i).row * gp.tileSize;
//				int screenX = worldX - gp.player.worldX + gp.player.screenX; 
//				int screenY = worldY - gp.player.worldY + gp.player.screenY; 
//				
//				g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
//				
//			}
//			
//		}

	}

}
