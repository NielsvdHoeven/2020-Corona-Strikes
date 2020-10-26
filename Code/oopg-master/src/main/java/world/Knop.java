package world;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public class Knop extends AnimatedSpriteObject implements ICollidableWithGameObjects {
    private WorldApp world;
    private Collision collision = new Collision();

    public Knop(WorldApp world, int totalFrames) {

        super(new Sprite(WorldApp.MEDIA_URL.concat("platformPack_tile053.png")), totalFrames);
        this.world = world;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {
        gameObjectCollisionOccurred(world.getGameObjectItems());
        initializeKnop();
    }

    public void initializeKnop() {
        if (world.maps.getReset()) {
            world.maps.setHiddenPlatform(-1);
            setCurrentFrameIndex(0);
        }
        if (world.maps.getLevel() == 1) {
            setPosition(300, 386);
        } else if (world.maps.getLevel() == 2) {
            setPosition(600, 686);
        }
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g : collidedGameObjects) {
            if (g instanceof Player) {
                if (collision.checkCollision(g, this)) {
                    setCurrentFrameIndex(1);
                    world.maps.setHiddenPlatform(0);
                }
            }
        }
    }
}
