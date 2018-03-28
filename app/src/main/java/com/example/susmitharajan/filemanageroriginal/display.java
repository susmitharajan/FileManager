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
        setContentView(R.layout.display_listview);
/*

        ArrayList audio=new ArrayList();
        String album = MediaStore.Video.Media.ALBUM;
        String[] albums = {album};
        Cursor c=getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,  albums, null, null, null);


        while(c.moveToNext())
        {
            String name=c.getString(c.getColumnIndex(album));
            audio.add(name);

        }
*/

        /*String temp, cval;
        ArrayList<String> albumlist = new ArrayList<>();
        String album = MediaStore.Video.Media.ALBUM;
        String[] albums = {album};
        Cursor c=getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,  albums, null, null, null);

        c.moveToFirst();
        temp = c.getString(c.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
        albumlist.add(temp);
        do
        {
            cval = c.getString(c.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
            if(!temp.equals(cval))
            {
                //Here you can check for it's genre as well, up to you
                albumlist.add(cval);
                //Only add the distinct album names to albumlist
            }
            temp = cval;
        } while(c.moveToNext());
*/


        String temp, cval;
        ArrayList<String> albumlist = new ArrayList<>();
        String album = MediaStore.Video.Media.ALBUM;
        String[] albums = {album};
        Cursor cursor=getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,  albums, null, null, null);
        cursor.moveToFirst();
        temp = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
        albumlist.add(temp);
        do
        {
            cval = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
            //if(!temp.equals(cval))
            if(!albumlist.contains(cval))
            {
                //Here you can check for it's genre as well, up to you
                albumlist.add(cval);
                //Only add the distinct album names to albumlist
            }
            temp = cval;
        } while(cursor.moveToNext());



        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.display_customlist, albumlist);

        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);

        Toast.makeText(getApplicationContext(),"CLicked Music!!!",Toast.LENGTH_SHORT).show();

    }
}
