package edu.hpu.csci4702;

import java.util.EmptyStackException;
import java.util.Stack;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GamePlayViewController extends Activity {
	static SoundPool audio;
	static int delete;
	static int falses;
	static int haha;
	static int key;
	static int recall;
	static int shuffle;
	static int trues;

	static myTimer countDownTimer;
	private final static long startTime = 60000;
	private final long interval = 1000;
	TextView letter1, letter2, letter3, letter4, letter5, letter6, dynamite,
			notifications, timer;
	Button b1, b2, b3, b4, b5, b6;
	WordMasterBrain brain;
	String set;
	String wordToSend = "";
	Button[] buttons = new Button[6];
	TextView[] textviews = new TextView[6];
	Stack<Button> buttonsToDelete = new Stack<Button>();
	static long currentTime = startTime;

	WordMasterBrain getBrain() {
		if (this.brain == null) {
			this.brain = new WordMasterBrain();
		}
		return this.brain;
	}

	TextView getTimer() {
		if (timer == null) {
			timer = (TextView) findViewById(R.id.timer);
		}
		return notifications;
	}

	TextView getNotification() {
		if (notifications == null) {
			notifications = (TextView) findViewById(R.id.notification);
		}
		return notifications;
	}

	TextView getLetter1() {
		if (letter1 == null) {
			letter1 = (TextView) findViewById(R.id.Letter1);
		}
		return letter1;
	}

	TextView getLetter2() {
		if (letter2 == null) {
			letter2 = (TextView) findViewById(R.id.Letter2);
		}
		return letter2;
	}

	TextView getLetter3() {
		if (letter3 == null) {
			letter3 = (TextView) findViewById(R.id.Letter3);
		}
		return letter3;
	}

	TextView getLetter4() {
		if (letter4 == null) {
			letter4 = (TextView) findViewById(R.id.Letter4);
		}
		return letter4;
	}

	TextView getLetter5() {
		if (letter5 == null) {
			letter5 = (TextView) findViewById(R.id.Letter5);
		}
		return letter5;
	}

	TextView getLetter6() {
		if (letter6 == null) {
			letter6 = (TextView) findViewById(R.id.Letter6);
		}
		return letter6;
	}

	TextView getDynamiteNumber() {
		if (dynamite == null) {
			dynamite = (TextView) findViewById(R.id.dynamiteNumber);
		}
		return dynamite;
	}

	Button getButton1() {
		if (b1 == null) {
			b1 = (Button) findViewById(R.id.b1);
		}
		return b1;
	}

	Button getButton2() {
		if (b2 == null) {
			b2 = (Button) findViewById(R.id.b2);
		}
		return b2;
	}

	Button getButton3() {
		if (b3 == null) {
			b3 = (Button) findViewById(R.id.b3);
		}
		return b3;
	}

	Button getButton4() {
		if (b4 == null) {
			b4 = (Button) findViewById(R.id.b4);
		}
		return b4;
	}

	Button getButton5() {
		if (b5 == null) {
			b5 = (Button) findViewById(R.id.b5);
		}
		return b5;
	}

	Button getButton6() {
		if (b6 == null) {
			b6 = (Button) findViewById(R.id.b6);
		}
		return b6;
	}

	void setButtonArray() {
		buttons[0] = getButton1();
		buttons[1] = getButton2();
		buttons[2] = getButton3();
		buttons[3] = getButton4();
		buttons[4] = getButton5();
		buttons[5] = getButton6();
	}

	void setTextViewArray() {
		textviews[0] = getLetter1();
		textviews[1] = getLetter2();
		textviews[2] = getLetter3();
		textviews[3] = getLetter4();
		textviews[4] = getLetter5();
		textviews[5] = getLetter6();
	}

	String shuffle(String s) {
		String shuffledString = "";

		// rearranges the order of the characters in the string
		while (s.length() != 0) {
			int index = (int) Math.floor(Math.random() * s.length());
			char c = s.charAt(index);
			s = s.substring(0, index) + s.substring(index + 1);
			shuffledString += c;
		}

		return shuffledString;
	}

	public void pausePressed(View v) {
		countDownTimer.cancel(); // stops timer, later will be restarted
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setClassName(this, PauseViewController.class.getName());
		this.startActivityForResult(intent, 0);
	}

	// buttons are pushed on a stack so they can be deleted on a LIFO basis and
	// are set to invisible to prevent additional use
	public void button1Pressed(View v) {
		letterPressed(v);
		v = findViewById(R.id.b1);
		v.setVisibility(View.INVISIBLE);
		buttonsToDelete.push(b1);
	}

	public void button2Pressed(View v) {
		letterPressed(v);
		v = findViewById(R.id.b2);
		v.setVisibility(View.INVISIBLE);
		buttonsToDelete.push(b2);
	}

	public void button3Pressed(View v) {
		letterPressed(v);
		v = findViewById(R.id.b3);
		v.setVisibility(View.INVISIBLE);
		buttonsToDelete.push(b3);
	}

	public void button4Pressed(View v) {
		letterPressed(v);
		v = findViewById(R.id.b4);
		v.setVisibility(View.INVISIBLE);
		buttonsToDelete.push(b4);
	}

	public void button5Pressed(View v) {
		letterPressed(v);
		v = findViewById(R.id.b5);
		v.setVisibility(View.INVISIBLE);
		buttonsToDelete.push(b5);
	}

	public void button6Pressed(View v) {
		letterPressed(v);
		v = findViewById(R.id.b6);
		v.setVisibility(View.INVISIBLE);
		buttonsToDelete.push(b6);
	}

	public void letterPressed(View v) {
		audio.play(key, 1, 1, 1, 0, 1);

		getNotification().setText("");
		// letter pressed will occupy the first empty slot
		if (getLetter1().getText().length() == 0) {
			String letter = (String) ((Button) v).getText();
			getLetter1().setText(Character.toString(letter.charAt(0)));
		} else if (getLetter2().getText().length() == 0) {
			String letter = (String) ((Button) v).getText();
			getLetter2().setText(Character.toString(letter.charAt(0)));
		} else if (getLetter3().getText().length() == 0) {
			String letter = (String) ((Button) v).getText();
			getLetter3().setText(Character.toString(letter.charAt(0)));
		} else if (getLetter4().getText().length() == 0) {
			String letter = (String) ((Button) v).getText();
			getLetter4().setText(Character.toString(letter.charAt(0)));
		} else if (getLetter5().getText().length() == 0) {
			String letter = (String) ((Button) v).getText();
			getLetter5().setText(Character.toString(letter.charAt(0)));
		} else if (getLetter6().getText().length() == 0) {
			String letter = (String) ((Button) v).getText();
			getLetter6().setText(Character.toString(letter.charAt(0)));
		}
	}

	public void shufflePressed(View v) {
		audio.play(shuffle, 1, 1, 1, 0, 1);
		// recalls all letters if user began to enter word
		if (getLetter1().getText().length() != 0) {
			recallPressed(v);
		}
		set = shuffle(set);
		// sets new letters for the buttons with shuffled string
		for (int i = 0; i < 6; i++) {
			buttons[i].setText(Character.toString(set.charAt(i)));
		}
	}

	public void recallPressed(View v) {
		audio.play(recall, 1, 1, 1, 0, 1);
		// sets all buttons back to visible and makes all letter slots blank
		v = findViewById(R.id.b1);
		v.setVisibility(View.VISIBLE);
		v = findViewById(R.id.b2);
		v.setVisibility(View.VISIBLE);
		v = findViewById(R.id.b3);
		v.setVisibility(View.VISIBLE);
		v = findViewById(R.id.b4);
		v.setVisibility(View.VISIBLE);
		v = findViewById(R.id.b5);
		v.setVisibility(View.VISIBLE);
		v = findViewById(R.id.b6);
		v.setVisibility(View.VISIBLE);

		for (int i = 0; i < 6; i++) {
			textviews[i].setText("");
		}
	}

	public void endGame() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setClassName(this, EndGameViewController.class.getName());
		this.startActivity(intent);
	}

	/*
	 * The enterPressed method first appends all of the TextViews together to
	 * get the word to send from the user. It then checks to see if the word is
	 * already used by the user, and if so, the word will not be used. Then it
	 * checks to see if the word is a legit word given the current letter set.
	 * If it is, the number is decremented, and nothing is done if otherwise.
	 */
	public void enterPressed(View v) {

		for (int i = 0; i < 6; i++) {
			String letter = textviews[i].getText().toString();
			wordToSend = wordToSend + letter;
		}

		recallPressed(v);

		if (getBrain().checkIfWordAlreadyExists(wordToSend)) {
			audio.play(haha, 1, 1, 1, 0, 1);
			getNotification().setText(
					"\"" + wordToSend + "\"" + " already used");
		} else if (getBrain().checkWord(wordToSend)) {
			audio.play(trues, 1, 1, 1, 0, 1);
			int dynamiteNum = Integer.parseInt(getDynamiteNumber().getText()
					.toString());
			dynamiteNum -= wordToSend.length();
			if (dynamiteNum <= 0) {
				// game is over
				endGame();
			} else {
				getDynamiteNumber().setText(Integer.toString(dynamiteNum));
				getBrain().addToUsedWords(wordToSend);
			}
		} else if (getBrain().checkWord(wordToSend) == false) {
			audio.play(falses, 1, 1, 1, 0, 1);
			getNotification().setText("\"" + wordToSend + "\"" + " is invalid");
		}

		wordToSend = "";
	}

	public void deletePressed(View v) {

		audio.play(delete, 1, 1, 1, 0, 1);

		// sets first occupied slot from the right blank
		if (getLetter6().getText().length() != 0) {
			getLetter6().setText("");
		} else if (getLetter5().getText().length() != 0) {
			getLetter5().setText("");
		} else if (getLetter4().getText().length() != 0) {
			getLetter4().setText("");
		} else if (getLetter3().getText().length() != 0) {
			getLetter3().setText("");
		} else if (getLetter2().getText().length() != 0) {
			getLetter2().setText("");
		} else if (getLetter1().getText().length() != 0) {
			getLetter1().setText("");
		}
		try {
			// sets the most recently pressed letter button visible again
			buttonsToDelete.pop().setVisibility(View.VISIBLE);
		} catch (EmptyStackException ese) {
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// game resumes from time left when user paused
		countDownTimer = new myTimer(currentTime, interval);
		countDownTimer.start();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		audio = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		delete = audio.load(this.getApplicationContext(), R.raw.delete, 1);
		falses = audio.load(this.getApplicationContext(), R.raw.falses, 1);
		haha = audio.load(this.getApplicationContext(), R.raw.haha, 1);
		key = audio.load(this.getApplicationContext(), R.raw.key, 1);
		recall = audio.load(this.getApplicationContext(), R.raw.recall, 1);
		shuffle = audio.load(this.getApplicationContext(), R.raw.shuffle, 1);
		trues = audio.load(this.getApplicationContext(), R.raw.trues, 1);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameplayview);
		timer = (TextView) this.findViewById(R.id.timer);
		countDownTimer = new myTimer(startTime, interval);
		setButtonArray();
		setTextViewArray();
		set = (String) getBrain().getLetterSet(this.getResources());

		for (int i = 0; i < 6; i++) {
			buttons[i].setText(Character.toString(set.charAt(i)));
		}
		countDownTimer.start();

	}

	public class myTimer extends CountDownTimer {
		public myTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			endGame();
		}

		@Override
		public void onTick(long millisUntilFinished) {
			timer.setText("Time remain: " + (millisUntilFinished / 1000));
			currentTime = millisUntilFinished;
		}
	}
}
