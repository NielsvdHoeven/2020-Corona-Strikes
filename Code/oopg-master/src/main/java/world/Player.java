package world;

import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import processing.core.PVector;

import java.util.List;

public class Player extends SpriteObject implements ICollidableWithTiles, ICollidableWithGameObjects {
    private WorldApp world;
    private Toets[] toetsen = {new Toets((char) world.LEFT, false), new Toets((char) world.RIGHT, false), new Toets((char) world.UP, false), new Toets((char) world.DOWN, false)};
    private boolean aanHetSpringen;
    private Collision collision = new Collision();
    private int nPotions = 3;
    private int nLives = 3;
    private int nBesmet = 0;

    public Player(WorldApp world) {
        // Met `.concat()` plak je 2 strings aan elkaar.
        // De methode returned een nieuwe String terug.
        super(new Sprite(WorldApp.MEDIA_URL.concat("wise-old-man.png")));
        this.world = world;
    }

    @Override
    public void update() {
        beweeg();
        setGravity(0.8f);
        gameObjectCollisionOccurred(world.getGameObjectItems());
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setAanHetSpringen(boolean waarde) {
        this.aanHetSpringen = waarde;
    }

    public boolean getAanHetSpringen() {
        return aanHetSpringen;
    }

    public int getNPotions() {
        return nPotions;
    }

    public void boundaries(int worldWidth, int worldHeight) {
        if (x <= 0) {
            x = 0;
        }
        if (x + width >= worldWidth) {
            x = worldWidth - width;
        }
        if (y <= 0) {
            y = 0;
        }
        if (y + height >= worldHeight) {
            y = worldHeight - height;
        }
    }

    public void keyReleased() {
        for (Toets toets : toetsen) {
            if (world.key == world.CODED) {
                if (world.keyCode == toets.getToets()) {
                    toets.setPressed(false);
                }
            }
        }
    }

    public void keyPressed() {
        for (Toets toets : toetsen) {
            if (world.key == world.CODED) {
                if (world.keyCode == toets.getToets()) {
                    toets.setPressed(true);
                }
            }
        }
    }

    public void beweeg() {
        setxSpeed(0);
        if (toetsen[0].getPressed()) {
            setxSpeed(-6);
        }
        if (toetsen[1].getPressed()) {
            setxSpeed(6);
        }
        if (toetsen[2].getPressed()) {
            setySpeed(-10);
            aanHetSpringen = true;
        }
        if (toetsen[3].getPressed()) {
            setySpeed(4);
        }
    }

    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
        PVector vector;
        for (CollidedTile ct : collidedTiles) {
            if (ct.getTile() instanceof FloorTile) {
                try {
                    vector = world.getTileMap().getTilePixelLocation(ct.getTile());
                    setY(vector.y - getHeight());
                } catch (TileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g : collidedGameObjects) {
            if (g instanceof Sanitizer) {
                if (collision.checkCollision(g, this)) {
                    System.out.println("sanitizer detected, resetting position");
                    x = 0;
                    y = 0;
                    nLives--;
                    System.out.println(nLives);
                }
            }
            if (g instanceof Human) {
                if (collision.checkCollision(g, this)) {

                }
            }
        }
    }
}

