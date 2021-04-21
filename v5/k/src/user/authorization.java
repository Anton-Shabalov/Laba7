package user;

import tools.Request;

import java.sql.SQLOutput;
import java.util.Scanner;

public class authorization {
    public static String login;
    public static String password;
    public static boolean logins=false;

    public static Request logIn(){
        String nameComand="login";
        Scanner scanner=new Scanner(System.in);
        System.out.println("Введите логин");
        login=scanner.nextLine();
        while (!checkString(login)){
            password=scanner.nextLine();
        }
        System.out.println("Введите пароль");
        password=scanner.nextLine();
        while (!checkString(password)){
            password=scanner.nextLine();
        }
        return new Request(nameComand,login,password);
    }

    private static boolean checkString(String str){
        if(str.trim().length()==0){
            System.out.println("Вы ввели пустую строку");
            return false;
        }else {
            return true;
        }
    }

}
