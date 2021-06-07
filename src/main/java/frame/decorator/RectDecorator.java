package frame.decorator;

import frame.GameObject;

import java.awt.*;

public class RectDecorator extends GODecorator {
    public RectDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        Color c =g.getColor();
        g.setColor(Color.RED);
        g.drawRect(this.go.x,this.go.y,this.go.width+2,this.go.height+2);
        g.setColor(c);
    }
}
