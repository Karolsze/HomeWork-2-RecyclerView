package com.example.hmv3;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hmv3.TaskFragment.OnListFragmentInteractionListener;
import com.example.hmv3.tasks.TaskListContent.Task;

import java.util.List;
import java.util.Random;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Task} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTaskRecyclerViewAdapter extends RecyclerView.Adapter<MyTaskRecyclerViewAdapter.ViewHolder> {

    private final List<Task> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyTaskRecyclerViewAdapter(List<Task> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // Bind the viewholder to the element at the position
        Task task = mValues.get(position);
        holder.mItem = task;
        // Set the task title
        holder.mContentView.setText(task.title);
        // Set the task image
        final String picPath = task.picPath;
        Context context = holder.mView.getContext(); // retreive the  context of the application




        TypedArray typedArray = context.getResources().obtainTypedArray(R.array.avatars);
        Drawable contactDrawable = typedArray.getDrawable(task.imageIndex);
        holder.mItemImageView.setImageDrawable(contactDrawable);


        // Set an onClickListener for the list element
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener){
                    mListener.onListFragmentClickInteraction(holder.mItem, position);
                }
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //Notify the active callbacks interface (the activity, if the
                // fragment is attached to one that an item has been selected.
                // here, put the song, add a variable to the mlistener for the long click

                mListener.onListFragmentLongClickInteraction(holder.mItem, position);
                return false;
            }
        });
        holder.mItemButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDeleteButtonClickInteraction(position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public final ImageView mItemImageView;
        public final ImageButton mItemButtonView;
        public Task mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mItemImageView = (ImageView) view.findViewById(R.id.item_image);
            mContentView = (TextView) view.findViewById(R.id.content);
            // the delete button
            mItemButtonView = (ImageButton) view.findViewById(R.id.deleteButton);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
