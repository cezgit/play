package com.elyzar.play.support.domain.media.mediaplayer;

public class MediaPlayerAdapter implements Playable {

    private AdvancedPlayable player;

    public MediaPlayerAdapter(String fileType) {
        if(fileType.equalsIgnoreCase("mp4")) {
            player = new Mp4Player();
        }
    }

    @Override
    public void play(String fileType, String fileName) {
        player.playAdvanced(fileType, fileName);
    }
}
