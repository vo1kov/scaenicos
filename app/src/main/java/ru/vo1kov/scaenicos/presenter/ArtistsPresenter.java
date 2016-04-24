package ru.vo1kov.scaenicos.presenter;

import android.content.SharedPreferences;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import ru.vo1kov.scaenicos.model.Model;
import ru.vo1kov.scaenicos.model.ModelImpl;
import ru.vo1kov.scaenicos.model.data.Artist;
import ru.vo1kov.scaenicos.view.View;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.Subscriptions;

/**
 * Реализация презентера
 */


public class ArtistsPresenter implements Presenter {

    private Model model = new ModelImpl(); //экземпляр модели для получения данных

    private View view; // сслыка на Activity
    private Subscription subscription = Subscriptions.empty();
    private SharedPreferences preferences;
    public ArtistsPresenter(View view, SharedPreferences preferences) {
        this.view = view;
       this.preferences = preferences;

    }

    @Override
    public void onLoad() {//подписываемся на получение данных

        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

        subscription = model.getArtists(preferences).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(String data) {
                        if (data != null && !data.isEmpty()) {

                            ArrayList<Artist> artists = (new GsonBuilder()).create()
                                    .fromJson(data, new TypeToken<ArrayList<Artist>>() {
                                    }.getType());//десереализуем JSON при помощи GSON

                            view.showList(artists);
                        } else {
                            view.showEmptyList();//если ничего не скачается выведем уведомление
                        }
                    }
                });
    }



    @Override
    public void onStop() {//отписываемся при остановке
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}