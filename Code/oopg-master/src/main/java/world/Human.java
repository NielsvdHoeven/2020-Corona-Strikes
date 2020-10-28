package world;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;
import java.util.Random;

public class Human extends AnimatedSpriteObject implements ICollidableWithGameObjects {

    private boolean infected;
    private Collision collision = new Collision();
    private WorldApp world;

    public Human(WorldApp world) {
        super(new Sprite(WorldApp.MEDIA_URL.concat("humans.png")), 4);
        this.world = world;
    }

    @Override
    public void update() {
        gameObjectCollisionOccurred(world.getGameObjectItems());

        if (infected) {
            setCurrentFrameIndex(1);
        } else {
            setCurrentFrameIndex(0);
        }

        initializeHumans();
    }

    /**
     * plaatst de humans op de correcte plek in ieder level
     * en reset de humans aan het begin van ieder level
     */
    public void initializeHumans() {
        if (world.maps.getReset()) {
            for (int i = 0; i < world.humans.length; i++) {
                world.humans[i].setInfected(false);
            }
        }

        switch (world.maps.getLevel()) {
            case 1:
                world.humans[0].setPosition(500, 300);
                world.humans[1].setPosition(950, 150);
                break;
            case 2:
                world.humans[0].setPosition(800, 50);
                world.humans[1].setPosition(50, 50);
                break;
            case 3:
                world.humans[0].setPosition(600, 100);
                world.humans[1].setPosition(1000, 250);
                break;
            case 4:
                world.humans[0].setPosition(0, 50);
                world.humans[1].setPosition(100, 50);
                break;
            case 5:
                world.humans[0].setPosition(-200, -200);
                world.humans[1].setPosition(-200, -200);
                world.humans[0].setInfected(true);
                world.humans[1].setInfected(true);
                break;
        }
    }

    /**
     * Checkt de collision met de Player
     */
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g : collidedGameObjects) {
            if (g instanceof Player) {
                if (collision.checkCollision(g, this)) {
                    infected = true;
                }
            }
        }
    }

    /**
     * Zet de position van de Human
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * returnt de boolean infected
     */
    public boolean getInfected() {
        return infected;
    }

    /**
     * zet de boolean infected
     */
    public void setInfected(boolean infected) {
        this.infected = infected;
    }
}
