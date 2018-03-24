package com.example.susmitharajan.filemanageroriginal;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by susmitharajan on 22/03/18.
 */

public class Internal extends Fragment {

    List<custom_internal> heroList;

    ListView listView;

    private List<String> fileList=new ArrayList<String>();

    String strtext = null;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Internal Storage");

        File root=new File(Environment.getExternalStorageDirectory().getAbsolutePath());
       // String[] files = getListFiles(root);
        if(strtext!=null){
            root = new File(strtext);
        }
        //test

        File[] files = root.listFiles();
        String[] filelist = new String[files.length];
        String[] lastModified = new String[files.length];
        String[] path = new String[files.length];

        int i= 0,y=0,k=0;
        for (File file : files) {
            filelist[i++] = file.getName().toString();
            path[k++] = file.toString();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy  hh:mm a");

            Date lastMod = new Date(file.lastModified());
            lastModified[y++] = format.format(Date.parse(lastMod.toString()));

        }

        //test


        heroList = new ArrayList<>();
        listView = (ListView) getView().findViewById(R.id.listView);

        for(int j = 0; j < filelist.length; j++){
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
