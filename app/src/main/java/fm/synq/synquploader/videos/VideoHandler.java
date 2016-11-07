package fm.synq.synquploader.videos;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;

/**
 * Created by kjartanvestvik on 07.11.2016.
 */

public class VideoHandler {

    public ArrayList<Video> videos;

    public VideoHandler(Context context) {

        // Get the videos on this device
        videos = getLocalVideoFiles(context);
    }


    private ArrayList<Video> getLocalVideoFiles(Context context) {
        ArrayList<Video> localVideos = new ArrayList<>();
        ContentResolver videoResolver = context.getContentResolver();
        Uri videoUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        Cursor videoCursor = videoResolver.query(videoUri, null, null, null, null);

        if(videoCursor!=null && videoCursor.moveToFirst()){
            //get columns
            int idColumn = videoCursor.getColumnIndex
                    (MediaStore.Video.Media._ID);
            int filePath = videoCursor.getColumnIndex
                    (MediaStore.Video.Media.DATA);
            int dateTakenColumn = videoCursor.getColumnIndex
                    (MediaStore.Video.Media.DATE_TAKEN);

            do {
                //long thisId = videoCursor.getLong(idColumn);
                String thisFile = videoCursor.getString(filePath);
                String thisThumbPath = videoCursor.getString(videoCursor.getColumnIndex(MediaStore.Video.Thumbnails.DATA));

                Video thisVideo = new Video(thisFile, thisThumbPath);
                localVideos.add(thisVideo);
            }
            while (videoCursor.moveToNext());
        }
        assert videoCursor != null;
        videoCursor.close();

        return localVideos;
    }
}
