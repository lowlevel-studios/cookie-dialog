package com.lowlevel.cookiedialog;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class CookieOverlay implements View.OnClickListener {
    /*
     * Private variables
     */
    private View mView;


    /*
     * Listener methods
     */
    @Override
    public void onClick(View v) {
        Context context = v.getContext();

        /* Set preference */
        CookieManager.setPreference(context, true);

        /* Remove view */
        remove();
    }


    /*
     * Private methods
     */
    private View createView(Activity activity) {
        LayoutInflater inflater = activity.getLayoutInflater();

        /* Create view */
        View view = inflater.inflate(R.layout.cd_overlay, null);

        /* Find button */
        View button = view.findViewById(R.id.cd_button);

        /* Set listener */
        button.setOnClickListener(this);

        /* Return view */
        return view;
    }

    private ViewGroup getRootView(Activity activity) {
        /* Get decoration */
        View decor = activity.getWindow().getDecorView();

        /* Find view */
        return (ViewGroup)decor.findViewById(android.R.id.content);
    }


    /*
     * Public methods
     */
    public void add(Activity activity) {
        ViewGroup root = getRootView(activity);

        /* Check root */
        if (root == null)
            return;

        /* Create view */
        mView = createView(activity);

        /* Add view */
        root.addView(mView);

        /* Load animation */
        Animation anim = AnimationUtils.loadAnimation(activity, R.anim.cd_slide_in);

        /* Start animation */
        mView.startAnimation(anim);
    }

    public void remove() {
        /* Check view */
        if (mView == null)
            return;

        /* Load animation */
        Animation anim = AnimationUtils.loadAnimation(mView.getContext(), R.anim.cd_slide_out);

        /* Set listener */
        anim.setAnimationListener(mRemoveListener);

        /* Start animation */
        mView.startAnimation(anim);
    }


    /*
     * Static methods
     */
    public static void show(Activity activity) {
        CookieOverlay overlay = new CookieOverlay();

        /* Add overlay */
        overlay.add(activity);
    }


    /*
     * Private listeners
     */
    private final Animation.AnimationListener mRemoveListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            /* Get parent view */
            ViewGroup parent = (ViewGroup)mView.getParent();

            /* Remove view */
            parent.removeView(mView);

            /* Reset variable */
            mView = null;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    };
}
