package com.projet5.mynewsreprog.ApiMostViewed;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.projet5.mynewsreprog.ApiBusiness.BusinessApi;
import com.projet5.mynewsreprog.ApiSearch.SearchApi;
import com.projet5.mynewsreprog.ApiTopStories.ApiTopStories;
import com.projet5.mynewsreprog.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.projet5.mynewsreprog.NytStreams.stream;


/**
 * @author Yacine
 * @since 2020
 * Fragment to display a list of "Most Viewed" articles.
 */
public class Fragment1 extends Fragment {
    SwipeRefreshLayout swipeRefreshLayout;
    private Disposable disposable;
    private RecyclerView recyclerView;
    List<Result> resultList;
    NytApi nytApi;
    MostViewed_Adapter adapter;

    public Fragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_1, container, false);
        resultList = new ArrayList<>();
        nytApi = new NytApi();
        recyclerView = v.findViewById(R.id.recyclerview_1);
        swipeRefreshLayout = v.findViewById(R.id.swipeRefresh);

        this.setSwipeRefreshLayout();
        this.executeHttpRequest();
        Log.i("onCreate","c'est ok");
        return v;
    }

    /**
     * Method to set the Adapter, RecyclerView and execute the request of API NytApi.
     */
    private void executeHttpRequest() {
        adapter = new MostViewed_Adapter(this.resultList, this.getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        this.disposable = stream().subscribeWith(new DisposableObserver<NytApi>() {
            @Override
            public void onNext(NytApi nytApi) {
                Log.i("http","c'est ok");
                swipeUpdateUI(nytApi);
                Log.i("httpRequest", "c'est ok");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("error", e.toString());
                Toast toast = Toast.makeText(getActivity(),"Erreur: Veuillez patienter", Toast.LENGTH_LONG);
                toast.show();
            }

            @Override
            public void onComplete() {
                Log.i("complete","c'est ok");
            }
        });
    }

    /**
     * Method to execute the method "executeHttpRequest()" with the swipeRefresh.
     */
    public void setSwipeRefreshLayout(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                executeHttpRequest();

            }
        });
    }


    /**
     * Method to update ui with the swipeRefreshLayout.
     * @param list list is the class of API that will be request.
     */
    private void swipeUpdateUI(NytApi list) {
        // 3 - Stop refreshing and clear actual list
        swipeRefreshLayout.setRefreshing(false);
        resultList.clear();
        resultList.addAll(list.getResults());
        adapter.notifyDataSetChanged();
    }

    private void disposeWhenDestroy() {
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    /**
     * Interface class to get the http link of a API.
     */
    public interface NytServices {

        @GET("svc/mostpopular/v2/viewed/1.json?api-key=xA1AV7KgJwyNgWzuMTgJg5Kfto6xAB7T")
        Observable<NytApi> getMostPopular();

        @GET("svc/topstories/v2/world.json?api-key=xA1AV7KgJwyNgWzuMTgJg5Kfto6xAB7T")
        Observable<ApiTopStories> getTopStories();

        @GET("svc/topstories/v2/business.json?api-key=xA1AV7KgJwyNgWzuMTgJg5Kfto6xAB7T")
        Observable<BusinessApi> getBusiness();

        @GET("svc/search/v2/articlesearch.json?api-key=xA1AV7KgJwyNgWzuMTgJg5Kfto6xAB7T")
        Observable<SearchApi> getSearch(@Query("q") String query,
                                        @Query("fq") String filter,
                                        @Query("begin_date") String begin_date,
                                        @Query("end_date") String end_date);

        @GET("svc/search/v2/articlesearch.json?api-key=xA1AV7KgJwyNgWzuMTgJg5Kfto6xAB7T")
        Observable<SearchApi> getNotification(@Query("q") String query,
                                              @Query("fq") String filter,
                                              @Query("begin_date") String beginDate);
    }
}
