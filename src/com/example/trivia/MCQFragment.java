package com.example.trivia;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

public class MCQFragment extends Fragment {
	
	private RadioGroup RadioMcq;
	private RadioButton opt1, opt2, opt3, opt4;
	private Button next, submit;
	private TextView mQuestion;
	private SmartImageView image;
	private static String mQuestionData, option1, option2, option3, option4, URL, answer;
 	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		LinearLayout mcqLinearLayout = (LinearLayout) inflater.inflate(R.layout.mcq_fragment, container, false);

		RadioMcq = (RadioGroup) mcqLinearLayout.findViewById(R.id.mcq);
		next = (Button) mcqLinearLayout.findViewById(R.id.nextBtn);
		submit = (Button) mcqLinearLayout.findViewById(R.id.submitBtn);
		mQuestion = (TextView) mcqLinearLayout.findViewById(R.id.MCQ);
		image = (SmartImageView) mcqLinearLayout.findViewById(R.id.image);
		opt1 = (RadioButton) mcqLinearLayout.findViewById(R.id.opt1);
		opt2 = (RadioButton) mcqLinearLayout.findViewById(R.id.opt2);
		opt3 = (RadioButton) mcqLinearLayout.findViewById(R.id.opt3);
		opt4 = (RadioButton) mcqLinearLayout.findViewById(R.id.opt4);
		mQuestion.setText(mQuestionData);
		image.setImageUrl(URL);

		opt1.setText(option1);
		opt2.setText(option2);
		opt3.setText(option3);
		opt4.setText(option4);
		
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(opt1.isEnabled() && option1.equals(answer)){
					opt1.setTypeface(null, Typeface.BOLD);
				}
				else if(opt2.isEnabled() && option2.equals(answer)){
					opt2.setTypeface(null, Typeface.BOLD);
				}
				else if(opt3.isEnabled() && option3.equals(answer)){
					opt3.setTypeface(null, Typeface.BOLD);
				}
				else if(opt4.isEnabled() && option4.equals(answer)){
					opt4.setTypeface(null, Typeface.BOLD);
				}
			}
		});
		
		
		next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
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
