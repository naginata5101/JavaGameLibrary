package jp.gr.java_conf.naginata5101.GameLibrary;

import java.awt.event.KeyEvent;

/**
 * 入力状況を調べられるキーの一覧.
 * <p>
 * InputクラスのgetKeyNum関数におけるキーの指定に使用する
 * </p>
 */
public enum GameKey {
	/** 1 */
	K_1(KeyEvent.VK_1),
	/** 2 */
	K_2(KeyEvent.VK_2),
	/** 3 */
	K_3(KeyEvent.VK_3),
	/** 4 */
	K_4(KeyEvent.VK_4),
	/** 5 */
	K_5(KeyEvent.VK_5),
	/** 6 */
	K_6(KeyEvent.VK_6),
	/** 7 */
	K_7(KeyEvent.VK_7),
	/** 8 */
	K_8(KeyEvent.VK_8),
	/** 9 */
	K_9(KeyEvent.VK_9),
	/** 0 */
	K_0(KeyEvent.VK_0),

	/** A */
	K_A(KeyEvent.VK_A),
	/** B */
	K_B(KeyEvent.VK_B),
	/** C */
	K_C(KeyEvent.VK_C),
	/** D */
	K_D(KeyEvent.VK_D),
	/** E */
	K_E(KeyEvent.VK_E),
	/** F */
	K_F(KeyEvent.VK_F),
	/** G */
	K_G(KeyEvent.VK_G),
	/** H */
	K_H(KeyEvent.VK_H),
	/** I */
	K_I(KeyEvent.VK_I),
	/** J */
	K_J(KeyEvent.VK_J),
	/** K */
	K_K(KeyEvent.VK_K),
	/** L */
	K_L(KeyEvent.VK_L),
	/** M */
	K_M(KeyEvent.VK_M),
	/** N */
	K_N(KeyEvent.VK_N),
	/** O */
	K_O(KeyEvent.VK_O),
	/** P */
	K_P(KeyEvent.VK_P),
	/** Q */
	K_Q(KeyEvent.VK_Q),
	/** R */
	K_R(KeyEvent.VK_R),
	/** S */
	K_S(KeyEvent.VK_S),
	/** T */
	K_T(KeyEvent.VK_T),
	/** U */
	K_U(KeyEvent.VK_U),
	/** V */
	K_V(KeyEvent.VK_V),
	/** W */
	K_W(KeyEvent.VK_W),
	/** X */
	K_X(KeyEvent.VK_X),
	/** Y */
	K_Y(KeyEvent.VK_Y),
	/** Z */
	K_Z(KeyEvent.VK_Z),

	/** - on main keyboard */
	K_MINUS(KeyEvent.VK_MINUS),
	/** ^ */
	K_CARET(KeyEvent.VK_CIRCUMFLEX),
	/** \ */
	K_BACKSLASH(KeyEvent.VK_BACK_SLASH),
	/** ` */
	K_BACKQUOTE(KeyEvent.VK_BACK_QUOTE),
	/** \@ */
	K_AT(KeyEvent.VK_AT),
	/** [ */
	K_LBRACKET(KeyEvent.VK_OPEN_BRACKET),
	/** ] */
	K_RBRACKET(KeyEvent.VK_CLOSE_BRACKET),
	/** ; (Japanese keyboard) */
	K_SEMICOLON(KeyEvent.VK_SEMICOLON),
	/** : */
	K_COLON(KeyEvent.VK_COLON),
	/** ' */
	K_QUOTE(KeyEvent.VK_QUOTE),
	/** , */
	K_COMMA(KeyEvent.VK_COMMA),
	/** . on main keyboard */
	K_PERIOD(KeyEvent.VK_PERIOD),
	/** / on main keyboard */
	K_SLASH(KeyEvent.VK_SLASH),
	/** $ */
	K_DOLLAR(KeyEvent.VK_DOLLAR),
	/** # */
	K_NUMBERSIGN(KeyEvent.VK_NUMBER_SIGN),
	/** € */
	K_EUROSIGN(KeyEvent.VK_EURO_SIGN),

	/** Escape */
	K_ESC(KeyEvent.VK_ESCAPE),
	/** backspace */
	K_BACK(KeyEvent.VK_BACK_SPACE),
	/** Tab */
	K_TAB(KeyEvent.VK_TAB),
	/** Enter on main keyboard */
	K_ENTER(KeyEvent.VK_ENTER),
	/** CapsLock */
	K_CAPSLOCK(KeyEvent.VK_CAPS_LOCK),
	/** Shift key */
	K_SHIFT(KeyEvent.VK_SHIFT),
	/** Control key */
	K_CTRL(KeyEvent.VK_CONTROL),
	/** Windows key */
	K_WIN(KeyEvent.VK_WINDOWS),
	/** Alt Key */
	K_ALT(KeyEvent.VK_ALT),
	/** Space */
	K_SPACE(KeyEvent.VK_SPACE),
	/** Context Menu key */
	K_MENU(KeyEvent.VK_CONTEXT_MENU),

	/** 全角 */
	K_FULL(KeyEvent.VK_FULL_WIDTH),
	/** 半角 */
	K_HALF(KeyEvent.VK_FULL_WIDTH),
	/** 変換 (Japanese keyboard) */
	K_CONVERT(KeyEvent.VK_CONVERT),
	/** 変換 (Japanese keyboard) */
	K_COMPOSE(KeyEvent.VK_COMPOSE),
	/** 無変換 (Japanese keyboard) */
	K_NOCONVERT(KeyEvent.VK_NONCONVERT),
	/** 英数 (Japanese keyboard) */
	K_ALPHANUMERIC(KeyEvent.VK_ALPHANUMERIC),
	/** ひらがな (Japanese keyboard) */
	K_HIRAGANA(KeyEvent.VK_HIRAGANA),
	/** カタカナ (Japanese keyboard) */
	K_KATAKANA(KeyEvent.VK_KATAKANA),
	/** かな (Japanese keyboard) */
	K_KANA(KeyEvent.VK_KANA),
	/** かなロック (Japanese keyboard) */
	K_KANALOCK(KeyEvent.VK_KANA_LOCK),
	/** 日本語-ひらがな (Japanese keyboard) */
	K_JHIRAGANA(KeyEvent.VK_JAPANESE_HIRAGANA),
	/** 日本語-カタカナ (Japanese keyboard) */
	K_JKATAKANA(KeyEvent.VK_JAPANESE_KATAKANA),
	/** 日本語-ローマ字 (Japanese keyboard) */
	K_JROMAN(KeyEvent.VK_JAPANESE_ROMAN),

	/** Function 1 */
	K_F1(KeyEvent.VK_F1),
	/** Function 2 */
	K_F2(KeyEvent.VK_F2),
	/** Function 3 */
	K_F3(KeyEvent.VK_F3),
	/** Function 4 */
	K_F4(KeyEvent.VK_F4),
	/** Function 5 */
	K_F5(KeyEvent.VK_F5),
	/** Function 6 */
	K_F6(KeyEvent.VK_F6),
	/** Function 7 */
	K_F7(KeyEvent.VK_F7),
	/** Function 8 */
	K_F8(KeyEvent.VK_F8),
	/** Function 9 */
	K_F9(KeyEvent.VK_F9),
	/** Function 10 */
	K_F10(KeyEvent.VK_F10),
	/** Function 11 */
	K_F11(KeyEvent.VK_F11),
	/** Function 12 */
	K_F12(KeyEvent.VK_F12),
	/** (NEC PC98) */
	K_F13(KeyEvent.VK_F13),
	/** (NEC PC98) */
	K_F14(KeyEvent.VK_F14),
	/** (NEC PC98) */
	K_F15(KeyEvent.VK_F15),
	/** Function 16 */
	K_F16(KeyEvent.VK_F16),
	/** Function 17 */
	K_F17(KeyEvent.VK_F17),
	/** Function 18 */
	K_F18(KeyEvent.VK_F18),
	/** Function 19 */
	K_F19(KeyEvent.VK_F19),
	/** Function 20 */
	K_F20(KeyEvent.VK_F20),
	/** Function 21 */
	K_F21(KeyEvent.VK_F21),
	/** Function 22 */
	K_F22(KeyEvent.VK_F22),
	/** Function 23 */
	K_F23(KeyEvent.VK_F23),
	/** Function 24 */
	K_F24(KeyEvent.VK_F24),

	/** Print Screen */
	K_PRINTSCREEN(KeyEvent.VK_PRINTSCREEN),
	/** Scroll Lock */
	K_SCROLLLOCK(KeyEvent.VK_SCROLL_LOCK),
	/** Pause */
	K_PAUSE(KeyEvent.VK_PAUSE),
	/** Insert on arrow keypad */
	K_INSERT(KeyEvent.VK_INSERT),
	/** Home on arrow keypad */
	K_HOME(KeyEvent.VK_HOME),
	/** PgUp on arrow keypad */
	K_PAGEUP(KeyEvent.VK_PAGE_UP),
	/** Delete on arrow keypad */
	K_DELETE(KeyEvent.VK_DELETE),
	/** End on arrow keypad */
	K_END(KeyEvent.VK_END),
	/** PgDn on arrow keypad */
	K_PAGEDOWN(KeyEvent.VK_PAGE_DOWN),
	/** UpArrow on arrow keypad */
	K_UP(KeyEvent.VK_UP),
	/** LeftArrow on arrow keypad */
	K_LEFT(KeyEvent.VK_LEFT),
	/** DownArrow on arrow keypad */
	K_DOWN(KeyEvent.VK_DOWN),
	/** RightArrow on arrow keypad */
	K_RIGHT(KeyEvent.VK_RIGHT),

	/** 0 on numeric keyboard */
	K_NUMPAD0(KeyEvent.VK_NUMPAD0),
	/** 1 on numeric keyboard */
	K_NUMPAD1(KeyEvent.VK_NUMPAD1),
	/** 2 on numeric keyboard */
	K_NUMPAD2(KeyEvent.VK_NUMPAD2),
	/** 3 on numeric keyboard */
	K_NUMPAD3(KeyEvent.VK_NUMPAD3),
	/** 4 on numeric keyboard */
	K_NUMPAD4(KeyEvent.VK_NUMPAD4),
	/** 5 on numeric keyboard */
	K_NUMPAD5(KeyEvent.VK_NUMPAD5),
	/** 6 on numeric keyboard */
	K_NUMPAD6(KeyEvent.VK_NUMPAD6),
	/** 7 on numeric keyboard */
	K_NUMPAD7(KeyEvent.VK_NUMPAD7),
	/** 8 on numeric keyboard */
	K_NUMPAD8(KeyEvent.VK_NUMPAD8),
	/** 9 on numeric keyboard */
	K_NUMPAD9(KeyEvent.VK_NUMPAD9),
	/** Numlock */
	K_NUMLOCK(KeyEvent.VK_NUM_LOCK),
	/** / on numeric keypad */
	K_DIVIDE(KeyEvent.VK_DIVIDE),
	/** * on numeric keypad */
	K_MULTIPLY(KeyEvent.VK_MULTIPLY),
	/** - on numeric keypad */
	K_SUBTRACT(KeyEvent.VK_SUBTRACT),
	/** + on numeric keypad */
	K_ADD(KeyEvent.VK_ADD),
	/** . on numeric keypad */
	K_DECIMAL(KeyEvent.VK_DECIMAL);

	private int	code;

	private GameKey(int k) {
		code = k;
	}

	/**
	 * KeyTypeの要素数を得る関数.
	 * 
	 * @return KeyTypeの要素数
	 * */
	public static int size() {
		return GameKey.values().length;
	}

	/**
	 * コードに対応するKeyTypeを得る関数.
	 * 
	 * @return 対応するKeyType
	 * */
	public static GameKey getKeyType(int code) {
		for (GameKey key: GameKey.values())
			if (code == key.code) return key;
		return null;
	}

	/**
	 * KeyTypeに対応するコードを得る関数.
	 * 
	 * @return Controllerに対応したコード
	 * */
	public int getKeyCode() {
		return code;
	}
}
