package gohleng.apps.itunessearch.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrackListResponse {
    @SerializedName("results")
    private List<Track> trackList;

    public List<Track> getTrackList() {
        return trackList;
    }
}
