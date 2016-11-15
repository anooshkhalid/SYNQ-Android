# README #


SynqUploader is a simple Android library that enables upload of videos to the [SYNQ platform](https://www.synq.fm).

The library uses [Ion](https://github.com/koush/ion) for communicating with the server. It features a callback handler (SynqUploadHandler) to report upload progress and completion.

## Example

An example app is included in this repo. The app will show a grid view with thumbnails of all the videos on your device. Clicking on a thumbnail will call the upload function and upload the video. Clone the repo to explore the example app.
**Important note:** The example project is dependant on access to the SYNQ API to be able to create a video object and to fetch the upload parameters needed when calling the upload function. You will need to get an API key from the SYNQ admin panel, and insert the key into the SynqAPI class. **Caution: this is not the proper way of doing this, and your api key might get exposed to others!** In a real world scenario, this should be handled by your own backend. The backend should then give your app the upload parameters.

## Installation

SynqUploader can be integrated into your project by adding the following dependency to your app's build.gradle: 

```java
compile 'fm.synq:synquploader:0.0.3'
```

## Getting started

### Add the following imports where you would like to use the uploader

```java
import fm.synq.synquploader.SynqUploader;
import fm.synq.synquploader.SynqUploadHandler;
```

### Create an instance of the uploader

```java
SynqUploader uploader = new SynqUploader();
```

### Start an upload

```java
// Call the uploadFile function, 
// pass the parameters you got from the SYNQ API's video/upload function as a JsonObject
// Also add the current context and the video file
File videoFile;
JsonObject jsonObject;
Context context;

uploader.uploadFile(videoFile, jsonObject, context, 
    new SynqUploadHandler() {
        @Override
        public void onCompleted() {
            // Upload success
        }
        
        @Override
        public void onFailure(String error) {
            // Handle error

        }

        @Override
        public void onProgress(long bytesTransferred, long totalSize) {
            double percent = (double)bytesTransferred / (double)totalSize * 100.0;
            Log.e("f", "Upload progress " + (int)percent + " %");
            // Report upload progress to UI
        }
    });
```
