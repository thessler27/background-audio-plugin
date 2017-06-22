package org.apache.cordova.plugin;

import org.apache.cordova.CordovaPlugin;
import android.media.AudioManager;
import android.content.Context;

public class BackgroundAudio extends CordovaPlugin {

	public AudioManager audioMgr;
	public AudioManager.OnAudioFocusChangeListener changeListener;
	public Context mContext;
	private final static String TAG = "AudioFocus";

	@Override
	public void pluginInitialize () {
		mContext = this.cordova.getActivity().getApplicationContext();
		audioMgr = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
		changeListener = new AudioManager.OnAudioFocusChangeListener() {
			public void onAudioFocusChange(int focusChange) {
				switch (focusChange) {
	                case AudioManager.AUDIOFOCUS_GAIN:
	                    Log.i(TAG, "AUDIOFOCUS_GAIN");
	                    //restart/resume your sound
	                    break;
	                case AudioManager.AUDIOFOCUS_LOSS:
	                    Log.e(TAG, "AUDIOFOCUS_LOSS");
	                    //Loss of audio focus for a long time
	                    //Stop playing the sound
	                    break;
	                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
	                    Log.e(TAG, "AUDIOFOCUS_LOSS_TRANSIENT");
	                    //Loss of audio focus for a short time
	                    //Pause playing the sound
	                    break;
	                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
	                    Log.e(TAG, "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
	                    //Loss of audio focus for a short time.
	                    //But one can duck. Lower the volume of playing the sound
	                    break;

	                default:
	                    //
	            }
			}
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