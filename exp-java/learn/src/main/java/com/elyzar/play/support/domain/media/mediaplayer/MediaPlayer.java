package com.elyzar.play.support.domain.media.mediaplayer;

import com.elyzar.play.support.domain.media.mediaplayer.states.MediaPlayerState;
import com.elyzar.play.support.domain.media.mediaplayer.states.ReadyState;

import java.util.ArrayList;
import java.util.List;

public class MediaPlayer implements Playable {

    private MediaPlayerAdapter mediaPlayerAdapter;

    private MediaPlayerState mediaPlayerState;
    private boolean playing = false;
    private List<String> playlist = new ArrayList<>();
    private int currentTrack = 0;

    public MediaPlayer() {
        this.mediaPlayerState = new ReadyState(this);
        setPlaying(true);
        for (int i = 1; i <= 12; i++) {
            playlist.add("Track " + i);
        }
    }

    public void changeState(MediaPlayerState mediaPlayerState) {
        this.mediaPlayerState = mediaPlayerState;
    }

    public MediaPlayerState getMediaPlayerState() {
        return mediaPlayerState;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public boolean isPlaying() {
        return playing;
    }

    public String startPlayback() {
        return "Playing " + playlist.get(currentTrack);
    }

    public String nextTrack() {
        currentTrack++;
        if (currentTrack > playlist.size() - 1) {
            currentTrack = 0;
        }
        return "Playing " + playlist.get(currentTrack);
    }

    public String previousTrack() {
        currentTrack--;
        if (currentTrack < 0) {
            currentTrack = playlist.size() - 1;
        }
        return "Playing " + playlist.get(currentTrack);
    }

    public void setCurrentTrackAfterStop() {
        this.currentTrack = 0;
    }

    @Override
    public void play(String fileType, String fileName) {
        if(fileType.equalsIgnoreCase("mp3")){
            System.out.println("SimpleMediaPlayer - Playing mp3 file. Name: " + fileName);
        }
        else if(fileType.equalsIgnoreCase("mp4")) {
            // use the Adapter of the AdvancedMediaPlayer to play the file
            mediaPlayerAdapter = new MediaPlayerAdapter(fileType);
            mediaPlayerAdapter.play(fileType, fileName);
        }
        else {
            System.out.println("Invalid media type: "+fileType);
        }
    }
}
