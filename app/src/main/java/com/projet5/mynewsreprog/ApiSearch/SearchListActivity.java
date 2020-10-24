package com.projet5.mynewsreprog.ApiSearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.projet5.mynewsreprog.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.projet5.mynewsreprog.NytStreams;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Class to get the data of the searchActivity and display it in a recyclerView.
 */
public class SearchListActivity extends AppCompatActivity {

    private Button button;
    private EditText editText;
    private List<Docs> docsList;
    private AdapterSearch adapterSearch;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private Disposable disposable;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.search_text);
        this.setToolbar();
        this.setAdapterSearch();
        this.setSwipeRefreshLayout();
        this.executeHttpRequest();
    }

    /**
     * Method to set the toolbar.
     */
    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Method to set the adapter and recyclerView.
     */
    private void setAdapterSearch() {
        docsList = new ArrayList<>();
        adapterSearch = new AdapterSearch(this, docsList);
        recyclerView = findViewById(R.id.recyclerviewSearch);
        recyclerView.setAdapter(this.adapterSearch);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }


    /**
     * Method to get the bundle that contains the data (dates, search text, checked boxes)
     * and execute the request of API SearchApi with the bundle's data.
     */
    private void executeHttpRequest() {

        Bundle bundle = getIntent().getBundleExtra("name");
        String text = bundle.getString("stringKey");
        String checkbox = bundle.getString("boxKey");
        String begin_date = bundle.getString("begin_date");
        String end_date = bundle.getString("end_date");
        Log.i("checkbox", checkbox);

        this.disposable = NytStreams.streamSearch(text, checkbox, begin_date, end_date).subscribeWith(new DisposableObserver<SearchApi>() {

            @Override
            public void onNext(SearchApi searchApi) {
                updateUi(searchApi);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("errorSearch", e.toString());
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
        swipeRefreshLayout = findViewById(R.id.swipeRefreshSearch);
        swipeRefreshLayout.setOnRefreshListener(this::executeHttpRequest);
    }

    /**
     * Method to update ui with the swipeRefreshLayout.
     * @param searchApi searchApi is the class of API that will be request.
     */
    private void updateUi(SearchApi searchApi) {
        swipeRefreshLayout.setRefreshing(false);
        docsList.clear();
        docsList.addAll(searchApi.getResponse().getDocs());
        adapterSearch.notifyDataSetChanged();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
