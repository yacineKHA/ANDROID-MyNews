package com.projet5.mynewsreprog.ApiTopStories;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.projet5.mynewsreprog.FormatDate;
import com.projet5.mynewsreprog.R;


public class ViewHolderTopStories extends RecyclerView.ViewHolder {

    private RelativeLayout relativeLayout;
    private TextView title_text, theme_text, date_text;
    private ImageView imageView;


    public ViewHolderTopStories(@NonNull View itemView) {
        super(itemView);
        relativeLayout = itemView.findViewById(R.id.relativeLayout);
        title_text = itemView.findViewById(R.id.title_text);
        theme_text = itemView.findViewById(R.id.theme_text);
        date_text = itemView.findViewById(R.id.date_text);
        imageView = itemView.findViewById(R.id.image);
    }

    protected void createHolder(Results results, Context context) {
        FormatDate formatDate = new FormatDate();
        title_text.setText(results.getTitle());
        theme_text.setText(results.getSection());
        date_text.setText(formatDate.formatDate(results.getPublished_date()));

        if (results.getMultimedia() != null && results.getMultimedia().get(0) != null && results.getMultimedia().get(0).getUrl() != null ){

        Glide.with(context).load(results.getMultimedia().get(0).getUrl()).circleCrop().into(imageView);}
        else {
            Glide.with(context).load("https://www.extern-dpo.fr/wp-content/themes/consultix/images/no-image-found-360x260.png").circleCrop().into(imageView);
        }
    }
}
