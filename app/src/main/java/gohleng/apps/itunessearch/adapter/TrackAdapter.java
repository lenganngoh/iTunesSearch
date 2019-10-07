package gohleng.apps.itunessearch.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import gohleng.apps.itunessearch.R;
import gohleng.apps.itunessearch.model.Track;
import gohleng.apps.itunessearch.realm.RealmManager;
import gohleng.apps.itunessearch.ui.TrackViewHolder;

public class TrackAdapter extends RecyclerView.Adapter<TrackViewHolder> {

    private Context mContext;
    private List<Track> tracks;

    public TrackAdapter(Context context, List<Track> tracks) {
        this.mContext = context;
        this.tracks = tracks;
    }

    @NonNull
    @Override
    public TrackViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.viewholder_track, viewGroup, false);
        ButterKnife.bind(this, view);

        final TrackViewHolder trackViewHolder = new TrackViewHolder(view);

        // Handles the click to expand of the view holder
        trackViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Track track = getUsableTrackModel(trackViewHolder.getAdapterPosition());

                // Updates the value in preparation for adapter notification
                boolean isDetailed = track.isDetailed();
                track.setDetailed(!isDetailed);

                // Updates the favorite state of the track object in the realm
                RealmManager.updateDetailedState(track.getId(), !isDetailed);
                // Notify the adapter that item has changed to smoothly animate the expansion/collision
                notifyItemChanged(tracks.indexOf(track));
            }
        });

        trackViewHolder.imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Track track = getUsableTrackModel(trackViewHolder.getAdapterPosition());

                // Updates the value in preparation for adapter notification
                v.setActivated(!track.isFavorite());
                track.setFavorite(v.isActivated());

                // Updates the detailed state of the track object in the realm
                RealmManager.updateFavoriteState(track.getId(), v.isActivated());
                // Notify the adapter that item has changed to smoothly animate the expansion/collision
                notifyItemChanged(tracks.indexOf(track));
            }
        });

        return trackViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final TrackViewHolder trackViewHolder, int i) {
        Track track = getUsableTrackModel(trackViewHolder.getAdapterPosition());

        // Load artwork url into the image view and displays image place holder when image did not load
        Picasso.with(mContext)
                .load(track.getArtworkUrl())
                .placeholder(R.drawable.icon_img_placeholder)
                .into(trackViewHolder.imgArtwork);

        // Populate values to the text views
        trackViewHolder.txtName.setText(track.getTrackName());
        trackViewHolder.txtPrice.setText(String.format("A$%s", track.getTrackPrice()));
        trackViewHolder.txtGenre.setText(track.getTrackGenre());
        trackViewHolder.txtDescription.setText(track.getTrackDescription());

        // Initial display presentation based on cached (if existing) or current state of isFavorite and isDetailed
        trackViewHolder.imgFavorite.setActivated(track.isFavorite());
        trackViewHolder.txtDescription.setVisibility(track.isDetailed() ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

    private Track getUsableTrackModel(int position) {
        // Get existing track from realm if existing based on the id in the given array position
        Track cachedTrack = RealmManager.getCachedTrackIfExisting
                (tracks.get(position).getId());

        // If track is existing in the RealmDB, get the isDetailed and isFavorite values and assign
        // to specific object in the list associated with the adapter
        if (cachedTrack != null) {
            tracks.get(position).setDetailed(cachedTrack.isDetailed());
            tracks.get(position).setFavorite(cachedTrack.isFavorite());
        }

        // return the updated list
        return tracks.get(position);
    }
}
