package org.apache.cordova.plugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.media.AudioManager;
import android.content.Context;
import android.util.Log;

public class BackgroundAudio extends CordovaPlugin {

	public AudioManager audioMgr;
	public AudioManager.OnAudioFocusChangeListener changeListener;
	public Context mContext;
	private final static String TAG = "AudioFocus";

	@Override
	public void pluginInitialize () {
		Log.i(TAG, "Initializing Background Audio Plugin....");
		mContext = this.cordova.getActivity().getApplicationContext();
		audioMgr = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
		changeListener = new AudioManager.OnAudioFocusChangeListener() {
			public void onAudioFocusChange(int focusChange) {
				switch (focusChange) {
	                case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK:
	                    Log.i(TAG, "AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK");
	                    //restart/resume your sound
	                    break;
	                case AudioManager.AUDIOFOCUS_LOSS:
	                    Log.e(TAG, "AUDIOFOCUS_LOSS");
	                    //Loss of audio focus for a long time
	                    //Stop playing the sound
	                    break;
	                case AudioManager.AUDIOFOCUS_GAIN:
	                    Log.e(TAG, "AUDIOFOCUS_GAIN");
	                    //Loss of audio focus for a short time
	                    //Pause playing the sound
	                    break;
	            }
			}
		};
		int result = audioMgr.requestAudioFocus(changeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK); //<-- init with this
		if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
			Log.d(TAG, "AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK Audio request granted");
		} else {
			Log.e(TAG, "AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK Audio request denieeddddd");
		}
	}

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("enableBackgroundMusic")) {
        	this.enableBackgroundMusic();
        	return true;
        } else if (action.equals("quietBackgroundMusic")) { 
        	this.quietBackgroundMusic();
        	return true;
        } else if (action.equals("disableBackgroundMusic")) {
        	this.disableBackgroundMusic();
        	return true;
        }
        return false;
    }


	public void enableBackgroundMusic () {
		Log.i(TAG, "requesting audio focus loss to enable bg music....");
		int result = audioMgr.requestAudioFocus(changeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_LOSS);
		if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
			Log.d(TAG, "AUDIOFOCUS_LOSS Audio request granted");
		} else {
			Log.e(TAG, "AUDIOFOCUS_LOSS Audio request denieeddddd");
		}
	}

	public void quietBackgroundMusic () {
		Log.i(TAG, "requesting to gain focus and transient to duck....");
		//quiet BG moosic
		int result = audioMgr.requestAudioFocus(changeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK);
		if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
			Log.d(TAG, "AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK Audio request granted");
		} else {
			Log.e(TAG, "AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK Audio request denieeddddd");
		}
	}

	public void disableBackgroundMusic () {
		Log.i(TAG, "requesting to disable the background music....");
		//disable bg moosic
		int result = audioMgr.requestAudioFocus(changeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
		if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
			Log.d(TAG, "AUDIOFOCUS_GAIN Audio request granted");
		} else {
			Log.e(TAG, "AUDIOFOCUS_GAIN Audio request denieeddddd");
		}
	}
}