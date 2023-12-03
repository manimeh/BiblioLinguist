package CATest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CATest {
    private static final Map<CAClass, List<Class<?>>> classes = new HashMap<>();

    @BeforeAll
    static void getCAClasses() throws IOException, ClassNotFoundException {
        classes.put(CAClass.ENTITY, ClassScanner.getClasses("entity"));

        List<Class<?>> restOfClasses = new ArrayList<>(ClassScanner.getClasses("interface_adapter"));
        restOfClasses.addAll(ClassScanner.getClasses("use_case"));
        restOfClasses.addAll(ClassScanner.getClasses("data_access"));
        restOfClasses.addAll(ClassScanner.getClasses("view"));

        for (CAClass caClass: CAClass.values())
        {
            if(caClass != CAClass.ENTITY)
            {
                List<Class<?>> temp = new ArrayList<>();
                classes.put(caClass, temp);

                for (Class<?> clazz: restOfClasses)
                {
                    if (clazz.getName().endsWith(caClass.getClassName()))
                    {
                        temp.add(clazz);
                    }
                }
            }
        }
    }

    @Test
    void interactorInterfaceTest()
    {
        checkInterfaceImplementation(classes.get(CAClass.INTERACTOR), CAClass.INPUT_BOUNDARY);
    }

    @Test
    void presenterInterfaceTest()
    {
        checkInterfaceImplementation(classes.get(CAClass.PRESENTER), CAClass.OUTPUT_BOUNDARY);
    }

    @Test
    void dataAccessInterfaceTest()
    {
        checkInterfaceImplementation(classes.get(CAClass.DATA_ACCESS_OBJECT), CAClass.DATA_ACCESS_INTERFACE);
    }

    @Test
    void frameworksAndDriversDependencyTest()
    {
        checkDependencies(classes.get(CAClass.VIEW), new HashSet<>(Set.of(CAClass.VIEW_MODEL, CAClass.CONTROLLER,
                CAClass.ENTITY)));
        checkDependencies(classes.get(CAClass.DATA_ACCESS_OBJECT), new HashSet<>(Set.of(CAClass.ENTITY)));
    }

    @Test
    void interfaceAdoptersDependencyTest()
    {
        checkDependencies(classes.get(CAClass.CONTROLLER), new HashSet<>(Set.of(CAClass.INPUT_BOUNDARY,
                CAClass.INPUT_DATA, CAClass.ENTITY)));
        checkDependencies(classes.get(CAClass.PRESENTER), new HashSet<>(Set.of(CAClass.VIEW_MODEL, CAClass.OUTPUT_DATA,
                CAClass.ENTITY)));

        checkDependencies(classes.get(CAClass.VIEW_MODEL), new HashSet<>(Set.of(CAClass.ENTITY)));
    }

    @Test
    void applicationBusinessRulesDependencyTest()
    {
        checkDependencies(classes.get(CAClass.INPUT_BOUNDARY), new HashSet<>(Set.of(CAClass.INPUT_DATA)));
        checkDependencies(classes.get(CAClass.OUTPUT_BOUNDARY), new HashSet<>(Set.of(CAClass.OUTPUT_DATA)));

        checkDependencies(classes.get(CAClass.INPUT_DATA), new HashSet<>(Set.of(CAClass.ENTITY)));
        checkDependencies(classes.get(CAClass.OUTPUT_DATA), new HashSet<>(Set.of(CAClass.ENTITY)));

        checkDependencies(classes.get(CAClass.DATA_ACCESS_INTERFACE), new HashSet<>(Set.of(CAClass.ENTITY)));

        checkDependencies(classes.get(CAClass.INTERACTOR), new HashSet<>(Set.of(CAClass.DATA_ACCESS_INTERFACE,
                CAClass.OUTPUT_BOUNDARY, CAClass.INPUT_DATA, CAClass.OUTPUT_DATA, CAClass.ENTITY)));
    }

    @Test
    void enterpriseBusinessRulesDependencyTest()
    {
        checkDependencies(classes.get(CAClass.ENTITY), new HashSet<>(Set.of(CAClass.ENTITY)));
    }

    private void checkInterfaceImplementation(List<Class<?>> caClasses, CAClass caInterface)
    {
        for(Class<?> clazz: caClasses)
        {
            assertTrue(hasInterface(clazz.getInterfaces(), caInterface),
                    String.format("%s does not implement %s. This violates CA", clazz, caInterface));
        }
    }

    private void checkDependencies(List<Class<?>> classes, Set<CAClass> validDependencies)
    {
        for (Class<?> clazz: classes)
        {
            for (Class<?> dependency: getDependencies(clazz))
            {
                if(isCAClass(dependency))
                {
                    assertTrue(isValidClass(dependency, validDependencies),
                            String.format("%s is using the class %s. This violates CA", clazz, dependency));
                }
            }
        }
    }

    private boolean hasInterface(Class<?>[] interfaces, CAClass desiredInterface) {
        for (Class<?> implementedInterface : interfaces) {
            if (classes.get(desiredInterface).contains(implementedInterface)) {
                return true;
            }
        }
        return false;
    }

    public List<Class<?>> getDependencies(Class<?> clazz) {
        List<Class<?>> dependencies = new ArrayList<>();

        for (Field field : clazz.getDeclaredFields())
        {
            dependencies.add(field.getType());
        }

        for (Method method : clazz.getDeclaredMethods())
        {
            dependencies.add(method.getReturnType());

            for (Type parameterType : method.getGenericParameterTypes()) {
                if (parameterType instanceof Class)
                {
                    if(isCAClass((Class<?>) parameterType))
                    {
                        dependencies.add((Class<?>) parameterType);
                    }
                }
                else if (parameterType instanceof ParameterizedType)
                {
                    for (Type actualTypeArgument : ((ParameterizedType) parameterType).getActualTypeArguments())
                    {
                        if (actualTypeArgument instanceof Class)
                        {
                            if(isCAClass((Class<?>) actualTypeArgument))
                            {
                                dependencies.add((Class<?>) actualTypeArgument);
                            }
                        }
                    }
                }
            }
        }

        return dependencies;
    }

    private boolean isCAClass(Class<?> clazz)
    {
        return isValidClass(clazz, classes.keySet());
    }

    private boolean isValidClass(Class<?> clazz, Set<CAClass> validClasses)
    {
        for(CAClass validClass: validClasses)
        {
            if (classes.get(validClass).contains(clazz))
            {
                return true;
            }
        }
        return false;
    }
}
