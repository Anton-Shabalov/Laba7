

import DB.DataBase;
import DB.WorkDB;
import network.*;

import tools.ConnectingСollection;
import tools.ServerCommand;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.sql.SQLOutput;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Main класс программы
 */
public class Server {
    private static int port = 1932;
    private static String hostname = "localhost";

    private final Selector selector;//доступ к кналом
    private final ServerSocketChannel server;//главый канал, генерит каналы для клиентов
//    private final ConnectionAccepter connectionAccepter;
    private final RequestReader requestReader;
    private final ResponseSender responseSender;
    private SocketChannel client;


    public Server(int port, String hostname) throws IOException {
        selector = Selector.open();
        server = ServerSocketChannel.open();
        server.socket().bind(new InetSocketAddress(hostname,port));//указываем порт, на котором будет приниматься подключение
//        connectionAccepter = new ConnectionAccepter(selector);
        requestReader = new RequestReader();
        responseSender = new ResponseSender();
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        int port=1932;
        if (!ConnectingСollection.connect()){
            System.out.println("Закрываем программу");
            System.exit(0);
        }else {

            Server serverObject = new Server(port, hostname);
            Helper helper = new Helper();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Завершение работы сервера...");
                ConnectingСollection.handlerCommand.commandsSave();
                System.out.println("Коллекция сохранена");
            }));

            SelectableChannel Channel = helper.getStdinChannel();
            Channel.register(serverObject.selector, SelectionKey.OP_READ);
            serverObject.startServer();
        }
    }
private static boolean flag=true;
    public void startServer() throws IOException, ClassNotFoundException {
        System.out.println("Сервер начал свою работу");
        DataBase dataBase=new DataBase();
        dataBase.connectBase();
        WorkDB.readDB();
       new ServerCommand().start();
        ExecutorService pool = Executors.newFixedThreadPool(200);
        while (true){
            SocketChannel socketChannel=server.accept();
            ConnectionAccepter connectionAccepter=new ConnectionAccepter(socketChannel);
            pool.submit(() -> connectionAccepter.start()); // Handle in thread pool

        }


//        while (flag) {
//            int readyChannels = selector.select();
//            if (readyChannels == 0) {
//                continue;
//            }
//            Set<SelectionKey> readyKeys = selector.selectedKeys();
//            Iterator<SelectionKey> iterator = readyKeys.iterator();
//            while (iterator.hasNext()) {
//                SelectionKey key = iterator.next();
//                iterator.remove();
//
//                if (key.isAcceptable()) {
//                    SocketChannel socketChannel = server.accept();
//
//                    connectionAccepter.acceptConnection(socketChannel);
//                    SERVER_LOGGER.info("Новое подключение");
//
//                } else if (key.isReadable()) {
//                    if (key.channel() instanceof SocketChannel) {
//
//                        SocketChannel socketChannel = (SocketChannel) key.channel();
//                        this.client = socketChannel;
//                        System.out.println(client);
//                        try {
//                            Optional<Request> command = requestReader.readRequest(socketChannel);
//                            if (command.isPresent()) {
//                              String s= ConnectingСollection.commandRead.reader(command.get(),client);
//                                responseSender.sendAnswer(s, socketChannel);
//                            }
//                        } catch (IOException e) {
//                            socketChannel.close();
//                        }
//                    } else {
//                        ReadableByteChannel channel = (ReadableByteChannel) key.channel();
//                        ByteBuffer readBuffer = ByteBuffer.allocate(102400);
//                        int num = channel.read(readBuffer);
//                        String command;
//                        if (num > 0) {
//                            command = new String(readBuffer.array());
//                            switch (command.trim()) {
//                                case "save":
//                                    ConnectingСollection.handlerCommand.commandsSave();
//                                    break;
//                                case "stop":
//                                    server.close();
//                                    System.exit(0);
//                                    break;
//                                default:
//                                    System.out.println("Вы ввели неподдерживаюмую команду");
//                            }
//                        } else if (num == -1) {
//                            SERVER_LOGGER.info("Связь с клиентом потеряна");
//                            channel.close();
//                        }
//                    }
//
//                }
//            }
//        }
    }

}
