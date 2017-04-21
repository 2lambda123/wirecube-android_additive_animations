package additive_animations.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;

import com.bartoszlipinski.viewpropertyobjectanimator.ViewPropertyObjectAnimator;

import at.wirecube.additiveanimations.additive_animator.AdditiveAnimator;
import at.wirecube.additiveanimations.additiveanimationsdemo.R;
import at.wirecube.additiveanimations.helper.EaseInOutPathInterpolator;

public class TapToMoveDemoFragment extends Fragment {
    FrameLayout rootView;
    View animatedView;
    float rotation = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (FrameLayout) inflater.inflate(R.layout.fragment_tap_to_move_demo, container, false);
        animatedView = rootView.findViewById(R.id.animated_view);

        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getX() < getResources().getDisplayMetrics().widthPixels/2.0) {
                        rotation -= 10;
                    } else {
                        rotation += 10;
                    }
                    if(AdditiveAnimationsShowcaseActivity.ADDITIVE_ANIMATIONS_ENABLED) {
                        AdditiveAnimator.animate(animatedView).x(event.getX()).y(event.getY()).rotation(rotation).setDuration(1000).start();
                    } else {
                        ViewPropertyObjectAnimator.animate(animatedView).x(event.getX()).y(event.getY()).rotation(rotation).setInterpolator(EaseInOutPathInterpolator.create()).setDuration(1000).start();
                    }
                    return true;
                }
                return true;
            }
        });
        return rootView;
    }
}