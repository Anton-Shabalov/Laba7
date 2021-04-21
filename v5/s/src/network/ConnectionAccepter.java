package network;



import tools.ConnectingСollection;
import tools.Request;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ConnectionAccepter extends Thread {
    private SocketChannel socket;
    public ConnectionAccepter(SocketChannel socket){
        this.socket=socket;
    }
    @Override
    public void run() {
        RequestReader requestReader=new RequestReader();
        ResponseSender responseSender=new ResponseSender();
        while (true){

            try {
                Optional<Request> command = requestReader.readRequest(socket);
                if (command.isPresent()) {
                    ExecutorService pool = Executors.newCachedThreadPool();
                    StringBuilder s = null;
                    pool.submit(()->(ConnectingСollection.commandRead.reader(command.get(),socket)));

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
}}
