package world;

import nl.han.ica.oopg.objects.GameObject;

public class Collision{

    public boolean checkCollision(GameObject g, GameObject g2) {
        return g2.getX() > g.getX() - g2.getWidth() && g2.getX() < g.getX() + g.getWidth() && g2.getY() > g.getY() - g2.getHeight() && g2.getY() < g.getY() + g.getHeight();
    }

}
