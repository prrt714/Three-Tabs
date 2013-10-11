package com.example.tabs3;

import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.threetabs.R;

public class Fragment3 extends Fragment {

	ImageView image;
	int[] imageResId = { R.drawable.pr, R.drawable.pr_bw };
	int currentIndex = 0;
	UpdateTask show;

	private class UpdateTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			while (!isCancelled()) {
				publishProgress();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					cancel(true);
				}
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			image.setImageResource(imageResId[++currentIndex % 2]);
			super.onProgressUpdate(values);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		image = (ImageView) inflater.inflate(R.layout.f3, container, false);
		return image;
	}

	@Override
	public void onPause() {
		if (show != null && show.getStatus() == Status.RUNNING) {
			show.cancel(true);
			show = null;
		}
		super.onPause();
	}

	@Override
	public void onResume() {
		if (show == null || show.getStatus() != Status.RUNNING) {
			show = new UpdateTask();
			show.execute();
		}
		super.onResume();
	}

	@Override
	public void onStop() {
		if (show != null && show.getStatus() == Status.RUNNING) {
			show.cancel(true);
			show = null;
		}
		super.onStop();
	}

	@Override
	public void onStart() {
		if (show == null || show.getStatus() != Status.RUNNING) {
			show = new UpdateTask();
			show.execute();
		}
		super.onStart();
	}
}