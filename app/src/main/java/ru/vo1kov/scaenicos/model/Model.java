package ru.vo1kov.scaenicos.model;

import rx.Observable;

/**
 * Created by vo1kov on 24.04.16.
 */
public interface Model {
        Observable<String> getArtists();
}
