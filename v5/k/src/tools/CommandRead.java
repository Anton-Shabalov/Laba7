package tools;
/**
 * Класс для обработки команд введеных в консоль
 */
//execute_script /Users/antonsabalov/Desktop/ol
import collection.Ticket;



import net.ServerConnection;
import user.*;

import java.io.*;
import java.net.SocketException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class CommandRead {
public static boolean on=true;
private String[] comannd;
public static String nowComman=null;
private boolean flag =true;
private FileWorker fileWorker;
public BufferedReader reader;
public ServerConnection serverConnection;
public CommandRead(){
    fileWorker=new FileWorker();
}

    public CommandRead(BufferedReader reader, int port, String host) throws IOException {
        this.reader = reader;
        serverConnection = new ServerConnection(port, host);
    }

    /**
     * Считывает команды из командной строки
     */
public void reader() throws SocketException {
    if(!authorization.logins){
        System.out.println("Для начала работы необходимо авторизироваться");
        login(authorization.logIn());
    }else {
reader=null;


if(nowComman==null){
    System.out.println("Введите команду, для простотра всех комманд введите help");
    Scanner scanner =new Scanner(System.in);
    while (!scanner.hasNextLine()) {}
    CommandRead.nowComman=scanner.nextLine();

}else {

}
        comannd=CommandRead.nowComman.toLowerCase().trim().split(" ");




             switch (comannd[0]) {
                 case "help":
                     if (checkCommndLine(0, comannd)) {
                         break;
                     }

                     makeObject(comannd[0], "null");

                     break;
                 case "exit":
                     if (checkCommndLine(0, comannd)) {
                         break;
                     }
                     CommandRead.on = false;

                     break;
                 case "show":
                     if (checkCommndLine(0, comannd)) {
                         break;
                     }
                     makeObject(comannd[0], "null");

                     break;
                 case "remove_key":
                     if (checkCommndLine(1, comannd)) {
                         break;
                     }
                     try {
                         Integer.parseInt(comannd[1]);
                         int key = Integer.parseInt(comannd[1]);
                         makeObject(comannd[0], comannd[1]);
                     } catch (NumberFormatException e) {
                         System.out.println("ключ может принимать только целое числовое значение ");
                         CommandRead.nowComman=null;
                     }

                     break;
                 case "clear":
                     if (checkCommndLine(0, comannd)) {
                         break;
                     }
                     makeObject(comannd[0], "null");

                     break;
                 case "history":
                     if (checkCommndLine(0, comannd)) {
                         break;
                     }
                     makeObject(comannd[0], "null");

                     break;
                 case "sum_of_discount":
                     if (checkCommndLine(0, comannd)) {
                         break;
                     }
                     makeObject(comannd[0], "null");

                     break;
                 case "filter_contains_name":
                     if (checkCommndLine(1, comannd)) {
                         break;
                     }
                     makeObject(comannd[0], comannd[1]);

                     break;
//                 case "save":
//                     if (checkCommndLine(0, comannd)) {
//                         break;
//                     }
//                     makeObject(comannd[0], "null");
//
//                     break;
                 case "remove_lower_key":
                     if (checkCommndLine(1, comannd)) {
                         break;
                     }
                     try {
                         int key = Integer.parseInt(comannd[1]);
                         makeObject(comannd[0], comannd[1]);
                     } catch (NumberFormatException e) {
                         System.out.println("ключ может принимать только целое числовое значение ");
                         CommandRead.nowComman=null;
                     }

                     break;
                 case "print_field_descending_type":
                     if (checkCommndLine(0, comannd)) {
                         break;
                     }
                     makeObject(comannd[0], "null");

                     break;
                 case "remove_lower":
                     if (checkCommndLine(1, comannd)) {
                         break;
                     }
                     try {
                         int key = Integer.parseInt(comannd[1]);
                         makeObject(comannd[0], comannd[1]);
                     } catch (NumberFormatException e) {
                         System.out.println("ключ может принимать только целое числовое значение ");
                         CommandRead.nowComman=null;
                     }

                     break;
                 case "execute_script":
                     if (checkCommndLine(1, comannd)) {
                         break;
                     }
                     try {
                         startRead(comannd[1]);
                     } catch (FileNotFoundException e) {
                         e.printStackTrace();
                     }


                     break;
                 case "info":
                     if (checkCommndLine(0, comannd)) {
                         break;
                     }
                     makeObject(comannd[0], "null");

                     break;
                 case "insert":
                     if (checkCommndLine(0, comannd)) {
                         break;
                     }
                     try {
                         int key = 123;
                         makeObject(comannd[0], String.valueOf(key), MakeTiket.commandInsert(Integer.parseInt(String.valueOf(key))));
                     } catch (NumberFormatException e) {
                         System.out.println("ключ может принимать только целое числовое значение, строго больше нуля  ");
                         CommandRead.nowComman=null;
                     }


                     break;
                 case "update":
                     if (checkCommndLine(1, comannd)) {
                         break;
                     }
                     try {
                         int key = Integer.parseInt(comannd[1]);
                         String[] a = MakeTiket.commandUpdate(key);
                         makeObject(comannd[0], comannd[1], a);
                     } catch (NumberFormatException e) {
                         System.out.println("ключ может принимать только целое числовое значение, строго больше нуля  ");
                         CommandRead.nowComman=null;

                     }

                     break;


                 default:
                     System.out.println("Такой команды не существует. Воскользуйтесь help для получения всех возможных команд");
                     CommandRead.nowComman=null;
                     break;

             }


        }



}
    /**
     * Проверяет количество аргементов введеное пользователем
     * @param arguments количество аргументов
     * @param comanndLine команда введеная пользователем разбитая по пробелам
     * @return возвращает true если данная команда не имеет введеное количество аргементов
     */
private boolean checkCommndLine(int arguments,String[] comanndLine){
    if(arguments!=comanndLine.length-1){
        System.out.println("данная команда принемает "+ arguments +" аргументов");
        return true;
    }else {
        return false;
    }

}
private void makeObject(String nameComand, String arguments){
    Request readLine=new Request(nameComand,arguments);
    readLine.setLogin(authorization.login);
    readLine.setPassword(authorization.password);
    try {
        System.out.println(serverConnection.sendCommand(readLine));
    } catch (SocketException k){
        System.out.println("Видимо сервер отключился (");
        CommandRead.on=false;

    } catch (IOException e) {
        e.printStackTrace();
    }

}
private void makeObject(String nameComand, String arguments, Ticket ticket){
    Request readLine=new Request(nameComand,arguments,ticket);
    readLine.setLogin(authorization.login);
    readLine.setPassword(authorization.password);

    try {
        System.out.println( serverConnection.sendCommand(readLine));
    } catch (SocketException k){
        System.out.println("Видимо сервер отключился (");
        CommandRead.on=false;

    } catch (IOException e) {
        e.printStackTrace();
    }

}
private void makeObject(String nameComand,String  arguments, String[] a){
    Request readLine=new Request(nameComand,arguments,a);
    readLine.setLogin(authorization.login);
    readLine.setPassword(authorization.password);
    try {
        System.out.println(serverConnection.sendCommand(readLine));
    } catch (SocketException k){
        System.out.println("Видимо сервер отключился (");
        CommandRead.on=false;

    } catch (IOException e) {
        e.printStackTrace();
    }

}
private void makeObject(String nameCommand,ArrayList<String> command){
    Request readline=new Request(nameCommand,command);
    readline.setLogin(authorization.login);
    readline.setPassword(authorization.password);
    try {
        System.out.println(serverConnection.sendCommand(readline));
    } catch (SocketException k){
        System.out.println("Видимо сервер отключился (");
        CommandRead.on=false;

    } catch (IOException e) {
        e.printStackTrace();
    }

    }
    public void startRead(String filleName) throws FileNotFoundException {
         ArrayList<String> commands = new ArrayList<String>();
       File file=new File(filleName);
        if(FileWorker.newfileCheckAccessReader(file)){
//execute_script /Users/antonsabalov/Desktop/test1
         Scanner  scanner=new Scanner(file);
            while (scanner.hasNextLine()){
                commands.add(scanner.nextLine());
            }
            makeObject("execute_script",commands);
        }

    }
    public void login(Request request){
        try {
            serverConnection.sendCommand(request);
            if(ServerConnection.lastRequest.isAuthorized()){
                authorization.logins=true;
            }else {
                System.out.println("Вы ввели неверные данные");
            }



        } catch (SocketException k){
            System.out.println("Видимо сервер отключился (");
            CommandRead.on=false;

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
