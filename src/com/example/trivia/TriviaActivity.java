package com.example.trivia;

import java.util.Random;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

public class TriviaActivity extends Activity {
	
	public static final String PREFS = "allPrefs";
	public static int COUNT = 0;
	private static JSONObject mQuestionSet; 
	private static JSONArray mQuestions;
	private static String QASet;
	private static JSONArray randQuestions = jsonData();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trivia);
//		Call start fragment
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		StartFragment startFragment = new StartFragment();
		ft.add(R.id.MainLay, startFragment);
		ft.commit();
//		Set Action Title
		highScore();		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.trivia, menu);
		return true;
	}
	
	public static JSONArray jsonData(){
		try {
			QASet = new JsonParser().execute("https://dl.dropboxusercontent.com/u/8606210/trivia.json").get();
			mQuestionSet = new JSONObject(QASet);
			mQuestions = mQuestionSet.getJSONArray("questions");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Shuffle JSONArray
		Random rnd = new Random();
        for (int i = mQuestions.length() - 1; i >= 0; i--){
	        int j = rnd.nextInt(i + 1);
	        // Simple swap
	          
	        try {
	        	JSONObject object = mQuestions.getJSONObject(j);
				mQuestions.put(j, mQuestions.getJSONObject(i));
				mQuestions.put(i, object);
	        } catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          
        }
        return mQuestions;
	}
	
	public void startFragment(){
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		StartFragment startFragment = new StartFragment();
		ft.replace(R.id.MainLay, startFragment);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		ft.addToBackStack(null);
		ft.commit();
	}
	
	public void mcqFragment(JSONObject jObj) throws JSONException{
		
		FragmentManager fm = getFragmentManager();
//		Array of incorrectAnswers
		JSONArray incorrectAnswers = jObj.getJSONArray("incorrectAnswers");
		
		
		FragmentTransaction ft = fm.beginTransaction();
		MCQFragment mcqFragment = new MCQFragment();
		final String [] options = new String[incorrectAnswers.length()+1];
		for(int i = 0; i< incorrectAnswers.length(); i++){
			options[i] = incorrectAnswers.getString(i);
		}
		options[3] = jObj.getString("correctAnswer");
		String[] opts = schuffleOptions(options);
		mcqFragment.setFields(jObj.getString("question"), opts[0], opts[1],
				opts[2], opts[3],jObj.getString("imageUrl"), jObj.getString("correctAnswer"));

		ft.replace(R.id.MainLay, mcqFragment);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		ft.addToBackStack(null);
		ft.commit();
	}
	
	public void trueFalseFragment(JSONObject jObj) throws JSONException{
		
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		TrueFalseFragment tfFragment = new TrueFalseFragment();
		tfFragment.setFields(jObj.getString("question"), jObj.getString("correctAnswer"), jObj.getString("imageUrl"), jObj.getString("answer"));
		ft.replace(R.id.MainLay, tfFragment);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		ft.addToBackStack(null);
		ft.commit();
	}
	
	public void highScore(){
		SharedPreferences allPrefs = getSharedPreferences(PREFS, 0);
		Editor editor = allPrefs.edit();
		editor.putInt("score", 1);
		editor.commit();
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("High Score");
	}
	
	public String[] schuffleOptions(String[] array){
		Random rnd = new Random();
        for (int i = array.length - 1; i > 0; i--){
	        int j = rnd.nextInt(i + 1);
	        // Simple swap
	       	String str = array[j];
	       	array[j] = array[i];
	       	array[i] = str;
        }
        return array;
	}
	
	public void nextQuestion(){
		
	
		while (COUNT< randQuestions.length() ){
			try {
				if(randQuestions.getJSONObject(COUNT).getString("questionType").equals("multipleChoice")){
//				Call to MCQFragment
					mcqFragment(randQuestions.getJSONObject(COUNT));
				}
				else if(randQuestions.getJSONObject(COUNT).getString("questionType").equals("trueFalse")){
//				Call to TrueFalseFragment
					trueFalseFragment(randQuestions.getJSONObject(COUNT));
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		COUNT++;
		if(COUNT > randQuestions.length()){
			Toast.makeText(getBaseContext(), Integer.toString(COUNT), Toast.LENGTH_SHORT).show();
			COUNT = 0;
			randQuestions = jsonData();
			startFragment();
		}

	}

}
