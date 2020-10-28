package world;

import nl.han.ica.oopg.objects.GameObject;

public class Collision{

    /**
     * checkt de collision tussen de 2 meegegeven object en returnt een true of false
     */
    public boolean checkCollision(GameObject g, GameObject g2) {
        return g2.getX() > g.getX() - g2.getWidth() && g2.getX() < g.getX() + g.getWidth() && g2.getY() > g.getY() - g2.getHeight() && g2.getY() < g.getY() + g.getHeight();
    }
}
