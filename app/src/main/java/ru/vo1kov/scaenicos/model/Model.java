package ru.vo1kov.scaenicos.model;

import android.content.SharedPreferences;

import rx.Observable;

/**
 * Интерфейс модели
 */
public interface Model {
        Observable<String> getArtists(SharedPreferences preferences);//получаем строку с JSON ответом сервера
}
