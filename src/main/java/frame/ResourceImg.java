package frame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceImg {
    public static BufferedImage tankL,tankR,tankU,tankD;
    public static BufferedImage bulletL,bulletR,bulletU,bulletD;

    static {
        try {
            tankL= ImageIO.read(ResourceImg.class.getClassLoader().getResourceAsStream("image/tankL.gif"));
            tankR= ImageIO.read(ResourceImg.class.getClassLoader().getResourceAsStream("image/tankR.gif"));
            tankD= ImageIO.read(ResourceImg.class.getClassLoader().getResourceAsStream("image/tankD.gif"));
            tankU= ImageIO.read(ResourceImg.class.getClassLoader().getResourceAsStream("image/tankU.gif"));
            bulletL= ImageIO.read(ResourceImg.class.getClassLoader().getResourceAsStream("image/bulletL.gif"));
            bulletR= ImageIO.read(ResourceImg.class.getClassLoader().getResourceAsStream("image/bulletR.gif"));
            bulletU= ImageIO.read(ResourceImg.class.getClassLoader().getResourceAsStream("image/bulletU.gif"));
            bulletD= ImageIO.read(ResourceImg.class.getClassLoader().getResourceAsStream("image/bulletD.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
