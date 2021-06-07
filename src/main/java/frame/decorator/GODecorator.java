package frame.decorator;

import frame.GameObject;

import java.awt.*;

public abstract class GODecorator extends GameObject {
    GameObject go;

    public GODecorator(GameObject go) {
        this.go = go;
    }
   public abstract void paint(Graphics g);
}
