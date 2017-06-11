---
title: Background Audio
description: Control whether or not your app allows background audio, dynamically. Note that this will only work in an iOS simulator/phone. This will not work in a browser or any other env. Sorry not sorry.
---

## Installation

1. `cordova plugin add https://github.com/thessler27/background-audio-plugin.git`

## Utilization

### There are two methods by which you can do this. The first enables background audio to play in sync with the current audio track using AVAudioSessions. The second disables background audio playing with the current audio track. The plugin attaches to the window at `window.plugins.backgroundaudio`

1. To enable background audio mixing with other audio tracks: `window.plugins.backgroundaudio.enableBackgroundMusic(successHandler, errorHandler)`

2. To disable: `window.plugins.backgroundaudio.disableBackgroundMusic(successHandler, errorHandler)`

3. To duck: `window.plugins.backgroundaudio.quietBackgroundMusic(successHandler, errorHandler)`

NOTE: both methods return void



Supported Platforms
-------------------

- iOS
