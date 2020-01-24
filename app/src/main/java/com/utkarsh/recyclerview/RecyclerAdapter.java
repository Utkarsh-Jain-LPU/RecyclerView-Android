package com.utkarsh.recyclerview;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.NoteHolder> {

    private Context context;
    private ArrayList<Example> examplelist;

    RecyclerAdapter(Context con, ArrayList<Example> list) {
        examplelist = list;
        context = con;
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int loc) {
        View itemview = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false);
        return new NoteHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull final NoteHolder noteHolder, final int loc) {
        final Example currentitem = examplelist.get(loc);
        noteHolder.data.setText(currentitem.getData());
        noteHolder.time.setText(currentitem.getTime());
        noteHolder.imageView.setImageBitmap(Bitmap.createScaledBitmap(currentitem.getBitmap(),150,150,false));
        noteHolder.relativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete")
                        .setIcon(R.drawable.delete)
                        .setMessage("You want to delete this item ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                examplelist.remove(loc);
                                RecyclerAdapter.this.notifyDataSetChanged();
                                Toast.makeText(context,"Item deleted Successfully...",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return examplelist.size();
    }

    class NoteHolder extends RecyclerView.ViewHolder {

        private TextView data;
        private TextView time;
        private ImageView imageView;
        private RelativeLayout relativeLayout;

        NoteHolder(@NonNull View itemView) {
            super(itemView);

            data = itemView.findViewById(R.id.data);
            time = itemView.findViewById(R.id.time);
            imageView = itemView.findViewById(R.id.image);
            relativeLayout = itemView.findViewById(R.id.linearlayout);
        }
    }
}
