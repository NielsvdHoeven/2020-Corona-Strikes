package world;

// deze 3 classes moet je geimporteerd hebben om het te laten werken.

import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.view.View;

public class WorldApp extends GameEngine {

    public static String MEDIA_URL = "src/main/java/media/";

    Player player;
    Human[] humans = new Human[2];
    Sanitizer[] sanitizers = new Sanitizer[3];
    Button button;
    Maps maps;
    Portal portal;
    View view;
    TextObject dashboardText;
    Dashboard titelDashboard;
    Dashboard levelDashboard;
    Dashboard endDashboard;

    public static void main(String[] args) {
        WorldApp wa = new WorldApp();

        // deze methode start de game.
        wa.runSketch();
    }

    /**
     * initialiseert alle objecten die in het spel zitten
     * door het zowel zelf te doen als door methoden van objecten zelf
     */
    @Override
    public void setupGame() {
        int worldWidth = 1200;
        int worldHeight = 800;

        createTitelDashboard(1200, 800);
        createLevelDashboard(worldWidth, 100);
        createEndDashboard(1200, 800);

        for (int i = 0; i < humans.length; i++) {
            humans[i] = new Human(this);
            addGameObject(humans[i]);
        }

        for (int i = 0; i < sanitizers.length; i++) {
            sanitizers[i] = new Sanitizer(this);
            addGameObject(sanitizers[i]);
        }

        player = new Player(this);
        button = new Button(this, 1);
        maps = new Maps(this);
        portal = new Portal(this);

        player.setPosition(100, 600);

        addGameObject(button);
        addGameObject(player);
        addGameObject(portal);



        view = new View(worldWidth, worldHeight);

        view.setBackground(loadImage(MEDIA_URL.concat("chinese_markt.jpg")));

        setView(view);

        size(worldWidth, worldHeight);
        maps.initializeStandardTileMap();
    }

    /**
     * update de game
     */
    @Override
    public void update() {
        maps.setMap();
        maps.initializeTileMap();
        setBackground();
        refreshLevelDashboard();
    }

    /**
     * creeert het titelscherm met de bijbehorende text
     */
    private void createTitelDashboard(int dashboardWidth, int dashboardHeight) {
        titelDashboard = new Dashboard(0, 0, dashboardWidth, dashboardHeight);
        TextObject titelText = new TextObject("2020: Corona Strikes", 600, 200);
        TextObject startText = new TextObject("Click to start!", 600, 600);
        titelDashboard.addGameObject(startText);
        titelDashboard.addGameObject(titelText);
        titelDashboard.setBackgroundImage(new Sprite(MEDIA_URL.concat("corona_map.jpg")));
        addDashboard(titelDashboard);
    }

    /**
     * Creeert de level counter
     */
    private void createLevelDashboard(int dashboardWidth, int dashboardHeight) {
        levelDashboard = new Dashboard(0, 0, dashboardWidth, dashboardHeight);
        dashboardText = new TextObject("", 0, 0);
        levelDashboard.addGameObject(dashboardText);
    }

    /**
     * creeert het eindscherm met de bijbehorende text
     */
    private void createEndDashboard(int dashboardWidth, int dashboardHeight) {
        endDashboard = new Dashboard(0, 0, dashboardWidth, dashboardHeight);
        TextObject endText1 = new TextObject("Congratulations!", 600, 200);
        TextObject endText2 = new TextObject("You have infected the whole world", 600, 400);
        TextObject endText3 = new TextObject("Thanks for Playing!", 600, 600);
        endDashboard.addGameObject(endText1);
        endDashboard.addGameObject(endText2);
        endDashboard.addGameObject(endText3);
        endDashboard.setBackgroundImage(new Sprite(MEDIA_URL.concat("eindscherm.jpg")));

    }

    /**
     * zorgt ervoor dat de text op het level dashboard up to date is
     */
    private void refreshLevelDashboard() {
        dashboardText.setText("Current Level: " + maps.getLevel());
    }

    /**
     * zet bij ieder level de bijbehorende achtergrond
     */
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
                    view.setBackground(loadImage(MEDIA_URL.concat("NY.jpg")));
                    break;
                case 6:
                    addDashboard(endDashboard);
                    break;
            }
        }
    }

    /**
     * methode die checkt of er een toets word ingedrukt
     */
    public void keyPressed() {
        player.keyPressed();
    }

    /**
     * methode die checkt of er een toets word los gelaten
     */
    public void keyReleased() {
        player.keyReleased();
    }

    /**
     * methode die checkt of er met de muis word geklikt
     */
    public void mousePressed() {

        deleteDashboard(titelDashboard);
        addDashboard(levelDashboard);
    }

}