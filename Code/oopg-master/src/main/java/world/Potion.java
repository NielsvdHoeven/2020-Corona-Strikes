package world;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

import java.util.List;

public class Potion extends SpriteObject implements ICollidableWithGameObjects {

    private WorldApp world;
    private Collision collision = new Collision();

    public Potion(WorldApp world) {
        super(new Sprite(WorldApp.MEDIA_URL.concat("potion.png")));
        this.world = world;
    }

    @Override
    public void update() {
        initializePotions();
    }

    public void initializePotions(){
        if (world.maps.getReset()){
            world.addGameObject(this);
        }
        switch (world.maps.getLevel()){
            case 1:
                setPosition(500, 500);
                break;
            default:
                setPosition(0,0);
        }
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for( GameObject g : collidedGameObjects){
            if (g instanceof Player){
                world.deleteGameObject(this);
                world.player.setnPotions(world.player.getNPotions() + 1);
            }
        }
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
}
