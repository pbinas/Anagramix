package edu.hpu.csci4702;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

public class TitleScreenViewController extends Activity {
	static SoundPool audio;
	static int happy;
	static int play;

	public void instructions(View v) {
		setContentView(R.layout.instructions);
	}

	public void instructionsBack(View v) {
		setContentView(R.layout.titlescreenview);
	}

	public void playGame(View v) {
		audio.play(play, 1, 1, 1, 0, 1);
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setClassName(this, GamePlayViewController.class.getName());
		this.startActivity(intent);
	}

	public void onCreate(Bundle savedInstanceState) {
		audio = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		play = audio.load(this.getApplicationContext(), R.raw.play, 1);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.titlescreenview);
	}
}
