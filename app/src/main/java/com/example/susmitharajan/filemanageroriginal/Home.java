package com.example.susmitharajan.filemanageroriginal;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by susmitharajan on 22/03/18.
 */

public class Home extends Fragment{

    List<HomeModel> heroList;

    //the listview
    GridView listView;

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

                }
                else if(position == 1){

                }
                else if(position == 2){
                    /*Intent i = new Intent(getContext(),display.class);
                    startActivity(i);*/
                }
                else if(position == 3){

                }
                else if(position == 4){

                }
                else if(position == 5){

                }
                else if(position == 6){

                }
                else if(position == 7){

                }
                else if(position == 8){

                }
                else if(position == 9){

                }
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
