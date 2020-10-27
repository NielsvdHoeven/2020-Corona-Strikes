package world;

// deze 3 classes moet je geimporteerd hebben om het te laten werken.

import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.view.View;

public class WorldApp extends GameEngine {

    public static String MEDIA_URL = "src/main/java/media/";

    Player player;
    Human[] humans = new Human[2];
    Knop knop;
    Maps maps;
    Sanitizer sanitizer;
    Portal portal;
    Potion potion;
    View view;
    TextObject dashboardText;

    public static void main(String[] args) {
        WorldApp wa = new WorldApp();

        // deze methode start de game.
        wa.runSketch();
    }

    @Override
    public void setupGame() {
        int worldWidth = 1200;
        int worldHeight = 800;

        potion = new Potion(this);
        player = new Player(this);
        knop = new Knop(this, 1);
        maps = new Maps(this);
        sanitizer = new Sanitizer(800, 100);
        portal = new Portal(this);

        createDashboard(worldWidth, 100);

        player.setPosition(100, 600);

        addGameObject(knop);
        addGameObject(player);
        addGameObject(portal);

        for (int i = 0; i < humans.length; i++) {
            humans[i] = new Human(this);
            addGameObject(humans[i]);
        }

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
        refreshDashboard();
    }

    private void createDashboard(int dashboardWidth, int dashboardHeight) {
        Dashboard dashboard = new Dashboard(0, 0, dashboardWidth, dashboardHeight);
        dashboardText = new TextObject("");
        dashboard.addGameObject(dashboardText);
        addDashboard(dashboard);
    }

    private void refreshDashboard() {
        dashboardText.setText("Current Level: " + maps.getLevel());
        switch (maps.getLevel()) {
            case 1:
                dashboardText.setColor(0);
                break;
            case 2:
        }
    }

    public void setBackground() {
        if (maps.getReset()) {
            switch (maps.getLevel()) {
                case 2:
                    view.setBackground(loadImage(MEDIA_URL.concat("background2.jpg")));
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

    }

}