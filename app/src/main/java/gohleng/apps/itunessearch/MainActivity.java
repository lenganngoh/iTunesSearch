package gohleng.apps.itunessearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gohleng.apps.itunessearch.adapter.TrackAdapter;
import gohleng.apps.itunessearch.api.GetTrackRequest;
import gohleng.apps.itunessearch.api.RetrofitBuilder;
import gohleng.apps.itunessearch.model.Track;
import gohleng.apps.itunessearch.model.TrackListResponse;
import gohleng.apps.itunessearch.realm.RealmManager;
import gohleng.apps.itunessearch.test.TestConstants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rvTracks)
    RecyclerView rvTracks;

    private TrackAdapter trackAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getTrackList();
    }

    private void getTrackList() {
        // Call the service to get the track list from url based on Get Track Request and retrofit base url
        GetTrackRequest getTrackRequest = RetrofitBuilder.getInstance().create(GetTrackRequest.class);
        // Initialize the call for service
        Call<TrackListResponse> rawTrackList = getTrackRequest.getTrackList(TestConstants.TERM,
                TestConstants.COUNTRY, TestConstants.MEDIA);
        // Start the request
        rawTrackList.enqueue(new Callback<TrackListResponse>() {
            @Override
            public void onResponse(Call<TrackListResponse> call, Response<TrackListResponse> response) {
                // Initiate loading of recycler view based on response track list
                loadTrackList(response.body().getTrackList());
            }

            @Override
            public void onFailure(Call<TrackListResponse> call, Throwable t) {
                // Displays a toast when Retrofit failed to process the request
                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadTrackList(List<Track> tracks) {
        // Initialization of TrackAdapter and passing tracks list
        if (trackAdapter == null) {
            trackAdapter = new TrackAdapter(this, tracks);
        }

        rvTracks.setAdapter(trackAdapter);
        rvTracks.setLayoutManager(new LinearLayoutManager(this));

        // Call realm updating and passing response list
        RealmManager.addOrUpdateRealm(tracks);
    }
}
