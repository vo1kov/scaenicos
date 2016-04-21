package ru.vo1kov.rxtest;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    public void runAsync() throws Exception {
        final Request request = new Request.Builder()
                .url("http://download.cdn.yandex.net/mobilization-2016/artists.json")
                .build();


        client.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return chain.proceed(request);
            }
        });
    }

    String runSync() throws IOException {
        Request request = new Request.Builder()
                .url("http://download.cdn.yandex.net/mobilization-2016/artists.json")
                .build();


        //String cachePath = getApplicationContext().getCacheDir().getPath();
        //File cacheFile = new File(cachePath + File.separator + BuildConfig.APPLICATION_ID);

        //DiskCache diskCache = new DiskCache(cacheFile, BuildConfig.VERSION_CODE, 1024 * 1024 * 10);


        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    Observable<String> myObservable = Observable.create(
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
    );

    Subscriber<String> mySubscriber = new Subscriber<String>() {
        @Override
        public void onNext(String s) {
            ArrayList<Artist> artists = (new GsonBuilder()).create()
                    .fromJson(s, new TypeToken<ArrayList<Artist>>() {
                    }.getType());

            ArtistsAdapter.OnItemClickListener onClick = new ArtistsAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Artist item) {
                    Intent i = new Intent(getApplicationContext(), DetailsActivity.class);

//                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                            activity, transitionView, DetailActivity.EXTRA_IMAGE);

                    i.putExtra("url", item.getCover().getBig());
                    i.putExtra("bio", item.getDescription());
                    i.putExtra("count", item.getCountString());
                    i.putExtra("genres", item.getGenresString());
                    i.putExtra("name", item.getName());
//                    ActivityCompat.startActivity(activity, i,
//                            options.toBundle());

                    startActivity(i);
                }
            };

            mAdapter = new ArtistsAdapter(artists, getApplicationContext(), onClick);
            mRecyclerView.setAdapter(mAdapter);
        }

        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }
    };

    private OkHttpClient client;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .color(ContextCompat.getColor(getApplicationContext(), R.color.colorGray))
               // .marginResId(R.dimen.activity_horizontal_margin)
               // .margin(0,0)
                .build());

        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(getCacheDir(), cacheSize);

        client = new OkHttpClient.Builder()
                .cache(cache)
                .build();

        myObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mySubscriber);
    }
}