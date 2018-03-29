package com.example.susmitharajan.filemanageroriginal;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.provider.SearchRecentSuggestions;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.util.Spliterator.DISTINCT;

/**
 * Created by susmitharajan on 28/03/18.
 */

public class display extends Activity {

    ArrayList<String> audioList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ArrayList<String> video_album = getIntent().getStringArrayListExtra("VIDEO_ALBUM");

        if(video_album.isEmpty()){
            setContentView((R.layout.nothing));
        }
        else{
            setContentView(R.layout.display_listview);
            ArrayAdapter adapter = new ArrayAdapter<String>(this,
                    R.layout.display_customlist, video_album);

            ListView listView = (ListView) findViewById(R.id.mobile_list);
            listView.setAdapter(adapter);
        }

        Toast.makeText(getApplicationContext(),"CLicked Music!!!",Toast.LENGTH_SHORT).show();
    }
}
