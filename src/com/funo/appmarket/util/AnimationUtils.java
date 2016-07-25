package com.funo.appmarket.util;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class AnimationUtils {

	/**
	 * 开始scale动画
	 */
	public static void scaleAnim(View view, float originalWidth, float originalHeight, float targetWidth,
			float targetHeight, long duration) {
		ObjectAnimator animX = ObjectAnimator.ofFloat(view, "scaleX", originalWidth, targetWidth);
		ObjectAnimator animY = ObjectAnimator.ofFloat(view, "scaleY", originalHeight, targetHeight);
		AnimatorSet set = new AnimatorSet();
		set.setDuration(duration);
		set.playTogether(animX, animY);
		set.start();
	}

}
