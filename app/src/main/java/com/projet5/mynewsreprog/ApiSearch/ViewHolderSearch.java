package com.projet5.mynewsreprog.ApiSearch;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.projet5.mynewsreprog.FormatDate;
import com.projet5.mynewsreprog.R;

public class ViewHolderSearch extends RecyclerView.ViewHolder {


    private TextView title, abstractText, dateText;
    private ImageView imageView;


    public ViewHolderSearch(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.theme_text);
        abstractText = itemView.findViewById(R.id.title_text);
        dateText = itemView.findViewById(R.id.date_text);
        imageView = itemView.findViewById(R.id.image);
    }

    public void createItem(Docs docs, Context context){
        FormatDate formatDate = new FormatDate();
        title.setText(docs.getNews_desk());
        abstractText.setText(docs.getLead_paragraph());
        dateText.setText(formatDate.formatDate(docs.getPub_date()));
        Glide.with(context).load("https://lh3.googleusercontent.com/proxy/U7zg1hHiST2RgQ4Td-xDSs7ge5jLGeKMCBEnyyGGfkvqdhIYQF06rDZNDp1KfIoW9xfrOEEZaK0eQ9JdS1aJVrn2p7tleFVNMoJ-Fyce5vof6KajAqnll7kSr7Z6ADpl47BO6KO3cjFQYCDzs2pTJ9b8ANW04FSFHfj5kAHbO7ykTWZFPplnjkoNnX2qvyyQxfkX8NqlLZy648LM3uIuLjZZL4qlrWKfF9KC").circleCrop().into(imageView);
    }
}
