package com.wd.play.support.domain.media.mediaplayer;

public class Mp4Player implements AdvancedPlayable {
    @Override
    public void playAdvanced(String fileType, String fileName) {
        System.out.println("AdvancedMediaPlayer - Playing mp4 file. Name: " + fileName);
    }
}
