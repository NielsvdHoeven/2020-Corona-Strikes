package world;

public class Toets {
    private char toets;
    private boolean pressed;

    public Toets(char toets, boolean pressed){
        this.toets = toets;
        this.pressed = pressed;
    }

    public void setPressed(boolean pressed){
        this.pressed = pressed;
    }

    public boolean getPressed(){
        return pressed;
    }

    public char getToets(){
        return toets;
    }
}