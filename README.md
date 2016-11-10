# README #


SynqUploader is a simple Android library that enables upload of videos to the [SYNQ platform](https://www.synq.fm).

The library uses [Ion](https://github.com/koush/ion) for communicating with the server. It features a callback handler (SynqUploadHandler) to report upload progress and completion.

## Example

The example project is not implemented yet. It will be added in a later version.

## Installation

SynqUploader can be integrated into your project by adding the following dependency to your app's build.gradle: 

```java
compile 'fm.synq:synquploader:0.0.1'
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
