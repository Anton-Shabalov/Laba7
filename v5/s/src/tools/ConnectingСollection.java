package tools;

import DB.DataBase;
import collection.Ticket;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectingСollection {
    public static ConcurrentHashMap<Integer, Ticket> collection;
    public static FileWorker fileWorker ;
    public static HandlerCommand handlerCommand;
    public static CommandRead commandRead;
    public static String filleName;
//    public static void connectFile(String filleName){
//        connect(filleName);
//
//    }
//    public static void connectFile(){
//        System.out.println("К сожалению вы не ввели название файла или ввели только пробел.Пожалуйста, введите корректное  название файла ");
//        connect("");
//
//    }
    public static boolean connect(){

//        Scanner scanner=new Scanner(System.in);
//        filleName=scanner.nextLine();
//        filleName="/Users/antonsabalov/Desktop/Все файлы с рс/5 лаба прога (не удачне варианты)/00-/1/data.json";

        collection=new ConcurrentHashMap<Integer, Ticket>();
        fileWorker = new FileWorker(filleName, collection);
        handlerCommand = new HandlerCommand();
        commandRead = new CommandRead();
        fileWorker.filleReader();
        if(DataBase.connectBase()){
            return true;
        }else {
            return false;
        }


    }

}
