package frame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceImg {
    public static BufferedImage tankL,tankR,tankU,tankD;
    public static BufferedImage bulletL,bulletR,bulletU,bulletD;
    public static BufferedImage[] explodes=new BufferedImage[16];

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
            for (int i = 0; i <explodes.length; i++) {
                explodes[i]= ImageIO.read(ResourceImg.class.getClassLoader().getResourceAsStream("image/e"+(i+1)+"" +
                        ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
