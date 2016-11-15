package by.ryanogles.springautowiredrevealed.components;

import by.ryanogles.springautowiredrevealed.annotations.Autowired;
import by.ryanogles.springautowiredrevealed.annotations.Component;

@Component
public class AComponent {
    private final AutowiredComponentOne autowiredComponentOne;
    private final AutowiredComponentTwo autowiredComponentTwo;

    @Autowired
    public AComponent(AutowiredComponentOne autowiredComponentOne, AutowiredComponentTwo autowiredComponentTwo) {
        this.autowiredComponentOne = autowiredComponentOne;
        this.autowiredComponentTwo = autowiredComponentTwo;
    }

    public void takeRoll() {
        System.out.println("AComponent is here");

        autowiredComponentOne.takeRoll();
        autowiredComponentTwo.takeRoll();
    }
}
