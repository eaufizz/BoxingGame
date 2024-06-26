package BoxingGame;
import java.util.Random;
import java.util.Scanner;

class GameMaster{
	protected int savedata;
	protected int difficulty;
	protected int stage;
	protected int wins;
	protected String usrname;
	
	GameMaster(int savedata){
		this.savedata = savedata;
	}
	
	public void startNewGame(Scanner scanner) {
		selectDifficulty(scanner);
		settingName(scanner);
		this.stage = 1;
		Fighter player = new Fighter(this.usrname, 4-this.difficulty);
		waitingRoom(scanner, player);
	}
	
	public void selectDifficulty(Scanner scanner) {
		while(true) {
			System.out.println("SELECT DIFFICULTY");
			System.out.println("1.easy\n2.normal\n3.hard");
			this.difficulty = scanner.nextInt();
			if(this.difficulty < 1 || 3 < this.difficulty) {
				Error(1);
				continue;
			}
			break;
		}
	}
	
	public void settingName(Scanner scanner) {
		String temp;
		while(true) {
			System.out.println("Tell me your name within 10 characters");
			temp = scanner.next();
			if(temp == null) {
				Error(2);
			}else if(temp.length() >= 10) {
				Error(4);
			}else if(!(temp.matches(".*[a-zA-Z]*."))) {
				Error(3);
			}else {
				break;
			}
		}
		this.usrname = temp;
	}
	
	public void waitingRoom(Scanner scanner, Fighter player) {
		int temp;
		System.out.println("____________________________");
		System.out.println("        WAITING ROOM        ");
		while(true) {
			System.out.println("1.start battle\n2.doping\n3.exit game");
			temp = scanner.nextInt();
			if(temp == 1) {
				battleField(player, scanner);
			}else if(temp == 2) {
				player.doping(scanner);
			}else if(temp == 3) {
				break;
			}else {
				Error(1);
				continue;
			}
		}
	}
	
	public void battleField(Fighter player, Scanner scanner) {
		int judgeResult;
		Fighter enemy;
		if(this.stage == 1) {
			enemy = new Fighter("enemy", (wins+1)*difficulty);			
		}else {
			enemy = new MikeTyson("Mike Tyson", (wins+1)*difficulty);
		}
		System.out.println("____________________________");
		System.out.println("    STAGE"+stage+" START!");
        System.out.println("____________________________");
        for(int i = 1; true; i++) {
        	System.out.println("        ROUND"+i);
      		System.out.println(player.getName()+" HP:"+player.getHP()+" "+enemy.getName()+(this.wins+1)+" HP:"+enemy.getHP());
      		player.selectAction(scanner);
      		enemy.randomAction(player.getAction());
      		player.printAction(player.getAction());
      		enemy.printAction(enemy.getAction());
      		calcDamage(player, enemy);
      		player.processDamage();
      		enemy.processDamage();
      		System.out.println("____________________________");
      		judgeResult = judge(player, enemy);
      		if(judgeResult!= 0)
      			break;
        	}
        if(judgeResult== 3 && this.stage != 3) {
        	if(this.stage == 3) {
        		System.out.println("You are champion");        	        		
        	}else {
        		stageChange(scanner);        		
        	}
        	processWin(player);
        	waitingRoom(scanner, player);
        }   
	}
		
	public void stageChange(Scanner scanner) {
		int temp;
		while(true) {
			System.out.println("Next stage?");
			System.out.println("1.yes\n2.no");
			temp = scanner.nextInt();
			if(temp == 1) {
				this.stage += 1;
				this.wins = 0;
				break;
			}else if(temp == 2) {
				break;
			}
		}
	}
	
	public void processWin(Fighter player) {
		Random rand = new Random();
		this.wins += 1;
		player.levelUp(rand.nextInt(4-this.difficulty)+1);
		player.setMorphine(rand.nextInt(2)+1);
		player.setSteroid(rand.nextInt(5)+1);
		player.setCbd(rand.nextInt(5)+1);
	}
	
	public void calcDamage(Fighter player, Fighter enemy) {
    	switch (player.getAction()) {
    		case 1:
    			if(enemy.getAction() == 1) {
    				enemy.setDamage(player.getPower());
        			player.setDamage(enemy.getPower());
    			}else if(enemy.getAction() == 2) {
    				enemy.setDamage(0);
        			player.setDamage(enemy.getPower()*2);
    			}else if(enemy.getAction() == 3) {
    				enemy.setDamage(player.getPower());
        			player.setDamage(enemy.getPower()*2);
    			}else if(enemy.getAction() == 4) {
    				enemy.setDamage(player.getPower()*2);
        			player.setDamage(0);
    			}
    			break;
    		case 2:
    			if(enemy.getAction() == 1) {
    				enemy.setDamage(player.getPower()*2);
        			player.setDamage(0);
    			}else if(enemy.getAction() == 3) {
    				enemy.setDamage(0);
        			player.setDamage(enemy.getPower()*3);
    			}else{
    				enemy.setDamage(0);
        			player.setDamage(0);
    			}
    			break;
    		case 3:
    			if(enemy.getAction() == 1) {
    				enemy.setDamage(player.getPower()*2);
        			player.setDamage(enemy.getPower());
    			}else if(enemy.getAction() == 2) {
    				enemy.setDamage(player.getPower()*3);
        			player.setDamage(0);
    			}else if(enemy.getAction() == 3) {
    				enemy.setDamage(player.getPower()*2);
        			player.setDamage(enemy.getPower()*2);
    			}else if(enemy.getAction() == 4) {
    				enemy.setDamage(0);
        			player.setDamage(enemy.getPower()*4);
    			}
    			break;
    		case 4:
    			if(enemy.getAction() == 1) {
    				enemy.setDamage(0);
        			player.setDamage(enemy.getPower()*2);
    			}else if(enemy.getAction() == 3) {
    				enemy.setDamage(player.getPower()*4);
        			player.setDamage(0);
    			}else{
    				enemy.setDamage(0);
        			player.setDamage(0);
    			}
    			break;
    	}
    }
	
	public static int judge(Fighter player, Fighter enemy) {
    	if(!(player.getHP() <= 0) && !(enemy.getHP() <= 0)) {
    		return 0;
    	}else if(player.getHP() <= 0) {
        	if(enemy.getHP() <= 0) {
        		System.out.println("-EVEN-");
        		return 1;
        	}else {
        		System.out.println("YOU LOSE");
        		return 2;
        	}
        }else {
        	System.out.println("YOU WIN!");
        	return 3;
        }
    }
	
	public void Error(int errorNumber) {
		System.out.println("____________________________");
		switch(errorNumber) {
		case 1:
			System.out.println("Wrong input.Please try again.");
			break;
		case 2:
			System.out.println("No string.Please try again.");
			break;
		case 3:
			System.out.println("Please use alphabet");
			break;
		case 4:
			System.out.println("Its too long.");
			break;
		}
	}
}