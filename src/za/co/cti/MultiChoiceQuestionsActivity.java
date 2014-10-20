package za.co.cti;

import java.util.Vector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MultiChoiceQuestionsActivity extends Activity implements
		OnClickListener {
	private TextView question;
	private TextView summary;
	private Vector<MCQuestion> questions;
	private RadioGroup opts;
	private MCQuestion currentQuestion;
	private int noCorrect;
	private int noAnswered;
	private int noQuestions = 3;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.multichoice);
		Log.i("MCQ", "MultiChoice starts");
		opts = (RadioGroup) findViewById(R.id.opts);
		Button sub = (Button) findViewById(R.id.submit);
		question = (TextView) findViewById(R.id.question_text);
		summary = (TextView) findViewById(R.id.multichoice_summary);
		noCorrect = 0;
		noAnswered = 0;
		sub.setOnClickListener(this);
		initialiseQuestions();
		displaySummary();
		generateQuestion(0);
	}

	private void generateQuestion(int noQ) {
		opts.removeAllViews();
		currentQuestion = questions.get(noQ);
		question.setText(currentQuestion.getQuestion());
		int i = 1;
		for (String ans : currentQuestion.getAnswers()) {
			RadioButton rb = new RadioButton(this);
			rb.setText(ans);
			rb.setId(i++);
			opts.addView(rb);
		}
	}

	private void initialiseQuestions() {
		questions = new Vector<MCQuestion>();
		MCQuestion q1 = new MCQuestion("Who conquered England in 1066?");
		q1.addAnswer("Harold Godwinson", false);
		q1.addAnswer("Edward the Confessor", false);
		q1.addAnswer("William the Conqueror", true);
		q1.addAnswer("Alfred the Great", false);
		questions.add(q1);
		q1 = new MCQuestion("Who first climbed Mount Everest?");
		q1.addAnswer("Edward Whymper", false);
		q1.addAnswer("Edmund Hillary", true);
		q1.addAnswer("Chris Bonnington", false);
		questions.add(q1);
		q1 = new MCQuestion("Who first reached the South Pole?");
		q1.addAnswer("Captain Scott", false);
		q1.addAnswer("Roald Amundsen", true);
		q1.addAnswer("Edmund Hillary", false);
		q1.addAnswer("Robert Peary", false);
		questions.add(q1);
		// more questions could be added
	}

	private String getSummary() {
		return noCorrect + " answers out of " + noAnswered + " attempted of "
				+ noQuestions + " questions";
	}

	private void displaySummary() {
		summary.setText(getSummary());
	}

	public void onClick(View arg0) {
		noAnswered++;
		int id;
		id = opts.getCheckedRadioButtonId();
		if (currentQuestion.isCorrect(id))
			noCorrect++;
		displaySummary();
		if (noAnswered == noQuestions) {
			displaySummary();
			Intent nextScreen = new Intent(getApplicationContext(),
					QuizActivity.class);
			String s = getSummary() + " for the last history quiz.";
			nextScreen.putExtra("multiSummary", s);

			String mathSum = getIntent().getStringExtra("mathSummary");
			if (mathSum != null && !"".equals(mathSum)) {
				nextScreen.putExtra("mathSummary", mathSum);
			}

			startActivity(nextScreen);

			// finish();
		} else {
			generateQuestion(noAnswered);
		}

	}
}