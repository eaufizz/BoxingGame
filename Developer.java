package BoxingGame;

import java.util.Scanner;

class Developer extends GameMaster{
    private String password = "houwa";
    
    Developer(int savedata){
        super(savedata);
    }
    public int PasswordCheck(String password) {
        if(password.equals(this.password)) {
            System.out.println("correct");
            return 1;
        }else {
            System.out.println("incorrect");
            return 0;
        }
    }
    
    public void startNewGame(Scanner scanner) {
        selectDifficulty(scanner);
        settingName(scanner);
        this.stage = 1;
        Fighter player = new Fighter(this.usrname, 4-this.difficulty);
        System.out.println("you can custom your status");
        custom(scanner, player);
        waitingRoom(scanner, player);
    }
    
    public void custom(Scanner scanner, Fighter player) {
        int value;
        System.out.print("HP:");
        value = scanner.nextInt();
        player.setHP(value);
        System.out.print("power:");
        value = scanner.nextInt();
        player.setPower(value);
        System.out.print("defense:");
        value = scanner.nextInt();
        player.setDefense(value);
        player.printStatus();
    }
}