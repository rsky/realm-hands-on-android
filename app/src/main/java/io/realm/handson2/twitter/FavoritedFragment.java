package io.realm.handson2.twitter;

import android.support.annotation.NonNull;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class FavoritedFragment extends TimelineFragment {
    @NonNull
    @Override
    protected RealmResults<Tweet> buildTweetList(Realm realm) {
        return realm.where(Tweet.class)
                .equalTo("favorited", true)
                .findAllSorted("createdAt", Sort.DESCENDING);
    }
}
