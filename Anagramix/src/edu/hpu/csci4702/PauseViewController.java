package edu.hpu.csci4702;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PauseViewController extends Activity {
	public void resumePressed(View v) {
		finish();
	}

	public void menuPressed(View v) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setClassName(this, TitleScreenViewController.class.getName());
		this.startActivity(intent);
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pauseview);
	}
}
