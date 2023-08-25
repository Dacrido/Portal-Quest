package Main;


import java.util.Random;

import Enemy.Monster_FlowerMonster;
import Enemy.Monster_Rat;
import Enemy.Monster_Shaman;
import Enemy.Monster_TreeEye;
import Enemy.Monster_TreeGhost;
import entity.Entity;
import entity.NPC_TreeWizard;
import object.Object_AttackPotion;
import object.Object_BubbleWand;
import object.Object_Chest;
import object.Object_DefensePotion;
import object.Object_Door;
import object.Object_FireWand;
import object.Object_ForestPortal;
import object.Object_Grass;
import object.Object_HealthPotion;
import object.Object_Key;
import object.Object_LeafWand;
import object.Object_LegendShield;
import object.Object_LegendSword;
import object.Object_ManaPotion;
import object.Object_Socks;
import object.Object_SpeedPotion;
import object.Object_TreeShield;
import object.Object_TreeSword;
import object.Object_PoisonPotion;

public class AssetSetter {
	
	// Class that places objects on the tileMap
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
		int mapNum = 0;
		gp.object[mapNum][0] = new Object_ForestPortal(gp);
		gp.object[mapNum][0].worldX = 36 * gp.tileSize;  // 36, 37
		gp.object[mapNum][0].worldY = 36 * gp.tileSize;
		
		gp.object[mapNum][1] = new Object_HealthPotion(gp);
		gp.object[mapNum][1].worldX = 8 * gp.tileSize; 
		gp.object[mapNum][1].worldY = 11 * gp.tileSize;
		
		gp.object[mapNum][2] = new Object_SpeedPotion(gp);
		gp.object[mapNum][2].worldX = 27 * gp.tileSize;  
		gp.object[mapNum][2].worldY = 7 * gp.tileSize;
		
		gp.object[mapNum][3] = new Object_Grass(gp);
		gp.object[mapNum][3].worldX = 30 * gp.tileSize;  
		gp.object[mapNum][3].worldY =22 * gp.tileSize;
		
		gp.object[mapNum][4] = new Object_Grass(gp);
		gp.object[mapNum][4].worldX = 31 * gp.tileSize;  
		gp.object[mapNum][4].worldY = 22 * gp.tileSize;
		
		gp.object[mapNum][5] = new Object_Grass(gp);
		gp.object[mapNum][5].worldX = 31 * gp.tileSize;  
		gp.object[mapNum][5].worldY = 21 * gp.tileSize;
		
		gp.object[mapNum][6] = new Object_Grass(gp);
		gp.object[mapNum][6].worldX = 32 * gp.tileSize;  
		gp.object[mapNum][6].worldY =22 * gp.tileSize;
		
		gp.object[mapNum][7] = new Object_Grass(gp);
		gp.object[mapNum][7].worldX = 32 * gp.tileSize;  
		gp.object[mapNum][7].worldY = 21 * gp.tileSize;
		
		gp.object[mapNum][8] = new Object_DefensePotion(gp);
		gp.object[mapNum][8].worldX = 4 * gp.tileSize;  
		gp.object[mapNum][8].worldY = 36 * gp.tileSize;
		
		gp.object[mapNum][9] = new Object_HealthPotion(gp);
		gp.object[mapNum][9].worldX = 11 * gp.tileSize;  
		gp.object[mapNum][9].worldY = 19 * gp.tileSize;
		
		mapNum = 1;
		gp.object[mapNum][0] = new Object_ForestPortal(gp);
		gp.object[mapNum][0].worldX = 9 * gp.tileSize;  // 10, 29
		gp.object[mapNum][0].worldY = 29 * gp.tileSize;
		
		gp.object[mapNum][1] = new Object_Door(gp);
		gp.object[mapNum][1].worldX = 9 * gp.tileSize;  // 9 , 26
		gp.object[mapNum][1].worldY = 26 * gp.tileSize;
		
		gp.object[mapNum][2] = new Object_Key(gp);
		gp.object[mapNum][2].worldX = 30 * gp.tileSize;  // 30, 22
		gp.object[mapNum][2].worldY = 22 * gp.tileSize;
		
		gp.object[mapNum][3] = new Object_Grass(gp);
		gp.object[mapNum][3].worldX = 18 * gp.tileSize; 
		gp.object[mapNum][3].worldY = 8 * gp.tileSize;
		
		gp.object[mapNum][4] = new Object_Grass(gp);
		gp.object[mapNum][4].worldX = 19 * gp.tileSize; 
		gp.object[mapNum][4].worldY = 17 * gp.tileSize;
		gp.object[mapNum][4].guarantee = true;
		
		gp.object[mapNum][5] = new Object_Grass(gp);
		gp.object[mapNum][5].worldX = 20 * gp.tileSize; 
		gp.object[mapNum][5].worldY = 17 * gp.tileSize;
		gp.object[mapNum][5].guarantee = true;
		
		gp.object[mapNum][6] = new Object_BubbleWand(gp);
		gp.object[mapNum][6].worldX = 9 * gp.tileSize; 
		gp.object[mapNum][6].worldY = 21 * gp.tileSize;
		
		gp.object[mapNum][7] = new Object_TreeSword(gp);
		gp.object[mapNum][7].worldX = 19 * gp.tileSize; 
		gp.object[mapNum][7].worldY = 25 * gp.tileSize;
	
		gp.object[mapNum][8] = new Object_TreeShield(gp);
		gp.object[mapNum][8].worldX = 11 * gp.tileSize; 
		gp.object[mapNum][8].worldY = 27 * gp.tileSize;
		
		gp.object[mapNum][9] = new Object_HealthPotion(gp);
		gp.object[mapNum][9].worldX = 11 * gp.tileSize;  
		gp.object[mapNum][9].worldY = 19 * gp.tileSize;
		
		mapNum = 2;
		gp.object[mapNum][0] = new Object_ForestPortal(gp);
		gp.object[mapNum][0].worldX = 31 * gp.tileSize;  // 31, 31
		gp.object[mapNum][0].worldY = 31 * gp.tileSize;
		
		gp.object[mapNum][1] = new Object_Grass(gp);
		gp.object[mapNum][1].worldX = 7 * gp.tileSize;  // 31, 31
		gp.object[mapNum][1].worldY = 5 * gp.tileSize;
		
		gp.object[mapNum][2] = new Object_Grass(gp);
		gp.object[mapNum][2].worldX = 8 * gp.tileSize;  // 31, 31
		gp.object[mapNum][2].worldY = 5 * gp.tileSize;
		
		gp.object[mapNum][3] = new Object_HealthPotion(gp);
		gp.object[mapNum][3].worldX = 34 * gp.tileSize;  // 31, 31
		gp.object[mapNum][3].worldY = 7 * gp.tileSize;
		
		gp.object[mapNum][4] = new Object_AttackPotion(gp);
		gp.object[mapNum][4].worldX = 34 * gp.tileSize;  // 31, 31
		gp.object[mapNum][4].worldY = 17 * gp.tileSize;
		
		gp.object[mapNum][5] = new Object_DefensePotion(gp);
		gp.object[mapNum][5].worldX = 5 * gp.tileSize;  // 31, 31
		gp.object[mapNum][5].worldY = 26 * gp.tileSize;
		
		gp.object[mapNum][6] = new Object_LeafWand(gp);
		gp.object[mapNum][6].worldX = 16 * gp.tileSize;  // 10, 45
		gp.object[mapNum][6].worldY = 17 * gp.tileSize;
		
		gp.object[mapNum][7] = new Object_HealthPotion(gp);
		gp.object[mapNum][7].worldX = 11 * gp.tileSize;  
		gp.object[mapNum][7].worldY = 19 * gp.tileSize;
		
		mapNum = 3;
		gp.object[mapNum][0] = new Object_ForestPortal(gp);
		gp.object[mapNum][0].worldX = 37 * gp.tileSize;  // 10, 45
		gp.object[mapNum][0].worldY = 18 * gp.tileSize;
		
		gp.object[mapNum][1] = new Object_LegendShield(gp);
		gp.object[mapNum][1].worldX = 36 * gp.tileSize;  // 10, 45
		gp.object[mapNum][1].worldY = 18 * gp.tileSize;
		
		gp.object[mapNum][2] = new Object_LegendSword(gp);
		gp.object[mapNum][2].worldX = 10 * gp.tileSize;  // 10, 45
		gp.object[mapNum][2].worldY = 14 * gp.tileSize;
		
		gp.object[mapNum][3] = new Object_Door(gp);
		gp.object[mapNum][3].worldX = 35 * gp.tileSize;  // 10, 45
		gp.object[mapNum][3].worldY = 18 * gp.tileSize;
		
		gp.object[mapNum][4] = new Object_Key(gp);
		gp.object[mapNum][4].worldX = 32 * gp.tileSize;  // 10, 45
		gp.object[mapNum][4].worldY = 12 * gp.tileSize;
		
		gp.object[mapNum][5] = new Object_HealthPotion(gp);
		gp.object[mapNum][5].worldX = 11 * gp.tileSize;  
		gp.object[mapNum][5].worldY = 19 * gp.tileSize;
		
		mapNum = 4;
		
		gp.object[mapNum][0] = new Object_FireWand(gp);
		gp.object[mapNum][0].worldX = 7 * gp.tileSize;  // 10, 45
		gp.object[mapNum][0].worldY = 23 * gp.tileSize;
		
		gp.object[mapNum][1] = new Object_HealthPotion(gp);
		gp.object[mapNum][1].worldX = 3 * gp.tileSize;  // 10, 45
		gp.object[mapNum][1].worldY = 22 * gp.tileSize;
		
		gp.object[mapNum][2] = new Object_AttackPotion(gp);
		gp.object[mapNum][2].worldX = 3 * gp.tileSize;  // 10, 45
		gp.object[mapNum][2].worldY = 23 * gp.tileSize;
		
		gp.object[mapNum][3] = new Object_Grass(gp);
		gp.object[mapNum][3].worldX = 4 * gp.tileSize;  // 10, 45
		gp.object[mapNum][3].worldY = 16 * gp.tileSize;
		
		gp.object[mapNum][4] = new Object_Grass(gp);
		gp.object[mapNum][4].worldX = 5 * gp.tileSize;  // 10, 45
		gp.object[mapNum][4].worldY = 16 * gp.tileSize;
		
		gp.object[mapNum][5] = new Object_Grass(gp);
		gp.object[mapNum][5].worldX = 6 * gp.tileSize;  // 10, 45
		gp.object[mapNum][5].worldY = 16 * gp.tileSize;
		
		gp.object[mapNum][6] = new Object_Grass(gp);
		gp.object[mapNum][6].worldX = 25 * gp.tileSize;  // 10, 45
		gp.object[mapNum][6].worldY = 9 * gp.tileSize;
		
		gp.object[mapNum][7] = new Object_Grass(gp);
		gp.object[mapNum][7].worldX = 28 * gp.tileSize;  // 10, 45
		gp.object[mapNum][7].worldY = 9 * gp.tileSize;
		
		gp.object[mapNum][8] = new Object_Grass(gp);
		gp.object[mapNum][8].worldX = 26 * gp.tileSize;  // 10, 45
		gp.object[mapNum][8].worldY = 10 * gp.tileSize;
		
		gp.object[mapNum][9] = new Object_Grass(gp);
		gp.object[mapNum][9].worldX = 25 * gp.tileSize;  // 10, 45
		gp.object[mapNum][9].worldY = 10 * gp.tileSize;
		
		gp.object[mapNum][10] = new Object_ForestPortal(gp);
		gp.object[mapNum][10].worldX = 14 * gp.tileSize;  // 10, 45
		gp.object[mapNum][10].worldY = 26 * gp.tileSize;
		
		
//		gp.object[mapNum][1] = new Object_Key(gp);
//		gp.object[mapNum][1].worldX = 12 * gp.tileSize;  
//		gp.object[mapNum][1].worldY = 20 * gp.tileSize;		
//
//		gp.object[mapNum][2] = new Object_ForestPortal(gp);
//		gp.object[mapNum][2].worldX = 29 * gp.tileSize;  // 30, 28
//		gp.object[mapNum][2].worldY = 27 * gp.tileSize;
//		
//		gp.object[mapNum][3] = new Object_HealthPotion(gp);
//		gp.object[mapNum][3].worldX = 14 * gp.tileSize; 
//		gp.object[mapNum][3].worldY = 20 * gp.tileSize;
//		
//		gp.object[mapNum][4] = new Object_SpeedPotion(gp);
//		gp.object[mapNum][4].worldX = 15 * gp.tileSize; 
//		gp.object[mapNum][4].worldY = 20 * gp.tileSize;
//		
//		gp.object[mapNum][5] = new Object_SpeedPotion(gp);
//		gp.object[mapNum][5].worldX = 16 * gp.tileSize; 
//		gp.object[mapNum][5].worldY = 20 * gp.tileSize;
//		
//		gp.object[mapNum][6] = new Object_AttackPotion(gp);
//		gp.object[mapNum][6].worldX = 12 * gp.tileSize; 
//		gp.object[mapNum][6].worldY = 21 * gp.tileSize;
//		
//		gp.object[mapNum][7] = new Object_AttackPotion(gp);
//		gp.object[mapNum][7].worldX = 13 * gp.tileSize; 
//		gp.object[mapNum][7].worldY = 21 * gp.tileSize;
//		
//		gp.object[mapNum][8] = new Object_DefensePotion(gp);
//		gp.object[mapNum][8].worldX = 14 * gp.tileSize; 
//		gp.object[mapNum][8].worldY = 21 * gp.tileSize;
//		
//		gp.object[mapNum][9] = new Object_DefensePotion(gp);
//		gp.object[mapNum][9].worldX = 15 * gp.tileSize; 
//		gp.object[mapNum][9].worldY = 21 * gp.tileSize;
//		
//		gp.object[mapNum][10] = new Object_TreeSword(gp);
//		gp.object[mapNum][10].worldX = 16 * gp.tileSize; 
//		gp.object[mapNum][10].worldY = 21 * gp.tileSize;
//		
//		gp.object[mapNum][11] = new Object_TreeShield(gp);
//		gp.object[mapNum][11].worldX = 17 * gp.tileSize; 
//		gp.object[mapNum][11].worldY = 21 * gp.tileSize;
//		
//		gp.object[mapNum][12] = new Object_LegendSword(gp);
//		gp.object[mapNum][12].worldX = 11 * gp.tileSize; 
//		gp.object[mapNum][12].worldY = 22 * gp.tileSize;
//		
//		gp.object[mapNum][13] = new Object_LegendShield(gp);
//		gp.object[mapNum][13].worldX = 12 * gp.tileSize; 
//		gp.object[mapNum][13].worldY = 22 * gp.tileSize;
//		
//		gp.object[mapNum][14] = new Object_HealthPotion(gp);
//		gp.object[mapNum][14].worldX = 13 * gp.tileSize; 
//		gp.object[mapNum][14].worldY = 20 * gp.tileSize;
//		
//		gp.object[mapNum][15] = new Object_PoisonPotion(gp);
//		gp.object[mapNum][15].worldX = 11 * gp.tileSize; 
//		gp.object[mapNum][15].worldY = 18 * gp.tileSize;
//		
//		gp.object[mapNum][16] = new Object_PoisonPotion(gp);
//		gp.object[mapNum][16].worldX = 12 * gp.tileSize; 
//		gp.object[mapNum][16].worldY = 18 * gp.tileSize;
//		
//		gp.object[mapNum][17] = new Object_BubbleWand(gp);
//		gp.object[mapNum][17].worldX = 12 * gp.tileSize; 
//		gp.object[mapNum][17].worldY = 19 * gp.tileSize;
//		
//		gp.object[mapNum][18] = new Object_ManaPotion(gp);
//		gp.object[mapNum][18].worldX = 11 * gp.tileSize; 
//		gp.object[mapNum][18].worldY = 17 * gp.tileSize;
//		
//		gp.object[mapNum][19] = new Object_LeafWand(gp);
//		gp.object[mapNum][19].worldX = 11 * gp.tileSize; 
//		gp.object[mapNum][19].worldY = 19 * gp.tileSize;
//		
//		gp.object[mapNum][20] = new Object_FireWand(gp);
//		gp.object[mapNum][20].worldX = 10 * gp.tileSize; 
//		gp.object[mapNum][20].worldY = 19 * gp.tileSize;
//		
//		gp.object[mapNum][21] = new Object_Grass(gp);
//		gp.object[mapNum][21].worldX = 9 * gp.tileSize; 
//		gp.object[mapNum][21].worldY = 19 * gp.tileSize;
//		
//		gp.object[mapNum][22] = new Object_Grass(gp);
//		gp.object[mapNum][22].worldX = 9 * gp.tileSize; 
//		gp.object[mapNum][22].worldY = 18 * gp.tileSize;
//		
	
		
		
		//mapNum = 1;
		
//		gp.object[mapNum][1] = new Object_ForestPortal(gp);
//		gp.object[mapNum][1].worldX = 29 * gp.tileSize;  // 30, 16
//		gp.object[mapNum][1].worldY = 15 * gp.tileSize;
		
		
		
		
		
		
	}
	
	
	public void setNPC() {
		
		int mapNum = 0;
		
		gp.npc[mapNum][0] = new NPC_TreeWizard(gp);
		gp.npc[mapNum][0].worldX = 28*gp.tileSize; // 29, 17
		gp.npc[mapNum][0].worldY = 28*gp.tileSize;
		
		
		
		mapNum = 1;
		
		
		
		
				
		
	}
	
	public void setMonster() {
		
		// Monsters 20+ are reserved for spawn in's. So each map has a max of 20 monsters at the start
		
		int mapNum = 0;
		
		gp.monster[mapNum][0] = new Monster_Rat(gp);
		gp.monster[mapNum][0].worldX = 16*gp.tileSize; 
		gp.monster[mapNum][0].worldY = 10*gp.tileSize;
		
		gp.monster[mapNum][1] = new Monster_Rat(gp);
		gp.monster[mapNum][1].worldX = 6*gp.tileSize; 
		gp.monster[mapNum][1].worldY = 7*gp.tileSize;
		
		gp.monster[mapNum][2] = new Monster_Rat(gp);
		gp.monster[mapNum][2].worldX = 5*gp.tileSize; 
		gp.monster[mapNum][2].worldY = 11*gp.tileSize;
		
		gp.monster[mapNum][3] = new Monster_Rat(gp);
		gp.monster[mapNum][3].worldX = 8*gp.tileSize; 
		gp.monster[mapNum][3].worldY = 6*gp.tileSize;
		
		gp.monster[mapNum][4] = new Monster_Rat(gp);
		gp.monster[mapNum][4].worldX = 6*gp.tileSize; 
		gp.monster[mapNum][4].worldY = 3*gp.tileSize;
		
		gp.monster[mapNum][5] = new Monster_Rat(gp);
		gp.monster[mapNum][5].worldX = 25*gp.tileSize; 
		gp.monster[mapNum][5].worldY = 6*gp.tileSize;
		
		gp.monster[mapNum][6] = new Monster_Rat(gp);
		gp.monster[mapNum][6].worldX = 34*gp.tileSize; 
		gp.monster[mapNum][6].worldY = 6*gp.tileSize;
		
		gp.monster[mapNum][7] = new Monster_Rat(gp);
		gp.monster[mapNum][7].worldX = 25*gp.tileSize; 
		gp.monster[mapNum][7].worldY = 10*gp.tileSize;
		
		gp.monster[mapNum][8] = new Monster_Rat(gp);
		gp.monster[mapNum][8].worldX = 20*gp.tileSize; 
		gp.monster[mapNum][8].worldY = 11*gp.tileSize;
		
		gp.monster[mapNum][9] = new Monster_Rat(gp);
		gp.monster[mapNum][9].worldX = 14*gp.tileSize; 
		gp.monster[mapNum][9].worldY = 7*gp.tileSize;
		
		gp.monster[mapNum][10] = new Monster_Rat(gp);
		gp.monster[mapNum][10].worldX = 12*gp.tileSize; 
		gp.monster[mapNum][10].worldY = 4*gp.tileSize;
		
		gp.monster[mapNum][11] = new Monster_Rat(gp);
		gp.monster[mapNum][11].worldX = 35*gp.tileSize; 
		gp.monster[mapNum][11].worldY = 17*gp.tileSize;
		
		gp.monster[mapNum][12] = new Monster_Rat(gp);
		gp.monster[mapNum][12].worldX = 28*gp.tileSize; 
		gp.monster[mapNum][12].worldY = 34*gp.tileSize;
		
		gp.monster[mapNum][13] = new Monster_Rat(gp);
		gp.monster[mapNum][13].worldX = 36*gp.tileSize; 
		gp.monster[mapNum][13].worldY = 31*gp.tileSize;
		
		gp.monster[mapNum][14] = new Monster_Rat(gp);
		gp.monster[mapNum][14].worldX = 12*gp.tileSize; 
		gp.monster[mapNum][14].worldY = 34*gp.tileSize;
		
		
		mapNum = 1;
		
		gp.monster[mapNum][0] = new Monster_Rat(gp);
		gp.monster[mapNum][0].worldX = 19*gp.tileSize; 
		gp.monster[mapNum][0].worldY = 35*gp.tileSize;
		
		gp.monster[mapNum][1] = new Monster_Rat(gp);
		gp.monster[mapNum][1].worldX = 9*gp.tileSize; 
		gp.monster[mapNum][1].worldY = 16*gp.tileSize;
		
		gp.monster[mapNum][2] = new Monster_Rat(gp);
		gp.monster[mapNum][2].worldX = 11*gp.tileSize; 
		gp.monster[mapNum][2].worldY = 17*gp.tileSize;
		
		gp.monster[mapNum][3] = new Monster_FlowerMonster(gp);
		gp.monster[mapNum][3].worldX = 19*gp.tileSize; 
		gp.monster[mapNum][3].worldY = 22*gp.tileSize;
		gp.monster[mapNum][3].ambush = true;

		gp.monster[mapNum][4] = new Monster_FlowerMonster(gp);
		gp.monster[mapNum][4].worldX = 20*gp.tileSize; 
		gp.monster[mapNum][4].worldY = 22*gp.tileSize;
		gp.monster[mapNum][4].ambush = true;
		
		gp.monster[mapNum][5] = new Monster_FlowerMonster(gp);
		gp.monster[mapNum][5].worldX = 28*gp.tileSize; 
		gp.monster[mapNum][5].worldY = 6*gp.tileSize;
		gp.monster[mapNum][5].ambush = true;
		
		gp.monster[mapNum][6] = new Monster_FlowerMonster(gp);
		gp.monster[mapNum][6].worldX = 28*gp.tileSize; 
		gp.monster[mapNum][6].worldY = 7*gp.tileSize;
		gp.monster[mapNum][6].ambush = true;
		
		gp.monster[mapNum][7] = new Monster_FlowerMonster(gp);
		gp.monster[mapNum][7].worldX = 18*gp.tileSize; 
		gp.monster[mapNum][7].worldY = 30*gp.tileSize;
		
		gp.monster[mapNum][8] = new Monster_FlowerMonster(gp);
		gp.monster[mapNum][8].worldX = 27*gp.tileSize; 
		gp.monster[mapNum][8].worldY = 22*gp.tileSize;
		
		gp.monster[mapNum][9] = new Monster_TreeEye(gp);
		gp.monster[mapNum][9].worldX = 17*gp.tileSize; 
		gp.monster[mapNum][9].worldY = 32*gp.tileSize;
		
		gp.monster[mapNum][10] = new Monster_TreeEye(gp);
		gp.monster[mapNum][10].worldX = 21*gp.tileSize; 
		gp.monster[mapNum][10].worldY = 35*gp.tileSize;
		
		gp.monster[mapNum][11] = new Monster_TreeEye(gp);
		gp.monster[mapNum][11].worldX = 30*gp.tileSize; 
		gp.monster[mapNum][11].worldY = 13*gp.tileSize;
		
		gp.monster[mapNum][12] = new Monster_TreeEye(gp);
		gp.monster[mapNum][12].worldX = 33*gp.tileSize; 
		gp.monster[mapNum][12].worldY = 23*gp.tileSize;
		
		mapNum = 2;
		
		gp.monster[mapNum][1] = new Monster_FlowerMonster(gp);
		gp.monster[mapNum][1].worldX = 8*gp.tileSize; 
		gp.monster[mapNum][1].worldY = 6*gp.tileSize;
		
		gp.monster[mapNum][2] = new Monster_FlowerMonster(gp);
		gp.monster[mapNum][2].worldX = 7*gp.tileSize; 
		gp.monster[mapNum][2].worldY = 26*gp.tileSize;
		
		gp.monster[mapNum][3] = new Monster_TreeEye(gp);
		gp.monster[mapNum][3].worldX = 14*gp.tileSize; 
		gp.monster[mapNum][3].worldY = 9*gp.tileSize;
		
		gp.monster[mapNum][4] = new Monster_TreeEye(gp);
		gp.monster[mapNum][4].worldX = 30*gp.tileSize; 
		gp.monster[mapNum][4].worldY = 10*gp.tileSize;
		
		gp.monster[mapNum][5] = new Monster_TreeEye(gp);
		gp.monster[mapNum][5].worldX = 32*gp.tileSize; 
		gp.monster[mapNum][5].worldY = 26*gp.tileSize;
		
		gp.monster[mapNum][6] = new Monster_TreeEye(gp);
		gp.monster[mapNum][6].worldX = 12*gp.tileSize; 
		gp.monster[mapNum][6].worldY = 32*gp.tileSize;
		
		gp.monster[mapNum][7] = new Monster_TreeEye(gp);
		gp.monster[mapNum][7].worldX = 7*gp.tileSize; 
		gp.monster[mapNum][7].worldY = 33*gp.tileSize;
		
		gp.monster[mapNum][8] = new Monster_TreeEye(gp);
		gp.monster[mapNum][8].worldX = 22*gp.tileSize; 
		gp.monster[mapNum][8].worldY = 26*gp.tileSize;
		
		gp.monster[mapNum][9] = new Monster_TreeEye(gp);
		gp.monster[mapNum][9].worldX = 32*gp.tileSize; 
		gp.monster[mapNum][9].worldY = 22*gp.tileSize;
		
		gp.monster[mapNum][10] = new Monster_TreeGhost(gp);
		gp.monster[mapNum][10].worldX = 30*gp.tileSize; 
		gp.monster[mapNum][10].worldY = 30*gp.tileSize;
		gp.monster[mapNum][10].defense = 3;
		
		mapNum = 3;
		
		gp.monster[mapNum][0] = new Monster_Shaman(gp);
		gp.monster[mapNum][0].worldX = 32*gp.tileSize; 
		gp.monster[mapNum][0].worldY = 19*gp.tileSize;
		
		gp.monster[mapNum][1] = new Monster_TreeGhost(gp);
		gp.monster[mapNum][1].worldX = 26*gp.tileSize; 
		gp.monster[mapNum][1].worldY = 14*gp.tileSize;
		
		gp.monster[mapNum][2] = new Monster_TreeGhost(gp);
		gp.monster[mapNum][2].worldX = 31*gp.tileSize; 
		gp.monster[mapNum][2].worldY = 22*gp.tileSize;
		
		gp.monster[mapNum][3] = new Monster_TreeEye(gp);
		gp.monster[mapNum][3].worldX = 31*gp.tileSize; 
		gp.monster[mapNum][3].worldY = 24*gp.tileSize;
		
		gp.monster[mapNum][4] = new Monster_TreeEye(gp);
		gp.monster[mapNum][4].worldX = 27*gp.tileSize; 
		gp.monster[mapNum][4].worldY = 10*gp.tileSize;
		
		mapNum = 4;
		
		gp.monster[mapNum][0] = new Monster_FlowerMonster(gp);
		gp.monster[mapNum][0].worldX = 7*gp.tileSize; 
		gp.monster[mapNum][0].worldY = 14*gp.tileSize;
		
		gp.monster[mapNum][1] = new Monster_FlowerMonster(gp);
		gp.monster[mapNum][1].worldX = 25*gp.tileSize; 
		gp.monster[mapNum][1].worldY = 12*gp.tileSize;
		
		gp.monster[mapNum][2] = new Monster_FlowerMonster(gp);
		gp.monster[mapNum][2].worldX = 31*gp.tileSize; 
		gp.monster[mapNum][2].worldY = 10*gp.tileSize;
		
		gp.monster[mapNum][3] = new Monster_FlowerMonster(gp);
		gp.monster[mapNum][3].worldX = 31*gp.tileSize; 
		gp.monster[mapNum][3].worldY = 6*gp.tileSize;
		
		gp.monster[mapNum][4] = new Monster_FlowerMonster(gp);
		gp.monster[mapNum][4].worldX = 17*gp.tileSize; 
		gp.monster[mapNum][4].worldY = 24*gp.tileSize;
		
		gp.monster[mapNum][5] = new Monster_TreeEye(gp);
		gp.monster[mapNum][5].worldX = 7*gp.tileSize; 
		gp.monster[mapNum][5].worldY = 15*gp.tileSize;
		
		gp.monster[mapNum][6] = new Monster_TreeEye(gp);
		gp.monster[mapNum][6].worldX = 5*gp.tileSize; 
		gp.monster[mapNum][6].worldY = 4*gp.tileSize;
		
		gp.monster[mapNum][7] = new Monster_TreeEye(gp);
		gp.monster[mapNum][7].worldX = 7*gp.tileSize; 
		gp.monster[mapNum][7].worldY = 6*gp.tileSize;
		
		gp.monster[mapNum][8] = new Monster_TreeEye(gp);
		gp.monster[mapNum][8].worldX = 12*gp.tileSize; 
		gp.monster[mapNum][8].worldY = 7*gp.tileSize;
		
		gp.monster[mapNum][9] = new Monster_Shaman(gp);
		gp.monster[mapNum][9].worldX = 25*gp.tileSize; 
		gp.monster[mapNum][9].worldY = 22*gp.tileSize;
		
		gp.monster[mapNum][10] = new Monster_Shaman(gp);
		gp.monster[mapNum][10].worldX = 28*gp.tileSize; 
		gp.monster[mapNum][10].worldY = 22*gp.tileSize;
		
		gp.monster[mapNum][11] = new Monster_Shaman(gp);
		gp.monster[mapNum][11].worldX = 31*gp.tileSize; 
		gp.monster[mapNum][11].worldY = 22*gp.tileSize;
		
		
	}
	
	public void spawnMonster(Entity entity) {
		
		int amount = 0;
		int maxAmount = 2;
		int mapNum = gp.currentMap;
		int worldCol = entity.worldX / gp.tileSize;
		int worldRow = entity.worldY / gp.tileSize;
		
		// Initializing spawning monsters
		for (int i = 20; i < 26; i++) { // 6 at a time max
			if (gp.monster[mapNum][i] == null && amount < maxAmount) {
				gp.monster[mapNum][i] = new Monster_TreeGhost(gp);
				spawn(gp.monster[mapNum][i], worldCol, worldRow);
				if (gp.monster[mapNum][i] != null) {
					gp.monster[mapNum][i].spawnAnimation = true;
					gp.monster[mapNum][i].lockedOn = true;
					amount++;
				}
				
					
				
				
			} else if (amount >= maxAmount) {
				break;
			}
		}
			
	}
	
	public void spawn(Entity entity, int worldCol, int worldRow) {
		
		int q = 0;
		Random random = new Random();
		int mapNum = gp.currentMap;
		int numOfTimes = 0;
		
		do {
			q = random.nextInt(3 + 3) - 3; // Max is 3; Min is -3
			if (q == 0) {
				q = 1;
			}
			numOfTimes++;
			entity.worldX = (worldCol - q) * gp.tileSize;
			entity.worldY = (worldRow - q) * gp.tileSize;
			entity.checkCollision();
		} while (entity.collisionOn == true && numOfTimes <= 20);
		
		if (numOfTimes == 20) {
			entity = null;
		}
		
	}
	
}
