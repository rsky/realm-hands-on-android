package io.realm.handson2.twitter;

import android.net.Uri;
import android.support.annotation.Nullable;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import lombok.Data;
import lombok.EqualsAndHashCode;
import twitter4j.Status;

@Data
@EqualsAndHashCode(callSuper = true)
public class Tweet extends RealmObject {
    @PrimaryKey
    private long id;
    @Index
    private Date createdAt;
    private String screenName;
    private String text;
    private String iconUrl;
    boolean favorited;

    public Tweet() {
    }

    public Tweet(Status status) {
        setId(status.getId());
        setScreenName(status.getUser().getScreenName());
        setText(status.getText());
        setIconUrl(status.getUser().getProfileImageURLHttps());
        setCreatedAt(status.getCreatedAt());
        setFavorited(status.isFavorited());
    }

    @Nullable
    public Uri getIconUri() {
        return iconUrl == null ? null : Uri.parse(iconUrl);
    }
}
