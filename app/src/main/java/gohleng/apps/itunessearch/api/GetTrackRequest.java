package gohleng.apps.itunessearch.api;

import gohleng.apps.itunessearch.model.TrackListResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetTrackRequest {
    /**
     * Retrofit service for getting the track list with given parameters
     * @param term is the word to be searched on the list
     * @param country on which to incorporate the resulting list; sends the price for given country
     * @param media is the type of media to be included on the list
     * @return resulting track list
     */
    @GET("/search")
    Call<TrackListResponse> getTrackList(@Query("term") String term,
                                         @Query("country") String country,
                                         @Query("media") String media);
}
