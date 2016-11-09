package fm.synq.synquploader_example.SynqAPI;

import android.util.Log;

import com.google.gson.JsonObject;

/**
 * Created by Kjartan on 09.11.2016.
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
     * Returns when the API call returned an error response.
     *
     * @param name      The name of the error
     * @param message   The error-message
     */
    public void onError(String name, String message) {
        //Log.w("SynqAPI", "onError() was not overriden, but callback was received:");
        Log.w("SynqAPI", "\tAPI error:");
        //Log.w("SynqAPI", "\t[" + code + "]: " + name + ": " + message + "(" + summary + ")");
    }
}

