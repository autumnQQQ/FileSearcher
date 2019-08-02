package com.will.filesearcher;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.will.filesearcher.delegate.FileSearcherDelegateActivity;
import com.will.filesearcher.filter.FileFilter;

import java.io.File;
import java.util.List;

public class FileSearcher  {
    private File path;
    static final String FILE_FILTER = "file_filter";
    static final String SEARCH_PATH = "search_path";

    private final FileFilter fileFilter = new FileFilter();
    private final Context context;
    public static FileSearcherCallback callback;

    /**
     *
     * @param context context
     */
    public FileSearcher(@NonNull Context context){
        this.context = context;
    }

    /**
     * search with keyword
     * @param keyword keyword
     * @return itself
     */
    public FileSearcher withKeyword(@NonNull String keyword){
        fileFilter.withKeyword(keyword);
        return this;
    }

    /**
     * search with specified conditions,if passed path is invalid,an IllegalStatementException will be thrown.
     * @param callback
     */
    public void search(FileSearcherCallback callback){
        if(path == null){
            path = Environment.getExternalStorageDirectory();
        }else if(!path.isDirectory()){
            throw new IllegalArgumentException("the path must be a directory");
        }
        this.callback = callback;
        Intent intent = new Intent(context,FileSearcherDelegateActivity.class);
        intent.putExtra(FILE_FILTER,fileFilter);
        intent.putExtra(SEARCH_PATH,path);
        context.startActivity(intent);
    }

    public interface FileSearcherCallback{
        void onSelect(List<File> files);
    }
}
