package fm.synq.synquploader;

import android.content.Context;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;

import java.io.File;

/**
 * Created by Kjartan on 09.11.2016.
 */

public class SynqUploader {

    public SynqUploader() {

    }


    /**
     * Upload a video file to Amazon Web Services
     *
     * @param action    The servers response (JSONObject/JSONArray)
     * @param videoFile The video file to upload
     * @param AWSAccessKeyID   Parameter for AWS
     */
    public void uploadFile(
            String action,
            File videoFile,
            String AWSAccessKeyID,
            String contentType,
            String policy,
            String signature,
            String ACL,
            String key,
            Context context,
            final SynqUploadHandler callback) {
        Ion.with(context)
                .load("POST", action)
                .uploadProgressHandler(new ProgressCallback() {
                    @Override
                    public void onProgress(long transferred, long total) {
                        callback.onProgress(transferred, total);
                    }
                })
                .setMultipartParameter("AWSAccessKeyId", AWSAccessKeyID)
                .setMultipartParameter("Content-Type", contentType)
                .setMultipartParameter("Policy", policy)
                .setMultipartParameter("Signature", signature)
                .setMultipartParameter("acl", ACL)
                .setMultipartParameter("key", key)
                .setMultipartFile("file", videoFile)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        callback.onCompleted(e, result);
                    }
                });
    }
}
