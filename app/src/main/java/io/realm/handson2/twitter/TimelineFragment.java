package io.realm.handson2.twitter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

public class TimelineFragment extends ListFragment {

    private Realm realm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        realm = Realm.getDefaultInstance();

        final RealmResults<Tweet> tweets = realm.allObjects(Tweet.class);
        final RealmBaseAdapter<Tweet> adapter = new RealmBaseAdapter<Tweet>(getContext(), tweets, true) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                final Tweet tweet = getItem(position);

                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.listitem_tweet, parent, false);
                }

                ViewHolder viewHolder = (ViewHolder) convertView.getTag(R.id.viewholder_tweet);
                if (viewHolder == null) {
                    viewHolder = new ViewHolder(convertView);
                    convertView.setTag(R.id.viewholder_tweet, viewHolder);
                }

                viewHolder.setTweet(tweet);

                return convertView;
            }
        };

        setListAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((RealmBaseAdapter<?>) getListAdapter()).updateRealmResults(null);
        realm.close();
        realm = null;
    }

    private class ViewHolder {
        private ImageView image;
        private TextView screenName;
        private TextView text;

        ViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.image);
            screenName = (TextView) view.findViewById(R.id.screen_name);
            text = (TextView) view.findViewById(R.id.text);
        }

        void setTweet(Tweet tweet) {
            screenName.setText(tweet.getScreenName());
            text.setText(tweet.getText());
        }
    }
}
