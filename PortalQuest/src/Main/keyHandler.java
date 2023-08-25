package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyHandler implements KeyListener {

	GamePanel gp;

	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed;
	// Debug
	boolean showDebugText = false;

	public keyHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	} // Key typed not used in this program. Its for when the user inputs a character,
		// while keyPressed is for when a key is pressed.

	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode(); // Returns the integer keyCode associated with the key in this event
		// There are many integers associated with many key codes (list online) (ie: 10
		// -- Enter)

		// TITLE STAATE
		if (gp.gameState == gp.titleState) {
			titleState(code);
		}
		// PLAY STATE
		else if (gp.gameState == gp.playState) {
			playState(code);
		}
		// PAUSE STATE
		else if (gp.gameState == gp.pauseState) {
			pauseState(code);
		}
		// DIALOGUE STATE
		else if (gp.gameState == gp.dialogueState) {
			dialogueState(code);
		}
		// CHARACTER STATE
		else if (gp.gameState == gp.characterState) {
			characterState(code);
		}
		// OPTIONS STATE
		else if (gp.gameState == gp.optionsState) {
			optionsState(code);
		}
		// GAME OVER STATE
		else if (gp.gameState == gp.gameOverState) {
			gameOverState(code);
		}
		// GAME WIN STATE
		else if (gp.gameState == gp.gameWinState) {
			gameWinState(code);
		}

	}

	public void titleState(int code) {
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			gp.ui.commandNum--;
			gp.playSoundEffect(10);
			if (gp.ui.commandNum < 0) {
				gp.ui.commandNum = 2;
			}

		}

		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			gp.ui.commandNum++;
			gp.playSoundEffect(10);
			if (gp.ui.commandNum > 2) {
				gp.ui.commandNum = 0;
			}
		}

		if (code == KeyEvent.VK_ENTER) {
			gp.playSoundEffect(10);
			switch (gp.ui.commandNum) {

				case 0:
					gp.gameState = gp.playState;
					gp.stopMusic();
					gp.playMusic(0);
					gp.restart();
					break;
				case 1:
					break;
				case 2:
					System.exit(0);
					break;

			}

		}
	}

	public void playState(int code) {
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			upPressed = true;
		}

		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			downPressed = true;
		}

		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			leftPressed = true;
		}

		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			rightPressed = true;
		}

		// Pause
		if (code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.pauseState;
			gp.ui.commandNum = 0;
			gp.playSoundEffect(10);

		}

		if (code == KeyEvent.VK_C) {
			gp.gameState = gp.characterState;
			gp.playSoundEffect(10);

		}

		if (code == KeyEvent.VK_ENTER) {
			enterPressed = true;

		}

		if (code == KeyEvent.VK_E) {
			shotKeyPressed = true;
		}

		// DEBUG MODE
		if (code == KeyEvent.VK_T) {
			if (showDebugText == false) {
				showDebugText = true;
			} else if (showDebugText == true) {
				showDebugText = false;
			}
		}

		if (code == KeyEvent.VK_R) {
			// Refresh, for editing the map real time
			if (gp.currentMap == 0) {
				// gp.tileM.loadMap("/maps/UpdatedMainWorld.txt", 0);
				gp.tileM.loadMap("/maps/FirstMap - Easy.txt", 0);
			} else if (gp.currentMap == 1) {
				// gp.tileM.loadMap("/maps/NewWorldMap.txt", 1);
				gp.tileM.loadMap("/maps/SecondMap - Medium.txt", 1);
			} else if (gp.currentMap == 2) {
				gp.tileM.loadMap("/maps/ThirdMap - Medium.txt", 2);
			} else if (gp.currentMap == 3) {
				gp.tileM.loadMap("/maps/FourthMap - Hard.txt", 3);
			} else if (gp.currentMap == 4) {
				gp.tileM.loadMap("/maps/FifthMap - Hard.txt", 4);
			}

		}

	}

	public void pauseState(int code) {
		if (code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
			gp.playSoundEffect(10);

		}

		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			gp.ui.commandNum--;
			gp.playSoundEffect(10);
			if (gp.ui.commandNum < 0) {
				gp.ui.commandNum = 3;
			}

		}

		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			gp.ui.commandNum++;

			gp.playSoundEffect(10);
			if (gp.ui.commandNum > 3) {
				gp.ui.commandNum = 0;
			}
		}

		if (code == KeyEvent.VK_ENTER) {
			gp.playSoundEffect(10);
			switch (gp.ui.commandNum) {

				case 0:
					gp.gameState = gp.playState;
					break;
				case 1:
					gp.gameState = gp.optionsState;
					gp.ui.commandNum = 0;
					break;
				case 2:
					System.exit(0);
					// Save files. They will be implemented later.
					break;
				case 3:
					gp.gameState = gp.titleState;
					gp.stopMusic();
					gp.playMusic(13);
					break;

			}

		}
	}

	public void dialogueState(int code) {
		if (gp.eHandler.dialogueEvent == true && code == KeyEvent.VK_ENTER) {
			gp.gameState = gp.playState;
			gp.eHandler.dialogueEvent = false;
		}

		else if (code == KeyEvent.VK_ENTER) {
			enterPressed = true;
			int npcIndex = gp.cChecker.checkEntity(gp.player, gp.npc);
			gp.player.interactNPC(npcIndex);

		}

	}

	public void characterState(int code) {
		if (code == KeyEvent.VK_C || code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
			gp.playSoundEffect(10);

		}

		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			if (gp.ui.slotRow != 0) {
				gp.ui.slotRow--;
				gp.playSoundEffect(10);
			}

		}
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			if (gp.ui.slotRow != 3) {
				gp.ui.slotRow++;
				gp.playSoundEffect(10);
			}

		}
		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			if (gp.ui.slotCol != 0) {
				gp.ui.slotCol--;
				gp.playSoundEffect(10);
			}

		}
		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			if (gp.ui.slotCol != 4) {
				gp.ui.slotCol++;
				gp.playSoundEffect(10);
			}

		}

		if (code == KeyEvent.VK_ENTER) {
			gp.player.selectItem();
			gp.playSoundEffect(10);

		}

	}

	public void optionsState(int code) {

		if (code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.pauseState;
			gp.ui.subState = 0;
			gp.playSoundEffect(10);

		}

		if (code == KeyEvent.VK_ENTER) {
			enterPressed = true;
			gp.playSoundEffect(10);

		}

		int maxCommandNum = 0; // have to initialize to use
		switch (gp.ui.subState) {
			case 0:
				maxCommandNum = 4;
				break;
			case 1:
				maxCommandNum = 0;
				break;
		}

		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			gp.ui.commandNum--;
			gp.playSoundEffect(10);

			if (gp.ui.commandNum < 0) {
				gp.ui.commandNum = maxCommandNum;
			}

		}

		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			gp.ui.commandNum++;
			gp.playSoundEffect(10);

			if (gp.ui.commandNum > maxCommandNum) {
				gp.ui.commandNum = 0;
			}
		}

		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			if (gp.ui.subState == 0) {
				if (gp.ui.commandNum == 0 && gp.music.volumeScale > 0) {
					gp.playSoundEffect(10);

					gp.music.volumeScale--;
					gp.music.checkVolume();
				}

				if (gp.ui.commandNum == 1 && gp.soundEffect.volumeScale > 0) {
					gp.playSoundEffect(10);
					gp.soundEffect.volumeScale--;
					// no need to checkVolume as music is already playing, but sound effects do not
				}

				if (gp.ui.commandNum == 3) {
					gp.playSoundEffect(10);
					if (gp.ui.onOffSwitch == true) {
						gp.ui.onOffSwitch = false;
					} else if (gp.ui.onOffSwitch == false) {
						gp.ui.onOffSwitch = true;
					}
				}
			}

		}

		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {

			if (gp.ui.subState == 0) {
				if (gp.ui.commandNum == 0 && gp.music.volumeScale < 10) {
					gp.playSoundEffect(10);

					gp.music.volumeScale++;
					gp.music.checkVolume();
				}

				if (gp.ui.commandNum == 1 && gp.soundEffect.volumeScale < 10) {
					gp.playSoundEffect(10);

					gp.soundEffect.volumeScale++;
				}

				if (gp.ui.commandNum == 3) {
					gp.playSoundEffect(10);

					if (gp.ui.onOffSwitch == true) {
						gp.ui.onOffSwitch = false;
					} else if (gp.ui.onOffSwitch == false) {
						gp.ui.onOffSwitch = true;
					}
				}
			}

		}

	}

	public void gameOverState(int code) {

		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			gp.ui.commandNum--;
			gp.playSoundEffect(10);
			if (gp.ui.commandNum < 0) {
				gp.ui.commandNum = 1;
			}

		}

		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			gp.ui.commandNum++;
			gp.playSoundEffect(10);
			if (gp.ui.commandNum > 1) {
				gp.ui.commandNum = 0;
			}
		}

		if (code == KeyEvent.VK_ENTER) {
			gp.playSoundEffect(10);
			if (gp.ui.commandNum == 0) {
				gp.gameState = gp.playState;
				// gp.retry();
				gp.restart();
				gp.stopMusic();
				gp.playMusic(0);
			} else if (gp.ui.commandNum == 1) {
				gp.gameState = gp.titleState;
				gp.stopMusic();
				gp.playMusic(13);
			}

		}

	}

	public void gameWinState(int code) {

		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			gp.ui.commandNum--;
			gp.playSoundEffect(10);

			if (gp.ui.commandNum < 0) {
				gp.ui.commandNum = 0;

			}

		}

		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			gp.ui.commandNum++;
			gp.playSoundEffect(10);

			if (gp.ui.commandNum > 0) {
				gp.ui.commandNum = 0;
			}
		}

		if (code == KeyEvent.VK_ENTER) {
			gp.playSoundEffect(10);

			if (gp.ui.commandNum == 0) {
				gp.stopMusic();
				gp.playMusic(13);
				gp.gameState = gp.titleState;
			}

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			upPressed = false;
		}

		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			downPressed = false;
		}

		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}

		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}

		if (code == KeyEvent.VK_E) {
			shotKeyPressed = false;
		}

	}

}
