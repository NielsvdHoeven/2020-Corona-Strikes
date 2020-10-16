package world;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

import java.util.List;

public class Sanitizer extends SpriteObject implements ICollidableWithGameObjects {


    public Sanitizer(float x, float y) {
        super(new Sprite(WorldApp.MEDIA_URL.concat("rsz_sanitizer_goed.png")));
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {

    }
}
