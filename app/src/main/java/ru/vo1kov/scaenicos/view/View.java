package ru.vo1kov.scaenicos.view;

import java.util.ArrayList;

import ru.vo1kov.scaenicos.model.data.Artist;

/**
 * Интерфейс вью
 */
public interface View {
    void showList(ArrayList<Artist> artists);
    void showError(String error);
    void showEmptyList();

}
