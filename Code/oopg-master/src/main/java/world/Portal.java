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

    public Portal(WorldApp world, Sprite sprite) {
        super(new Sprite(world.MEDIA_URL.concat("portals png.png")), 4);

        this.world = world;
    }

    @Override
    public void update() {

    }


    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g : collidedGameObjects) {
            if (g instanceof Player) {

            }
        }
    }
}
