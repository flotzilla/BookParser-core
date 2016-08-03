package org.home.scanner;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ExcludedPathList {
    private List<Path> pathList;
    private List<Path> fileList;
    private List<Path> directoryList;


    public ExcludedPathList(List<Path> pathList) {
        if(pathList == null){
            this.pathList = new ArrayList<>();
        }else{
            this.pathList = pathList;
        }
        init();
    }
    private void init(){
        fileList = new ArrayList<>();
        directoryList = new ArrayList<>();
        for (Path item: pathList){
            if(Files.isDirectory(item, LinkOption.NOFOLLOW_LINKS)){
                directoryList.add(item);
            }else if (Files.isRegularFile(item, LinkOption.NOFOLLOW_LINKS)){
                fileList.add(item);
            }
        }
    }

    public List<Path> getFileList() {
        return fileList;
    }

    public List<Path> getDirectoryList() {
        return directoryList;
    }
}
