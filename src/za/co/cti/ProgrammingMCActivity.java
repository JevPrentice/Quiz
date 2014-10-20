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

//Quiz on programming theory
public class ProgrammingMCActivity extends Activity implements OnClickListener {
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
		setContentView(R.layout.programmingmc);
		Log.i("PMQ", "Programming multiple choice starts");
		opts = (RadioGroup) findViewById(R.id.opts);
		Button sub = (Button) findViewById(R.id.submit);
		question = (TextView) findViewById(R.id.question_text);
		summary = (TextView) findViewById(R.id.programming_summary);
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

	// Creates a vector of multiple choice questions and populates it with
	// programming theory questions
	private void initialiseQuestions() {
		questions = new Vector<MCQuestion>();
		MCQuestion q1 = new MCQuestion(
				"Select the best definition of an object");
		q1.addAnswer("Converts source code to machine code before execution. ",
				false);
		q1.addAnswer(
				"An entity having specific characteristics(variables) and behaviour(Methods)",
				true);
		q1.addAnswer(
				"When a class acquires properties of another class (may add a few of its own).",
				false);
		q1.addAnswer(
				"Two byte character code for multilingual characters. ASCII is a subset of Unicodes",
				false);
		questions.add(q1);
		q1 = new MCQuestion("Select the best definition for Encapsulation");
		q1.addAnswer(
				"A machine code program which can run on a specific platform(hardware/ software) only.",
				false);
		q1.addAnswer(
				"Internet Applets are small programs that are embedded in web pages and are run on the viewers’ machine",
				false);
		q1.addAnswer("Binding and wrapping up of methods and data members",
				true);
		q1.addAnswer(
				"Set of Java programs (interpreter compiler etc.) which converts source code to byte code.",
				false);
		questions.add(q1);
		q1 = new MCQuestion(
				"Which of these keyword must be used to inherit a class");
		q1.addAnswer("super", false);
		q1.addAnswer("this", false);
		q1.addAnswer("extent", false);
		q1.addAnswer("extends", true);
		questions.add(q1);
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

			// Set the next activity as QuizActivity to return to the main menu
			Intent nextScreen = new Intent(getApplicationContext(),
					QuizActivity.class);

			// Make summary avaliable for the other activities
			String s = getSummary() + " for the last programming quiz.";
			nextScreen.putExtra("progSummary", s);

			String mathSum = getIntent().getStringExtra("mathSummary");
			String multiSum = getIntent().getStringExtra("multiSummary");
			if (mathSum != null && !"".equals(mathSum)) {
				nextScreen.putExtra("mathSummary", mathSum);
			}
			if (multiSum != null && !"".equals(multiSum)) {
				nextScreen.putExtra("multiSummary", multiSum);
			}

			startActivity(nextScreen);

		} else {
			generateQuestion(noAnswered);
		}

	}
}
