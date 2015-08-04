package com.lowlevel.cookiedialog.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.lowlevel.cookiedialog.CookieManager;

public class MainActivity extends Activity {
	/*
	 * Private variables
	 */
	private CookieManager mCookieManager = new CookieManager(this);


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* Set content view */
		setContentView(R.layout.activity_main);

		/* Set listener */
		findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/* Show cookie dialog (all users) */
				mCookieManager.show(false);
			}
		});

		/* Show cookie dialog (only for EU users) */
		mCookieManager.showOnce(true);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		/* Cancel manager */
		mCookieManager.cancel();
	}
}
