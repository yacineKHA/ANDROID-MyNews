package com.projet5.mynewsreprog.ApiMostViewed;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.projet5.mynewsreprog.FormatDate;
import com.projet5.mynewsreprog.R;


public class MostViewed_ViewHolder extends RecyclerView.ViewHolder {

    private TextView title_text, theme_text, date_text;
    private ImageView image;

    public MostViewed_ViewHolder(@NonNull View itemView) {
        super(itemView);
        title_text = itemView.findViewById(R.id.title_text);
        theme_text = itemView.findViewById(R.id.theme_text);
        date_text = itemView.findViewById(R.id.date_text);
        image = itemView.findViewById(R.id.image);
    }

    public void createList(Result result, Context context) {
        FormatDate formatDate = new FormatDate();
        title_text.setText(result.getTitle());
        theme_text.setText(result.getSection());
        date_text.setText(formatDate.formatDate(result.getPublishedDate()));

        if (result.getMedia().size() > 0 && result.getMedia().get(0).getMediaMetadata().size() > 0 && result.getMedia().get(0).getMediaMetadata().get(0).getUrl() != null) {
            Glide.with(context).load(result.getMedia().get(0).getMediaMetadata().get(0).getUrl()).circleCrop().into(image);
        } else {
            Glide.with(context).load("https://www.extern-dpo.fr/wp-content/themes/consultix/images/no-image-found-360x260.png").circleCrop().into(image);
        }
    }
}