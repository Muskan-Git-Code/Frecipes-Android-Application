package com.example.democode;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.github.barteksc.pdfviewer.PDFView;

import java.util.ArrayList;
import java.util.Arrays;

public class F2Adapter extends RecyclerView.Adapter<F2Adapter.MyViewHolder> {
    ArrayList<String> l1;
    ArrayList<Integer> l2;
    Context context;
    final String[] dstory= {"baking.pdf", "cheesecake.pdf", "chocolate.pdf", "desserts.pdf", "french.pdf", "indian.pdf", "italian.pdf", "snacks.pdf"};

    public F2Adapter(Context context, ArrayList<String> l1, ArrayList<Integer> l2) {
        this.context = context;
        this.l1 = l1;
        this.l2 = l2;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;
        public MyViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
            image = (ImageView) v.findViewById(R.id.image);
        }
    }

    @Override
    public F2Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_f2_adapter, parent, false);
        return new F2Adapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(F2Adapter.MyViewHolder holder, final int position) {
        holder.name.setText(l1.get(position));
        holder.image.setImageResource(l2.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animation a1 = AnimationUtils.loadAnimation(context, R.anim.bounce);
                holder.itemView.startAnimation(a1);

                Intent i= new Intent(context, pdfopen.class);
                i.putExtra("story", dstory[position]);
                context.startActivity(i);
            }
        });
    }

    @Override  public int getItemCount() { return l1.size();}

    public void filterList(ArrayList<String> filterdNames) {
        this.l1 = filterdNames;
        notifyDataSetChanged();
    }
}
