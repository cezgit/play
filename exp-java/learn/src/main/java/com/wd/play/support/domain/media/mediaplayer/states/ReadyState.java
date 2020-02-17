package com.wd.play.support.domain.media.mediaplayer.states;

import com.wd.play.support.domain.media.mediaplayer.MediaPlayer;

public class ReadyState extends MediaPlayerState {

    public ReadyState(MediaPlayer player) {
        super(player);
    }

    @Override
    public String onLock() {
        player.changeState(new LockedState(player));
        return "Locked...";
    }

    @Override
    public String onPlay() {
        String action = player.startPlayback();
        player.changeState(new PlayingState(player));
        return action;
    }

    @Override
    public String onNext() {
        return "Locked...";
    }

    @Override
    public String onPrevious() {
        return "Locked...";
    }
}
