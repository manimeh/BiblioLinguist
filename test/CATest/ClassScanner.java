package CATest;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ClassScanner {
    public static List<Class<?>> getClasses(String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);

        List<Class<?>> classes = new ArrayList<>();

        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            classes.addAll(findClasses(resource, packageName));
        }

        return classes;
    }

    private static List<Class<?>> findClasses(URL directory, String packageName) throws ClassNotFoundException, MalformedURLException {
        List<Class<?>> classes = new ArrayList<>();
        File dir = new File(directory.getFile());

        if (dir.exists()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        classes.addAll(findClasses(file.toURI().toURL(), packageName + "." + file.getName()));
                    } else if (file.getName().endsWith(".class")) {
                        String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                        Class<?> clazz = Class.forName(className);
                        classes.add(clazz);
                    }
                }
            }
        }

        return classes;
    }
}
