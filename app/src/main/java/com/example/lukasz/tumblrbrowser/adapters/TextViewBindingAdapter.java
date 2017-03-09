package com.example.lukasz.tumblrbrowser.adapters;

import android.databinding.BindingAdapter;
import android.text.Html;
import android.widget.TextView;

/**
 * Created by lukasz on 08.03.2017.
 */

public class TextViewBindingAdapter {

    @BindingAdapter("bind:htmlText")
    public static void setHtmlText(TextView textView, String text) {
        textView.setText(Html.fromHtml(text));
    }

}
