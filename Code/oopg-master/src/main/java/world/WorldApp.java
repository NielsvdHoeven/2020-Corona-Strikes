package world;

// deze 3 classes moet je geimporteerd hebben om het te laten werken.

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.View;

public class WorldApp extends GameEngine {

    public static String MEDIA_URL = "src/main/java/media/";

    Player player;
    Human[] humans = new Human[2];
    Knop knop;
    Maps maps;
    Sanitizer sanitizer;
    Portal portal;

    public int level = 0;

    public static void main(String[] args) {
        WorldApp wa = new WorldApp();

        // deze methode start de game.
        wa.runSketch();
    }

    @Override
    public void setupGame() {
        int worldWidth = 1200;
        int worldHeight = 800;

        player = new Player(this);
        knop = new Knop(this, 1);
        maps = new Maps(this);
        sanitizer = new Sanitizer(800, 100);
        portal = new Portal(this);
        player.setY(500);
        player.setX(700);

        addGameObject(sanitizer);
        addGameObject(knop);
        addGameObject(player);
        addGameObject(portal);
        for (int i = 0; i < humans.length; i++) {
            humans[i] = new Human(this);
            addGameObject(humans[i]);
        }

        View view = new View(worldWidth, worldHeight);

        view.setBackground(loadImage(MEDIA_URL.concat("background.jpg")));

        setView(view);

        size(worldWidth, worldHeight);
        maps.initializeStandardTileMap();
    }

    @Override
    public void update() {
        maps.setMap();
        maps.initializeTileMap();
    }

    @Override
    public void keyPressed() {
        player.keyPressed();
    }

    public void keyReleased() {
        player.keyReleased();
    }

    public void mousePressed() {

    }


}