package BoxingGame;

import java.util.Random;
import java.util.Scanner;

class Fighter{
	protected 	String name;
	protected	int level;
	protected int HP;
	protected int power;
	protected int defense;
	
	protected int action;
	protected int damage;
	
	protected int morphine;
	protected int steroid;
	protected int cbd;
	
	protected int ODlevel;
	
	Fighter(String name, int level){
		this.level = level;
		this.name = name;
		this.HP = 80 + (4 * this.level);
		this.power = this.level * 2;
		this.defense = this.level; 
		this.morphine = 1;
		this.steroid = 1;
		this.cbd = 1;
	}
	
	public int getHP() {
		return this.HP;
	}
	
	public void printStatus() {
		System.out.println(this.name+"s status");
		System.out.println("LEVEL:"+this.level);
		System.out.println("HP:"+this.HP);
		System.out.println("power:"+this.power);
		System.out.println("defense:"+this.defense);
	}
	
	public void setHP(int value) {
		this.HP = value;
	}
	public void setPower(int value) {
		this.power = value;
	}
	public void setDefense(int value) {
		this.defense = value;
	}
	
	public int getPower() {
		return this.power;
	}
	
	public int getAction() {
		return this.action;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setMorphine(int amount) {
		this.morphine += amount;
		System.out.println("morphine +"+amount);
	}
	
	public void setSteroid(int amount) {
		this.steroid += amount;
		System.out.println("steroid  +"+amount);
	}

	public void setCbd(int amount) {
		this.cbd += amount;
		System.out.println("cbd      +"+amount);
	}
	
	public void levelUp(int amount) {
		this.level += amount;
		System.out.println("LEVEL    +"+amount);
		this.HP += amount*5;
		System.out.println("HP       +"+amount*5);
		this.power += amount*2;
		System.out.println("power    +"+amount*2);
		this.defense += amount;
		System.out.println("defense  +"+amount);
	}
	
	public void doping(Scanner scanner) {
		int temp;
		while(true) {
			System.out.println("____________________________");
			System.out.println("        SELECT ITEM");
			System.out.println("1.morphine(HP):"+this.morphine);
			System.out.println("2.steroid(power):"+this.steroid);
			System.out.println("3.cbd(defense):"+this.cbd);
			System.out.println("4.exit");
			temp = scanner.nextInt();
			if(temp < 1 || 4 < temp) {
				System.out.println("Wrong input.Please try again.");
				continue;
			}
			switch(temp) {
			case 1:
				if(this.morphine == 0) {
					System.out.println("no item");
					continue;
				}
				this.morphine -= 1;
				this.HP += 100;
				System.out.println(this.name+" HP +100");
				System.out.println("now "+this.name+" HP:"+this.HP);
				ODcheck();
				continue;
			case 2:
				if(this.steroid == 0) {
					System.out.println("no item");
					continue;
				}
				this.steroid -= 1;
				this.power += 1;
				System.out.println(this.name+" power +1");
				System.out.println("now "+this.name+" power:"+this.power);
				ODcheck();
				continue;
			case 3:
				if(this.cbd == 0) {
					System.out.println("no item");
					continue;
				}
				this.cbd -= 1;
				this.defense += 1;
				System.out.println(this.name+" defense +1");
				System.out.println("now "+this.name+" defense:"+this.defense);
				ODcheck();
				continue;
			case 4:
				break;
			}
			break;
		}
	}
	
	public void ODcheck() {
		Random rand = new Random();
		int check = rand.nextInt(50);
		if(this.ODlevel > check) {
			System.out.println("OVERDOSE!");
			this.HP-=150;
			if(this.HP <= 0) {
				this.HP = 1;
			}
			this.power-=3;
			if(this.power < 1) {
				this.power = 1;
			}
			this.defense-=3;
			if(this.defense < 1) {
				this.defense = 1;
			}
			System.out.println("HP:-150");
			System.out.println("power:-3");
			System.out.println("defense:-3");
			this.printStatus();
		}
		this.ODlevel += 1;
	}
	public int randomAction(int playerAction) {
		Random rand = new Random();
		this.action = rand.nextInt(4)+1;
		return this.action;
	}
	
	public int selectAction(Scanner scanner) {
		while(true) {
			System.out.println("     SELECT ACTION\n1.punch 2.punch counter\n3.kick  4. kick counter");
	    	this.action = scanner.nextInt();
	    	if(this.action < 1 || 4 < this.action)
	    		continue;
	    	return this.action;
		}
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public void printAction(int action) {
		switch (action){
		case 1:
			System.out.println(this.name+":"+"punch");
			break;
		case 2:
			System.out.println(this.name+":"+"punch counter");
			break;
		case 3:
			System.out.println(this.name+":"+"kick");
			break;
		case 4:
			System.out.println(this.name+":"+"kick counter");
			break;
		}
	}
	
	public void processDamage() {
		Random rand = new Random();
		
		int addition = rand.nextInt(10);
		if(this.damage != 0) {
			this.damage += addition;
			this.damage -= this.defense;
		}
		this.HP -= this.damage;
		System.out.println(this.name+":"+this.damage+"damage");
		if(this.HP < 0) {
			this.HP = 0;
		}
	}
}