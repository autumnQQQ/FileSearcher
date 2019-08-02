package com.will.filesearcher;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.will.filesearcher.searchengine.FileItem;
import com.will.filesearcher.searchengine.SearchEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Will on 2017/11/1.
 */

public class FileSearcherAdapter extends RecyclerView.Adapter<FileSearcherAdapter.FileSearcherVH> {
    private List<FileItem> items = new ArrayList<>();
    private List<FileItem> selectedItems = new ArrayList<>();
    private final int colorUnchecked;
    private final int colorChecked;
    private SearchEngine searchEngine;

    public FileSearcherAdapter(Context context,@NonNull SearchEngine searchEngine){
        colorUnchecked = context.getResources().getColor(R.color.fileSearcherWhite);
        colorChecked = context.getResources().getColor(R.color.fileSearcherCheckedBackground);
        this.searchEngine = searchEngine;
    }

    @Override
    public FileSearcherVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FileSearcherVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.file_searcher_item,parent,false));
    }

    @Override
    public void onBindViewHolder(FileSearcherVH holder, int position) {
        FileItem item = items.get(position);
        holder.title.setText(item.getName());
        holder.path.setText(item.getPath());
        holder.detail.setText(item.getDetail());
        holder.itemView.setBackgroundColor(item.isChecked()? colorChecked : colorUnchecked);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(FileItem item){
        items.add(item);
        notifyDataSetChanged();
    }

    public void addItem(List<FileItem> item){
        items.addAll(item);
        notifyDataSetChanged();
    }

    class FileSearcherVH extends RecyclerView.ViewHolder{
        TextView title, detail, path;
        public FileSearcherVH(final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.file_searcher_item_text_title);
            detail = itemView.findViewById(R.id.file_searcher_item_text_detail);
            path = itemView.findViewById(R.id.file_searcher_item_text_path);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //items.get(getLayoutPosition()).setChecked(checkBox.isChecked());
                }
            });

        }
    }


}
