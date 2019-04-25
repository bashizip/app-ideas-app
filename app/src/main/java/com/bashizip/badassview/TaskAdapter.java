package com.bashizip.badassview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by bashizip
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    private List<Task> tasks;

    public interface ListItemClickListener {
        void clickPosition(int position, View v);
    }

    private ListItemClickListener listItemClickListener;
    private Context ctx;

    public TaskAdapter(Context ctx, List<Task> tasks, ListItemClickListener listItemClickListener) {
        this.listItemClickListener = listItemClickListener;
        this.tasks = tasks;
        this.ctx = ctx;

    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_row, parent, false);

        return new MyViewHolder(itemView, listItemClickListener);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Task task = tasks.get(position);
        holder.tv_name.setText(task.getName());

        Task.State state = task.getState();

        switch (state) {
            case done:
                holder.statusIcon.setVisibility(View.VISIBLE);
                holder.cardView.setCardBackgroundColor(ctx.getResources().getColor(R.color.colorPrimary));
                holder.tv_name.setTextColor(Color.WHITE);
                holder.statusIcon.setImageDrawable(ctx.getResources().getDrawable(R.drawable.ic_done_all_white_24dp));
                break;
            case pending:
                holder.tv_name.setTextColor(ctx.getResources().getColor(R.color.primaryTetx));
                holder.cardView.setCardBackgroundColor(ctx.getResources().getColor(R.color.white));
                holder.statusIcon.setVisibility(View.INVISIBLE);
                break;
            case assigned:
                holder.tv_name.setTextColor(Color.WHITE);
                holder.statusIcon.setVisibility(View.VISIBLE);
                holder.cardView.setCardBackgroundColor(ctx.getResources().getColor(R.color.colorAccent));
                holder.statusIcon.setImageDrawable(ctx.getResources().getDrawable(R.drawable.ic_timer_black_24dp));
                break;
        }

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_name;
        ImageView statusIcon;
        LinearLayout topLayout;
        CardView cardView;


        private ListItemClickListener listItemClickListener;

        public MyViewHolder(View view, ListItemClickListener listItemClickListener) {
            super(view);
            tv_name = view.findViewById(R.id.tv_taskName);
            topLayout = view.findViewById(R.id.topLayout);
            statusIcon = view.findViewById(R.id.iv_statusIcon);
            cardView = view.findViewById(R.id.card_item);

            this.listItemClickListener = listItemClickListener;
            topLayout.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            listItemClickListener.clickPosition(getAdapterPosition(), v);
        }
    }
}
