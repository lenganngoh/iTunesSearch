package gohleng.apps.itunessearch.realm;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class iTunesSearchApplication extends Application {

     final String REALM_NAME = "search.realm";

    /**
     * Creates the Realm Configuration
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(REALM_NAME)
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
