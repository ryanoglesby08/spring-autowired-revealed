package by.ryanogles.springautowiredrevealed;

import by.ryanogles.springautowiredrevealed.annotations.Component;
import by.ryanogles.springautowiredrevealed.components.AComponent;
import by.ryanogles.springautowiredrevealed.util.ClasspathScanner;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        ComponentRegistry registry = createRegistry();

        AComponent aComponent = registry.get(AComponent.class);
        aComponent.takeRoll();
    }

    private static ComponentRegistry createRegistry() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<Class> components = ClasspathScanner.findClassesWithAnnotation("by.ryanogles.springautowiredrevealed.components", Component.class);

        ComponentRegistry componentRegistry = new ComponentRegistry();
        componentRegistry.registerAll(components);

        return componentRegistry;
    }
}
