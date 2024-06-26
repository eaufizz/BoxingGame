package BoxingGame;

import java.util.Scanner;

public class BoxingGame {
    
    public static void main(String[] args) {
        int select;
        String password;
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("____________________________");
            System.out.println("SELECT MENU");
            System.out.println("1.savedata1\n2.savedata2\n3.developer mode\n4.exit game");
            select = scanner.nextInt();
            if(select == 1) {
                GameMaster savedata1 = new GameMaster(1);
                savedata1.startNewGame(scanner);
                
            }else if(select == 2) {
                GameMaster savedata2 = new GameMaster(2);
                savedata2.startNewGame(scanner);
            }else if(select == 3) {
                Developer developer = new Developer(3);
                System.out.print("password:");
                password = scanner.next();
                if(developer.PasswordCheck(password) == 1) {
                    developer.startNewGame(scanner);                    
                }
            }else if(select == 4) {
                break;
            }else{
                System.out.println("Wrong input.Please try again.");
            }
        }
        System.out.println("____________________________");
        System.out.println("EXIT");
        System.out.println("____________________________");
        scanner.close();
    }
}