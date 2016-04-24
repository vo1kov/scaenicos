package ru.vo1kov.scaenicos.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ru.vo1kov.scaenicos.R;
import ru.vo1kov.scaenicos.model.data.Artist;

/**
 * Адаптер для RecyclerView для отображения списка исполнителей
 */
public class ArtistsAdapter extends RecyclerView.Adapter<ArtistsAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Artist item);
    }

    ArrayList<Artist> artists; //данные
    Context context;
    private final OnItemClickListener listener;

    public ArtistsAdapter(ArrayList<Artist> _artists, Context _context, OnItemClickListener _listener) {
        artists = _artists;
        context = _context;
        listener = _listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup,int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.artists_layout, viewGroup, false);
        return new ViewHolder(v, i);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.artistTextView.setText(artists.get(position).getName());
        holder.genresTextView.setText(artists.get(position).getGenresString());
        holder.countTextView.setText(artists.get(position).getCountString());

        Picasso.with(context).load(artists.get(position).getCover().getSmall()).into(holder.coverImageView);//грузим картинки в отдельном потоке используя picasso, она же сделает кэш в памяти и на диске

        holder.bind(artists.get(position), listener);
    }
    
    @Override
    public int getItemCount() {
        return artists.size();
    }






    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView artistTextView;
        public TextView genresTextView;
        public TextView countTextView;
        public ImageView coverImageView;

        public ViewHolder(View itemView, final int position) {

            super(itemView);
            artistTextView = (TextView) itemView.findViewById(R.id.textViewTitle);
            genresTextView = (TextView) itemView.findViewById(R.id.textViewGenres);
            countTextView = (TextView) itemView.findViewById(R.id.textViewCount);
            coverImageView = (ImageView) itemView.findViewById(R.id.imageViewCover);
        }

        public void bind(final Artist item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}