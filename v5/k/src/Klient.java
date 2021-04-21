import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.util.NoSuchElementException;

import java.util.Scanner;


import tools.CommandRead;
import tools.MakeTiket;

public class Klient {



    private static boolean on=true;
    private static boolean flagConnect = true;
    private static String host = "localhost";
    private static int port = 1932;

    public static void main(String[] args) {


        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            CommandRead commandRead = new CommandRead(reader, port, host);
            while (CommandRead.on) {
                    commandRead.reader();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Конец ввода");
        } catch (NumberFormatException e) {
            System.out.println("Неправильно задан порт");
        }  catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (ConnectException e) {
            System.out.println("Сервер недоступен");
        } catch (IOException e) {
            System.out.println("Потеряно соединение с сервером");
        }
        System.out.println("Приложение закрыто");

    }

}