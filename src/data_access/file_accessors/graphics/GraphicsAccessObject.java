package data_access.file_accessors.graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GraphicsAccessObject implements ApplicationGraphicsAccessInterface, HomePageGraphicsAccessInterface,
        CreateQuizGraphicsAccessInterface
{
    private final Map<ImageType, Image> images;

    private GraphicsAccessObject(Map<ImageType, Image> images)
    {
        this.images = images;
    }

    @Override
    public Image getHomePageBackgroundImage() {
        return images.get(ImageType.HOME_PAGE_BG);
    }

    @Override
    public Image getLogoImage() {
        return images.get(ImageType.LOGO);
    }

    @Override
    public Image getCreateQuizHeaderImage() {
        return images.get(ImageType.CREATE_QUIZ_HEADER);
    }

    @Override
    public Image getCreateQuizBackgroundImage() {
        return images.get(ImageType.CREATE_QUIZ_BG);
    }

    public static class Builder
    {
        private final Map<ImageType, Image> images = new HashMap<>();

        public Builder(){};

        public Builder setImage(ImageType imageType, String fileName) throws IOException {
            images.put(imageType, ImageIO.read(new File(fileName)));
            return this;
        }

        public GraphicsAccessObject Build()
        {
            return new GraphicsAccessObject(images);
        }
    }
}
