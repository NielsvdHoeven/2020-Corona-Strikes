package world;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

import java.util.List;

public class Portal extends AnimatedSpriteObject implements ICollidableWithGameObjects {

    private WorldApp world;
    private Collision collision = new Collision();

    public Portal(WorldApp world) {

        super(new Sprite(world.MEDIA_URL.concat("portals.png")), 4);

        this.world = world;
        setCurrentFrameIndex(1);
    }

    @Override
    public void update() {
        initializePortal();
    }

    public void initializePortal() {
        if (world.maps.checkAllInfected(world.humans)) {
            setCurrentFrameIndex(3);
        } else {
            setCurrentFrameIndex(1);
        }
        x = 1050;
        y = 500;
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g : collidedGameObjects) {
            if (g instanceof Player) {
                if (world.maps.checkAllInfected(world.humans)) {
                    world.maps.setLevel(world.maps.getLevel() + 1);
                    world.maps.setReset(true);
                    world.player.setPosition(0, 600);
                    System.out.println(world.maps.getLevel());
                }
            }
        }
    }
}
