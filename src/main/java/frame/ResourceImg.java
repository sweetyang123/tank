package frame;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceImg {
    public static BufferedImage goodTankL,goodTankR,goodTankU,goodTankD;
    public static BufferedImage badTankL,badTankR,badTankU,badTankD;
    public static BufferedImage bulletL,bulletR,bulletU,bulletD;
    public static BufferedImage[] explodes=new BufferedImage[16];

    static {
        try {
            goodTankU= ImageIO.read(ResourceImg.class.getClassLoader().getResourceAsStream("image/GoodTank1.png"));
            goodTankL= ImageUtil.rotateImage(goodTankU,-90);
            goodTankR= ImageUtil.rotateImage(goodTankU,90);
            goodTankD= ImageUtil.rotateImage(goodTankU,180);
            badTankU= ImageIO.read(ResourceImg.class.getClassLoader().getResourceAsStream("image/BadTank1.png"));
            badTankL= ImageUtil.rotateImage(badTankU,-90);
            badTankR= ImageUtil.rotateImage(badTankU,90);
            badTankD= ImageUtil.rotateImage(badTankU,180);
            bulletU= ImageIO.read(ResourceImg.class.getClassLoader().getResourceAsStream("image/bulletU.gif"));
            bulletL= ImageUtil.rotateImage(bulletU,-90);
            bulletR= ImageUtil.rotateImage(bulletU,90);
            bulletD= ImageUtil.rotateImage(bulletU,180);
            for (int i = 0; i <explodes.length; i++) {
                explodes[i]= ImageIO.read(ResourceImg.class.getClassLoader().getResourceAsStream("image/e"+(i+1)+"" +
                        ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
