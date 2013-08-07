package edu.hpu.csci4702;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EndGameViewController extends Activity {
	TextView result;

	TextView getResult() {
		if (result == null) {
			result = (TextView) findViewById(R.id.result);
		}
		return result;
	}

	public void menuPressed(View v) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setClassName(this, TitleScreenViewController.class.getName());
		this.startActivity(intent);
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.endgameview);
		getResult().setText("GAME OVER! TRY AGAIN?");
	}
}