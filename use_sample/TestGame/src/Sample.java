import jp.gr.java_conf.naginata5101.GameLibrary.*;

import java.awt.Color;
import java.awt.Font;

public class Sample implements GameBody {
	public static void main(String[] args) {
		new Sample();
	}

	private GameDraw		draw;
	private GameInput		input;
	private GameLibrary	library;

	private int					playerX, playerY;
	private int					arrowX[]	= new int[100];
	private int					arrowY[]	= new int[100];
	private boolean			arrowL[]	= new boolean[100];

	private final int		playerW		= 36, playerH = 36;
	private final int		arrowW		= 8, arrowH = 8;
	private final int		speed			= 3;

	public Sample() {
		input = new GameInput();
		draw = new GameDraw();
		library = new GameLibrary("Game Title", input, draw, this);
	}

	@ Override
	public void userInit() {
		playerX = 100;
		playerY = 100;

		for (int i = 0; i < 100; i++) {
			arrowX[i] = i * 10;
			arrowY[i] = 0;
			arrowL[i] = false;
		}
	}

	@ Override
	public void mainloop() {
		// ƒvƒŒƒCƒ„[‚Ì‘€ì
		if (input.getKeyNum(GameKey.K_LEFT) > 0) playerX -= speed;
		if (input.getKeyNum(GameKey.K_RIGHT) > 0) playerX += speed;
		if (input.getKeyNum(GameKey.K_UP) > 0) playerY -= speed;
		if (input.getKeyNum(GameKey.K_DOWN) > 0) playerY += speed;
		final int width = library.getScreenWidth();
		;
		final int height = library.getScreenHeight();
		if (playerY < 0) playerY = 0;
		if (playerX < 0) playerX = 0;
		if (playerY > height - playerH) playerY = height - playerH;
		if (playerX > width - playerH) playerX = width - playerH;

		// ’e‚ðŒ‚‚Â
		if (input.getKeyNum(GameKey.K_Z) % 5 == 1) {
			for (int i = 0; i < 100; i++) {
				if (!arrowL[i]) {
					arrowX[i] = playerX + playerW / 2;
					arrowY[i] = playerY;
					arrowL[i] = true;
					break;
				}
			}
		}
		
		for(int i = 0; i < 100; i++){
			arrowY[i] -= 5;
		}

		for (int i = 0; i < 100; i++)
			if (arrowL[i]) {
				draw.drawFillOval(Color.YELLOW, arrowX[i], arrowY[i], arrowW, arrowH);
				if (arrowY[i] < -arrowH) arrowL[i] = false;
			}
		

		draw.drawFillRect(Color.RED, playerX, playerY, playerW, playerH);
		draw.drawFillRect(Color.WHITE, playerX, playerY, 3, 3);
		if (input.getKeyNum(GameKey.K_ESC) > 0) System.exit(0);
	}
	
	void arrowMove() {
		for (int i = 0; i < 100; i++)
			arrowY[i] -= 5;
	}

	@ Override
	public void cleanup() {}
}
