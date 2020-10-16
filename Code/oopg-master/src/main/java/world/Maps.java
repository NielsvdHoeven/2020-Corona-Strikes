package world;

public class Maps {
    private WorldApp world;
    private int hiddenPlatform = -1;
    private int level = 0;

    private int[][] maps;

    public Maps(WorldApp world) {
        this.world = world;
    }


    public int getLevel(){
        return level;
    }

    public void setLevel(int level){
        this.level = level;
    }
}
