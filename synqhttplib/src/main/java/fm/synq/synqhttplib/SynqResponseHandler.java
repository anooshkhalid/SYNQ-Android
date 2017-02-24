package fm.synq.synqhttplib;

import android.util.Log;

import com.google.gson.JsonObject;

/**
 * Created by Kjartan on 24.02.2017.
 */

public class SynqResponseHandler {

    /**
     * Returns when request succeeds
     *
     * @param jsonResponse  The servers response (JsonObject)
     */
    public void onSuccess(JsonObject jsonResponse) {
        //Log.w("SynqAPI", "onSuccess(JsonObject jsonResponse) was not overriden, but callback was received");
    }

    /**
     * Returns when request failed due to a network-error
     * or because the server returned an HTTP error.
     *
     * @param e     throwable describing the way request failed
     */
    public void onFailure(Exception e) {
        Log.w("SynqAPI", "onFailure(Exception e) was not overriden, but callback was received", e);
    }

    /**
     * Returns when the http call returned an error response.
     *
     * @param message   The error message
     */
    public void onError(String message) {
        //Log.w("SynqAPI", "onError() was not overriden, but callback was received:");
        Log.w("SynqHttpClient", "\terror:");
    }
}
