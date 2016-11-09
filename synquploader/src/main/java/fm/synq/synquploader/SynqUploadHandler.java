package fm.synq.synquploader;

/**
 * Created by Kjartan on 09.11.2016.
 */

public interface SynqUploadHandler {

    void onCompleted(Exception e, String result);
    void onProgress(long bytesTransferred, long totalSize);
}
