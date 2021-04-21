package tools;
/**
 * Класс для обработки команд введеных в консоль
 */

import network.ResponseSender;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommandRead {
private String[] comannd;
private boolean flag =true;
private SocketChannel client;
public CommandRead( ){

}
HashMap<SocketChannel, ArrayList<String>> history = new HashMap<>();
private HashMap<SocketChannel, ArrayDeque<String>> userHistory = new HashMap<>();


public void removeFirstCommand(SocketChannel client) {
        this.history.get(client).remove(0);
    }

public void recordHistory(SocketChannel client,String stringCommand){
    if (this.history.get(client) != null) {
        if (this.history.get(client).size() < 15) this.addCommandToHistory(stringCommand,client);
        else {
            this.removeFirstCommand(client);
            this.addCommandToHistory(stringCommand,client);
        }
    } else this.addCommandToHistory(stringCommand,client);
}
public void addCommandToHistory(String command,SocketChannel client) {
        ArrayList list = this.history.get(client);
        if (list == null) list = new ArrayList();
        list.add(command);
        this.history.put(client, list);
    }


    /**
     * Считывает команды из командной строки
     * @return
     */
public Request reader(Request request, SocketChannel client){
    HandlerCommand handlerCommand=new HandlerCommand();
    handlerCommand.login(request);




this.client=client;

             if (request.getArguments()!=null){

                 comannd=new String[2];
                 comannd[0]=request.getNameCommand();
                 comannd[1]=request.getArguments();
             }else {
                 comannd=new String[1];
                 comannd[0]=request.getNameCommand();
             }
             switch (comannd[0]){
                 case "login":
                     break;
                  case "help":
                      if(checkCommndLine(0,comannd)){
                          break;
                      }
                      handlerCommand.commandsHelp();
                      recordHistory(client,comannd[0]);
                      break;
                 case "exit":
                     if(checkCommndLine(0,comannd)){
                         break;
                     }
                     flag=false;
                   //  handlerCommand.historyRecord(comannd[0]);
                     break;
                 case "show":
                     if(checkCommndLine(0,comannd)){
                         break;
                     }
                     handlerCommand.commandsShow();
                     recordHistory(client,comannd[0]);
                     break;
                 case "remove_key":
                     if(checkCommndLine(1,comannd)){
                         break;
                     }
                     try {
                         Integer.parseInt(comannd[1]);
                         int key =Integer.parseInt(comannd[1]);
                          handlerCommand.commandsRemoveKey(key);
                     } catch(NumberFormatException e){
                         System.out.println("ключ может принимать только целое числовое значение ");
                     }
                    recordHistory(client,comannd[0]);
                     break;
                 case "clear":
                     if(checkCommndLine(0,comannd)){
                         break;
                     }
                     handlerCommand.commandsClear();
                    recordHistory(client,comannd[0]);
                     break;
                 case "history":
                     if(checkCommndLine(0,comannd)){
                         break;
                     }
                     handlerCommand.send(history.get(client).toString());
                     recordHistory(client,comannd[0]);
                     break;
                 case "sum_of_discount":
                     if(checkCommndLine(0,comannd)){
                         break;
                     }
                     handlerCommand.commandsSumOfDiscount();
                    recordHistory(client,comannd[0]);
                     break;
                 case "filter_contains_name":
                     if(checkCommndLine(1,comannd)){
                         break;
                     }
                     handlerCommand.commandsFilterContainsName(comannd[1]);
                     recordHistory(client,comannd[0]);
                     break;
                 case "save":
                     if(checkCommndLine(0,comannd)){
                         break;
                     }
                     handlerCommand.commandsSave();
                     recordHistory(client,comannd[0]);
                     break;
                 case "remove_lower_key":
                     if(checkCommndLine(1,comannd)){
                         break;
                     }
                     try {
                         int key =Integer.parseInt(comannd[1]);
                         handlerCommand.commandsRemoveLowerKey(key);
                     } catch(NumberFormatException e){
                         System.out.println("ключ может принимать только целое числовое значение ");
                     }
                     recordHistory(client,comannd[0]);
                     break;
                 case "print_field_descending_type":
                     if(checkCommndLine(0,comannd)){
                         break;
                     }
                     handlerCommand.commandsPrintFieldDescendingType();
                    recordHistory(client,comannd[0]);
                     break;
                 case "remove_lower":
                     if(checkCommndLine(1,comannd)){
                         break;
                     }
                     try {
                         int key =Integer.parseInt(comannd[1]);
                         handlerCommand.commandsRemoveLower(key);
                     } catch(NumberFormatException e){
                         System.out.println("ключ может принимать только целое числовое значение ");
                     }
                     recordHistory(client,comannd[0]);
                     break;
                 case "execute_script":
                     if(checkCommndLine(1,comannd)){
                         break;
                     }

                     handlerCommand.newCommandsExecuteScript(request.getArrayList());

                    recordHistory(client,comannd[0]);
                     break;
                 case "info":
                     if(checkCommndLine(0,comannd)){
                         break;
                     }
                     handlerCommand.commandsInfo();
                     recordHistory(client,comannd[0]);
                     break;
                 case "insert":
                     if(checkCommndLine(0,comannd)){
                         break;
                     }
                     try {
                         int key =Integer.parseInt(comannd[1]);

                         if (handlerCommand.commandInsertCheckId(request.getTicket())){
                         }else {
                         }
                     } catch(NumberFormatException e){
                         System.out.println("ключ может принимать только целое числовое значение, строго больше нуля  ");
                     }
                    recordHistory(client,comannd[0]);

                     break;
                 case "update":

                     if(checkCommndLine(0,comannd)){
                         break;
                     }

                     try {
                         int key =Integer.parseInt(comannd[1]);
                         ArrayList<String> strings=new ArrayList<String>();
                         for(String a:request.getA()){
                             strings.add(a);
                         }
                         handlerCommand.newcommandsUpdateExecute(key,strings);
                     } catch(NumberFormatException e){
                         System.out.println("ключ может принимать только целое числовое значение, строго больше нуля  ");
                     }
                     recordHistory(client,comannd[0]);
                     break;



                 default:
                     System.out.println("Такой команды не существует. Воскользуйтесь help для получения всех возможных команд");
                     break;



             }
    ResponseSender responseSender=new ResponseSender();
ExecutorService pools = Executors.newCachedThreadPool();
pools.submit(() -> {
    try {
        responseSender.sendAnswer(handlerCommand.getAns(), client);
    } catch (IOException e) {
        e.printStackTrace();
    }
});
    return handlerCommand.getAns();

}
    /**
     * Проверяет количество аргементов введеное пользователем
     * @param arguments количество аргументов
     * @param comanndLine команда введеная пользователем разбитая по пробелам
     * @return возвращает true если данная команда не имеет введеное количество аргементов
     */
private boolean checkCommndLine(int arguments,String[] comanndLine){
    if(arguments!=comanndLine.length-1){
return false;
    }
    return false;

}
public void sendHistory(){
    try {
        ConnectingСollection.handlerCommand.send(history.get(client).toString());
    }catch (Exception e){}


}
private void giveAnswer(String answer){
    Request request =new Request(answer);

}



}
