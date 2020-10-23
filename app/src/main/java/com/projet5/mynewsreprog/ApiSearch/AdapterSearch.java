package com.projet5.mynewsreprog.ApiSearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projet5.mynewsreprog.R;

import java.util.List;

public class AdapterSearch extends RecyclerView.Adapter<ViewHolderSearch> {

    Context context;
    List<Docs> docsList;

    public AdapterSearch(Context context, List<Docs> docsList) {
        this.context = context;
        this.docsList = docsList;
    }

    @NonNull
    @Override
    public ViewHolderSearch onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_items, parent, false);
        return new ViewHolderSearch(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSearch holder, int position) {
        holder.createItem(docsList.get(position), context);
    }

    @Override
    public int getItemCount() {
        return docsList.size();
    }
}
