package BoxingGame;

import java.util.Random;

class MikeTyson extends Fighter{ MikeTyson(String name, int level){
        super(name, level);
    }
    
    public int randomAction(int playerAction) {
        Random rand = new Random();
        int reaction = rand.nextInt(100);
        reaction += this.power*4;
        if(reaction >= 100) {
            if(playerAction == 1) {
                this.action = 2;
                return 2;
            }else if(playerAction == 3){
                this.action = 4;
                return this.action;
            }
        }
        this.action = rand.nextInt(4)+1;
        return this.action;
    }
}