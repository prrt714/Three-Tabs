package com.example.tabs3;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.threetabs.R;

public class Fragment1 extends Fragment {

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		((WebView) getView().findViewById(R.id.webview))
				.loadUrl("http://m.yandex.ru");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final WebView webview = (WebView) inflater.inflate(R.layout.f1,
				container, false);

		webview.setWebViewClient(new WebViewClient() {
			ProgressDialog progressDialog = null;

			public void onLoadResource(WebView view, String url) {
				if (progressDialog == null) {
					progressDialog = new ProgressDialog(getActivity());
					progressDialog.setMessage("Loading...");
					progressDialog.show();
				}
			}

			public void onPageFinished(WebView view, String url) {
				try {
					if (progressDialog.isShowing()) {
						progressDialog.dismiss();
						progressDialog = null;
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}

			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				webview.stopLoading();
				onPageFinished(null, "");
			}

		});
		return webview;
	}
}