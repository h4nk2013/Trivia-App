package com.example.trivia;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.trivia.R.raw;
import com.loopj.android.image.SmartImageView;
public class TrueFalseFragment extends Fragment{
	private RadioButton True, False;
	private Button next, submit;
	private TextView mQuestion;
	private static String question, truefalse, imageUrl, answer, selectedAnswer = "" ;
	private MediaPlayer mp;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LinearLayout mcqLinearLayout = (LinearLayout) inflater.inflate(R.layout.truefalse_fragment, container, false);
	
		next = (Button) mcqLinearLayout.findViewById(R.id.nextBtn);
		next.setEnabled(false);
		submit = (Button) mcqLinearLayout.findViewById(R.id.submitBtn);
		True = (RadioButton) mcqLinearLayout.findViewById(R.id.True);
		False = (RadioButton) mcqLinearLayout.findViewById(R.id.False);
		mQuestion = (TextView) mcqLinearLayout.findViewById(R.id.TFQ);
		mQuestion.setText(question);
		SmartImageView image = (SmartImageView) mcqLinearLayout.findViewById(R.id.image);
		if(imageUrl != "null"){
			image.setImageUrl(imageUrl);			
		}
		else{
			mcqLinearLayout.removeView(image);			
		}
		True.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectedAnswer = "true";
			}
		});
		False.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectedAnswer = "false";
			}
		});
		next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mp.stop();				
				((TriviaActivity)getActivity()).nextQuestion();
			}
		});
		submit.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(True.isChecked() || False.isChecked()){
					next.setEnabled(true);
					submit.setEnabled(false);
					if (selectedAnswer.equals(truefalse)){
						mp = MediaPlayer.create((TriviaActivity)(getActivity()), raw.scifi002);
						TriviaActivity.score += 5;
						mp.start();
						System.out.println(True.getText());
					}
					else{
						mp = MediaPlayer.create((TriviaActivity)(getActivity()), raw.electronics011);
						mp.start();
						System.out.println(False.getText());
					}
					if(True.equals(truefalse)){
						True.setTypeface(null, Typeface.BOLD);
						False.setTextColor(0xffff0000);
						True.setTextColor(0xff00ff00);
					}
					else{
						False.setTypeface(null, Typeface.BOLD);
						True.setTextColor(0xffff0000);
						False.setTextColor(0xff00ff00);
					}
					Toast.makeText((TriviaActivity)(getActivity()), answer, Toast.LENGTH_LONG).show();
				}
				else{
					Builder alert = new AlertDialog.Builder(getActivity());
					alert.setMessage("Select one of the options");
					alert.setPositiveButton("OK",null);
					alert.show();
				}
			}
		});
		return mcqLinearLayout;	
	}
	public void setFields(String quest, String correct, String URL, String correctAnswer){
		question = quest;
		truefalse = correct;
		imageUrl = URL;
		answer = correctAnswer;
		System.out.println(truefalse);
	}
}
