package com.gmail.spreee;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.gmail.spreee.RegisterActivity.onResetPasswordFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;

    //////// Banner Slider
    private ViewPager bannerSliderViewPager;
    private List<SliderModel> sliderModelList;
    private int currentPage = 2;
    private Timer timer;
    final private long DELAY_TIME = 3000;
    final private long PERIOD_TIME = 3000;
    //////// Banner Slider

    ///////Start Shopping Button
    private Button startShoppingButton;
    ///////Start Shopping Button

    //////// Strip Ad
    private ImageView stripAdImage;
    private ConstraintLayout stripAdContainer;
    //////// Strip Ad

    //////// Horizontal Product Layout
    private TextView horizontalLayoutTitle;
    private Button horizontalLayoutViewAllBtn;
    private RecyclerView horizontalRecyclerView;
    //////// Horizontal Product Layout

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        categoryRecyclerView = view.findViewById(R.id.category_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        List<CategoryModel> categoryModelList = new ArrayList<CategoryModel>();
        categoryModelList.add(new CategoryModel( "link", "Home"));
        categoryModelList.add(new CategoryModel( "link", "Electronnics"));
        categoryModelList.add(new CategoryModel( "link", "Appliances"));
        categoryModelList.add(new CategoryModel( "link", "Furniture"));
        categoryModelList.add(new CategoryModel( "link", "Fashion"));
        categoryModelList.add(new CategoryModel( "link", "Toys"));
        categoryModelList.add(new CategoryModel( "link", "Stationery"));
        categoryModelList.add(new CategoryModel( "link", "Groceries"));
        categoryModelList.add(new CategoryModel( "link", "Beverages"));
        categoryModelList.add(new CategoryModel( "link", "Cosmetics"));

        categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        //////// Banner Slider
        bannerSliderViewPager = view.findViewById(R.id.banner_slider_view_pager);

        sliderModelList = new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.mipmap.naivas,"#FF4600"));
        sliderModelList.add(new SliderModel(R.mipmap.society,"#ffffff"));
        sliderModelList.add(new SliderModel(R.mipmap.tuskys,"#026103"));

        sliderModelList.add(new SliderModel(R.mipmap.carre,"#ffffff"));
        sliderModelList.add(new SliderModel(R.mipmap.clean,"#ffffff"));
        sliderModelList.add(new SliderModel(R.mipmap.quick3,"#EC1D25"));
        sliderModelList.add(new SliderModel(R.mipmap.ad,"#000000"));
        sliderModelList.add(new SliderModel(R.mipmap.gil,"#ffffff"));
        sliderModelList.add(new SliderModel(R.mipmap.chop,"#ffffff"));
        sliderModelList.add(new SliderModel(R.mipmap.naivas,"#FF4600"));

        sliderModelList.add(new SliderModel(R.mipmap.society,"#ffffff"));
        sliderModelList.add(new SliderModel(R.mipmap.tuskys,"#026103"));
        sliderModelList.add(new SliderModel(R.mipmap.cart,"#D73439"));

        BannerSliderAdapter bannerSliderAdapter = new BannerSliderAdapter(sliderModelList);
        bannerSliderViewPager.setAdapter(bannerSliderAdapter);
        bannerSliderViewPager.setClipToPadding(false);
        bannerSliderViewPager.setPageMargin(20);

        bannerSliderViewPager.setCurrentItem(currentPage);

        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                currentPage = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if (i == ViewPager.SCROLL_STATE_IDLE){
                    pageLooper();
                }
            }
        };
        bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

        startbannerSlideShow();

        bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pageLooper();
                stopbannerSlideShow();
                if (event.getAction() == MotionEvent.ACTION_UP){
                    startbannerSlideShow();
                }
                return false;
            }
        });
        //////// Banner Slider

        ///////Start Shopping Button
        startShoppingButton = view.findViewById(R.id.start_shopping_button);

        startShoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResetPasswordFragment = true;
                testIntent();
            }
        });
        ///////Start Shopping Button

        //////// Strip Ad
        stripAdImage = view.findViewById(R.id.strip_ad_image);
        stripAdContainer = view.findViewById(R.id.strip_ad_container);

        stripAdImage.setImageResource(R.mipmap.phantom);
        stripAdContainer.setBackgroundColor(Color.parseColor("#ffffff"));
        //////// Strip Ad

        //////// Horizontal Product Layout
        horizontalLayoutTitle = view.findViewById(R.id.horizontal_scroll_layout_title);
        horizontalLayoutViewAllBtn = view.findViewById(R.id.horizontal_scroll_view_all_btn);
        horizontalRecyclerView = view.findViewById(R.id.horizontal_scroll_layout_recyclerview);

        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();

        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.chair6, "Fanta","Fanta orange can","Kshs. 99/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.soft1, "Fanta","Fanta orange can","Kshs. 99/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.lamp, "Fanta","Fanta orange can","Kshs. 99/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.soft2, "Fanta","Fanta orange can","Kshs. 99/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.soft4, "Fanta","Fanta orange can","Kshs. 99/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.soft1, "Fanta","Fanta orange can","Kshs. 99/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.soft3, "Fanta","Fanta orange can","Kshs. 99/-"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.soft4, "Fanta","Fanta orange can","Kshs. 99/-"));

        HorizontalProductScrollAdapter horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductScrollModelList);
         LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
         linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
         horizontalRecyclerView.setLayoutManager(linearLayoutManager);

         horizontalRecyclerView.setAdapter(horizontalProductScrollAdapter);
         horizontalProductScrollAdapter.notifyDataSetChanged();

        //////// Horizontal Product Layout

        //////// Grid Product Layout

        TextView gridLayoutTitle = view.findViewById(R.id.grid_product_layout_title);
        Button gridLayoutViewAllBtn = view.findViewById(R.id.grid_product_layout_viewall_btn);
        GridView gridView = view.findViewById(R.id.grid_product_layout_gridview);

        gridView.setAdapter(new GridProductLayoutAdapter(horizontalProductScrollModelList));

        //////// Grid Product Layout


        return view;
    }

    //////// Banner Slider
    private void pageLooper(){
        if(currentPage == sliderModelList.size() - 2){
            currentPage = 2;
            bannerSliderViewPager.setCurrentItem(currentPage, false);
        }
        if(currentPage == 1){
            currentPage = sliderModelList.size() -3;
            bannerSliderViewPager.setCurrentItem(currentPage, false);
        }
    }

    private void startbannerSlideShow(){
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentPage >= sliderModelList.size()){
                    currentPage = 1;
                }
                 bannerSliderViewPager.setCurrentItem(currentPage++,true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },DELAY_TIME, PERIOD_TIME);
    }
    private void stopbannerSlideShow(){
        timer.cancel();
    }
    //////// Banner Slider


    ///////Start Shopping Button
    private void testIntent(){
        Intent testIntent = new Intent(getActivity(),TestActivity.class);
        startActivity(testIntent);
        getActivity().finish();
    }
    ///////Start Shopping Button


}
