package com.example.susmitharajan.filemanageroriginal;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by susmitharajan on 22/03/18.
 */

public class Internal extends Fragment implements MyRecyclerViewAdapter.ItemClickListener {

    List<custom_internal> heroList;

    ListView listView;

    private List<String> fileList=new ArrayList<String>();

    String strtext = null;

    ArrayList<String> navigation = new ArrayList<>();

    ArrayList<String> send = new ArrayList<>();

    private MyRecyclerViewAdapter adapter1;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Internal Storage");

        File root=new File(Environment.getExternalStorageDirectory().getAbsolutePath());
       // String[] files = getListFiles(root);
        if(strtext!=null){
            root = new File(strtext);
        }

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
            else
            {
                size = size - 1;
            }
        }

        heroList = new ArrayList<>();
        listView = (ListView) getView().findViewById(R.id.listView);

        for(int j = 0; j < size; j++){
            File children_count = new File(path[j]);
            int count = 0;
            if(children_count.listFiles() != null)
            {
                File[] files1 = children_count.listFiles();
                for(int t=0; t<files1.length; t++){
                    if(!files1[t].isHidden())
                        count++;
                }
            }

            heroList.add(new custom_internal(R.drawable.ic_folder_black_24dp, filelist[j], lastModified[j], path[j],String.valueOf(count)));
        }

        //creating the adapter
        Internal_adapter adapter = new Internal_adapter(getContext(), R.layout.internal_listview, heroList);

        //attaching adapter to the listview
        listView.setAdapter(adapter);


        //Test

        ArrayList<Integer> viewColoers = new ArrayList<>();
        viewColoers.add(Color.BLUE);
        viewColoers.add(Color.YELLOW);
        viewColoers.add(Color.MAGENTA);
        viewColoers.add(Color.RED);
        viewColoers.add(Color.BLACK);

        /*final ArrayList<String> animalNames = new ArrayList<>();

        animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");*/

        // set up the RecyclerView

        navigation = new ArrayList<>();
        if(send.size() == 0)
        navigation.add("Internal Storage");


        navigation.addAll(send);

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.rvAnimals);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);
        if(navigation != null){
            adapter1 = new MyRecyclerViewAdapter(getContext(), viewColoers, navigation);
            adapter1.setClickListener(this);
            recyclerView.setAdapter(adapter1);
        }

        //Test


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                custom_internal hero = heroList.get(i);
                File name = new File(hero.getPath());
                navigation.add(hero.getName());
                if(name.isDirectory()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("Name", hero.getPath());
                    //Test
                    bundle.putStringArrayList("Navigation",navigation);
                    //Test
                    Internal nextFrag = new Internal();
                    nextFrag.setArguments(bundle);

                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.layout_container, nextFrag, "FRAGMENT_TAG")
                            .addToBackStack(null)
                            .commit();
                }
            }

        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(getArguments()!=null){
            strtext = getArguments().getString("Name");
            send = getArguments().getStringArrayList("Navigation");
        }
        if(container!=null){
            container.removeAllViews();
        }
        return inflater.inflate(R.layout.internal, container, false);
    }

    @Override
    public void onItemClick(View view, int position) {

        int page = navigation.size() - position;

        Toast.makeText(getContext(), "You clicked " + page+"-"+adapter1.getItem(position) + " on item position " + position, Toast.LENGTH_SHORT).show();

        for(int y = page -1 ; y > 0; y--){
            FragmentManager fm = getFragmentManager();
            fm.popBackStack();
        }
    }

}
