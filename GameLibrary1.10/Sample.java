import jp.gr.java_conf.naginata5101.GameLibrary.*;
import java.awt.Color;

public class Sample implements GameBody {
	public static void main(String[] args) {
		new Sample();
	}

	private GameDraw		draw;
	private GameInput		input;
	private GameLibrary	library;

	private int					x, y;
	private final int		w	= 100, h = 50;

	public Sample() {
		input = new GameInput();
		draw = new GameDraw();
		library = new GameLibrary("Game Title", input, draw, this);
	}

	@ Override
	public void userInit() {
		x = 0;
		y = library.getScreenHeight();
	}

	@ Override
	public void mainloop() {
		draw.drawFillRect(Color.RED, x, y - h, w, h);
		draw.drawFillRect(Color.BLUE, y, x - h, w, h);
		++x;
		--y;

		if (input.getKeyNum(GameKey.K_ESC) > 0) System.exit(0);
	}

	@ Override
	public void cleanup() {}
}
