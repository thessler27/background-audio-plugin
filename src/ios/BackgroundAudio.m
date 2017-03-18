#import "BackgroundAudio.h"
#import <AVFoundation/AVFoundation.h>

@implementation BackgroundAudio

// this method is executed when the app loads because of the onload param in plugin.xml
- (void)pluginInitialize {
  AVAudioSession *audioSession = [AVAudioSession sharedInstance];
  NSError *setCategoryError = nil;
  BOOL ok = [audioSession setCategory:AVAudioSessionCategoryPlayback withOptions: AVAudioSessionCategoryOptionDuckOthers error:&setCategoryError];

  NSLog(@"BackgroundAudio plugin ok with duck? %@", ok ? @"YES" : @"NO");
  if (!ok) {
    NSLog(@"BackgroundAudio plugin error: %@", setCategoryError.description);
  }
}

- (void) enableBackgroundMusic: (CDVInvokedUrlCommand *) command {
    [self.commandDelegate runInBackground:^{
        NSError *setCategoryError = nil;
        NSError *setActiveError = nil;
        AVAudioSession *audioSession = [AVAudioSession sharedInstance];
        BOOL categoryOk = [audioSession setCategory:AVAudioSessionCategoryPlayback withOptions: AVAudioSessionCategoryOptionMixWithOthers error:&setCategoryError];
        BOOL activeOk = [audioSession setActive:NO withOptions: AVAudioSessionSetActiveOptionNotifyOthersOnDeactivation error:&setActiveError];
        NSLog(@"Enabled new bg moosic? %@", categoryOk && activeOk ? @"YES" : @"NO");
        if(!categoryOk) NSLog(@"Issue with enabling background audio category error: %@", setCategoryError.description);
        if(!activeOk) NSLog(@"Issue with enabling bcakground audio active error %@", setActiveError.description);
    }];
    
}

- (void) quietBackgroundMusic: (CDVInvokedUrlCommand *) command {
    [self.commandDelegate runInBackground:^{
        AVAudioSession *audioSession = [AVAudioSession sharedInstance];
        NSError *setCategoryError = nil;
        NSError *setActiveError = nil;
        BOOL categoryOk = [audioSession setCategory:AVAudioSessionCategoryPlayback withOptions: AVAudioSessionCategoryOptionDuckOthers error:&setCategoryError];
        BOOL activeOk = [audioSession setActive:YES error:&setActiveError];
        NSLog(@"Quieted new bg moosic? %@", categoryOk && activeOk ? @"YES" : @"NO");
        if(!categoryOk) NSLog(@"Issue with enabling background audio category error: %@", setCategoryError.description);
        if(!activeOk) NSLog(@"Issue with enabling bcakground audio active error %@", setActiveError.description);
    }];

}
 
- (void) disableBackgroundMusic: (CDVInvokedUrlCommand *) command {

    [self.commandDelegate runInBackground:^{
        AVAudioSession *audioSession = [AVAudioSession sharedInstance];
        NSError *setCategoryError = nil;
        BOOL methodSuccess = [audioSession setCategory:AVAudioSessionCategorySoloAmbient error:&setCategoryError];
        NSLog(@"Disabled background music? %@", methodSuccess ? @"YES": @"NO");
        if(!methodSuccess) { NSLog(@"Issue with disabling background audio: %@", setCategoryError.description); }
    }];
    

}

@end