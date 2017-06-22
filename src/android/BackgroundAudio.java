package org.apache.cordova.plugin;

import org.apache.cordova.CordovaPlugin;
import android.media.AudioManager;
import android.content.Context;

public class BackgroundAudio extends CordovaPlugin {

	public AudioManager audioMgr;
	public AudioManager.OnAudioFocusChangeListener changeListener;
	public Context mContext;

	@Override
	public void pluginInitialize () {
		mContext = this.cordova.getActivity().getApplicationContext();
		audioMgr = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
		changeListener = new AudioManager.OnAudioFocusChangeListener() {
			public void onAudioFocusChange(int focusChange) {}
		};
	}

	public void enableBackgroundMusic () {
		int result = audioMgr.requestAudioFocus(changeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_LOSS);
	}

	public void quietBackgroundMusic () {
		//quiet BG moosic
		int result = audioMgr.requestAudioFocus(changeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK);
	}

	public void disableBackgroundMusic () {
		//disable bg moosic
		int result = audioMgr.requestAudioFocus(changeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
	}
}