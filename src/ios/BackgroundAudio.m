#import "BackgroundAudio.h"
#import <AVFoundation/AVFoundation.h>

@implementation BackgroundAudio

// this method is executed when the app loads because of the onload param in plugin.xml
- (void)pluginInitialize {
  AVAudioSession *audioSession = [AVAudioSession sharedInstance];
  NSError *setCategoryError = nil;
  BOOL ok = [audioSession setCategory:AVAudioSessionCategoryPlayback error:&setCategoryError];

  NSLog(@"BackgroundAudio plugin ok? %@", ok ? @"YES" : @"NO");
  if (!ok) {
    NSLog(@"BackgroundAudio plugin error: %@", setCategoryError.description);
  }
}

- (void) enableBackgroundMusic: (CDVInvokedUrlCommand *) command {
    AVAudioSession *audioSession = [AVAudioSession sharedInstance];
    NSError *setCategoryError = nil;
    BOOL methodSuccess = [audioSession setCategory:AVAudioSessionCategoryPlayback withOptions:
  AVAudioSessionCategoryOptionMixWithOthers error:&setCategoryError];
    NSLog(@"Enabled background music? %@", methodSuccess ? @"YES": @"NO");
    if(!methodSuccess) { NSLog(@"Issue with enabling background audio: %@", setCategoryError.description); }
    
}

- (void) disableBackgroundMusic: (CDVInvokedUrlCommand *) command {

    AVAudioSession *audioSession = [AVAudioSession sharedInstance];
    NSError *setCategoryError = nil;
    BOOL methodSuccess = [audioSession setCategory:AVAudioSessionCategorySoloAmbient error:&setCategoryError];
    NSLog(@"Disabled background music? %@", methodSuccess ? @"YES": @"NO");
    if(!methodSuccess) { NSLog(@"Issue with disabling background audio: %@", setCategoryError.description); }

}

@end