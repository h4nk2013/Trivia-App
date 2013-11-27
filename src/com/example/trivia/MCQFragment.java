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

import com.example.trivia.R.raw;
import com.loopj.android.image.SmartImageView;

public class MCQFragment extends Fragment {
	private RadioButton opt1, opt2, opt3, opt4;
	private Button next, submit;
	private TextView mQuestion;
	private SmartImageView image;
	private static String mQuestionData, option1, option2, option3, option4, URL, answer, selectedAnswer="NO ANSWER SELECTED";
	private MediaPlayer mp;
 	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LinearLayout mcqLinearLayout = (LinearLayout) inflater.inflate(R.layout.mcq_fragment, container, false);
		next = (Button) mcqLinearLayout.findViewById(R.id.nextBtn);
		next.setEnabled(false);
		submit = (Button) mcqLinearLayout.findViewById(R.id.submitBtn);
		mQuestion = (TextView) mcqLinearLayout.findViewById(R.id.MCQ);
		image = (SmartImageView) mcqLinearLayout.findViewById(R.id.image);
		opt1 = (RadioButton) mcqLinearLayout.findViewById(R.id.opt1);
		opt2 = (RadioButton) mcqLinearLayout.findViewById(R.id.opt2);
		opt3 = (RadioButton) mcqLinearLayout.findViewById(R.id.opt3);
		opt4 = (RadioButton) mcqLinearLayout.findViewById(R.id.opt4);

		mQuestion.setText(mQuestionData);
		if(URL != "null"){
			image.setImageUrl(URL);
		}
		else{
			mcqLinearLayout.removeView(image);
		}
		opt1.setText(option1);
		opt2.setText(option2);
		opt3.setText(option3);
		opt4.setText(option4);
		opt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectedAnswer = (String) opt1.getText();
				
			}
		});
		opt2.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectedAnswer = (String) opt2.getText();
			}
		});
		opt3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectedAnswer = (String) opt3.getText();
				
			}
		});
		opt4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectedAnswer = (String) opt4.getText();
				
			}
		});
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(opt1.isChecked() || opt2.isChecked() || opt3.isChecked() || opt4.isChecked()){
					next.setEnabled(true);
					submit.setEnabled(false);
					if(selectedAnswer.equals(answer)){
						mp = MediaPlayer.create((TriviaActivity)(getActivity()), raw.scifi002);
						mp.start();
						TriviaActivity.score += 5;
					}
					else{
						mp = MediaPlayer.create((TriviaActivity)(getActivity()), raw.electronics011);
						mp.start();
					}
					if(option1.equals(answer)){
						opt1.setTypeface(null, Typeface.BOLD);
						opt1.setTextColor(0xff00ff00);
						opt2.setTextColor(0xffff0000);
						opt3.setTextColor(0xffff0000);
						opt4.setTextColor(0xffff0000);
					}
					else if(option2.equals(answer)){
						opt2.setTypeface(null, Typeface.BOLD);
						opt2.setTextColor(0xff00ff00);
						opt1.setTextColor(0xffff0000);
						opt3.setTextColor(0xffff0000);
						opt4.setTextColor(0xffff0000);
					}
					else if(option3.equals(answer)){
						opt3.setTypeface(null, Typeface.BOLD);
						opt3.setTextColor(0xff00ff00);
						opt1.setTextColor(0xffff0000);
						opt2.setTextColor(0xffff0000);
						opt4.setTextColor(0xffff0000);
					}
					else if(option4.equals(answer)){
						opt4.setTypeface(null, Typeface.BOLD);
						opt4.setTextColor(0xff00ff00);
						opt1.setTextColor(0xffff0000);
						opt2.setTextColor(0xffff0000);
						opt3.setTextColor(0xffff0000);
					}
				}
				else{
					
					Builder alert = new AlertDialog.Builder(getActivity());
					alert.setMessage("Select one of the options");
					alert.setPositiveButton("OK",null);
					alert.show();
				}
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
		// TODO Auto-generated method stub
		return mcqLinearLayout;
	}
 	public void setFields(String question, String opt1, String opt2, String opt3, String opt4, String url, String ans){
		mQuestionData = question;
		option1 = opt1;
		option2 = opt2;
		option3 = opt3;
		option4 = opt4;
		URL = url;
		answer = ans;
	}
	
	

}
