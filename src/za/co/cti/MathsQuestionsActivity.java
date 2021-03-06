package za.co.cti;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MathsQuestionsActivity extends Activity implements OnClickListener {

	private TextView question;
	private TextView summary;
	private EditText answer;
	private Button answerButton;

	private int answerValue;
	private int operation;
	private String questionText;
	private int operand1Value;
	private int operand2Value;

	private int noQuestions = 3;
	private int noAnswered;
	private int noCorrect;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maths);
		Log.i("ARITH", "Started");

		question = (TextView) findViewById(R.id.maths_question_text);
		answer = (EditText) findViewById(R.id.maths_answer_text);
		summary = (TextView) findViewById(R.id.maths_summary);
		answerButton = (Button) findViewById(R.id.maths_answer_button);
		answerButton.setOnClickListener(this);
		noAnswered = 0;
		noCorrect = 0;
		displaySummary();
		generateQuestion();
	}

	private String getSummary() {
		return noCorrect + " answers out of " + noAnswered + " attempted of "
				+ noQuestions + " questions";
	}

	private void displaySummary() {
		summary.setText(getSummary());
	}

	private void generateQuestion() {
		operation = (int) (Math.random() * 3.0);
		operand1Value = (int) (Math.random() * 19.0) + 1;
		operand2Value = (int) (Math.random() * 19.0) + 1;
		switch (operation) {
		case 0:
			questionText = operand1Value + " + " + operand2Value;
			answerValue = operand1Value + operand2Value;
			break;
		case 1:
			questionText = operand1Value + " x " + operand2Value;
			answerValue = operand1Value * operand2Value;
			break;
		case 2:
			if (operand1Value < operand2Value) {
				answerValue = operand2Value;
				operand2Value = operand1Value;
				operand1Value = answerValue;
			}
			questionText = operand1Value + " - " + operand2Value;
			answerValue = operand1Value - operand2Value;
			break;
		default:
			break;
		}
		question.setText(questionText);
	}

	public void onClick(View arg0) {
		if (arg0 == answerButton) {
			int response = 0;
			noAnswered++;
			try {
				response = Integer.decode(answer.getText().toString());
				if (response == answerValue) {
					noCorrect++;
				}
			} catch (NumberFormatException nfe) {
				// can't be a correct answer, so ignore it!
			}
			answer.setText("");
			displaySummary();

			if (noAnswered < noQuestions) {
				generateQuestion();
			} else {
				answer.setText("");
				displaySummary();

				// Set the next activity as QuizActivity to return to the main
				// menu
				Intent nextScreen = new Intent(getApplicationContext(),
						QuizActivity.class);

				//Retrieve summaries and add them to next the intent
				String multiSum = getIntent().getStringExtra("multiSummary");
				String progSum = getIntent().getStringExtra("progSummary");

				if (multiSum != null && !"".equals(multiSum)) {
					nextScreen.putExtra("multiSummary", multiSum);
				}
				if (progSum != null && !"".equals(progSum)) {
					nextScreen.putExtra("progSummary", progSum);
				}

				String result = getSummary() + " for the last math quiz.";
				nextScreen.putExtra("mathSummary", result);

				startActivity(nextScreen);
			}
		}
	}
}