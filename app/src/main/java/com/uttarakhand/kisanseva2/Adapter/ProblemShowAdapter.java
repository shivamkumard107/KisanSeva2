package com.uttarakhand.kisanseva2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.uttarakhand.kisanseva2.R;

import org.jetbrains.annotations.NotNull;


/**
 * Created by sukhbir on 6/8/16.
 */
public class ProblemShowAdapter extends RecyclerView.Adapter<ProblemShowAdapter.viewHolder> {
    private String[] array;
    private Intent[] links;
    private Context context;

    public ProblemShowAdapter(Context context, String[] array, Intent[] links) {
        this.context = context;
        this.array = array;
        this.links = links;
    }

    @NotNull
    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = R.layout.item_category;
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, final int position) {

        holder.textView.setText(array[position]);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(links[position]);
            }
        });
    }

    @Override
    public int getItemCount() {
        return array.length;
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView card;

        public viewHolder(View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card);
            textView = itemView.findViewById(R.id.title);
        }
    }
}
