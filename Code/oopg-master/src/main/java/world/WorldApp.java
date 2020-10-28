package world;

// deze 3 classes moet je geimporteerd hebben om het te laten werken.

import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.sound.Sound;
import nl.han.ica.oopg.view.View;

public class WorldApp extends GameEngine {

    public static String MEDIA_URL = "src/main/java/media/";

    Player player;
    Human[] humans = new Human[2];
    Sanitizer[] sanitizers = new Sanitizer[3];
    Knop knop;
    Maps maps;
    Portal portal;
    Potion potion;
    View view;
    TextObject dashboardText;
    TextObject titelText;
    TextObject startText;
    Dashboard titelDashboard;
    Dashboard levelDashboard;

    public static void main(String[] args) {
        WorldApp wa = new WorldApp();

        // deze methode start de game.
        wa.runSketch();
    }

    @Override
    public void setupGame() {
        int worldWidth = 1200;
        int worldHeight = 800;


        createTitelDashboard(worldWidth, worldHeight);


        for (int i = 0; i < humans.length; i++) {
            humans[i] = new Human(this);
            addGameObject(humans[i]);
        }

        for (int i = 0; i < sanitizers.length; i++) {
            sanitizers[i] = new Sanitizer(this);
            addGameObject(sanitizers[i]);
        }

        potion = new Potion(this);
        player = new Player(this);
        knop = new Knop(this, 1);
        maps = new Maps(this);
        portal = new Portal(this);

        player.setPosition(100, 600);

        addGameObject(knop);
        addGameObject(player);
        addGameObject(portal);

        createLevelDashboard(worldWidth, 100);


        view = new View(worldWidth, worldHeight);

        view.setBackground(loadImage(MEDIA_URL.concat("chinese_markt.jpg")));

        setView(view);

        size(worldWidth, worldHeight);
        maps.initializeStandardTileMap();
    }

    @Override
    public void update() {
        maps.setMap();
        maps.initializeTileMap();
        setBackground();
        refreshLevelDashboard();
    }

    private void createTitelDashboard(int dashboardWidth, int dashboardHeight) {
        titelDashboard = new Dashboard(0, 0, dashboardWidth, dashboardHeight);
        titelText = new TextObject("2020: Corona Strikes", 600, 200);
        startText = new TextObject("Click to start!", 600, 600);
        titelDashboard.addGameObject(startText);
        titelDashboard.addGameObject(titelText);
        titelDashboard.setBackgroundImage(new Sprite(MEDIA_URL.concat("corona_map.jpg")));
        addDashboard(titelDashboard);
    }

    private void createLevelDashboard(int dashboardWidth, int dashboardHeight) {
        levelDashboard = new Dashboard(0, 0, dashboardWidth, dashboardHeight);
        dashboardText = new TextObject("", 0, 0);
        levelDashboard.addGameObject(dashboardText);
    }

    private void refreshLevelDashboard() {
        dashboardText.setText("Current Level: " + maps.getLevel());
    }

    public void setBackground() {
        if (maps.getReset()) {
            switch (maps.getLevel()) {
                case 2:
                    view.setBackground(loadImage(MEDIA_URL.concat("pisa.jpeg")));
                    break;
                case 3:
                    view.setBackground(loadImage(MEDIA_URL.concat("berlin.jpg")));
                    break;
                case 4:
                    view.setBackground(loadImage(MEDIA_URL.concat("brazil.jpg")));
                    break;
                case 5:
                    view.setBackground(loadImage(MEDIA_URL.concat("background2.jpg")));
                    break;
            }
        }
    }

    public void keyPressed() {
        player.keyPressed();
    }

    public void keyReleased() {
        player.keyReleased();
    }

    public void mousePressed() {

        deleteDashboard(titelDashboard);
        addDashboard(levelDashboard);
    }

}