package com.example.imageslider;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    List<SliderItem> items;
    Handler sliderHandler = new Handler();
    Button right;
    Button left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager2 = findViewById(R.id.viewPagerImageSlider);
        right = findViewById(R.id.right);
        left = findViewById(R.id.left);

        items = new ArrayList<>();
        items.add(new SliderItem(R.drawable.iph4));
        items.add(new SliderItem(R.drawable.iph));
        items.add(new SliderItem(R.drawable.iph3));
        items.add(new SliderItem(R.drawable.iph2));

        viewPager2.setAdapter(new SliderAdapter(items, viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });


        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 3000);
            }
        });


        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRight();
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLeft();
            }
        });
    }


    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    void goToRight(){
        viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
    }
    void goToLeft(){
        viewPager2.setCurrentItem(viewPager2.getCurrentItem() - 1);
    }
}

