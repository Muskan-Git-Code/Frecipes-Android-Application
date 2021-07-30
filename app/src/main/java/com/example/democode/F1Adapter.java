package com.example.democode;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;


public class F1Adapter extends RecyclerView.Adapter<F1Adapter.MyViewHolder> {

    ImageView iv1;
    TextView tv1, tv2, tv4;
    private List<PostPojo1> d1;
    Context context;

    public F1Adapter(Context context, List<PostPojo1> d1) {
        this.context = context;
        this.d1 = d1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View v) {
            super(v);
            tv2 = (TextView) v.findViewById(R.id.name2);
            iv1= (ImageView)v.findViewById(R.id.imageView);
        }
    }

    @Override
    public F1Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_f1_adapter, parent, false);
        return new F1Adapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(F1Adapter.MyViewHolder holder, final int position) {
        tv2.setText(d1.get(position).getStrMeal());

        Glide.with(context).load(d1.get(position).getStrMealThumb()).
                placeholder(R.drawable.loading).diskCacheStrategy(DiskCacheStrategy.NONE).into(iv1);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder b= new AlertDialog.Builder(view.getContext());
                b.setTitle(d1.get(position).getStrMeal());   b.setMessage(d1.get(position).getStrInstructions());
                b.setPositiveButton("OK", null);
                AlertDialog a= b.create();    a.show();

                Animation a1 = AnimationUtils.loadAnimation(context, R.anim.bounce);
                holder.itemView.startAnimation(a1);
            }
        });
    }

    @Override  public int getItemCount() { return d1.size();}
}