package ru.vo1kov.scaenicos.view;

//import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ru.vo1kov.scaenicos.R;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(intent.getExtras().getString("name"));//выведем имя исполнителя в заголовок
            ab.setDisplayHomeAsUpEnabled(true);
        }

        ImageView cover = (ImageView) findViewById(R.id.imageViewBigCover);
        TextView textViewBio = (TextView) findViewById(R.id.textViewBio);
        TextView textViewCount = (TextView) findViewById(R.id.textViewCount);
        TextView textViewGenres = (TextView) findViewById(R.id.textViewGenres);


        Picasso.with(getApplicationContext())
                .load(intent.getExtras().getString("url"))
                .fit().centerCrop()
                .into(cover);// обрежем картинку в соответсвии с макетом

        if (textViewBio != null)
            textViewBio.setText(intent.getExtras().getString("bio"));
        String count = intent.getExtras().getString("count");


        if ((textViewCount != null) && (count != null))
            textViewCount.setText(count.replace(", ", " • "));

        if (textViewGenres != null)
            textViewGenres.setText(intent.getExtras().getString("genres"));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) { //кнопка назад на actionbar е
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
