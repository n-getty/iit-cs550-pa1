package main.java.host;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

import static java.util.Arrays.asList;

public class LookupThread implements Callable<List>{

    private final String fileName;
    private final int peerId;
    private final HashMap<Integer, List> peerMap;

    public LookupThread(String fileName, int peerId, HashMap peerMap){
        this.fileName = fileName;
        this.peerId = peerId;
        this.peerMap = peerMap;
    }

    public List call() {
            return peerMap.get(fileName);
    }

}
