import java.rmi.Naming;
import java.util.Scanner;


public class StartFileClient {

    public static void main(String[] args) {
	try{
	    FileClient c=new FileClient("imed");
	    FileServerInt server=(FileServerInt)Naming.lookup("rmi://10.0.0.1/home/Research/cs550/iit-cs550-pa1");
	    server.login(c);
	    System.out.println("Listening.....");
	    Scanner s=new Scanner(System.in);
	    while(true){
		String line=s.nextLine();
	    }
	}catch(Exception e){
	    e.printStackTrace();
	}
    }

}
