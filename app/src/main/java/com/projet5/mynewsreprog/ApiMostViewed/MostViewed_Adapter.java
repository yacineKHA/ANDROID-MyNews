package com.projet5.mynewsreprog.ApiMostViewed;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projet5.mynewsreprog.R;
import com.projet5.mynewsreprog.WebView;

import java.util.List;

public class MostViewed_Adapter extends RecyclerView.Adapter<MostViewed_ViewHolder> {

    private List<Result> resultList;
    private Context context;

    public MostViewed_Adapter(List<Result> resultList, Context context) {
        this.resultList = resultList;
        this.context = context;
    }

    @NonNull
    @Override
    public MostViewed_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_items, parent, false);
        return new MostViewed_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MostViewed_ViewHolder holder, int position) {
        holder.createList(resultList.get(position), context);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String r = resultList.get(position).getUrl();
                Intent intent = new Intent(context, WebView.class).putExtra("url", r);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }
}
