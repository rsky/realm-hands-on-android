package io.realm.handson2.twitter;

import io.realm.RealmObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import twitter4j.Status;

@Data
@EqualsAndHashCode(callSuper = true)
public class Tweet extends RealmObject {

    private String screenName;
    private String text;
    private String iconUrl;

    public Tweet() {
    }

    public Tweet(Status status) {
        setScreenName(status.getUser().getScreenName());
        setText(status.getText());
        setIconUrl(status.getUser().getProfileImageURLHttps());
    }
}
