package com.nvisio.mvvm.androidmvvmsamplefirst.basic.view.adapter;

import android.databinding.BindingAdapter;
import android.view.View;

public class CustomBindingAdapter {

    @BindingAdapter("visibleGone")
    public static void ShowHide(View view, boolean show){
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
