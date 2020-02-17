package com.wd.play.support.domain.media.news;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

// observable
public class NewsAgency {

    private String news;

    private PropertyChangeSupport support;

    public NewsAgency(){
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl){
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl){
        support.removePropertyChangeListener(pcl);
    }

    public void setNews(String value){
        support.firePropertyChange("news",this.news,value);
        this.news = value;
    }

}
