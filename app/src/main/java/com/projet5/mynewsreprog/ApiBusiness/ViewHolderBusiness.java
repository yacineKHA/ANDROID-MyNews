package com.projet5.mynewsreprog.ApiBusiness;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.projet5.mynewsreprog.FormatDate;
import com.projet5.mynewsreprog.R;

import java.util.List;

import com.projet5.mynewsreprog.ApiTopStories.Results;

public class ViewHolderBusiness extends RecyclerView.ViewHolder {

    private List<Results> resultsList;
    private TextView title_text, theme_text, date_text;
    private ImageView imageView;


    public ViewHolderBusiness(@NonNull View itemView) {
        super(itemView);

        title_text = itemView.findViewById(R.id.title_text);
        theme_text = itemView.findViewById(R.id.theme_text);
        date_text = itemView.findViewById(R.id.date_text);
        imageView = itemView.findViewById(R.id.image);
    }

    /**
     * @param resultBusiness
     * @param context Context of the class.
     */
    public void create(ResultBusiness resultBusiness, Context context) {
        FormatDate formatDate = new FormatDate();
        title_text.setText(resultBusiness.getTitle());
        theme_text.setText(resultBusiness.getSection());
        date_text.setText(formatDate.FormatDate(resultBusiness.getPublished_date()));

        if (resultBusiness.getMultimedia() != null && resultBusiness.getMultimedia().size() > 0 && resultBusiness.getMultimedia().get(0) != null && resultBusiness.getMultimedia().get(0).getUrl() != null){
            Glide.with(context).load(resultBusiness.getMultimedia().get(0).getUrl()).circleCrop().into(imageView);
        } else {
            Glide.with(context).load("https://www.extern-dpo.fr/wp-content/themes/consultix/images/no-image-found-360x260.png").circleCrop().into(imageView);
        }
    }
}
