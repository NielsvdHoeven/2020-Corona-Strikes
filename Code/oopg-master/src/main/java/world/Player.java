package world;

import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
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
    private Collision collision = new Collision();
    private int nPotions = 3;
    private int nLives = 3;

    public Player(WorldApp world) {
        // Met `.concat()` plak je 2 strings aan elkaar.
        // De methode returned een nieuwe String terug.
        super(new Sprite(WorldApp.MEDIA_URL.concat("corona_alive.png")));
        this.world = world;
    }

    @Override
    public void update() {
        beweeg();
        setGravity(0.8f);
        gameObjectCollisionOccurred(world.getGameObjectItems());
        boundaries();
    }

    /**
     * Deze functie zorgt ervoor dat de player niet buiten het spel
     * window kan bewegen
     */

    public void boundaries() {
        if (getX() < 0) {
            setxSpeed(0);
            setX(0);
        }
        if (getY() < 0) {
            setySpeed(0);
            setY(0);
        }
        if (getX() > world.width - width) {
            setxSpeed(0);
            setX(world.width - width);
        }
        if (getY() > world.height - height) {
            setySpeed(0);
            setY(world.height - height);
        }
    }

    /**
     * Houd bij welke key er losgelaten word en verandert daarmee ook de pressed boolean
     */
    public void keyReleased() {
        for (Toets toets : toetsen) {
            if (world.key == world.CODED) {
                if (world.keyCode == toets.getToets()) {
                    toets.setPressed(false);
                }
            }
        }
    }

    /**
     * Houd bij welke key er ingedrukt word en verandert daarmee ook de pressed boolean
     */
    public void keyPressed() {
        for (Toets toets : toetsen) {
            if (world.key == world.CODED) {
                if (world.keyCode == toets.getToets()) {
                    toets.setPressed(true);
                }
            }
        }
    }

    /**
     * Zorgt dat de speler kan bewegen aan de hand van ingedrukte toetsen
     */
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
        }
        if (toetsen[3].getPressed()) {
            setySpeed(4);
        }
    }

    /**
     * Checkt de collision met de tiles
     */
    @Override
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
                if (ct.getCollisionSide() == CollisionSide.BOTTOM) {
                    vector = world.getTileMap().getTilePixelLocation(ct.getTile());

                    setY(vector.y + height);

                }
            }
        }
    }

    /**
     * Checkt de collision met de sanitizer
     */
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
        }
    }

    /**
     * Zet de position van de speler
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Zet het aantal potions
     */
    public void setnPotions(int value) {
        this.nPotions = value;
    }

    /**
     * return het aantal potions
     */
    public int getNPotions() {
        return nPotions;
    }
}

