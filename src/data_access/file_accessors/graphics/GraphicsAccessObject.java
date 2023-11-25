package data_access.file_accessors.graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class GraphicsAccessObject implements ApplicationGraphicsAccessInterface, HomePageGraphicsAccessInterface,
        CreateQuizGraphicsAccessInterface, LoadingScreenGraphicsAccessInterface
{
    private final Map<ImageType, Image> images;
    private final Queue<Image> loadingAnimations;

    private GraphicsAccessObject(Map<ImageType, Image> images, Queue<Image> loadingAnimations)
    {
        this.images = images;
        this.loadingAnimations = loadingAnimations;
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

    @Override
    public Queue<Image> getLoadingAnimationGifs() {
        return loadingAnimations;
    }

    public static class Builder
    {
        private final Map<ImageType, Image> images = new HashMap<>();
        private final Queue<Image> loadingAnimations = new LinkedList<>();

        public Builder(){};

        public Builder setImage(ImageType imageType, String fileName) throws IOException {
            images.put(imageType, ImageIO.read(new File(fileName)));
            return this;
        }

        public Builder setLoadingAnimations(String[] fileNames) {
            for (String fileName : fileNames) {
                loadingAnimations.add(Toolkit.getDefaultToolkit().createImage(fileName));
            }
            return this;
        }

        public GraphicsAccessObject Build()
        {
            return new GraphicsAccessObject(images, loadingAnimations);
        }
    }
}
