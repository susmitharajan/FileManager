package com.example.susmitharajan.filemanageroriginal;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.os.EnvironmentCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.support.v4.content.ContextCompat.getExternalFilesDirs;

/**
 * Created by susmitharajan on 22/03/18.
 */

public class External extends Fragment {
    List<custom_internal> heroList;

    ListView listView;

    private List<String> fileList=new ArrayList<String>();

    String strtext = null;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("External Storage");

       // File root=new File(Environment.getExternalStorageDirectory().getAbsolutePath());
         String[] file1 = getExternalStorageDirectories();

        File root = new File(file1[0]);
        if(strtext!=null){
            root = new File(strtext);
        }
        //test

        File[] files = root.listFiles();
        int size = files.length;
        String[] filelist = new String[size];
        String[] lastModified = new String[size];
        String[] path = new String[size];

        int i= 0,y=0,k=0;
        for (File file : files) {
            if(!file.isHidden()){
                filelist[i++] = file.getName().toString();
                path[k++] = file.toString();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy  hh:mm a");

                Date lastMod = new Date(file.lastModified());
                lastModified[y++] = format.format(Date.parse(lastMod.toString()));
            }
            else{
                size = size - 1;
            }


        }

        //test


        heroList = new ArrayList<>();
        listView = (ListView) getView().findViewById(R.id.listView);

        for(int j = 0; j < size; j++){
            heroList.add(new custom_internal(R.drawable.icon_folder, filelist[j], lastModified[j], path[j]));
        }

        //creating the adapter
        Internal_adapter adapter = new Internal_adapter(getContext(), R.layout.internal_listview, heroList);

        //attaching adapter to the listview
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                custom_internal hero = heroList.get(i);
                Bundle bundle=new Bundle();
                bundle.putString("Name", hero.getPath());
                Internal nextFrag= new Internal();
                nextFrag.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.layout_container, nextFrag,"FRAGMENT_TAG")
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    public String[] getExternalStorageDirectories() {

        List<String> results = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { //Method 1 for KitKat & above
            File[] externalDirs = getExternalFilesDirs(getContext(),null);

            for (File file : externalDirs) {
                String path = file.getPath().split("/Android")[0];

                boolean addPath = false;

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    addPath = Environment.isExternalStorageRemovable(file);
                }
                else{
                    addPath = Environment.MEDIA_MOUNTED.equals(EnvironmentCompat.getStorageState(file));
                }

                if(addPath){
                    results.add(path);
                }
            }
        }

        if(results.isEmpty()) { //Method 2 for all versions
            // better variation of: http://stackoverflow.com/a/40123073/5002496
            String output = "";
            try {
                final Process process = new ProcessBuilder().command("mount | grep /dev/block/vold")
                        .redirectErrorStream(true).start();
                process.waitFor();
                final InputStream is = process.getInputStream();
                final byte[] buffer = new byte[1024];
                while (is.read(buffer) != -1) {
                    output = output + new String(buffer);
                }
                is.close();
            } catch (final Exception e) {
                e.printStackTrace();
            }
            if(!output.trim().isEmpty()) {
                String devicePoints[] = output.split("\n");
                for(String voldPoint: devicePoints) {
                    results.add(voldPoint.split(" ")[2]);
                }
            }
        }

        //Below few lines is to remove paths which may not be external memory card, like OTG (feel free to comment them out)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (int i = 0; i < results.size(); i++) {
                if (!results.get(i).toLowerCase().matches(".*[0-9a-f]{4}[-][0-9a-f]{4}")) {
                    Log.d("MainActivity", results.get(i) + " might not be extSDcard");
                    results.remove(i--);
                }
            }
        } else {
            for (int i = 0; i < results.size(); i++) {
                if (!results.get(i).toLowerCase().contains("ext") && !results.get(i).toLowerCase().contains("sdcard")) {
                    Log.d("MainActivity", results.get(i)+" might not be extSDcard");
                    results.remove(i--);
                }
            }
        }

        String[] storageDirectories = new String[results.size()];
        for(int i=0; i<results.size(); ++i) storageDirectories[i] = results.get(i);

        return storageDirectories;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(getArguments()!=null){
            strtext = getArguments().getString("Name");
        }
        if(container!=null){
            container.removeAllViews();
        }
        return inflater.inflate(R.layout.internal, container, false);
    }
}
