package jp.gr.java_conf.naginata5101.GameLibrary;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * GameLibrary全体のクラスの管理を行うクラス.
 * <p>
 * 主な仕事は、それぞれのクラスの関数を適切なタイミングで呼び出すこと
 * </p>
 */
public class GameLibrary extends JFrame implements ActionListener {
	private GameBody	body;
	private GameInput	input;
	private GameDraw	draw;
	private Timer			timer;
	// 描画域の横幅、縦幅、及びゲーム開始からの経過フレーム数
	private int				wid, hei, frame;
	// コンストラクタ及びuserInitが実行済みか否か
	private boolean		endCons	= false, endInit = false;

	/**
	 * ゲーム本体のクラスのコンストラクタで呼び出すこと.
	 * 
	 * @param title ウィンドウに表示するタイトル
	 * @param gameInput ゲーム本体のクラスで宣言したInputクラス
	 * @param gameDraw ゲーム本体のクラスで宣言したDrawクラス
	 * @param gameBody ゲーム本体のクラス、"this"と引き渡せば良い
	 * @param width ウィンドウの「描画域」の横幅
	 * @param height ウィンドウの「描画域」の縦幅
	 * @param fps 実行速度の指定、単位は1秒間に実行されるフレーム数（Frame Per Second）
	 */
	public GameLibrary(String title, GameInput gameInput, GameDraw gameDraw,
			GameBody gameBody, int width, int height, int fps) {
		input = gameInput;
		draw = gameDraw;
		body = gameBody;
		wid = width;
		hei = height;
		frame = 0;

		Container conpane = getContentPane();

		timer = new javax.swing.Timer(1000 / fps, this);
		timer.start();

		setBackground(Color.BLACK);
		setTitle(title);
		conpane.setPreferredSize(new Dimension(wid, hei));
		pack();

		add(draw);
		conpane.add(draw);
		addKeyListener(input);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// ウィンドウが閉じるときの処理
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				body.cleanup();
				System.exit(0);
			}
		});
		endCons = true;
	}

	/**
	 * ゲーム本体のクラスのコンストラクタで呼び出すこと.
	 * <p>
	 * 特に指定が無い場合、 実行速度は60FPSとなる
	 * </p>
	 * 
	 * @param title ウィンドウに表示するタイトル
	 * @param gameInput ゲーム本体のクラスで宣言したInputクラス
	 * @param gameDraw ゲーム本体のクラスで宣言したDrawクラス
	 * @param gameBody ゲーム本体のクラス、"this"と引き渡せば良い
	 * @param width ウィンドウの「描画域」の横幅
	 * @param height ウィンドウの「描画域」の縦幅
	 */
	public GameLibrary(String title, GameInput gameInput, GameDraw gameDraw,
			GameBody gameBody, int width, int height) {
		this(title, gameInput, gameDraw, gameBody, width, height, 60);
	}

	/**
	 * ゲーム本体のクラスのコンストラクタで呼び出すこと.
	 * <p>
	 * 特に指定が無い場合、 ウィンドウの「描画域」のサイズはデフォルトで640*480、 実行速度は60FPSとなる
	 * </p>
	 * 
	 * @param title ウィンドウに表示するタイトル
	 * @param gameInput ゲーム本体のクラスで宣言したInputクラス
	 * @param gameDraw ゲーム本体のクラスで宣言したDrawクラス
	 * @param gameBody ゲーム本体のクラス、"this"と引き渡せば良い
	 */
	public GameLibrary(String title, GameInput gameInput, GameDraw gameDraw,
			GameBody gameBody) {
		this(title, gameInput, gameDraw, gameBody, 640, 480, 60);
	}

	/**
	 * ウィンドウの「描画域」の横幅を返す関数.
	 * 
	 * @return 描画域の横幅
	 */
	public int getScreenWidth() {
		return wid;
	}

	/**
	 * ウィンドウの「描画域」の縦幅を返す関数.
	 * 
	 * @return 描画域の縦幅
	 */
	public int getScreenHeight() {
		return hei;
	}

	/**
	 * ゲーム開始からの経過フレーム数を返す関数.
	 * 
	 * @return 経過フレーム数
	 */
	public int getFrame() {
		return frame;
	}

	/**
	 * 現在の実現FPSを返す関数.
	 * <p>
	 * 試験中
	 * </p>
	 * 
	 * @return 現在のFPS
	 */
	public double getFPS() {
		return 1000 / timer.getDelay();
	}

	@ Override
	/**
	 * ActionListenerよりオーバーロードした関数.
	 * <p>
	 * 外部から決して触らないこと！
	 * </p>
	 */
	public void actionPerformed(ActionEvent arg0) {
		if (!endCons) return;
		else if (!endInit) {
			body.userInit();
			endInit = true;
			return;
		}

		// 入力状況の更新
		input.update();
		// mainloopの実行
		body.mainloop();
		// 再描画
		repaint();

		++frame;
	}
}
