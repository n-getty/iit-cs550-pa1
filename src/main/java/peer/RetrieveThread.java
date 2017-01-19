package main.java.peer;

import java.io.File;
import java.util.concurrent.Callable;

public class RetrieveThread implements Callable<File>{

    private final String fileName;

    public RetrieveThread(String fileName){
        this.fileName = fileName;
        }

    public File call() {
        File requestedFile = new File(fileName);
        return requestedFile;
    }

}
