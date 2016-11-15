package by.ryanogles.springautowiredrevealed;

import by.ryanogles.springautowiredrevealed.annotations.Autowired;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Stores all application component instances
 */
public class ComponentRegistry {
    private Map<Class, Object> registry;

    public ComponentRegistry() {
        registry = new HashMap<>();
    }

    public void registerAll(List<Class> components) {
        components.forEach(this::registerDependents);
    }

    private void registerDependents(Class component) {
        // Get the constructor marked as Autowired. Assume only 1 autowired constructor for simplicity.
        Optional<Constructor> autowiredConstructor = getAutowiredConstructor(component);

        autowiredConstructor.ifPresent((Constructor constructor) -> {
            try {
                // Get the Autowired constructor's parameters
                Class<?>[] dependencies = constructor.getParameterTypes();

                // No parameters means it's the default constructor
                if (dependencies.length == 0) {
                    // No dependencies to register, so we can instantiate this component and store the instance right away
                    registry.putIfAbsent(component, constructor.newInstance());
                } else {
                    // If there were parameters, then we must instantiate those dependencies first
                    registerAll(Arrays.asList(dependencies));

                    // Now that all it's dependencies are registered, we can instantiate this component and register it
                    List<Object> parameters = getAll(constructor.getParameterTypes());
                    registry.putIfAbsent(component, constructor.newInstance(parameters.toArray()));
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    private List<Object> getAll(Class[] components) {
        return Arrays
                .stream(components)
                .map((Function<Class, Object>) this::get)
                .collect(Collectors.toList());
    }

    private Optional<Constructor> getAutowiredConstructor(Class component) {
        return Arrays
                    .stream(component.getConstructors())
                    .filter((Constructor constructor) -> constructor.isAnnotationPresent(Autowired.class))
                    .findFirst();
    }

    public <T> T get(Class<T> klass) {
        return (T) registry.get(klass);
    }
}
