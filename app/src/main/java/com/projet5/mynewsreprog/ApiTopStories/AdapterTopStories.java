package com.projet5.mynewsreprog.ApiTopStories;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projet5.mynewsreprog.R;
import com.projet5.mynewsreprog.WebView;

import java.util.List;



public class AdapterTopStories extends RecyclerView.Adapter<ViewHolderTopStories> {

    private List <Results> resultsList;
    private Context context;

    public AdapterTopStories(List<Results> resultsList, Context context) {
        this.resultsList = resultsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderTopStories onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_items, parent, false);
        return new ViewHolderTopStories(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTopStories holder, int position) {
        holder.createHolder(resultsList.get(position), context);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String r = resultsList.get(position).getShort_url();
                Intent intent = new Intent(context, WebView.class).putExtra("url", r);
                context.startActivity(intent);
                Log.e("poso", "position:" + position + r);
            }
        });
        }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

}
