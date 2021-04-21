package net;


import tools.CommandRead;
import tools.Request;
import user.authorization;
import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;

public class ServerConnection {
    public static Request lastRequest=null;


    public final SocketChannel socketChannel;

    public ServerConnection(int port, String host) throws IOException {
        socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(host, port));
    }

    public String sendCommand(Request command) throws IOException, SocketException {
        // Send requests
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(command);
        oos.flush();
        byte[] data = bos.toByteArray();
        ByteBuffer buffer = ByteBuffer.wrap(data);
        socketChannel.write(buffer);
        // Read response
        ByteBuffer readBuffer = ByteBuffer.allocate(102400);
        int num;

        if ((num = socketChannel.read(readBuffer)) > 0) {
            ((Buffer)readBuffer).flip();

            byte[] re = new byte[num];
            readBuffer.get(re);

            ByteArrayInputStream b = new ByteArrayInputStream(re);
                ObjectInputStream o = new ObjectInputStream(b);

            try {
                String result=" ";
                Request request=(Request) o.readObject();
                ServerConnection.lastRequest=request;
                if(!request.isAuthorized()){
                    authorization.logins=false;
                     result ="Что-то произошло с вашим логином, авторизируйтесь еще раз";
                }else {
                     result = request.getValue();
                     CommandRead.nowComman=null;
                }

                return result;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        while (true){
            System.out.println("Соединение было разовано, через 10 секунд попробуем отправить запрос еще раз");
            try {
                Thread.sleep( 10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Попытка создать новое соединение");
            String host = "localhost";
            int port = 1932;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

                CommandRead commandRead = new CommandRead(reader, port, host);
                while (true) {

                    commandRead.reader();
                }
            } catch (Exception e){
                System.out.println("Соединиться не получилось");
            }

        }
//        System.out.println("Сервер отключился, через 10 секунд попробуем подключиться снова");
//        while (true){
//            try {
//                Thread.sleep(1000);
//                ServerConnection serverConnection= new ServerConnection(1932,"localhost",ServerConnection.commandRead);
//                if (serverConnection.socketChannel.isConnected()){
//                    while (true){
//                        ServerConnection.commandRead.reader();
//                    }
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

        }







    }

