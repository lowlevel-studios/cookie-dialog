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

		/* Set listeners */
		findViewById(R.id.buttonDialog).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/* Show cookie dialog (all users) */
				mCookieManager.show(false, CookieManager.Type.DIALOG);
			}
		});

		findViewById(R.id.buttonOverlay).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/* Show cookie overlay (all users) */
				mCookieManager.show(false, CookieManager.Type.OVERLAY);
			}
		});

		/* Show cookie dialog (only for EU users) */
		mCookieManager.showOnce(true, CookieManager.Type.DIALOG);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		/* Cancel manager */
		mCookieManager.cancel();
	}
}
