package com.projet5.mynewsreprog;

import com.projet5.mynewsreprog.ApiBusiness.BusinessApi;
import com.projet5.mynewsreprog.ApiMostViewed.NytApi;
import com.projet5.mynewsreprog.ApiSearch.SearchApi;
import com.projet5.mynewsreprog.ApiTopStories.ApiTopStories;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Interface class to get the http link of the API.
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
