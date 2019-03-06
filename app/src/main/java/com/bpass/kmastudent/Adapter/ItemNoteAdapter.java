package com.bpass.kmastudent.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bpass.kmastudent.Activity.NoteActivity;
import com.bpass.kmastudent.DTO.Note;
import com.bpass.kmastudent.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HoangNguyen on 25-Nov-16.
 */

public class ItemNoteAdapter extends RecyclerView.Adapter<ItemNoteAdapter.RecyclerViewHolder> {

    // danh sách note
    private List<Note> list = new ArrayList<Note>();
    private Context context;

    public ItemNoteAdapter(Context context, List<Note> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //kết nối tới item view
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.item_row, viewGroup, false);
        return new RecyclerViewHolder(itemView);
    }

    //set dữ liệu cho item
    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, int position) {
        Note note = list.get(position);
        viewHolder.tvTitle.setText(note.getTitle());
        viewHolder.tvContent.setText(note.getContent());
        viewHolder.tvLastModified.setText(note.getLastModified());
    }


    public void addItem(int position, Note note) {
        list.add(position, note);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * ViewHolder for item view of list
     */

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        public LinearLayout container;
        public TextView tvTitle;
        public TextView tvContent;
        public TextView tvLastModified;

        public RecyclerViewHolder(View itemView) {
            super(itemView);

            container = (LinearLayout) itemView.findViewById(R.id.linear_item);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            tvLastModified = (TextView) itemView.findViewById(R.id.tv_last_modified);

            container.setOnClickListener(this);
        }

        // khi click chọn item nào thì hiển thị nó
        @Override
        public void onClick(View v) {
            NoteActivity.showNote(context, list.get(getPosition()).getId());
        }
    }
}
