package main.java.peer;
import main.java.host.ServerImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class PeerImpl implements PeerInt {

    public PeerImpl() {
		try {
			PeerInt stub = (PeerInt) UnicastRemoteObject.exportObject(this, 0);
			// Bind the remote object's stub in the registry
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("PeerInt", stub);
			System.err.println("PeerImpl ready");
		} catch (Exception e) {
			System.err.println("PeerImpl exception: " + e.toString());
			e.printStackTrace();
		}
	}

    public void retrieve(String fileName, PeerInt client) {
		try {
			File requestedFile = new File(fileName);
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
	}

	public void sendData(String fileName, byte[] data, int len) {
		try{
			File file = new File(fileName);
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
