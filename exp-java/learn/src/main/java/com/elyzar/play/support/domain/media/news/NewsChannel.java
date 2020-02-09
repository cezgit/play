package com.elyzar.play.support.domain.media.news;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

// observer
public class NewsChannel implements PropertyChangeListener {

    private String news;

    public void propertyChange(PropertyChangeEvent evt) {
        this.setNews((String) evt.getNewValue());
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getNews() {
        return news;
    }
}
