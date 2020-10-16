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

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setInfected(boolean infected) {
        this.infected = infected;
    }

    @Override
    public void update() {
        gameObjectCollisionOccurred(world.getGameObjectItems());

        if (infected) {
            setCurrentFrameIndex(1);
        }
    }

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
}
