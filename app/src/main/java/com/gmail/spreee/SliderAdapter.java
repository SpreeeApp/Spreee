package com.gmail.spreee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {

        this.context = context;

    }

    //Arrays

    public int[] slide_images = {

            R.drawable.online,
            R.drawable.service,
            R.drawable.delivery,

    };

    public String[] slide_headings = {

            "SHOP ONLINE",
            "GET FULL SERVICES",
            "GET DELIVERY",

    };

    public String[] slide_descs = {

            "Shop online on your device from your" +
                    "                    " +
                    " favourite store without  having  to go there" +
                    "         physically.",

            "Get full store services like packaging " +
                    "                            " +
                    "and loyalty points redemption without any" +
                    "                         extra costs.",

            "Get your shopping delivered to your" +
                    "                           " +
                    "doorstep in the shortest time possible",

    };
    //Arrays

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == (RelativeLayout) o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container,   false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView) view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);

        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((RelativeLayout)object);
    }
}
