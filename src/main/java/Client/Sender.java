package Client;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;


public class Sender extends Thread {
	public PrintWriter mOut;
	volatile String massage;
	public Sender(PrintWriter aOut) {
		mOut = aOut;
	}
	public Sender() {
		
	}
	
	
	public void run() {
		//Doing some fucked up stuff
   try{
		while (!isInterrupted()) {	
			while(massage == null){ 
			     try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}

			if(massage.contains("asd"))
			{
				System.out.println("ITTVAGYOK");
		    mOut.println(massage);
			mOut.flush();
			massage=null;
			}
			
			
		}
	
	}finally{/*POTATO*/}/*catch (IOException ioe) {

	}*/
	}
	public void setMassage(String s){
		System.out.println("modify string");
		this.massage=s;
	}
	
}