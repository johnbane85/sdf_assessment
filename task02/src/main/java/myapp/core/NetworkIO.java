package myapp.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NetworkIO {

    private InputStream is;
    private OutputStream os;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public NetworkIO(Socket sock) throws IOException {
        
        
        os = sock.getOutputStream();
        is = sock.getInputStream();
        oos = new ObjectOutputStream(os);
        ois = new ObjectInputStream(is);
        
    }

    public String read() throws IOException {
      
        return ois.readUTF();

    }

    public boolean booleanRead() throws IOException {
      return ois.readBoolean();
    }


    public void write(String msg) throws IOException {
        
        oos.writeUTF(msg);
        oos.flush();
    }

    public void writeAverage(float average) throws IOException {
      oos.writeFloat(average);
      oos.flush();
    }

    public void close() {
        try {
            ois.close();
            is.close();
            oos.close();
            os.close();
        } catch (IOException ex) { 
            // don't care if we get exception because we are closing the connection
        }
    }
    
}
