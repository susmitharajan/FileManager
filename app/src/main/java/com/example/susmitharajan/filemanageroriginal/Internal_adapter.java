package com.example.susmitharajan.filemanageroriginal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by susmitharajan on 24/03/18.
 */

public class Internal_adapter extends ArrayAdapter<custom_internal> {
    List<custom_internal> heroList;

    //activity context
    Context context;

    //the layout resource file for the list items
    int resource;

    //constructor initializing the values
    public Internal_adapter(Context context, int resource, List<custom_internal> heroList) {
        super(context, resource, heroList);
        this.context = context;
        this.resource = resource;
        this.heroList = heroList;
    }

    //this will return the ListView Item as a View
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(resource, null, false);

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewTeam = view.findViewById(R.id.textViewTeam);
        final ImageView imageView1 = view.findViewById(R.id.popup);
        ImageView add = view.findViewById(R.id.add);
        TextView children = view.findViewById(R.id.children);

        custom_internal hero = heroList.get(position);
        imageView.setImageDrawable(context.getResources().getDrawable(hero.getImage()));
        textViewName.setText(hero.getName());
        textViewTeam.setText(String.valueOf(hero.getModified()));
        children.setText(hero.getChilren());

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popup = new PopupMenu(getContext(),imageView1);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Toast.makeText(getContext(),"You Clicked : " + menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();

            }
        });
        return view;
    }
}


