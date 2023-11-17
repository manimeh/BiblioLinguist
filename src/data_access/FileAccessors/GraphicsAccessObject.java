package data_access.FileAccessors;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GraphicsAccessObject implements GraphicsAccessInterface
{
    private Image homePageBackgroundImage;
    private Image logoImage;

    private GraphicsAccessObject() {}

    private GraphicsAccessObject(Image homePageBackgroundImage, Image logoImage)
    {
        this.homePageBackgroundImage = homePageBackgroundImage;
        this.logoImage = logoImage;
    }

    @Override
    public Image getHomePageBackgroundImage() {
        return homePageBackgroundImage;
    }

    @Override
    public Image getLogoImage() {
        return logoImage;
    }

    public static class Builder
    {
        private Image homePageBackgroundImage = null;
        private Image logoImage = null;

        public Builder(){};

        public Builder setHomePageBackgroundImage(String fileName) throws IOException {
            homePageBackgroundImage = ImageIO.read(new File(fileName));
            return this;
        }

        public Builder setLogoImage(String fileName) throws IOException {
            logoImage = ImageIO.read(new File(fileName));
            return this;
        }

        public GraphicsAccessObject Build()
        {
            return new GraphicsAccessObject(homePageBackgroundImage, logoImage);
        }
    }
}
