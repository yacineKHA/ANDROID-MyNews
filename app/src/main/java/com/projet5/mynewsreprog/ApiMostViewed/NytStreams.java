package com.projet5.mynewsreprog.ApiMostViewed;



import java.util.concurrent.TimeUnit;

import com.projet5.mynewsreprog.ApiBusiness.BusinessApi;
import com.projet5.mynewsreprog.ApiSearch.SearchApi;
import com.projet5.mynewsreprog.ApiTopStories.ApiTopStories;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Class to
 */
public class NytStreams {

    public static Observable<NytApi> stream() {
        NytServices nytService = ApiBaseUrl.retrofit.create(NytServices.class);
        return nytService.getMostPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<ApiTopStories> streamTopStories() {
        NytServices nytServices = ApiBaseUrl.retrofit.create(NytServices.class);
        return nytServices.getTopStories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10,TimeUnit.SECONDS);
    }

    public static Observable<BusinessApi> streamBusiness() {
        NytServices nytServices = ApiBaseUrl.retrofit.create(NytServices.class);
        return nytServices.getBusiness()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10,TimeUnit.SECONDS);
    }

    public static Observable<SearchApi> streamSearch(String query, String checkbox, String begin_date, String end_date){
        NytServices nytServices = ApiBaseUrl.retrofit.create(NytServices.class);
        return nytServices.getSearch(query, checkbox, begin_date, end_date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<SearchApi> streamNotification(String query, String checkbox, String begin_date){
        NytServices nytServices = ApiBaseUrl.retrofit.create(NytServices.class);
        return nytServices.getNotification(query, checkbox, begin_date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
}
