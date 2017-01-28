package main.java.peer;

import main.java.host.IndexInt;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
//import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.File;

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
	id = host;
	try {
	    //PeerInt stub = (PeerInt) UnicastRemoteObject.exportObject(this, 0);
	    Registry thisregistry = LocateRegistry.getRegistry();
	    Registry remoteregistry = LocateRegistry.getRegistry("10.0.0.1", 1099);

	    indexStub = (IndexInt) remoteregistry.lookup("IndexInt");
	    // register functions for peer:server

	    //thisregistry.rebind("PeerInt", stub);
	    
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
        int i;
	for(i=0;i<listOfFiles.length;i++) {
            fileList.add(listOfFiles[i].getName());
        }
    }

    public void registerAll(){
	
	try {
            for(String fileName : fileList){
		System.out.println("INFO: registering file: " + fileName);
		indexStub.register(id, fileName);
	    }
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
	    System.out.println("INFO: connecting to "+peerId+" to retrieve file "+fileName);
            Registry registry = LocateRegistry.getRegistry(peerId,1099);
            PeerInt peerStub = (PeerInt) registry.lookup("PeerInt");
	    peerStub.retrieve(fileName, thisStub);
	    
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
