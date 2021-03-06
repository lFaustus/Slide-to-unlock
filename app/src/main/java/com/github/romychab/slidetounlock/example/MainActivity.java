package com.github.romychab.slidetounlock.example;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.romychab.slidetounlock.ISlideChangeListener;
import com.github.romychab.slidetounlock.ISlideListener;
import com.github.romychab.slidetounlock.SlideLayout;
import com.github.romychab.slidetounlock.example.databinding.ActivityMainBinding;
import com.github.romychab.slidetounlock.example.ios.IosRenderer;
import com.github.romychab.slidetounlock.renderers.ScaleRenderer;
import com.github.romychab.slidetounlock.renderers.TranslateRenderer;
import com.github.romychab.slidetounlock.sliders.Direction;
import com.github.romychab.slidetounlock.sliders.HorizontalSlider;
import com.github.romychab.slidetounlock.sliders.RadialSlider;
import com.github.romychab.slidetounlock.sliders.VerticalSlider;

public class MainActivity
        extends AppCompatActivity
        implements
            ISlideListener {

    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setupSlider1();
        setupSlider2();
        setupSlider3();
        setupSlider4();

        setupIosSlider();
    }

    @Override
    public void onSlideDone(SlideLayout slider, boolean done) {
        if (done) {
            slider.reset();
        }
    }

    private void setupSlider1() {
        mBinding.slide1.setRenderer(new ScaleRenderer());
        mBinding.slide1.setSlider(new HorizontalSlider());
        mBinding.slide1.addSlideListener(this);
    }

    private void setupSlider2() {
        mBinding.slide2.setRenderer(new ScaleRenderer());
        mBinding.slide2.setSlider(new HorizontalSlider());
        mBinding.slide2.setChildId(R.id.slide_child);
        mBinding.slide2.setThreshold(0.9f);
        mBinding.slide2.addSlideChangeListener(new ISlideChangeListener() {
            @Override
            public void onSlideStart(SlideLayout slider) { ; }

            @Override
            public void onSlideChanged(SlideLayout slider, float percentage) {
                mBinding.txtText.setAlpha(1 - percentage);
            }

            @Override
            public void onSlideFinished(SlideLayout slider, boolean done) {
                onSlideDone(slider, done);
            }
        });
    }

    private void setupSlider3() {
        mBinding.slide3.setRenderer(new TranslateRenderer());
        mBinding.slide3.setSlider(new VerticalSlider(Direction.INVERSE));
        mBinding.slide3.setChildId(R.id.slide_child_3);
        mBinding.slide3.addSlideChangeListener(new ISlideChangeListener() {
            @Override
            public void onSlideStart(SlideLayout slider) { ; }

            @Override
            public void onSlideChanged(SlideLayout slider, float percentage) {
                Log.d("TAG", "p = " + percentage);
                mBinding.upArrow.setAlpha(1 - percentage);
                mBinding.upArrow.setScaleX(1 - percentage);
                mBinding.upArrow.setScaleY(1 - percentage);
                mBinding.upArrow.setTranslationY(-slider.getHeight() * percentage / 3);
            }

            @Override
            public void onSlideFinished(SlideLayout slider, boolean done) {
                onSlideDone(slider, done);
            }
        });
    }

    private void setupSlider4() {
        mBinding.slide4.setRenderer(new TranslateRenderer());
        mBinding.slide4.setSlider(new RadialSlider());
        mBinding.slide4.setChildId(R.id.slide_child_4);
        mBinding.slide4.addSlideListener(this);
    }

    private void setupIosSlider() {
        mBinding.iosSlider.addSlideListener(this);
    }
}
