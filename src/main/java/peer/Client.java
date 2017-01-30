package main.java.peer;

import main.java.host.IndexInt;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.File;


/**
 * Client class creates client to index server 
 */
public class Client {
    // Remote object of indexing server
    IndexInt indexStub;
    // ID of this peer
    String id;
    // contains the file objects
    List<File> files = new ArrayList<File>();
    // contains the list of file names ( for registering )
    List<String> fileList = new ArrayList<String>();


    public Client(String host) {
	id = host;
	try {
	    Registry remoteregistry = LocateRegistry.getRegistry("10.0.0.1", 1099);

	    indexStub = (IndexInt) remoteregistry.lookup("IndexInt");
	    // register functions for peer:server
	    
	} catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }


    /**
     * Store all the file names of a given directory
     */
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

    /**
     * Register all files in list with the indexing server
     */
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
    
    /**
     * Register file with just one file name with the indexing server
     *   WatchDir calls this when a new file is downloaded / created 
     */
    public void register(String fileName){
        fileList.add(fileName);
        try {
            indexStub.register(id, fileName);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    /**
     * Deregister file with just one file name with the indexing server
     *   WatchDir calls this when a file is deleted 
     */
    public void deregister(String fileName){
        fileList.remove(fileName);
        try {
            indexStub.deregister(id, fileName);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }


    /**
     * Get the list of peers that have a given file
     */
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

    /**
     * Retrieve a file from a given peer
     */
    public byte[] retrieve(String fileName, String peerId){
        try {
	    System.out.println("INFO: connecting to "+peerId+" to retrieve file "+fileName);
	        // Get the remote object for the given peer
            Registry registry = LocateRegistry.getRegistry(peerId,1099);
            PeerInt peerStub = (PeerInt) registry.lookup("PeerInt");
	    return peerStub.retrieve(fileName);
	    
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
	byte[] x = "x".getBytes();
	return x;
    }
}
