package world;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

import java.util.List;

public class Sanitizer extends SpriteObject {

    private WorldApp world;

    public Sanitizer(WorldApp world) {
        super(new Sprite(WorldApp.MEDIA_URL.concat("san.png")));
        this.world = world;
    }

    @Override
    public void update() {
        initializeSanitizer();
    }

    /**
     * zet voor ieder level de sanitizers op de correcte plek neer
     */
    public void initializeSanitizer() {
        switch (world.maps.getLevel()) {
            case 1:
                world.sanitizers[0].setPosition(900, 690);
                world.sanitizers[1].setPosition(900, 490);
                world.sanitizers[2].setPosition(-100, -100);
                break;
            case 2:
                world.sanitizers[0].setPosition(900, 690);
                world.sanitizers[1].setPosition(300, 290);
                world.sanitizers[2].setPosition(200, 690);
                break;
            case 3:
                world.sanitizers[0].setPosition(900, 690);
                world.sanitizers[1].setPosition(100, 340);
                world.sanitizers[2].setPosition(-100, -100);
                break;
            case 4:
                world.sanitizers[0].setPosition(-100, -100);
                world.sanitizers[1].setPosition(-100, -100);
                world.sanitizers[2].setPosition(-100, -100);
                break;
            case 5:
                world.sanitizers[0].setPosition(-100, -100);
                world.sanitizers[1].setPosition(-100, -100);
                world.sanitizers[2].setPosition(-100, -100);
                break;
        }
    }

    /**
     * zet de positie van de sanitizer
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
