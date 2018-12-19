package com.denofdevelopers.www.blogfadeinoutanimation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private Animator animator;
    private static final long ANIMATION_DURATION = 1000L;
    @BindView(R.id.fadeInOutText)
    TextView fadeInOutText;
    @BindView(R.id.startStopButton)
    Button startButton;
    @BindView(R.id.pauseResumeButton)
    Button pauseResumeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        animator = ObjectAnimator.ofFloat(fadeInOutText, View.ALPHA, 0f, 1f);
    }

    @OnClick(R.id.startStopButton)
    public void onStartClick() {
        if (animator != null) {
            if (!animator.isStarted()) {
                fadeInOutAnim();
                startButton.setText(getString(R.string.stop));
                pauseResumeButton.setVisibility(View.VISIBLE);

            } else {
                animator.end();
                fadeInOutText.setVisibility(View.INVISIBLE);
                startButton.setText(getString(R.string.start));
                pauseResumeButton.setVisibility(View.INVISIBLE);
                pauseResumeButton.setText(getString(R.string.pause));
            }
        }
    }

    @OnClick(R.id.pauseResumeButton)
    public void onPauseClick() {
        if (animator != null) {
            if (animator.isStarted() && !animator.isPaused()) {
                animator.pause();
                pauseResumeButton.setText(getString(R.string.resume));
            } else {
                animator.resume();
                pauseResumeButton.setText(getString(R.string.pause));
            }
        }
    }

    private void fadeInOutAnim() {
        fadeInOutText.setVisibility(View.VISIBLE);
        animator.setDuration(ANIMATION_DURATION);
        ((ObjectAnimator) animator).setRepeatMode(ValueAnimator.REVERSE);
        ((ObjectAnimator) animator).setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
    }
}