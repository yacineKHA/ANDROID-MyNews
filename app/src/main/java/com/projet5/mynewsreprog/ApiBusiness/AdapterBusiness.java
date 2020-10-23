package com.projet5.mynewsreprog.ApiBusiness;

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

public class AdapterBusiness extends RecyclerView.Adapter<ViewHolderBusiness> {

    private List<ResultBusiness> resultBusinessList;
    private Context context;

    public AdapterBusiness(List<ResultBusiness> resultBusinessList, Context context) {
        this.resultBusinessList = resultBusinessList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderBusiness onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_items, parent, false);
        return new ViewHolderBusiness(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderBusiness holder, int position) {
        holder.create(resultBusinessList.get(position), context);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String r = resultBusinessList.get(position).getUrl();
                Intent intent = new Intent(context, WebView.class).putExtra("url", r);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultBusinessList.size();
    }

}
