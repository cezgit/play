package com.elyzar.play.support.domain.media.mediaplayer.states;

import com.elyzar.play.support.domain.media.mediaplayer.MediaPlayer;

public abstract class MediaPlayerState {

    MediaPlayer player;

    /**
     * Context passes itself through the state constructor. This may help a
     * state to fetch some useful context data if needed.
     */
    MediaPlayerState(MediaPlayer player) {
        this.player = player;
    }

    public abstract String onLock();
    public abstract String onPlay();
    public abstract String onNext();
    public abstract String onPrevious();
}
