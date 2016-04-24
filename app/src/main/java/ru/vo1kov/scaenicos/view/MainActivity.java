package ru.vo1kov.scaenicos.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;

import ru.vo1kov.scaenicos.model.data.Artist;
import ru.vo1kov.scaenicos.R;
import ru.vo1kov.scaenicos.presenter.ArtistsPresenter;

/**
 * Главный класс приложения, основная Activity со списком исполнителей
 */
public class MainActivity extends AppCompatActivity implements View {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArtistsPresenter presenter;


    /**
     * Загрузка списка исполнителей
     */
    @Override
    public void showList(ArrayList<Artist> artists) {
        ArtistsAdapter.OnItemClickListener onClick = new ArtistsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Artist item) {//привязываем нажатие на исполнителя к открытию активити с подробной информацией
                Intent i = new Intent(getApplicationContext(), DetailsActivity.class);

            //  ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            //   activity, transitionView, DetailActivity.EXTRA_IMAGE);

                i.putExtra("url", item.getCover().getBig());
                i.putExtra("bio", item.getDescription());
                i.putExtra("count", item.getCountString());
                i.putExtra("genres", item.getGenresString());
                i.putExtra("name", item.getName());
            //   ActivityCompat.startActivity(activity, i,
            //    options.toBundle());

                startActivity(i);
            }
        };

        mAdapter = new ArtistsAdapter(artists, getApplicationContext(), onClick);
        mRecyclerView.setAdapter(mAdapter);//загружкем полученные данные в RecyclerView

    }


    /**
     * Если пороизошла ошибка покажем уведомление в toast
     */
    @Override
    public void showError(String error) {
        Toast.makeText(getApplicationContext(),
                error, Toast.LENGTH_SHORT).show();
    }

    /**
     * Если неудасться получить список исполнителей вывем уведомление
     */
    @Override
    public void showEmptyList() {
        Toast.makeText(getApplicationContext(),
                "Список исполнителей пуст!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        if (mRecyclerView != null) {
            mRecyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                    .color(ContextCompat.getColor(getApplicationContext(), R.color.colorGray))
                    .build());//насраиваем разделители списка
        }

       SharedPreferences preferences = getPreferences(MODE_PRIVATE);//нужно для кэша

        presenter = new ArtistsPresenter(this, preferences);
        presenter.onLoad();
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.onStop();//отписываемся при остановке
        }
    }
}