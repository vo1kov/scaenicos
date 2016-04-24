package ru.vo1kov.scaenicos.model;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by vo1kov on 24.04.16.
 */
public class ModelImpl implements Model {

    private String runSync() throws IOException {

        //int cacheSize = 10 * 1024 * 1024; // 10 MiB
        //Cache cache = new Cache(getCacheDir(), cacheSize);

        OkHttpClient client = new OkHttpClient.Builder()
                //.cache(cache) //использовтаь кеш http клиента не удалось изза настроек сервера CDN
                .build();


        Request request = new Request.Builder()
                .url("http://download.cdn.yandex.net/mobilization-2016/artists.json")
                .build();


        //String cachePath = getApplicationContext().getCacheDir().getPath();
        //File cacheFile = new File(cachePath + File.separator + BuildConfig.APPLICATION_ID);

        //DiskCache diskCache = new DiskCache(cacheFile, BuildConfig.VERSION_CODE, 1024 * 1024 * 10);


        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    @Override
    public Observable<String> getArtists() {
        return Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        try {
                            sub.onNext(runSync());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        sub.onCompleted();
                    }
                }
        ).subscribeOn(Schedulers.newThread()).timeout(5, TimeUnit.SECONDS) //таймаут 5 секунд
                .retry(3) ;//делаем 3 попытки
    }
}
