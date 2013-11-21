package com.example.trivia;

import org.json.JSONException;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

public class TrueFalseFragment extends Fragment{
	
	private RadioGroup RadioTF;
	private RadioButton True, False;
	private Button next, submit;
	private TextView mQuestion;
	private static String question, questionType, truefalse, imageUrl, answer ;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		
		LinearLayout mcqLinearLayout = (LinearLayout) inflater.inflate(R.layout.truefalse_fragment, container, false);
		
		RadioTF = (RadioGroup) mcqLinearLayout.findViewById(R.id.truefalse);
		next = (Button) mcqLinearLayout.findViewById(R.id.nextBtn);
		submit = (Button) mcqLinearLayout.findViewById(R.id.submitBtn);
		True = (RadioButton) mcqLinearLayout.findViewById(R.id.True);
		False = (RadioButton) mcqLinearLayout.findViewById(R.id.False);
		mQuestion = (TextView) mcqLinearLayout.findViewById(R.id.TFQ);
		mQuestion.setText(question);
		
		SmartImageView image = (SmartImageView) mcqLinearLayout.findViewById(R.id.image);
		image.setImageUrl(imageUrl);	
		
		
		next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				((TriviaActivity)getActivity()).nextQuestion();
			}
	
		});
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		return mcqLinearLayout;
		
	}
	public void setFields(String quest, String correct, String URL, String correctAnswer){
		question = quest;
		truefalse = correct;
		imageUrl = URL;
		answer = correctAnswer;
		
		
	}


}
