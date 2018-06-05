package backgrounds;

import biuoop.DrawSurface;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

/**
 * A ImageBackground class.
 *
 * @author Omer Wolf.
 */
public class ImageBackground extends Background {
    private Image image;
    private String stringImage;


    /**
     * Constructor getting image.
     *
     * @param initString Is String representing the name of image file.
     */
    public ImageBackground(String initString) {
        this.stringImage = initString;
        InputStream is = null;

        try {
            //read image from file.
            is = ClassLoader.getSystemClassLoader().getResourceAsStream(this.stringImage);
            //Getting image.
            this.image = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace(System.err);

        } finally {
            try {
                if (is != null) {
                    //Close file.
                    is.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file");
            }
        }
    }

    /**
     * Draw the sprite to the screen with image.
     *
     * @param d - the draw surface.
     */

    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage(0, 0, this.image); // draw the image
    }
}
