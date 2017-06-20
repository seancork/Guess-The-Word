package com.seanoneill.guessword;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;
public class ScoreActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);

		TextView t=(TextView)findViewById(R.id.textResult);
		//get your score
		Bundle b = getIntent().getExtras();
		int score= b.getInt("score");
		//display your score
		 t.setText("\n\n\n\nyour score was: " + score);
	}

}
