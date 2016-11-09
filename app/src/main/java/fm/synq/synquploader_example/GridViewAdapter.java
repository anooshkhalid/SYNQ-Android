package fm.synq.synquploader_example;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

import fm.synq.synquploader_example.videos.Video;

/**
 * Created by kjartanvestvik on 07.11.2016.
 */

public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private int layoutResourceId;
    private GridView gridView;
    private ArrayList<Video> videos = new ArrayList<>();

    public GridViewAdapter(Context context, int layoutResourceId, ArrayList<Video> data, GridView view) {
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.videos = data;
        this.gridView = view;
    }


    public void updateData(ArrayList<Video> videosArray) {
        // Update the adapter´s dataset
        this.videos = videosArray;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return this.videos.size();
    }

    @Override
    public Object getItem(int position) {
        return videos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.selectedOverlay = convertView.findViewById(R.id.selectedOverlay);
            holder.selectedOverlay.setAlpha(0.6f);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Video video = videos.get(position);

        // Check that video thumbnail path is set (to avoid a crash)
        if (video.getVideoThumbnailPath() != null) {
            holder.image.setImageURI(Uri.parse(video.getVideoThumbnailPath()));
            Log.e("f", "thumbnnail path: " + video.getVideoThumbnailPath());
            Log.e("f", "file path  : " + video.getVideoFilePath());
        }
        else {
            Log.e("f", "thumbnail path NULL");
        }


        return convertView;
    }

    static private class ViewHolder {
        ImageView image;
        View selectedOverlay;

    }
}
