package gohleng.apps.itunessearch.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import gohleng.apps.itunessearch.R;

public class TrackViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.imgArtwork)
    public ImageView imgArtwork;
    @BindView(R.id.txtName)
    public TextView txtName;
    @BindView(R.id.txtPrice)
    public TextView txtPrice;
    @BindView(R.id.txtGenre)
    public TextView txtGenre;
    @BindView(R.id.txtDescription)
    public TextView txtDescription;
    @BindView(R.id.imgFavorite)
    public ImageView imgFavorite;

    public TrackViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
