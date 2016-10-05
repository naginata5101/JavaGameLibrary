package jp.gr.java_conf.naginata5101.GameLibrary;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * キーボードにおけるキーの入力状況を取得するクラス.
 * <p>
 * 調べたいキーの指定にはKeyTypeクラスを用いること
 * </p>
 */
public class GameInput implements KeyListener {
	private static int	KEY_NUM	= GameKey.size();
	private int					frame[]	= new int[KEY_NUM];
	private boolean			press[]	= new boolean[KEY_NUM];

	public GameInput() {
		for (int i = 0; i < KEY_NUM; i++) {
			frame[i] = 0;
			press[i] = false;
		}
	}

	/**
	 * 毎フレーム実行し、各キー操作状況を更新する.
	 * <p>
	 * 1フレーム毎に呼び出す<br>
	 * 外部から触らない様に
	 * </p>
	 * */
	public void update() {
		for (int i = 0; i < KEY_NUM; i++) {
			if (press[i]) ++frame[i];
			else {
				if (frame[i] == 0) continue;
				if (frame[i] > 0) frame[i] = -1;
				else frame[i] = 0;
			}
		}
	}

	/*
	 * private void test (KeyEvent e) { int code = e.getKeyCode();
	 * System.out.println(KeyEvent.getKeyText(code) + "@ " +
	 * KeyType.getKeyType(e.getKeyCode()).name()); }
	 */

	/**
	 * 各キーの押下状況を返す関数.
	 * 
	 * @param key 調べたいキーの種類、詳しくはKeyType参照のこと
	 * @return -1:キーが離された、それ以外：連続して押されているフレーム数
	 */
	public int getKeyNum(GameKey key) {
		return frame[key.getKeyCode()];
	}

	/**
	 * EventTypeに対応するコードを返す関数.
	 * <p>
	 * 得られたKeyLisenerにより得られたKeyEventに対応するKeyTypeのコードを返す
	 * </p>
	 * 
	 * @param e KeyEventそのまま
	 * @return Controllerに対応したコード
	 */
	private int getKeyCode(KeyEvent e) {
		return GameKey.getKeyType(e.getKeyCode()).getKeyCode();
	}

	/**
	 * KeyListenerよりオーバーロードした関数.
	 * <p>
	 * 決して外部から触らないこと！
	 * </p>
	 */
	@ Override
	public void keyPressed(KeyEvent e) {
		// test (e);
		press[getKeyCode(e)] = true;
	}

	/**
	 * KeyListenerよりオーバーロードした関数.
	 * <p>
	 * 決して外部から触らないこと！
	 * </p>
	 */
	@ Override
	public void keyReleased(KeyEvent e) {
		press[getKeyCode(e)] = false;
	}

	/**
	 * KeyListenerよりオーバーロードした関数.
	 * <p>
	 * 決して外部から触らないこと！
	 * </p>
	 */
	@ Override
	public void keyTyped(KeyEvent e) {}
}
