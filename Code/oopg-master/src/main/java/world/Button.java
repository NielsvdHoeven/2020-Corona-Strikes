package world;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public class Button extends AnimatedSpriteObject implements ICollidableWithGameObjects {

    private WorldApp world;
    private Collision collision = new Collision();

    public Button(WorldApp world, int totalFrames) {

        super(new Sprite(WorldApp.MEDIA_URL.concat("platformPack_tile053.png")), totalFrames);
        this.world = world;
    }

    /**
     * Zet de knop op de goede positie
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {
        gameObjectCollisionOccurred(world.getGameObjectItems());
        initializeButton();
    }

    /**
     * zet de knop op de goede positie en reset hem in het begin van ieder level
     */
    public void initializeButton() {
        if (world.maps.getReset()) {
            world.maps.setHiddenPlatform(-1);
            setCurrentFrameIndex(0);
        }
        switch (world.maps.getLevel()) {
            case 1:
                setPosition(350, 386);
                world.maps.setReset(false);
                break;
            case 2:
                setPosition(-100, -100);
                world.maps.setReset(false);

                break;
            case 3:
                setPosition(600, 686);
                world.maps.setReset(false);
                break;
            case 4:
                setPosition(830, 136);
                world.maps.setReset(false);
                break;
            case 5:
                setPosition(-100, -100);
                world.maps.setReset(false);
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
                    setCurrentFrameIndex(1);
                    world.maps.setHiddenPlatform(0);
                }
            }
        }
    }
}
