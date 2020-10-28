package world;

public class Key {
    private char key;
    private boolean pressed;

    public Key(char key, boolean pressed){
        this.key = key;
        this.pressed = pressed;
    }

    /**
     * zet de waarde van de boolean pressed
     */
    public void setPressed(boolean pressed){
        this.pressed = pressed;
    }

    /**
     * returnt de waarde van de boolean pressed
     */
    public boolean getPressed(){
        return pressed;
    }

    /**
     * returnt de char/toets
     */
    public char getKey(){
        return key;
    }
}