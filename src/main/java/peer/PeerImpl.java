package main.java.peer;
import main.java.host.ServerImpl;

import java.nio.file.Files;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.io.*;
import java.nio.file.Paths;


public class PeerImpl implements PeerInt {

    String folder;
    
    /**
     * Constructor for exporting each peer to the registry
     */
    public PeerImpl(String fold) {
        folder = fold;
        try {
            PeerInt stub = (PeerInt) UnicastRemoteObject.exportObject(this, 0);
            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("PeerInt", stub);
            System.err.println("PeerImpl ready");
        } catch (Exception e) {
            System.err.println("PeerImpl exception: " + e.toString());
            e.printStackTrace();
        }
    }

    /**
     * Pass chunks of the file to the clients remote peer object until the file is written
     */
    public byte[] retrieve(String fileName, PeerInt client)
	throws IOException, RemoteException {

	try {
	    byte[] requestedFile = Files.readAllBytes(Paths.get(folder+"/"+fileName));
	    return requestedFile;
	}
	catch(Exception e) {
	    e.printStackTrace();
	}
	byte[] x = "x".getBytes();
	return x;
	/*
	try {
            File requestedFile = new File(folder+"/"+fileName);
            FileInputStream in = new FileInputStream(requestedFile);
            byte [] mydata = new byte[1024*1024];
            int len = in.read(mydata);
            while(len>0){
                client.sendData(fileName, mydata, len);
                len = in.read(mydata);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
	*/
    }

    /**
     * Write the file to disk using the clients peer object
     */
    public void sendData(String fileName, byte[] data, int len) {
        try{
            File file = new File(folder+"/"+fileName);
            file.createNewFile();
            FileOutputStream out=new FileOutputStream(file,true);
            out.write(data,0,len);
            out.flush();
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
