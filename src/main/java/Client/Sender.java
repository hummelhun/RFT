package Client;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;


public class Sender extends Thread {
	public PrintWriter mOut;
	public volatile static String massage;
	public Sender(PrintWriter aOut) {
		mOut = aOut;
	}
	public Sender() {
		
	}
	
	
	public void run() {
		//Doing some fucked up stuff
   try{
	 /*  BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while (!isInterrupted()) {
			String message = in.readLine();
			mOut.println(message);
			mOut.flush();
		}*/

	   /*LoggedPrintStream lpsOut = LoggedPrintStream.create(System.out);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		*/
		while (!isInterrupted()) {	
			while(massage == null){ 
			     try {
					//nothing just SLEEP you mofo
				} finally{}
			}
			/*System.setOut(lpsOut);
			System.out.flush();
			System.setOut(lpsOut.underlying);
			//System.out.println("----- Log for System.out: -----\n" + lpsOut.buf);
			String message = lpsOut.buf.toString();
			lpsOut.buf.delete( 0, lpsOut.buf.length() );*/
			if(massage.contains("asd"))
			{
				System.out.println("ITTVAGYOK");
		    mOut.println(massage);
			mOut.flush();
			massage="";
			}
			
			
		}
	
	}finally{}/*catch (IOException ioe) {

	}*/
	}
	public void setMassage(String s){
		this.massage=s;
	}
	
}