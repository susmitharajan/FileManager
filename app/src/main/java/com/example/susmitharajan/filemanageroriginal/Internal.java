package com.example.susmitharajan.filemanageroriginal;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by susmitharajan on 22/03/18.
 */

public class Internal extends Fragment {

    List<custom_internal> heroList;

    ListView listView;

    private List<String> fileList=new ArrayList<String>();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Internal Storage");

        File root=new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        String[] files = getListFiles(root);


        heroList = new ArrayList<>();
        listView = (ListView) getView().findViewById(R.id.listView);

        for(int i = 0; i < files.length; i++){
            heroList.add(new custom_internal(R.drawable.nature, files[i], "Avengers"));
        }

        //creating the adapter
        Internal_adapter adapter = new Internal_adapter(getContext(), R.layout.internal_listview, heroList);

        //attaching adapter to the listview
        listView.setAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.internal, container, false);
    }

    String[] getListFiles(File parentDir) {
        ArrayList<File> inFiles = new ArrayList<File>();
        File[] files = parentDir.listFiles();
        String[] filelist = new String[files.length];
        int i= 0;
        for (File file : files) {
            filelist[i++] = file.toString();
        }
        return filelist;
    }
}
