package frame.decorator;

import frame.GameObject;

import java.awt.*;

public class RectDecorator extends GODecorator {
    public RectDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        this.x=go.x;
        this.y=go.y;
        go.paint(g);

        Color c =g.getColor();
        g.setColor(Color.RED);
        g.drawRect(go.x,go.y,getWidth()+2,getHeight()+2);
        g.setColor(c);
    }

    @Override
    public int getWidth() {
        return go.getWidth();
    }

    @Override
    public int getHeight() {
        return go.getHeight();
    }
}