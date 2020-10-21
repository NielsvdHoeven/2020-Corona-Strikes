package world;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public class Knop extends AnimatedSpriteObject implements ICollidableWithGameObjects {
    private WorldApp world;
    private Collision collision = new Collision();
    private int platform = -1;

    public Knop(WorldApp world, int totalFrames) {

        super(new Sprite(WorldApp.MEDIA_URL.concat("platformPack_tile053.png")), totalFrames);
        this.world = world;
        this.x = 1100;
        this.y = 684;
    }

    public int getPlatform() {
        return platform;
    }

    @Override
    public void update() {
        gameObjectCollisionOccurred(world.getGameObjectItems());
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
