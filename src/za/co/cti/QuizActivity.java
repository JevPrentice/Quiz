package za.co.cti;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity implements OnClickListener {
	private Button historyButton;
	private Button mathsButton;
	private Button programmingButton;

	// TextViews for summaries
	private TextView mathSummary;
	private TextView multiSummary;
	private TextView programmingSummary;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Activate buttons
		historyButton = (Button) findViewById(R.id.history_button);
		historyButton.setOnClickListener(this);
		mathsButton = (Button) findViewById(R.id.maths_button);
		mathsButton.setOnClickListener(this);
		programmingButton = (Button) findViewById(R.id.programming_button);
		programmingButton.setOnClickListener(this);
		mathSummary = (TextView) findViewById(R.id.math_summary);
		multiSummary = (TextView) findViewById(R.id.history_summary);
		programmingSummary = (TextView) findViewById(R.id.programming_summary);

		// Get current intent
		Intent i = getIntent();

		// Get summary values from intent
		String mathSum = i.getStringExtra("mathSummary");
		String multiSum = i.getStringExtra("multiSummary");
		String progSum = i.getStringExtra("progSummary");

		// set the lables if values are present
		if (mathSum != null && !"".equals(mathSum)) {
			mathSummary.setText(mathSum);
		}
		if (multiSum != null && !"".equals(multiSum)) {
			multiSummary.setText(multiSum);
		}
		if (progSum != null && !"".equals(progSum)) {
			programmingSummary.setText(progSum);
		}

	}

	public void onClick(View arg0) {

		// The class to be navigated to
		Class<?> c = null;

		if (arg0 == historyButton) {
			Log.i("QUIZ", "History selected");
			Toast.makeText(getApplicationContext(), "History button pressed",
					Toast.LENGTH_LONG).show();

			// Set var to the MultiChoiceActivity
			c = MultiChoiceQuestionsActivity.class;

		}
		if (arg0 == mathsButton) {
			Log.i("QUIZ", "Maths selected");
			Toast.makeText(getApplicationContext(), "Maths button pressed",
					Toast.LENGTH_LONG).show();

			// Set var to the MathsQuestionsActivity
			c = MathsQuestionsActivity.class;

		}

		if (arg0 == programmingButton) {
			Log.i("QUIZ", "Maths selected");
			Toast.makeText(getApplicationContext(),
					"Programming button pressed", Toast.LENGTH_LONG).show();

			// Set var to the ProgrammingMCActivity
			c = ProgrammingMCActivity.class;

		}

		// Invoke the view that was selected
		Intent nextScreen = new Intent(getApplicationContext(), c);

		// Keep last quiz results in memory by passing vars through to next
		// activity
		Intent i = getIntent();
		String mathSum = i.getStringExtra("mathSummary");
		String multiSum = i.getStringExtra("multiSummary");
		String progSum = i.getStringExtra("progSummary");

		if (mathSum != null && !"".equals(mathSum)) {
			nextScreen.putExtra("mathSummary", mathSum);
		}
		if (multiSum != null && !"".equals(multiSum)) {
			nextScreen.putExtra("multiSummary", multiSum);
		}
		if (progSum != null && !"".equals(progSum)) {
			nextScreen.putExtra("progSummary", progSum);
		}

		// start the activity
		startActivity(nextScreen);
	}
}