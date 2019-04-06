package com.example.twentyone;

import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

public class AnimationManager {
    private static final AnimationManager ourInstance = new AnimationManager();

    public static AnimationManager getInstance() {
        return ourInstance;
    }

    private AnimationManager() {
    }

    public Animation shakeError() {

        //Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);

        TranslateAnimation shake = new TranslateAnimation(-5,5,0,0);
        shake.setDuration(300);
        shake.setRepeatMode(Animation.REVERSE);
        shake.setInterpolator(new CycleInterpolator(3));
        return shake;
    }

    public void hapticError(Context context) {


        Vibrator vibrator= (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);

        if (!vibrator.hasVibrator()) return;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            //VibrationEffect effect = VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE);

            if (!vibrator.hasAmplitudeControl()) return;

            long[] mVibratePattern = new long[]{0, 50, 50, 50, 50, 50};
            int[] mAmplitudes = new int[]{0, 255, 0, 255, 0, 255};

            VibrationEffect effect = VibrationEffect.createWaveform(mVibratePattern, mAmplitudes, -1);
            //VibrationEffect effect = VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE);
            vibrator.vibrate(effect);

        } else {
            long[] mVibratePattern = new long[]{0, 400, 200, 400};
            vibrator.vibrate(mVibratePattern, -1);
        }
    }
}
