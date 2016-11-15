package by.ryanogles.springautowiredrevealed.components;

import by.ryanogles.springautowiredrevealed.annotations.Autowired;
import by.ryanogles.springautowiredrevealed.annotations.Component;

@Component
public class AutowiredComponentOne {
    @Autowired
    public AutowiredComponentOne() {
    }

    public void takeRoll() {
        System.out.println("AutowiredComponent ONE is here");
    }
}
