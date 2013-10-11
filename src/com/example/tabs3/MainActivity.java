/*
 * Test Assignment
 * 
 * */
package com.example.tabs3;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBarActivity;

import com.example.threetabs.R;

public class MainActivity extends ActionBarActivity {
	private FragmentTabHost mTabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_tabs);
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		mTabHost.addTab(mTabHost.newTabSpec("yandex").setIndicator("Yandex"),
				Fragment1.class, null);
		mTabHost.addTab(mTabHost.newTabSpec("map").setIndicator("Google Map"),
				Fragment2.class, null);
		mTabHost.addTab(mTabHost.newTabSpec("image").setIndicator("Image"),
				Fragment3.class, null);
	}
}