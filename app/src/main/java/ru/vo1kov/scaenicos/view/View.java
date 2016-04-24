package ru.vo1kov.scaenicos.view;

import java.util.ArrayList;

import ru.vo1kov.scaenicos.model.data.Artist;

/**
 * Created by vo1kov on 24.04.16.
 */
public interface View {
    void showList(ArrayList<Artist> artists);
    void showError(String error);
    void showEmptyList();

}
