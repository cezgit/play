package com.elyzar.play.patterns.behavioral;

import com.elyzar.play.support.domain.media.news.NewsAgency;
import com.elyzar.play.support.domain.media.news.NewsChannel;

public class ObserverPatternDemo {

    public static void main(String[] args) {

        // observable
        NewsAgency newsAgency = new NewsAgency();

        // observer
        NewsChannel newsChannel = new NewsChannel();

        newsAgency.addPropertyChangeListener(newsChannel);
        newsAgency.setNews("hey observers, I have news!");

        System.out.println("NewsChannel got the news from observable: "+newsChannel.getNews());
    }
}
