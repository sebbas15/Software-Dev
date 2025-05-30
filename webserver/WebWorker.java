/**
* This file was first created by Dr. Jonathan Cook 
*           was updated by Dr. Huiping Cao in January 2025
* 
* Web worker: an object of this class executes in its own new thread
* to receive and respond to a single HTTP request. After the constructor
* the object executes on its "run" method, and leaves when it is done.
*
* One WebWorker object is only responsible for one client connection. 
* This code uses Java threads to parallelize the handling of clients:
* each WebWorker runs in its own thread. This means that you can essentially
* just think about what is happening on one client at a time, ignoring 
* the fact that the entirety of the webserver execution might be handling
* other clients, too. 
*
* This WebWorker class (i.e., an object of this class) is where all the
* client interaction is done. The "run()" method is the beginning -- think
* of it as the "main()" for a client interaction. It does three things in
* a row, invoking three methods in this class: it reads the incoming HTTP
* request; it writes out an HTTP header to begin its response, and then it
* writes out some HTML content for the response content. HTTP requests and
* responses are just lines of text (in a very particular format). 
*
**/

import java.net.Socket;
import java.lang.Runnable;
import java.io.*;
import java.util.Date;
import java.text.DateFormat;
import java.util.TimeZone;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class WebWorker implements Runnable
{

private Socket socket;
private String directory;

/**
* Constructor: must have a valid open socket
**/
public WebWorker(Socket s)
{
   socket = s;
}

/**
* Worker thread starting point. Each worker handles just one HTTP 
* request and then returns, which destroys the thread. This method
* assumes that whoever created the worker created it with a valid
* open socket object.
**/
public void run()
{
   System.err.println("Handling connection...");
   try {
      InputStream  is = socket.getInputStream();
      OutputStream os = socket.getOutputStream();

      // returns fileName if cient is requesting one
      String fileName = readHTTPRequest(is);

      // checks if user is requesting a file
      boolean headerInputted;
      if (fileName.equals("/")) headerInputted = false; else headerInputted = true;

      // creates directory string with file name
      directory = System.getProperty("user.dir");
      directory = directory + fileName;

      // checks if file exist in directory
      boolean fileCheck = new File(directory).exists();

      writeHTTPHeader(os,"text/html", fileCheck);
      writeContent(os, headerInputted, fileCheck);
      os.flush();
      socket.close();
   } catch (Exception e) {
      System.err.println("Output error: "+e);
   }
   System.err.println("Done handling connection.");
   return;
}

/**
* Read the HTTP request header.
**/
private String readHTTPRequest(InputStream is)
{
   String line;
   BufferedReader r = new BufferedReader(new InputStreamReader(is));
   while (true) {
      try {
         boolean getRequest = false;
         while (!r.ready()) Thread.sleep(1);
         line = r.readLine();
         getRequest = line.contains("GET");
         if (getRequest) {
            String inLine[] = line.split(" ");
            String name = inLine[1];
            return name;
         }
         System.err.println("Request line: ("+line+")");
         if (line.length()==0) break;
      } catch (Exception e) {
         System.err.println("Request error: "+e);
         break;
      }
   }
   return null;
}

/**
* Write the HTTP header lines to the client network connection.
* @param os is the OutputStream object to write to
* @param contentType is the string MIME content type (e.g. "text/html")
**/
private void writeHTTPHeader(OutputStream os, String contentType, boolean fileExist) throws Exception
{
   Date d = new Date();
   DateFormat df = DateFormat.getDateTimeInstance();
   df.setTimeZone(TimeZone.getTimeZone("GMT"));

   if (!fileExist) { os.write("HTTP/1.1 404 Not Found\n".getBytes()); }
   else {os.write("HTTP/1.1 200 OK\n".getBytes()); }
   os.write("Date: ".getBytes());
   os.write((df.format(d)).getBytes());
   os.write("\n".getBytes());
   os.write("Server: CS 371 very own server\n".getBytes());
   //os.write("Last-Modified: Wed, 08 Jan 2003 23:11:55 GMT\n".getBytes());
   //os.write("Content-Length: 438\n".getBytes()); 
   os.write("Connection: close\n".getBytes());
   os.write("Content-Type: ".getBytes());
   os.write(contentType.getBytes());
   os.write("\n\n".getBytes()); // HTTP header ends with 2 newlines
   return;
}

/**
* Write the data content to the client network connection. This MUST
* be done after the HTTP header has been written out.
* @param os is the OutputStream object to write to
**/
private void writeContent(OutputStream os, boolean headerInputted, boolean goodFile) throws Exception
{  // No header. Prompt
   if (!headerInputted) {
      os.write("<html><head></head><body>\n".getBytes());
      os.write("<h3>My web server works!</h3>\n".getBytes());
      os.write("</body></html>\n".getBytes());
   }

   else {
         // bad file entry
      if (!goodFile) {
         os.write("<html><head></head><body>\n".getBytes());
         os.write("<h3>404 Not Found</h3>\n".getBytes());
         os.write("</body></html>\n".getBytes());
      }
      else {
         // opens file
         try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            String line;
            while ((line = br.readLine()) != null) {
               Date date = new Date();
               line = line.replaceAll("\\{\\{cs371date\\}\\}", "" + date);
               line = line.replaceAll("\\{\\{cs371server\\}\\}", "My Server");
               os.write(line.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
      }
   }

}

} // end class
