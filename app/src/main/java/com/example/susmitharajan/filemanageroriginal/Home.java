package com.example.susmitharajan.filemanageroriginal;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by susmitharajan on 22/03/18.
 */

public class Home extends Fragment{

    List<HomeModel> heroList;

    //the listview
    GridView listView;

    ArrayList<String> videos = new ArrayList<>();
    ArrayList<String> music = new ArrayList<>();
    ArrayList<String> image = new ArrayList<>();
    ArrayList<String> document = new ArrayList<>();
    ArrayList<String> compressed = new ArrayList<>();
    ArrayList<String> apk = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Home");

        heroList = new ArrayList<>();
        listView = (GridView) getView().findViewById(R.id.listView);

        //adding some values to our list
        heroList.add(new HomeModel(R.drawable.ic_folder_black_24dp, "Images"));
        heroList.add(new HomeModel(R.drawable.ic_folder_black_24dp, "Videos"));
        heroList.add(new HomeModel(R.drawable.ic_folder_black_24dp, "Music"));
        heroList.add(new HomeModel(R.drawable.ic_folder_black_24dp, "Compressed"));
        heroList.add(new HomeModel(R.drawable.ic_folder_black_24dp, "Favorites"));
        heroList.add(new HomeModel(R.drawable.ic_folder_black_24dp, "Apps"));
        heroList.add(new HomeModel(R.drawable.ic_folder_black_24dp, "Documents"));
        heroList.add(new HomeModel(R.drawable.ic_folder_black_24dp, "Downloads"));
        heroList.add(new HomeModel(R.drawable.ic_folder_black_24dp, "Recent"));

        //creating the adapter
        HomeAdapter adapter = new HomeAdapter(getContext(), R.layout.home_customlist, heroList);

        //attaching adapter to the listview
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if(position == 0){
                    Intent in = new Intent(getActivity(),display.class);
                    in.putExtra("VIDEO_ALBUM",image);
                    startActivity(in);
                }
                else if(position == 1){
                    Intent in = new Intent(getActivity(),display.class);
                    in.putExtra("VIDEO_ALBUM",videos);
                    startActivity(in);
                }
                else if(position == 2){
                    Intent in = new Intent(getActivity(),display.class);
                    in.putExtra("VIDEO_ALBUM",music);
                    startActivity(in);
                }
                else if(position == 3){
                    Intent in = new Intent(getActivity(),display.class);
                    in.putExtra("VIDEO_ALBUM",compressed);
                    startActivity(in);
                }
                else if(position == 4){


                }
                else if(position == 5){
                    Intent in = new Intent(getActivity(),display.class);
                    in.putExtra("VIDEO_ALBUM",apk);
                    startActivity(in);
                }
                else if(position == 6){
                    Intent in = new Intent(getActivity(),display.class);
                    in.putExtra("VIDEO_ALBUM",document);
                    startActivity(in);
                }
                else if(position == 7){

                }
                else if(position == 8){

                }
            }
        });
    }

    ArrayList<String> displayVideoAlbums(){
        String temp, cval;
        ArrayList<String> albumlist = new ArrayList<>();
        String album = MediaStore.Video.Media.ALBUM;
        String[] albums = {album};
        Cursor cursor=getActivity().getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,  albums, null, null, null);
        if(cursor!=null && cursor.getCount()>0){
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
        }
        return albumlist;

    }

    ArrayList<String> displayMusicAlbums(){
        String temp, cval;
        ArrayList<String> albumlist = new ArrayList<>();
        String album = MediaStore.Audio.Media.ALBUM;
        String[] albums = {album};
        Cursor cursor=getActivity().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,  albums, null, null, null);
        if(cursor!=null && cursor.getCount() > 0){
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
        }
        return albumlist;
    }

    ArrayList<String> displayImageAlbums(){
        String temp, cval;
        ArrayList<String> albumlist = new ArrayList<>();
        String album = MediaStore.Images.Media.BUCKET_DISPLAY_NAME;
        String[] albums = {album};
        Cursor cursor=getActivity().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,  albums, null, null, null);
        if(cursor!=null && cursor.getCount() > 0){
            cursor.moveToFirst();
            temp = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
            albumlist.add(temp);
            do
            {
                cval = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
                //if(!temp.equals(cval))
                if(!albumlist.contains(cval))
                {
                    //Here you can check for it's genre as well, up to you
                    albumlist.add(cval);
                    //Only add the distinct album names to albumlist
                }
                temp = cval;
            } while(cursor.moveToNext());
        }
        return albumlist;

    }

    private void getListFiles(File parentDir) {
        Queue<File> files = new LinkedList<>();
        files.addAll(Arrays.asList(parentDir.listFiles()));
        while (!files.isEmpty()) {
            File file = files.remove();
            if (file.isDirectory()) {
                files.addAll(Arrays.asList(file.listFiles()));
            } else if (file.getName().endsWith(".csv")) {
                document.add(file.getName().toString());
            }else if(file.getName().endsWith(".zip")){
                compressed.add(file.getName().toString());
            }else if(file.getName().endsWith(".apk")){
                apk.add(file.getName().toString());
            }

        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        videos = displayVideoAlbums();

        music = displayMusicAlbums();

        image = displayImageAlbums();

        File root=new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        getListFiles(root);


    }
}
