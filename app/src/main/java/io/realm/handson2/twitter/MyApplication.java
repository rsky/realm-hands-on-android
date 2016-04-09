package io.realm.handson2.twitter;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObjectSchema;
import twitter4j.TwitterFactory;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        TwitterAuthUtil.init(this);

        TwitterFactory.getSingleton().setOAuthConsumer(
                BuildConfig.TWITTER_CONSUMER_KEY,
                BuildConfig.TWITTER_CONSUMER_SECRET);

        Realm.setDefaultConfiguration(buildMigration());
    }

    private RealmConfiguration buildMigration() {
        return new RealmConfiguration.Builder(this)
                .schemaVersion(1L)
                .migration((realm, oldVersion, newVersion) -> {
                    if (oldVersion == 0L) {
                        final RealmObjectSchema tweetSchema = realm.getSchema().get("Tweet");
                        tweetSchema.addField("favorited", boolean.class);

                        // noinspection UnusedAssignment
                        oldVersion++;
                    }
                })
                .build();
    }
}
