package com.projet5.mynewsreprog.ApiBusiness;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.projet5.mynewsreprog.R;

import java.util.ArrayList;
import java.util.List;

import com.projet5.mynewsreprog.ApiMostViewed.NytStreams;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * @author Yacine
 * @since 2020
 * Fragment to display a list of "Business" articles.
 */
public class Fragment3 extends Fragment {

    private List<ResultBusiness> resultsList;
    private Disposable disposable;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private AdapterBusiness adapterBusiness;

    /**
     * Empty public constructor
     */
    public Fragment3() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_3, container, false);
        resultsList = new ArrayList<>();
        recyclerView = v.findViewById(R.id.recyclerview3);
        this.swipeRefreshLayout = v.findViewById(R.id.swipeRefresh3);
        this.setSwipeRefreshLayout();
        this.executeHttpRequest();

        return v;
    }

    /**
     * Method to set the Adapter, RecyclerView and execute the request of API Business.
     */
    private void executeHttpRequest(){
        adapterBusiness = new AdapterBusiness(resultsList, getContext());
        recyclerView.setAdapter(adapterBusiness);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        this.disposable = NytStreams.streamBusiness().subscribeWith(new DisposableObserver<BusinessApi>() {

            @Override
            public void onNext(BusinessApi businessApi) {
                swipeRefreshUi(businessApi);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("onError", e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * Method to execute the method "executeHttpRequest()" with the swipeRefresh.
     */
    private void setSwipeRefreshLayout(){

        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                executeHttpRequest();
            }
        });
    }

    /**
     * Method to update ui with the swipeRefreshLayout.
     * @param businessApi businessApi is the class of API that will be request.
     */
    private void swipeRefreshUi(BusinessApi businessApi){
        this.swipeRefreshLayout.setRefreshing(false);
        this.resultsList.clear();
        this.resultsList.addAll(businessApi.getResults());
        this.adapterBusiness.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (this.disposable != null && !this.disposable.isDisposed()){
            this.disposable.isDisposed();
        }
    }
}
