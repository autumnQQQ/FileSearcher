package com.will.filesearcher.filter;

import java.io.File;
import java.io.Serializable;

/**
 * Created by Will on 2017/11/1.
 */

public class FileFilter implements Serializable{
    private String keyword = null;

    public void withKeyword(String keyword){
        this.keyword = keyword;
    }

    public boolean filter(File file){
        return  keywordFilter(file);
    }

    private boolean keywordFilter(File file){
        if(keyword == null || keyword.isEmpty() ){
            return true;
        }
        String fileName = file.getName();
        return fileName.toUpperCase().contains(keyword.toUpperCase());
    }
}
