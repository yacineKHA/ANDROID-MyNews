package com.projet5.mynewsreprog.ApiTopStories;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projet5.mynewsreprog.R;
import com.projet5.mynewsreprog.WebView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import static com.projet5.mynewsreprog.ApiMostViewed.NytStreams.streamTopStories;


/**
 * @author Yacine
 * @since 2020
 * Fragment to display a list of "Top Stories" articles.
 */
public class Fragment2 extends Fragment {

    private List<Results> resultsList;
    private Disposable disposable;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private AdapterTopStories adapterTopStories;

    public Fragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_2, container, false);
        recyclerView = v.findViewById(R.id.recyclerview2);
        swipeRefreshLayout = v.findViewById(R.id.swipeRefresh2);
        this.resultsList = new ArrayList<>();
        this.setSwipeRefreshLayout();
        executeHttpRequest();
        return v;
    }

    /**
     * Method to set the Adapter, RecyclerView and execute the request of API TopStories.
     */
    private void executeHttpRequest() {
        this.adapterTopStories = new AdapterTopStories(resultsList, this.getActivity());
        this.recyclerView.setAdapter(adapterTopStories);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        Log.i("http", "ok");
        this.disposable = streamTopStories().subscribeWith(new DisposableObserver<ApiTopStories>() {

            @Override
            public void onNext(ApiTopStories apiTopStories) {
                swipeUpdateUi(apiTopStories);
                Log.i("onNext", "Ok");
            }


            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("Error2", e.toString());
            }
        });
    }

    /**
     * Method to execute the method "executeHttpRequest()" with the swipeRefresh.
     */
    private void setSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(this::executeHttpRequest);
    }

    /**
     * Method to update ui with the swipeRefreshLayout.
     * @param apiTopStories apiTopStories is the class of API that will be request.
     */
    private void swipeUpdateUi(ApiTopStories apiTopStories) {
        swipeRefreshLayout.setRefreshing(false);
        resultsList.clear();
        resultsList.addAll(apiTopStories.getResults());
        adapterTopStories.notifyDataSetChanged();
        adapterTopStories.getItemId(-1);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.disposable != null && !this.disposable.isDisposed()) {
            this.disposable.dispose();
        }
    }
}
