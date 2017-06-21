import org.apache.cordova.CordovaPlugin;
import android.media.AudioManager;

public class BackgroundAudio extends CordovaPlugin {

	public audioMgr 						= AudioManager;
	public audioChangeListener 	= AudioManager.OnAudioFocusChangeListener;

	protected void pluginInitialize () {
		audioMgr = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
	}

	@Override
	public void enableBackgroundMusic () {
		int result = audioMgr.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_LOSS);
		if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
			Log.d('Woo enabled background moosic!', result);
		} else {
			Log.d('Damn, issue enabling background moosic.');
		}
	}

	@Override
	public void quietBackgroundMusic () {
		//quiet BG moosic
		int result = audioMgr.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK);
		if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
			Log.d('Woo enabled background moosic!', result);
		} else {
			Log.d('Damn, issue enabling background moosic.');
		}
	}

	@Override
	public void disableBackgroundMusic () {
		//disable bg moosic
		int result = audioMgr.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
		if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
			Log.d('Woo enabled background moosic!', result);
		} else {
			Log.d('Damn, issue enabling background moosic.');
		}
	}
}