//import collection.Ticket;
//import com.google.gson.Gson;
//import net.Connect;
//import net.ReadRequest;
//import tools.CommandRead;
//import tools.ConnectingСollection;
//import tools.FileWorker;
//import tools.HandlerCommand;
//
//import java.util.HashMap;
//import java.util.Scanner;
//
///**
// * {@author Шабалов Антон Павлович p3115
// */
//public class Main {
//    private static final Gson Gson =new Gson();
//    private static String filleName;
//    private static Connect connect=new Connect();
//    private static ReadRequest readRequest;
//
//
//    /**
//     * {@param args Принимает файл json
//     */
//    public static void main(String[] args) {
//        if (args.length != 0) {
//            ConnectingСollection.connectFile(args[0]);
//        }else {
//            ConnectingСollection.connectFile();
//        }
//        System.out.println("Колекция из файла прочитана");
//        Connect.makeConnect();
//        ReadRequest.read();
//
//        }
//
//
//
//
////
////
////
//
//
////
////
////
////        Integer in=9;
////        Long w=new Long(12);
////        Location asa =new Location(in,8.5,w);
////        Address a=new Address("123",asa);
////        Coordinates coordinates=new Coordinates(12,21);
////        Date as=new Date();
////
////        Venue venue=new Venue(1,"123",in, VenueType.MALL,a);
////        Ticket ticket=new Ticket(1,"123",coordinates,as,1000f,50,TicketType.CHEAP,venue);
////         final Gson json = new Gson();
////        String popa=Gson.toJson(ticket);
////        Ticket ticket1=json.fromJson(popa,Ticket.class);
////        collection.put(1,ticket1);
////        fileWorker.filleWrite();
//    }
//
//
//
//
