# README #


SynqLib is a simple Android library that enables upload of videos to the [SYNQ platform](https://www.synq.fm).

The library uses [Ion](https://github.com/koush/ion) for communicating with the server. It features a callback handler (SynqUploadHandler) to report upload progress and completion.

## Example

The example project is not implemented yet. It will be added in a later version.

## Installation

SynqUpload can be integrated into your project by adding the following dependency to your app's build.gradle: 

```java
compile 'fm.synq.kjartan:synquploadlib:0.0.1'
```

## Getting started

### Add the following imports where you would like to use the uploader

```java
import fm.synq.kjartan.synquploadlib.SynqUploader;
import fm.synq.kjartan.synquploadlib.SynqUploadHandler;
```

### Create an instance of the uploader

```java
SynqUploader uploader = new SynqUploader();
```

### Start an upload

```java
// Call the uploadFile function, 
// setting the parameters you got from the SYNQ API's video/upload function:
String action;
String aws_access_key_id;
String content_type;
String policy;
String signature;
String acl;
String key;
// Also add the current context and the video file
Context context;
File videoFile;

uploader.uploadFile(action, videoFile, aws_access_key_id, content_type, policy, signature, acl, key, context, 
    new SynqUploadHandler() {
                            public void onCompleted(Exception e, String result) {
                                if (result != null && result.length() > 0) {
                                    // Upload error occurred
                                    // Notify upload error
                                    return;
                                }
                                // Upload success

                            }
                            public void onProgress(long bytesTransferred, long totalSize){
                                double percent = 0.0;
                                if(bytesTransferred != 0){
                                    percent = ((bytesTransferred/(float)totalSize))*100.0;
                                }
                                //Report upload progress to UI
                                
                            }
                        });
```