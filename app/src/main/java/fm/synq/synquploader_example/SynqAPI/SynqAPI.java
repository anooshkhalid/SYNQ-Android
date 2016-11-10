package fm.synq.synquploader_example.SynqAPI;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.body.StringBody;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Created by Kjartan on 09.11.2016.
 */

public class SynqAPI {

    private static final String BASE_URL = "http://api.staging.synq.fm/v1/";
    private static final String API_KEY = "210515fb2dc04bf48cd97a722505b0e5";

    public SynqAPI() {

    }


    /**
     *  Create a new video
     *
     *  @param handler
     */
    public void video_create(
            final SynqResponseHandler handler,
            Context context
    ) {
        Ion.with(context)
                .load("POST", BASE_URL + "video/create")
                .setBodyParameter("api_key", API_KEY)
                .asJsonObject()
                .withResponse()
                .setCallback(new FutureCallback<Response<JsonObject>>() {
                    @Override
                    public void onCompleted(Exception e, Response<JsonObject> result) {
                        //Log.e("f", "Res: " + result.getHeaders().message());

                        if(e != null){
                            handler.onFailure(e);
                        }
                        else if(result.getHeaders().code() == 400){
                            // a HTTP 400 was returned -- extract error
                            handler.onError(result.getResult().get("name").getAsString(), result.getResult().get("message").getAsString());
                        }
                        else {
                            handler.onSuccess(result.getResult());
                        }
                    }
                });
    }


    /**
     *  Create a new video
     *
     *  @param handler
     */
    public void video_upload(
            final SynqResponseHandler handler,
            Context context,
            String videoId
    ) {
        Ion.with(context)
                .load("POST", BASE_URL + "video/upload")
                .setBodyParameter("api_key", API_KEY)
                .setBodyParameter("video_id", videoId)
                .asJsonObject()
                .withResponse()
                .setCallback(new FutureCallback<Response<JsonObject>>() {
                    @Override
                    public void onCompleted(Exception e, Response<JsonObject> result) {
                        //Log.e("f", "Response: " + result);
                        Log.e("f", "Res: " + result.getHeaders().message());

                        if(e != null){
                            handler.onFailure(e);
                        }
                        else if(result.getHeaders().code() == 400){
                            // a HTTP 400 was returned -- extract error

                            handler.onError(result.getResult().get("name").getAsString(), result.getResult().get("message").getAsString());
                        }
                        else {
                            handler.onSuccess(result.getResult());
                        }
                    }
                });
    }
}
