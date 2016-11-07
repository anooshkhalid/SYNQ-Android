package fm.synq.synquploader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import fm.synq.synquploader.videos.Video;

public class MainActivity extends AppCompatActivity {

    private GridViewAdapter gridAdapter;
    private ArrayList<Video> videos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videos = new ArrayList<>();

        final GridView gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(this, R.layout.grid_item, videos, gridView);
        gridView.setAdapter(gridAdapter);

        // Item click listener
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Video video = (Video) parent.getItemAtPosition(position);

                //gridAdapter.notifyDataSetChanged();

                // Upload video:


            }
        });
    }
}
