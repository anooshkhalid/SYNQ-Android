# SynqAndroid #


This is the SYNQ mobile SDK for Android. It lets you easily integrate your mobile app with the SYNQ platform and the  [SYNQ Video API](https://www.synq.fm).

The library uses [Ion](https://github.com/koush/ion) for communicating with the server. It features a callback handler (SynqUploadHandler) to report upload progress and completion.

## Example

An example app is included in this repo. The app will show a grid view with thumbnails of all the videos on your device. Clicking on a thumbnail will call the upload function and upload the video. Clone the repo to explore the example app.

**Important note:** The example app is dependant on access to the SYNQ API to be able to create a video object and to fetch the upload parameters needed when calling the upload function. The SYNQ API is designed to be accessed from a server, and your mobile client should communicate with this server to get the needed information from the API. If you do not have your own server wired up to the SYNQ API, we have created an example server that can be used to perform these tasks when developing with our SDK. Clone the [GitHub repo](https://github.com/SYNQfm/SYNQ-Nodejs-example-server)  and follow instructions provided in the README. The SynqAndroid SDK also includes a library called 'synqhttplib', this can be used to make the relevant http calls to the example server (authorize user, create video objects and fetch upload parameters).

For more info, please read the [projects and api keys](https://docs.synq.fm/#projects-and-api-keys) section in the docs.

## Installation

SynqAndroid can be integrated into your project by adding the following dependency to your app's build.gradle: 

```java
compile 'fm.synq:SynqAndroid:0.1.0'
```

## Getting started

### Add the following imports where you would like to use the uploader

```java
import fm.synq.SynqAndroid.SynqUploader;
import fm.synq.SynqAndroid.SynqUploadHandler;
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
