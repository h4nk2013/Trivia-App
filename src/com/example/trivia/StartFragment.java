package com.example.trivia;



import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class StartFragment extends Fragment {
	
	private Button mPlay;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(R.layout.start_fragment, container, false);
		mPlay = (Button) mLinearLayout.findViewById(R.id.playBtn);
		mPlay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((TriviaActivity)getActivity()).nextQuestion();
			}
		});
		
		// TODO Auto-generated method stub
		return mLinearLayout;
	}
	
	


}
