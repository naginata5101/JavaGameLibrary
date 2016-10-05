package jp.gr.java_conf.naginata5101.GameLibrary;

//GameLibraryの機能を提供するクラスが実装する必要があるインターフェース
//以下の3つの関数をGameクラスが適切なタイミングで呼び出す（はず！）
public interface GameBody {
	void userInit();

	void mainloop();

	void cleanup();
}
