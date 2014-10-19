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

public class QuizActivity 
	extends Activity
	implements OnClickListener
{
	private Button historyButton;
	private Button mathsButton;
	private TextView summary;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        historyButton = (Button) findViewById(R.id.history_button);
        historyButton.setOnClickListener(this);
        mathsButton = (Button) findViewById(R.id.maths_button);
        mathsButton.setOnClickListener(this);
        summary = (TextView) findViewById(R.id.overall_summary);
   }
    
	public void onClick(View arg0)
	{
		
		
		if ( arg0 == historyButton ) {
			Log.i("QUIZ", "History selected");
			Toast.makeText(getApplicationContext(), "History button pressed", Toast.LENGTH_LONG).show();
		}
		if ( arg0 == mathsButton ) {
			Log.i("QUIZ", "Maths selected");
			Toast.makeText(getApplicationContext(), "Maths button pressed", Toast.LENGTH_LONG).show();
			
			Intent intent = new Intent(QuizActivity.this, MathsQuestionsActivity.class);
			QuizActivity.this.startActivity(intent);
			//finish();
			
		}

	}
}