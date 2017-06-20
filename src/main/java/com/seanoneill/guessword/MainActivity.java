package com.seanoneill.guessword;

import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity{
	List<Question> QuestionList;
	int score = 0;
	int picture_id = 0;
	Question currentQ;
	TextView label;
	TextView setScore;
	Button nextPicture;
	ImageView image;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_picture);

		DatabaseCreate db=new DatabaseCreate(this);
		QuestionList =db.getAllQuestions();
		currentQ= QuestionList.get(picture_id);

		image = (ImageView) findViewById(R.id.imageView);
		label =(TextView)findViewById(R.id.label);
		setScore =(TextView)findViewById(R.id.score);

		setPictureView();

		nextPicture =(Button)findViewById(R.id.picture_button);
		nextPicture.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText text = (EditText) findViewById(R.id.editText);
				String value = text.getText().toString().toLowerCase();
				String textbox_answer = value.replaceAll("\\s+", "").toLowerCase();

				String correct_answer = currentQ.getCORRECT_ANSWER().replaceAll("\\s+","");
				if (textbox_answer.equals(correct_answer)) {
					//Toast to user if they picked the right answer
					Toast.makeText(getApplicationContext(), "You are correct",
							Toast.LENGTH_SHORT).show();
					score++; // add one to the score
					String scoretoString = Integer.toString(score); // covert score from int to string to display
					setScore.setText("Score: "+scoretoString);
				}else{
					//Toast to user if they picked the wrong answer
					Toast.makeText(getApplicationContext(), "The Correct answer was: " + currentQ.getCORRECT_ANSWER(),
							Toast.LENGTH_SHORT).show();
				}
				if (picture_id < 5) {
					currentQ = QuestionList.get(picture_id);
						setPictureView();
				} else {
					Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
					Bundle b = new Bundle();
					//get the score ready to pass in a intent
					b.putInt("score", score);
					//pass score to next screen(question) by intent
					intent.putExtras(b);
					startActivity(intent);
					finish();
				}
			}
		});
	}

	private void setPictureView() {
		currentQ = QuestionList.get(picture_id);
		if( picture_id == 0){
			image.setImageResource(R.drawable.beach);
		}
		if( picture_id == 1){
			image.setImageResource(R.drawable.eiffel);
		}
		if( picture_id == 2){
		image.setImageResource(R.drawable.forrest);
		}
		if( picture_id == 3){
			image.setImageResource(R.drawable.school);
		}
		if( picture_id == 4){
			image.setImageResource(R.drawable.house);
		}
			picture_id++;
	}
}
