package frame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceImg {
    public static BufferedImage tankL,tankR,tankU,tankD;

    static {
        try {
            tankL= ImageIO.read(ResourceImg.class.getClassLoader().getResourceAsStream("image/tankL.gif"));
            tankR= ImageIO.read(ResourceImg.class.getClassLoader().getResourceAsStream("image/tankR.gif"));
            tankD= ImageIO.read(ResourceImg.class.getClassLoader().getResourceAsStream("image/tankD.gif"));
            tankU= ImageIO.read(ResourceImg.class.getClassLoader().getResourceAsStream("image/tankU.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
