package fm.synq.SynqAndroid;

/**
 * Created by Kjartan on 09.11.2016.
 */

public interface SynqUploadHandler {

    /**
     * Returns when the request finished successfully
     */
    void onCompleted();

    /**
     * Returns when the request failed due to a network error
     * or because the server returned an HTTP error.
     *
     * @param error     Error message
     */
    void onFailure(String error);

    /**
     * Reports the file upload progress periodically
     */
    void onProgress(long bytesTransferred, long totalSize);
}
