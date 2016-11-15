package by.ryanogles.springautowiredrevealed.components;

import by.ryanogles.springautowiredrevealed.annotations.Autowired;
import by.ryanogles.springautowiredrevealed.annotations.Component;

@Component
public class AutowiredComponentTwo {
    @Autowired
    public AutowiredComponentTwo() {
    }

    public void takeRoll() {
        System.out.println("AutowiredComponent TWO is here");
    }
}
