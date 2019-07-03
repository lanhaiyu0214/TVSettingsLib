package com.example.simpledemo;

import com.example.simpledemo.R;
import com.example.simpledemo.R.id;
import com.example.simpledemo.R.layout;
import com.example.simpledemo.fragments.LanguageFragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class UseLibFragment extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new LanguageFragment()).commit();
		}
		
		/*SimulateFragment fragment = new SimulateFragment();
		FragmentManager fm = this.getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		
		ft.replace(R.id.flayout, fragment).commit();*/
	}
}
