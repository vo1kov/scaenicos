package ru.vo1kov.rxtest;

//import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(intent.getExtras().getString("name"));
            ab.setDisplayHomeAsUpEnabled(true);
        }

        ImageView cover = (ImageView) findViewById(R.id.imageViewBigCover);
        TextView textViewBio = (TextView) findViewById(R.id.textViewBio);


        Picasso.with(getApplicationContext())
                .load(intent.getExtras().getString("url"))
                .fit().centerCrop()
                .into(cover);

        if (textViewBio != null)
            textViewBio.setText(intent.getExtras().getString("bio"));
        ((TextView) findViewById(R.id.textViewCount)).setText(intent.getExtras().getString("count").replace(", ", " - "));
        ((TextView) findViewById(R.id.textViewGenres)).setText(intent.getExtras().getString("genres"));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
