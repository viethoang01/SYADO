package com.example.project4;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;

public class TranslateAnimationUtil implements View.OnTouchListener{
    private GestureDetector gestureDetector;

    public TranslateAnimationUtil(Context context,View viewAnim) {
        gestureDetector = new GestureDetector(context,new SimleGestureDetector(viewAnim)) ;
    }



    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent );
    }

    class SimleGestureDetector extends GestureDetector.SimpleOnGestureListener{
        private View view;
        private boolean isFinishAnimation = true;
        public SimleGestureDetector(View viewAnim){
            this.view = viewAnim;
        }

        @Override
        public boolean onScroll(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
            if(distanceY > 0){
                hiddenView();
            }else {
                showView();
            }
            return super.onScroll(e1, e2, distanceX, distanceY);

        }

        private void hiddenView() {
            if(view == null || view.getVisibility() == View.VISIBLE){
                return;
            }
            Animation animationDown = AnimationUtils.loadAnimation(view.getContext(),R.anim.move_down);
            animationDown.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    view.setVisibility(View.VISIBLE);
                    isFinishAnimation = false;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    view.setVisibility(View.GONE);
                    isFinishAnimation = true;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            if(isFinishAnimation){
                view.startAnimation(animationDown);
            }
        }

        private void showView() {
            if(view == null || view.getVisibility() == View.GONE){
                return;
            }
            Animation animationUp = AnimationUtils.loadAnimation(view.getContext(),R.anim.move_up);
            animationUp.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    view.setVisibility(View.VISIBLE);
                    isFinishAnimation = false;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    isFinishAnimation = true;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            if(isFinishAnimation){
                view.startAnimation(animationUp);
            }
        }
    }

}
