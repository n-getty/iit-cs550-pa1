package main.java.peer;

import main.java.host.*;//IndexInt.*;

import java.io.File;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Client {
    // TODO
    IndexInt indexStub;
    // TODO
    String id;

    // contains the file objects
    List<File> files = new ArrayList<File>();
    // contains the list of file names ( for registering )
    List<String> fileList = new ArrayList<String>();

    // TODO
    PeerInt thisStub;

    public Client(String host) {
        registerAll();
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            indexStub = (IndexInt) registry.lookup("IndexInt");
            thisStub = (PeerInt) LocateRegistry.getRegistry(id).lookup("PeerInt");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public void register(String fileName){
        fileList.add(fileName);
        try {
            indexStub.register(id, fileName);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public void getFilesToRegister(String folder) {
        // currently we only support files inside of folders (i.e. no folders inside folders)

        // read in files from given folder
        File fold = new File("./"+folder);
        File[] listOfFiles = fold.listFiles();

        // convert list of files into ArrayList of strings
        // the strings are the names

        files = Arrays.asList(listOfFiles);
        for(int i=0;i<listOfFiles.length;i++) {
            fileList.add(listOfFiles[i].getName());
        }
    }

    public void registerAll(){
        try {
            for(String fileName : fileList)
                indexStub.register(id, fileName);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public List<String> lookup(String fileName){
        List targetIds = null;
        try {
            targetIds = indexStub.lookup(fileName);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
        return targetIds;
    }

    public void retrieve(String fileName, String peerId){
        try {
            Registry registry = LocateRegistry.getRegistry(peerId);
            PeerInt peerStub = (PeerInt) registry.lookup("PeerInt");
            peerStub.retrieve(fileName, thisStub);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
