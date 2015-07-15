package com.slashandhyphen.tasterly.FlavorViewStuff;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.slashandhyphen.tasterly.R;


/**
 * Created by ookamijin on 6/26/2015.
 */
public class OmNomView extends RelativeLayout {
    static String TAG = "OmDomView";
    Context context;
    FlavorView originView;
    Button controlButton;
    LayoutListener mLayoutListener;

    //Programmatic constructor
    public OmNomView(Context context) {
        super(context);
        this.context = context;
    }

    //XML constructor
    public OmNomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        mLayoutListener = new LayoutListener();
        getViewTreeObserver().addOnGlobalLayoutListener(mLayoutListener);

        originView = new FlavorView(context);
        originView.setId(View.generateViewId());
        originView.setVisibility(INVISIBLE);
        addView(originView);
    }

    class LayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

        @Override
        public void onGlobalLayout() {
            getViewTreeObserver().removeOnGlobalLayoutListener(this);
            Log.d(TAG, "In global layout listener");
            buildFlavorTree();
        }
    }

    public void buildFlavorTree() {
        originView.setY(this.getHeight() / 2);
        originView.setX(this.getWidth() / 2);

        OneRingGeomancy circleMaker = new OneRingGeomancy(originView, this);
        circleMaker.setDimensions();

        // Add controlButton
        controlButton = new Button(context);
        controlButton.setBackgroundResource(R.drawable.beer_icon);
        controlButton.setLayoutParams(new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        controlButton.setId(View.generateViewId());
        controlButton.setOnClickListener(new ControlButtonHandler());
        controlButton.setX(originView.getX() - controlButton.getWidth() / 2);
        controlButton.setY(originView.getY() - controlButton.getHeight() / 2);
        addView(controlButton);
    }

    public void saveCoords() {
        for(FlavorView child : originView.getChildren()) {
            child.saveCoords();
        }
    }

    public void moveCoords(float dX, float dY) {
        for(FlavorView child : originView.getChildren()) {
            child.moveCoords(dX, dY);
        }
    }

    class ControlButtonHandler implements  View.OnClickListener {
        @Override
        public void onClick(View mButton) {
                Toast.makeText(context, "This will give you some options probably at some point, " +
                        "maybe the same as the yet to be created menu...",
                        Toast.LENGTH_SHORT).show();
        }
    }
}
