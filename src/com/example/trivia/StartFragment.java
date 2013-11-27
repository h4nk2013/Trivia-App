package com.example.trivia;



import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StartFragment extends Fragment {
	public static final String PREFS = "allPrefs";
	private Button mPlay;
	private TextView mHighScore;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(R.layout.start_fragment, container, false);
		
		

		mPlay = (Button) mLinearLayout.findViewById(R.id.playBtn);
		if(!TriviaActivity.isDataConnectionAvailable((TriviaActivity)(getActivity()))){
			mPlay.setEnabled(false);
		}
		mPlay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((TriviaActivity)getActivity()).nextQuestion();
			}
		});	
		
		mHighScore = (TextView) mLinearLayout.findViewById(R.id.highScore);

		SharedPreferences allPrefs = getActivity().getSharedPreferences(PREFS, 0);
		int oldScore = allPrefs.getInt("score", 0);
		mHighScore.setText("High Score is " + oldScore );
		
		// TODO Auto-generated method stub
		return mLinearLayout;
	}
}
