package ru.vo1kov.scaenicos;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import ru.vo1kov.scaenicos.view.MainActivity;

import static org.junit.Assert.assertEquals;

/**
 * Проверка загрузки списка
 */



@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Test
    public void LoadedMain() {
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.my_recycler_view);

        boolean isRecyclerLoaded = (recyclerView != null);

        assertEquals(isRecyclerLoaded, true);
    }
}