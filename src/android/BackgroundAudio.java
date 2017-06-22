package com.thessler.backgroundAudioPlugin;

import org.apache.cordova.CordovaPlugin;
import android.media.AudioManager;

public class BackgroundAudio extends CordovaPlugin {

	public audioMgr AudioManager;

	public void pluginInitialize () {
		audioMgr = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
	}

	public void enableBackgroundMusic () {
		int result = audioMgr.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_LOSS);
	}

	public void quietBackgroundMusic () {
		//quiet BG moosic
		int result = audioMgr.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK);
	}

	public void disableBackgroundMusic () {
		//disable bg moosic
		int result = audioMgr.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
	}
}