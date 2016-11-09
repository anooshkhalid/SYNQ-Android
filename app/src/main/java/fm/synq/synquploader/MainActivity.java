package fm.synq.synquploader;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import fm.synq.synquploader.videos.Video;
import fm.synq.synquploader.videos.VideoHandler;

public class MainActivity extends AppCompatActivity {

    private GridViewAdapter gridAdapter;
    private ArrayList<Video> videos;
    static int MY_PERMISSIONS_REQUEST_READ_STORAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videos = new ArrayList<>();

        // Setup GridView
        final GridView gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(this, R.layout.grid_item, videos, gridView);
        gridView.setAdapter(gridAdapter);

        // GridView item click listener
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Video video = (Video) parent.getItemAtPosition(position);

                //gridAdapter.notifyDataSetChanged();

                // Synq API:
                // - Create video object, video/create
                // - Get upload params, video/upload

                // SynqUploader:
                // - Upload video to AWS, using parameters returned from video/upload



            }
        });

        // Check permissions. If permissions not granted, request them and wait with accessing videos until request returns
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //Log.e("f", "PERMISSION storage needed");

            requestPermissionStorage();
        }
        else {
            // Init VideoHandler
            VideoHandler videoHandler = new VideoHandler(this);
            videos = videoHandler.videos;

            Log.e("f", "Videos: " + videos.size());

            //
            setThumbnailPathForVideos();

            // Refresh gridView
            gridAdapter.updateData(videos);
        }


    }


    //
    // Thumbnail methods //
    //

    private void setThumbnailPathForVideos() {
        for (Video video : videos) {
            String thumbnailPath = getThumbnailPathForVideo(this, video);
            video.setVideoThumbnailPath(thumbnailPath);
        }
    }

    private static String[] thumbColumns = { MediaStore.Video.Thumbnails.DATA };

    private static String getThumbnailPathForVideo(Context context, Video video) {

        long fileId = video.getFileId();

        MediaStore.Video.Thumbnails.getThumbnail(context.getContentResolver(),
                fileId, MediaStore.Video.Thumbnails.MICRO_KIND, null);

        Cursor thumbCursor = null;
        try {

            thumbCursor = context.getContentResolver().query(
                    MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI,
                    thumbColumns, MediaStore.Video.Thumbnails.VIDEO_ID + " = "
                            + fileId, null, null);

            if (thumbCursor.moveToFirst()) {
                return thumbCursor.getString(thumbCursor
                        .getColumnIndex(MediaStore.Video.Thumbnails.DATA));
            }
        }
        finally {
            if(thumbCursor != null)
                thumbCursor.close();
        }
        return null;
    }





    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_STORAGE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // Init VideoHandler
                VideoHandler videoHandler = new VideoHandler(this);
                videos = videoHandler.videos;

                Log.e("f", "Videos: " + videos.size());

                //
                setThumbnailPathForVideos();

                // Refresh gridView
                gridAdapter.updateData(videos);
            }
            else {
                Log.e("f", "Storage permission DENIED");
                // permission denied
                // TODO: display an info message in gridView

            }
        }
    }

    private void requestPermissionStorage() {
        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response!
            // Display a snackbar and then request
            // the permission again when the snackbar is dismissed.
            Snackbar.make(findViewById(android.R.id.content),
                    R.string.storage_permission_rationale,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Request the permission again.
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_STORAGE);
                        }
                    }).show();
        } else {

            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_STORAGE);
        }
    }
}
