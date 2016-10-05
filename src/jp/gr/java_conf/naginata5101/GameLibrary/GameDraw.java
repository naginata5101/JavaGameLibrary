package jp.gr.java_conf.naginata5101.GameLibrary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * 様々な図形や画像の描画を行うクラス.
 * <p>
 * フレーム毎に描画命令を呼び出した順に描画される<br>
 * つまり、後に実行された図形が前のものを上書きする可能性がある
 * </p>
 */
public class GameDraw extends JPanel {
	private LinkedList<DrawStack>	stack	= new LinkedList<DrawStack>();
	private ArrayList<Image>			image	= new ArrayList<Image>();
	private ArrayList<Font>				font	= new ArrayList<Font>();

	/**
	 * 再描画の度に呼び出される関数.
	 * <p>
	 * 外部から触らないこと
	 * </p>
	 * 
	 * @param g 描画先であるGraphicsクラス
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.BLACK);

		DrawStack[] st = stack.toArray(new DrawStack[0]);
		// 溜まっている描画命令を全て実行
		for (DrawStack s: st)
			s.draw(g);

		// スタックの初期化
		stack.clear();
	}

	/**
	 * 予め登録しておいた画像を描画する関数.
	 * <p>
	 * makeImage関数で登録しておいた番号を用いて画像を指定する<br>
	 * 指定された番号が未登録の場合は-1が返される
	 * </p>
	 * 
	 * @param id 描画する画像の番号
	 * @param dx 描画先（画面）の左上のx座標
	 * @param dy 描画先（画面）の左上のy座標
	 * @param rx 描画元（画像）の左上のx座標
	 * @param ry 描画元（画像）の左上のy座標
	 * @param w 描画する横幅
	 * @param h 描画する縦幅
	 * @return 描画される順番
	 */
	public int drawImage(int id, int dx, int dy, int rx, int ry, int w, int h) {
		try {
			stack.add(new DrawImage(image.get(id), dx, dy, rx, ry, w, h));
			return stack.size() - 1;
		} catch (IndexOutOfBoundsException e) {
			return -1;
		}
	}

	/**
	 * 予め登録していない画像を描画する関数.
	 * <p>
	 * 必要に応じて作成したjava.awt.Imageクラスを引き渡すこと
	 * </p>
	 * 
	 * @param img 描画する画像本体
	 * @param dx 描画先（画面）の左上のx座標
	 * @param dy 描画先（画面）の左上のy座標
	 * @param rx 描画元（画像）の左上のx座標
	 * @param ry 描画元（画像）の左上のy座標
	 * @param w 描画する横幅
	 * @param h 描画する縦幅
	 * @return 描画される順番
	 */
	public int drawImage(Image img, int dx, int dy, int rx, int ry, int w, int h) {
		stack.add(new DrawImage(img, dx, dy, rx, ry, w, h));
		return stack.size() - 1;
	}

	/**
	 * 予め登録しておいたフォントを用いて文字列を描画する関数.
	 * <p>
	 * makeFont関数で登録しておいた番号を用いてフォントを指定する<br>
	 * 指定された番号が未登録の場合は-1が返される
	 * </p>
	 * 
	 * @param id 描画するフォントの番号
	 * @param c 描画する色
	 * @param x 描画先の左上のx座標
	 * @param y 描画先の左上のy座標
	 * @param str 描画する文字列
	 * @return 描画される順番
	 */
	public int drawString(int id, Color c, int x, int y, String str) {
		try {
			stack.add(new DrawString(str, c, font.get(id), x, y));
			return stack.size() - 1;
		} catch (IndexOutOfBoundsException e) {
			return -1;
		}
	}

	/**
	 * 予め登録していないフォントを用いて文字列を描画する関数.
	 * <p>
	 * 必要に応じて作成したjava.awt.Fontクラスを引き渡すこと
	 * </p>
	 * 
	 * @param f 描画するフォント本体
	 * @param c 描画する色
	 * @param x 描画先の左上のx座標
	 * @param y 描画先の左上のy座標
	 * @param str 描画する文字列
	 * @return 描画される順番
	 */
	public int drawString(Font f, Color c, int x, int y, String str) {
		stack.add(new DrawString(str, c, f, x, y));
		return stack.size() - 1;
	}

	/**
	 * 直線を描画する関数.
	 * <p>
	 * (x, y)と(x + w, y + h)を結ぶ線分を描画する
	 * </p>
	 * 
	 * @param c 描画する色
	 * @param x 描画先の左上のx座標
	 * @param y 描画先の左上のy座標
	 * @param w 描画する横幅
	 * @param h 描画する縦幅
	 * @return 描画される順番
	 */
	public int drawLine(Color c, int x, int y, int w, int h) {
		stack.add(new DrawLine(c, x, y, x + w, y + h));
		return stack.size() - 1;
	}

	/**
	 * 円の輪郭を描画する関数.
	 * <p>
	 * 指定された矩形に収まる円を描画する
	 * </p>
	 * 
	 * @param c 描画する色
	 * @param x 描画先の左上のx座標
	 * @param y 描画先の左上のy座標
	 * @param r 描画する半径
	 * @return 描画される順番
	 */
	public int drawLineCircle(Color c, int x, int y, int r) {
		stack.add(new DrawOval(false, c, x, y, 2 * r, 2 * r));
		return stack.size() - 1;
	}

	/**
	 * 円の輪郭を中心から描画する関数.
	 * <p>
	 * 指定された矩形に収まる円を描画する
	 * </p>
	 * 
	 * @param c 描画する色
	 * @param x 描画先の左上のx座標
	 * @param y 描画先の左上のy座標
	 * @param r 描画する半径
	 * @return 描画される順番
	 */
	public int drawLineCircleC(Color c, int x, int y, int r) {
		stack.add(new DrawOval(false, c, x - r, y - r, 2 * r, 2 * r));
		return stack.size() - 1;
	}

	/**
	 * 塗り潰された円を描画する関数.
	 * <p>
	 * 指定された矩形に収まる円を描画する
	 * </p>
	 * 
	 * @param c 描画する色
	 * @param x 描画先の左上のx座標
	 * @param y 描画先の左上のy座標
	 * @param r 描画する半径
	 * @return 描画される順番
	 */
	public int drawFillCircle(Color c, int x, int y, int r) {
		stack.add(new DrawOval(true, c, x, y, 2 * r, 2 * r));
		return stack.size() - 1;
	}

	/**
	 * 塗り潰された円を中心から描画する関数.
	 * <p>
	 * 指定された矩形に収まる円を描画する
	 * </p>
	 * 
	 * @param c 描画する色
	 * @param x 描画先の左上のx座標
	 * @param y 描画先の左上のy座標
	 * @param r 描画する半径
	 * @return 描画される順番
	 */
	public int drawFillCircleC(Color c, int x, int y, int r) {
		stack.add(new DrawOval(true, c, x - r, y - r, 2 * r, 2 * r));
		return stack.size() - 1;
	}

	/**
	 * 円または楕円の輪郭を描画する関数.
	 * <p>
	 * 指定された矩形に収まる円または楕円を描画する
	 * </p>
	 * 
	 * @param c 描画する色
	 * @param x 描画先の左上のx座標
	 * @param y 描画先の左上のy座標
	 * @param w 描画する横幅
	 * @param h 描画する縦幅
	 * @return 描画される順番
	 */
	public int drawLineOval(Color c, int x, int y, int w, int h) {
		stack.add(new DrawOval(false, c, x, y, w, h));
		return stack.size() - 1;
	}

	/**
	 * 円または楕円の輪郭を中心から描画する関数.
	 * <p>
	 * 指定された矩形に収まる円または楕円を描画する
	 * </p>
	 * 
	 * @param c 描画する色
	 * @param x 描画先の中心のx座標
	 * @param y 描画先の中心のy座標
	 * @param w 描画する横幅
	 * @param h 描画する縦幅
	 * @return 描画される順番
	 */
	public int drawLineOvalC(Color c, int x, int y, int w, int h) {
		stack.add(new DrawOval(false, c, x - w / 2, y - h / 2, w, h));
		return stack.size() - 1;
	}

	/**
	 * 塗り潰された円または楕円を描画する関数.
	 * <p>
	 * 指定された矩形に収まる円または楕円を描画する
	 * </p>
	 * 
	 * @param c 描画する色
	 * @param x 描画先の左上のx座標
	 * @param y 描画先の左上のy座標
	 * @param w 描画する横幅
	 * @param h 描画する縦幅
	 * @return 描画される順番
	 */
	public int drawFillOval(Color c, int x, int y, int w, int h) {
		stack.add(new DrawOval(true, c, x, y, w, h));
		return stack.size() - 1;
	}

	/**
	 * 塗り潰された円または楕円を中心から描画する関数.
	 * <p>
	 * 指定された矩形に収まる円または楕円を描画する
	 * </p>
	 * 
	 * @param c 描画する色
	 * @param x 描画先の中心のx座標
	 * @param y 描画先の中心のy座標
	 * @param w 描画する横幅
	 * @param h 描画する縦幅
	 * @return 描画される順番
	 */
	public int drawFillOvalC(Color c, int x, int y, int w, int h) {
		stack.add(new DrawOval(true, c, x - w / 2, y - h / 2, w, h));
		return stack.size() - 1;
	}

	/**
	 * 円弧または楕円弧の輪郭を描画する関数.
	 * <p>
	 * 指定された矩形に収まる円弧または楕円弧を描画する
	 * </p>
	 * 
	 * @param c 描画する色
	 * @param x 描画先の左上のx座標
	 * @param y 描画先の左上のy座標
	 * @param w 描画する横幅
	 * @param h 描画する縦幅
	 * @param startAngle 描画の開始角度
	 * @param arcAngle 開始角度に対する展開角度の大きさ
	 * @return 描画される順番
	 */
	public int drawLineArc(Color c, int x, int y, int w, int h, int startAngle,
			int arcAngle) {
		stack.add(new DrawArc(false, c, x, y, w, h, startAngle, arcAngle));
		return stack.size() - 1;
	}

	/**
	 * 円弧または楕円弧の輪郭を描画する関数.
	 * <p>
	 * 指定された矩形に収まる円弧または楕円弧を描画する
	 * </p>
	 * 
	 * @param c 描画する色
	 * @param x 描画先の左上のx座標
	 * @param y 描画先の左上のy座標
	 * @param w 描画する横幅
	 * @param h 描画する縦幅
	 * @param startAngle 描画の開始角度
	 * @param arcAngle 開始角度に対する展開角度の大きさ
	 * @return 描画される順番
	 */
	public int drawLineArcC(Color c, int x, int y, int w, int h, int startAngle,
			int arcAngle) {
		stack.add(new DrawArc(false, c, x - w / 2, y - h / 2, w, h, startAngle,
				arcAngle));
		return stack.size() - 1;
	}

	/**
	 * 塗り潰された円弧または楕円弧を中心から描画する関数.
	 * <p>
	 * 指定された矩形に収まる円弧または楕円弧を描画する
	 * </p>
	 * 
	 * @param c 描画する色
	 * @param x 描画先の中心のx座標
	 * @param y 描画先の中心のy座標
	 * @param w 描画する横幅
	 * @param h 描画する縦幅
	 * @param startAngle 描画の開始角度
	 * @param arcAngle 開始角度に対する展開角度の大きさ
	 * @return 描画される順番
	 */
	public int drawFillArc(Color c, int x, int y, int w, int h, int startAngle,
			int arcAngle) {
		stack.add(new DrawArc(true, c, x, y, w, h, startAngle, arcAngle));
		return stack.size() - 1;
	}

	/**
	 * 塗り潰された円弧または楕円弧を中心から描画する関数.
	 * <p>
	 * 指定された矩形に収まる円弧または楕円弧を描画する
	 * </p>
	 * 
	 * @param c 描画する色
	 * @param x 描画先の中心のx座標
	 * @param y 描画先の中心のy座標
	 * @param w 描画する横幅
	 * @param h 描画する縦幅
	 * @param startAngle 描画の開始角度
	 * @param arcAngle 開始角度に対する展開角度の大きさ
	 * @return 描画される順番
	 */
	public int drawFillArcC(Color c, int x, int y, int w, int h, int startAngle,
			int arcAngle) {
		stack.add(new DrawArc(true, c, x - w / 2, y - 2 / h, w, h, startAngle,
				arcAngle));
		return stack.size() - 1;
	}

	/**
	 * 四角形の輪郭を描画する関数.
	 * 
	 * @param c 描画する色
	 * @param x 描画先の左上のx座標
	 * @param y 描画先の左上のy座標
	 * @param w 描画する横幅
	 * @param h 描画する縦幅
	 * @return 描画される順番
	 */
	public int drawLineRect(Color c, int x, int y, int w, int h) {
		stack.add(new DrawRect(false, c, x, y, w, h));
		return stack.size() - 1;
	}

	/**
	 * 四角形の輪郭を中心から描画する関数.
	 * 
	 * @param c 描画する色
	 * @param x 描画先の中心のx座標
	 * @param y 描画先の中心のy座標
	 * @param w 描画する横幅
	 * @param h 描画する縦幅
	 * @return 描画される順番
	 */
	public int drawLineRectC(Color c, int x, int y, int w, int h) {
		stack.add(new DrawRect(false, c, x - w / 2, y - h / 2, w, h));
		return stack.size() - 1;
	}

	/**
	 * 塗り潰された四角形を描画する関数.
	 * 
	 * @param c 描画する色
	 * @param x 描画先の左上のx座標
	 * @param y 描画先の左上のy座標
	 * @param w 描画する横幅
	 * @param h 描画する縦幅
	 * @return 描画される順番
	 */
	public int drawFillRect(Color c, int x, int y, int w, int h) {
		stack.add(new DrawRect(true, c, x, y, w, h));
		return stack.size() - 1;
	}

	/**
	 * 塗り潰された四角形を中心から描画する関数.
	 * 
	 * @param c 描画する色
	 * @param x 描画先の中心のx座標
	 * @param y 描画先の中心のy座標
	 * @param w 描画する横幅
	 * @param h 描画する縦幅
	 * @return 描画される順番
	 */
	public int drawFillRectC(Color c, int x, int y, int w, int h) {
		stack.add(new DrawRect(true, c, x - w / 2, y - h / 2, w, h));
		return stack.size() - 1;
	}

	/**
	 * 丸いコーナー付きの四角形の輪郭を描画する関数.
	 * 
	 * @param c 描画する色
	 * @param x 描画先の左上のx座標
	 * @param y 描画先の左上のy座標
	 * @param w 描画する横幅
	 * @param h 描画する縦幅
	 * @param arcWidth 四隅の弧の水平方向の直径
	 * @param arcHeight 四隅の弧の垂直方向の直径
	 * @return 描画される順番
	 */
	public int drawLineRoundRect(Color c, int x, int y, int w, int h,
			int arcWidth, int arcHeight) {
		stack.add(new DrawRoundRect(false, c, x, y, w, h, arcWidth, arcHeight));
		return stack.size() - 1;
	}

	/**
	 * 丸いコーナー付きの四角形の輪郭を中心から描画する関数.
	 * 
	 * @param c 描画する色
	 * @param x 描画先の中心のx座標
	 * @param y 描画先の中心のy座標
	 * @param w 描画する横幅
	 * @param h 描画する縦幅
	 * @param arcWidth 四隅の弧の水平方向の直径
	 * @param arcHeight 四隅の弧の垂直方向の直径
	 * @return 描画される順番
	 */
	public int drawLineRoundRectC(Color c, int x, int y, int w, int h,
			int arcWidth, int arcHeight) {
		stack.add(new DrawRoundRect(false, c, x - w / 2, y - h / 2, w, h, arcWidth,
				arcHeight));
		return stack.size() - 1;
	}

	/**
	 * 塗り潰された丸いコーナー付きの四角形を描画する関数.
	 * 
	 * @param c 描画する色
	 * @param x 描画先の左上のx座標
	 * @param y 描画先の左上のy座標
	 * @param w 描画する横幅
	 * @param h 描画する縦幅
	 * @param arcWidth 四隅の弧の水平方向の直径
	 * @param arcHeight 四隅の弧の垂直方向の直径
	 * @return 描画される順番
	 */
	public int drawFillRoundRect(Color c, int x, int y, int w, int h,
			int arcWidth, int arcHeight) {
		stack.add(new DrawRoundRect(true, c, x, y, w, h, arcWidth, arcHeight));
		return stack.size() - 1;
	}

	/**
	 * 塗り潰された丸いコーナー付きの四角形を中心から描画する関数.
	 * 
	 * @param c 描画する色
	 * @param x 描画先の中心のx座標
	 * @param y 描画先の中心のy座標
	 * @param w 描画する横幅
	 * @param h 描画する縦幅
	 * @param arcWidth 四隅の弧の水平方向の直径
	 * @param arcHeight 四隅の弧の垂直方向の直径
	 * @return 描画される順番
	 */
	public int drawFillRoundRectC(Color c, int x, int y, int w, int h,
			int arcWidth, int arcHeight) {
		stack.add(new DrawRoundRect(true, c, x - w / 2, y - h / 2, w, h, arcWidth,
				arcHeight));
		return stack.size() - 1;
	}

	/**
	 * 座標の配列に基づいて多角形の輪郭を描画する関数.
	 * <p>
	 * x座標とy座標の配列で定義された閉じた多角形を描画する<br>
	 * 各座標は配列の順番に結ばれる
	 * </p>
	 * 
	 * @param c 描画する色
	 * @param x x座標の配列
	 * @param y y座標の配列
	 * @param n 点の総数
	 * @return 描画される順番
	 */
	public int drawLinePolygon(Color c, int x[], int y[], int n) {
		stack.add(new DrawPolygon(false, c, new Polygon(x, y, n)));
		return stack.size() - 1;
	}

	/**
	 * 与えられた多角形の輪郭を描画する関数.
	 * <p>
	 * 必要に応じて作成したjava.awt.Polygonを引き渡すこと
	 * </p>
	 * 
	 * @param c 描画する色
	 * @param p 描画する多角形
	 * @return 描画される順番
	 */
	public int drawLinePolygon(Color c, Polygon p) {
		stack.add(new DrawPolygon(false, c, p));
		return stack.size() - 1;
	}

	/**
	 * 座標の配列に基づいて塗り潰された多角形を描画する関数.
	 * <p>
	 * x座標とy座標の配列で定義された閉じた多角形を描画する<br>
	 * 各座標は配列の順番に結ばれる
	 * </p>
	 * 
	 * @param c 描画する色
	 * @param x x座標の配列
	 * @param y y座標の配列
	 * @param n 点の総数
	 * @return 描画される順番
	 */
	public int drawFillPolygon(Color c, int x[], int y[], int n) {
		stack.add(new DrawPolygon(true, c, new Polygon(x, y, n)));
		return stack.size() - 1;
	}

	/**
	 * 与えられた塗り潰された多角形を描画する関数.
	 * <p>
	 * 必要に応じて作成したjava.awt.Polygonを引き渡すこと
	 * </p>
	 * 
	 * @param c 描画する色
	 * @param p 描画する多角形
	 * @return 描画される順番
	 */
	public int drawFillPolygon(Color c, Polygon p) {
		stack.add(new DrawPolygon(true, c, p));
		return stack.size() - 1;
	}

	/**
	 * ファイル名を指定して画像を登録する関数.
	 * <p>
	 * 画像はresフォルダからの相対パスで指定すること
	 * </p>
	 * 
	 * @param name 登録する画像ファイル名
	 * @return 画像登録番号
	 */
	public int makeImage(String name) {
		BufferedImage img = null;
		File imgFile = new File("./res/" + name);
		try {
			img = ImageIO.read(imgFile);
		} catch (Exception e) {
			System.out.println(imgFile.getAbsoluteFile() + ": not exist!");
		}
		// 画像を取得できなかった場合は-1を返し、ArrayListに追加しない
		if (img == null) return -1;

		image.add(img);
		// 追加できた場合はその番号を返す
		return image.size() - 1;
	}

	/**
	 * Imageクラスから画像を登録する関数.
	 * <p>
	 * 必要に応じて作成したjava.awt.Imageを引き渡すこと
	 * </p>
	 * 
	 * @param img 登録する画像本体
	 * @return 画像登録番号
	 */
	public int makeImage(Image img) {
		image.add(img);
		return image.size() - 1;
	}

	/**
	 * フォント名とスタイルを指定してフォントを登録する関数.
	 * <p>
	 * フォント名にはフォントフェース名またはフォントファミリ名を指定すること<br>
	 * フォントのスタイルにはjava.awt.Fontのフィールドを用いること
	 * </p>
	 * 
	 * @param name 登録するフォント名
	 * @param style 登録するフォントのスタイル
	 * @return フォント登録番号
	 */
	public int makeFont(String name, int style, int size) {
		font.add(new Font(name, style, size));
		return font.size() - 1;
	}

	/**
	 * Fontクランからフォントを登録する関数.
	 * <p>
	 * 必要に応じて作成したjava.awt.Fontを引き渡すこと
	 * </p>
	 * 
	 * @param f 登録するフォント本体
	 * @return フォント登録番号
	 */
	public int makeFont(Font f) {
		font.add(f);
		return font.size() - 1;
	}

	/**
	 * 登録した画像の横幅を確認する関数.
	 * <p>
	 * 指定された番号が未登録の場合は-1が返される
	 * </p>
	 * 
	 * @param id 確認する画像の番号
	 * @return 指定された画像の横幅
	 */
	public int getImageWidth(int id) {
		try {
			return image.get(id).getWidth(null);
		} catch (IndexOutOfBoundsException e) {
			return -1;
		}
	}

	/**
	 * 登録した画像の縦幅を確認する関数.
	 * <p>
	 * 指定された番号が未登録の場合は-1が返される
	 * </p>
	 * 
	 * @param id 確認する画像の番号
	 * @return 指定された画像の縦幅
	 */
	public int getImageHeight(int id) {
		try {
			return image.get(id).getHeight(null);
		} catch (IndexOutOfBoundsException e) {
			return -1;
		}
	}
}

// Viewクラスで実行する描画インターフェース
interface DrawStack {
	void draw(Graphics g);
}

class DrawImage implements DrawStack {
	private Image	image;
	private int		dx, dy, rx, ry, w, h;

	public DrawImage(Image I, int DX, int DY, int RX, int RY, int W, int H) {
		image = I;
		dx = DX;
		dy = DY;
		rx = RX;
		ry = RY;
		w = W;
		h = H;
	}

	@ Override
	public void draw(Graphics g) {
		g.drawImage(image, dx, dy, dx + w, dy + h, rx, ry, rx + w, ry + h, null);
	}
}

class DrawString implements DrawStack {
	private String	s;
	private Color		c;
	private Font		f;
	private int			x, y;

	public DrawString(String S, Color C, Font F, int X, int Y) {
		s = S;
		c = C;
		f = F;
		x = X;
		y = Y;
	}

	@ Override
	public void draw(Graphics g) {
		g.setColor(c);
		g.setFont(f);
		g.drawString(s, x, y);
	}
}

class DrawLine implements DrawStack {
	private Color	c;
	private int		x1, y1, x2, y2;

	public DrawLine(Color C, int X1, int Y1, int X2, int Y2) {
		c = C;
		x1 = X1;
		y1 = Y1;
		x2 = X2;
		y2 = Y2;
	}

	@ Override
	public void draw(Graphics g) {
		g.setColor(c);
		g.drawLine(x1, y1, x2, y2);
	}

}

class DrawOval implements DrawStack {
	private boolean	fill;
	private Color		c;
	private int			x, y, w, h;

	public DrawOval(boolean isFill, Color C, int X, int Y, int W, int H) {
		fill = isFill;
		c = C;
		x = X;
		y = Y;
		w = W;
		h = H;
	}

	@ Override
	public void draw(Graphics g) {
		g.setColor(c);
		if (fill) g.fillOval(x, y, w, h);
		else g.drawOval(x, y, w, h);
	}
}

class DrawArc implements DrawStack {
	private boolean	fill;
	private Color		c;
	private int			x, y, w, h;
	private int			start, arc; // about angle

	public DrawArc(boolean isFill, Color C, int X, int Y, int W, int H, int S,
			int A) {
		fill = isFill;
		c = C;
		x = X;
		y = Y;
		w = W;
		h = H;
		start = S;
		arc = A;
	}

	@ Override
	public void draw(Graphics g) {
		g.setColor(c);
		if (fill) g.fillArc(x, y, w, h, start, arc);
		else g.drawArc(x, y, w, h, start, arc);
	}
}

class DrawRect implements DrawStack {
	private boolean	fill;
	private Color		c;
	private int			x, y, w, h;

	public DrawRect(boolean isFill, Color C, int X, int Y, int W, int H) {
		fill = isFill;
		c = C;
		x = X;
		y = Y;
		w = W;
		h = H;
	}

	@ Override
	public void draw(Graphics g) {
		g.setColor(c);
		if (fill) g.fillRect(x, y, w, h);
		else g.drawRect(x, y, w, h);
	}
}

class DrawRoundRect implements DrawStack {
	private boolean	fill;
	private Color		c;
	private int			x, y, w, h;
	private int			arcW, arcH;

	public DrawRoundRect(boolean isFill, Color C, int X, int Y, int W, int H,
			int AW, int AH) {
		fill = isFill;
		c = C;
		x = X;
		y = Y;
		w = W;
		h = H;
		arcW = AW;
		arcH = AH;
	}

	@ Override
	public void draw(Graphics g) {
		g.setColor(c);
		if (fill) g.fillRoundRect(x, y, w, h, arcW, arcH);
		else g.drawRoundRect(x, y, w, h, arcW, arcH);
	}
}

class DrawPolygon implements DrawStack {
	private boolean	fill;
	private Color		c;
	private Polygon	p;

	public DrawPolygon(boolean isFill, Color C, Polygon P) {
		fill = isFill;
		c = C;
		p = P;
	}

	@ Override
	public void draw(Graphics g) {
		g.setColor(c);
		if (fill) g.fillPolygon(p);
		else g.drawPolygon(p);
	}
}

/*
 * class DrawStack { public static final int IMAGE = 0, LINE = 1, STRING = 2,
 * LINE_ARC = 10, FULL_ARC = 11, LINE_OVAL = 20, FULL_OVAL = 21, LINE_RECT = 30,
 * FULL_RECT = 31, LINE_ROUND_RECT = 40, FULL_ROUND_RECT = 41, LINE_POLYGON =
 * 50, FULL_POLYGON = 51;
 * 
 * // arg1 = sourceX (@Image) startAngle (@Arc) or arcWidth (@RoundRect) // arg2
 * = sourceY (@Image) arcAngle (@Arc) or arcHeight (@RoundRect) private int
 * type, x, y, w, h, arg1, arg2; private Color c; private Image image; private
 * Polygon polygon; private String str;
 * 
 * DrawStack (int T, Image I, Polygon P, String str, Color C, int X, int Y, int
 * W, int H, int A1, int A2) { type = T; image = I; polygon = P; c = C; x = X; y
 * = Y; w = W; h = H; arg1 = A1; arg2 = A2; }
 * 
 * void draw (Graphics g) { switch (type) { case IMAGE: g.drawImage(image, x, y,
 * x + w, y + h, arg1, arg2, arg1 + w, arg2 + h, null); break; case LINE:
 * 
 * break; case STRING:
 * 
 * break; case LINE_ARC:
 * 
 * break; case FULL_ARC:
 * 
 * break; case LINE_OVAL:
 * 
 * break; case FULL_OVAL:
 * 
 * break; case LINE_RECT:
 * 
 * break; case FULL_RECT:
 * 
 * break; case LINE_ROUND_RECT:
 * 
 * break; case FULL_ROUND_RECT:
 * 
 * break; case LINE_POLYGON:
 * 
 * break; case FULL_POLYGON:
 * 
 * break; } } }
 */
