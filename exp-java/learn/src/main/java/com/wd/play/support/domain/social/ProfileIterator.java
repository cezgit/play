package com.wd.play.support.domain.social;

public interface ProfileIterator {

    boolean hasNext();

    Profile getNext();

    void reset();
}
