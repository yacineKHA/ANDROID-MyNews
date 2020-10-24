package com.projet5.mynewsreprog;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.projet5.mynewsreprog.ApiSearch.SearchApi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import static androidx.core.content.ContextCompat.getSystemService;

/**
 * @author Yacine
 * @since 2020
 * Class to set the notifications.
 */
public class MyPeriodicWork extends Worker {

    private static final String CHANNEL_ID = "CHANNEL_1";
    @SuppressLint("SimpleDateFormat")
    private static final DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    private static final int NOTIFICATION_ID = 0;
    private final Preferences preferences = new Preferences(getApplicationContext());
    private final NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
    private Disposable disposable;


    public MyPeriodicWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        if (preferences.loadBoolean()){
            retrofit();
        }

        return Result.success();
    }

    /**
     * Method to set the notifications.
     * @param number Number is the number of news.
     */
    private void setNotifications(int number){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "My News", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("My news notification");
            NotificationManager notificationManager = getSystemService(getApplicationContext(), NotificationManager.class);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("My News")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        if (number == 0){
            notificationBuilder.setContentText("Aucun nouvel article disponible !");
        }
        if (number == 1){
            notificationBuilder.setContentText(number + " nouvel article disponible !");}
        else {
            notificationBuilder.setContentText(number + " nouveaux articles disponibles !");
        }


        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
    }

    /**
     * Method to get the number of new articles with retrofit.
     */
    private void retrofit(){

        Calendar calendar = Calendar.getInstance();
        String currentDate = sdf.format(calendar.getTime());

        Preferences preferences = new Preferences(getApplicationContext());
        String query = preferences.loadPreferences("NOTIFICATION_TEXT");
        String filter = preferences.loadPreferences("NOTIFICATION_SEARCH");
        if (query != null || filter != null){

            this.disposable = NytStreams.streamNotification(query, filter, currentDate).subscribeWith(new DisposableObserver<SearchApi>() {
                @Override
                public void onNext(SearchApi searchApi) {
                    int number = searchApi.getResponse().getDocs().size();
                    setNotifications(number);
                }

                @Override
                public void onError(Throwable e) {
                    Log.e("error on notification", e.toString());
                }

                @Override
                public void onComplete() {
                    Log.i("onComplete notification", "complete");
                }
            });
        }
    }
}
