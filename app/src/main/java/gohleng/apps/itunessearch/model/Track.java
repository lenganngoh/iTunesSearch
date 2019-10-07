package gohleng.apps.itunessearch.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Track extends RealmObject {
    @PrimaryKey
    @SerializedName("trackId")
    private long id;
    @SerializedName("trackName")
    private String trackName;
    @SerializedName("artworkUrl100")
    private String artworkUrl;
    @SerializedName("trackPrice")
    private double trackPrice;
    @SerializedName("primaryGenreName")
    private String trackGenre;
    @SerializedName("longDescription")
    private String trackDescription;
    // Item detailed view state
    private boolean isFavorite;
    private boolean isDetailed;

    public long getId() {
        return id;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getArtworkUrl() {
        return artworkUrl;
    }

    public double getTrackPrice() {
        return trackPrice;
    }

    public String getTrackGenre() {
        return trackGenre;
    }

    public String getTrackDescription() {
        return trackDescription;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public boolean isDetailed() {
        return isDetailed;
    }

    public void setDetailed(boolean detailed) {
        isDetailed = detailed;
    }
}
