package ru.vo1kov.scaenicos.model;
import android.content.SharedPreferences;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Получаем данные с сервера или из кэша
 */
public class ModelImpl implements Model {

    private String runSync(SharedPreferences preferences) throws IOException {
        String result = preferences.getString("JSON", null); //примитивная реализация кеша через SharedPreferences

        if(result==null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    //.cache(cache) //использовтаь кеш http клиента не удалось из-за настроек сервера CDN
                    .build();


            Request request = new Request.Builder()
                    .url("http://download.cdn.yandex.net/mobilization-2016/artists.json")
                    .build();


            Response response = client.newCall(request).execute();
            result =  response.body().string();

            if(result!=null) {//если удалось получить ответ запишем в кэш
                SharedPreferences.Editor ed = preferences.edit();
                ed.putString("JSON", result);
                ed.commit();
            }
        }
        return result;
    }


    /**
     * Подписываемся на события получения списка
     */
    @Override
    public Observable<String> getArtists(SharedPreferences preferences) {
        final SharedPreferences preferences1 = preferences;
        return Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        try {
                            sub.onNext(runSync(preferences1));
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
