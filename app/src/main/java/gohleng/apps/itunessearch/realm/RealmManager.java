package gohleng.apps.itunessearch.realm;

import android.support.annotation.NonNull;

import java.util.List;

import gohleng.apps.itunessearch.model.Track;
import io.realm.Realm;

public class RealmManager {

    /**
     * Add an item to the realm list if not yet existing
     * @param trackList list of tracks
     */
    public static void addOrUpdateRealm(final List<Track> trackList) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                for (Track track : trackList) {
                    Track mTrack = realm.where(Track.class).equalTo("id", track.getId()).findFirst();
                    if (mTrack == null) {
                        realm.insertOrUpdate(track);
                    }
                }
            }
        });
    }

    /**
     * Updates the isFavorite boolean value
     * @param trackId id of the track
     * @param isFavorite new value to be used for update
     */
    public static void updateFavoriteState(final long trackId, final boolean isFavorite) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                Track track = realm.where(Track.class).equalTo("id", trackId).findFirst();
                if (track != null) {
                    track.setFavorite(isFavorite);
                    realm.insertOrUpdate(track);
                }
            }
        });
    }

    /**
     * Updated the isDetailed boolean value
     * @param trackId id of the track
     * @param isDetailed new value to be used for update
     */
    public static void updateDetailedState(final long trackId, final boolean isDetailed) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                Track track = realm.where(Track.class).equalTo("id", trackId).findFirst();
                if (track != null) {
                    track.setDetailed(isDetailed);
                    realm.insertOrUpdate(track);
                }
            }
        });
    }

    /**
     * Gets the cached value existing in the RealmDB with given trackId
     * @param trackId the id to be used for searching
     * @return track object
     */
    public static Track getCachedTrackIfExisting(final long trackId) {
        Realm realm = Realm.getDefaultInstance();
        Track track = realm.where(Track.class).equalTo("id", trackId).findFirst();
        if (track != null) {
            return track;
        }
        return null;
    }

}
