package com.mybeats.fixkoro.util;

import android.view.View;

import com.daimajia.slider.library.Animations.BaseAnimationInterface;

import musabbir.mybeats.fixkoro.R;

/**
 * Created by MUSABBIR MAMUN on 12-Jan-18.
 */
public class DescriptionAnimationCustom implements BaseAnimationInterface {
    @Override
    public void onPrepareCurrentItemLeaveScreen(View current) {
        View descriptionLayout = current.findViewById(R.id.description_layout);
        if (descriptionLayout != null) {
            current.findViewById(R.id.description_layout).setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onPrepareNextItemShowInScreen(View next) {
        View descriptionLayout = next.findViewById(R.id.description_layout);
        if (descriptionLayout != null) {
            next.findViewById(R.id.description_layout).setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public void onCurrentItemDisappear(View view) {
        View descriptionLayout = view.findViewById(R.id.description_layout);
        if (descriptionLayout != null) {
            view.findViewById(R.id.description_layout).setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onNextItemAppear(View view) {
        View descriptionLayout = view.findViewById(R.id.description_layout);
        if (descriptionLayout != null) {
            view.findViewById(R.id.description_layout).setVisibility(View.INVISIBLE);
        }
    }
}