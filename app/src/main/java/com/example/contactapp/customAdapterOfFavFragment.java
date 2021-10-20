package com.example.contactapp;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class customAdapterOfFavFragment extends RecyclerView.Adapter<customAdapterOfFavFragment.myviewholder>
{

    ArrayList<dataModelOfContact> favList;
    Context context;

    public customAdapterOfFavFragment(ArrayList<dataModelOfContact> favList,Context context) {
        this.favList = favList;
        this.context = context;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_fav_contact,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        //holder.img.setImageResource();
        holder.name.setText(favList.get(position).getName());
        holder.number.setText(favList.get(position).getNumber());

    }

    @Override
    public int getItemCount() {
        return favList.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView name,number;
        Button remove;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.contact_name);
            number = itemView.findViewById(R.id.contact_number);
            remove = itemView.findViewById(R.id.callButton);
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    favVariable db = new favVariable(context);
                    int position = getAbsoluteAdapterPosition();
                    // Toast.makeText(context,"i clicked on position:"+position,Toast.LENGTH_SHORT).show();
                    dataModelOfContact temp = favList.get(position);
                    String name = temp.getName();
                    db.deleteRecordAlternate(name);
                    Toast.makeText(context,"removing",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


}