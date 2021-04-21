package network;



import tools.Request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Optional;
import java.util.logging.Logger;

public class RequestReader {


    public RequestReader() {}

    public Optional<Request> readRequest(SocketChannel channel) throws IOException, ClassNotFoundException {//проверка на Null
        try {
            ByteBuffer readBuffer = ByteBuffer.allocate(102400);//выделяем буффер на 1Кб
            int num = channel.read(readBuffer);
            if (num > 0) {
                // Processing incoming data...
                ByteArrayInputStream inputStream = new ByteArrayInputStream(readBuffer.array());//массив байтов
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                Request command = (Request) objectInputStream.readObject();//считываем объект

                System.out.println("Новый запрос от клиента: " + command.getNameCommand());
                return Optional.of(command);
            } else if (num == -1) {
                // - 1 represents that the connection has been closed
                channel.close();
            }


        }catch (Exception e){

        }
        return Optional.empty();


    }
}
