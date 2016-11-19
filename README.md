Spring Autowired Revealed
===================

A toy example of a working re-implementation of [Spring's Autowired](http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/beans/factory/annotation/Autowired.html) functionality for dependency injection.

I wrote this while writing a blog post title "It's not magic, you just don't understand it yet." Have a look at that here: <http://ryanogles.by/2016/11/19/its-not-magic-you-just-dont-understand-it-yet.html>

## Usage

`./go.sh`

Expected Output:
```
AComponent is here
AutowiredComponent ONE is here
AutowiredComponent TWO is here
```

Or load up the project in your IDE (Eclipse, IntelliJ, etc) and run Main.java.


## How it works

As I said, this is just a toy example to demonstrate how I think Autowired could work. I have not actually gone through the Spring source code to see how close I am, but Spring is undoubtedly much more clever and efficient than I attempt to be.

_(For example, I do not handle circular dependencies. I also use an eager loading strategy, while Spring could use a lazy one)_

Nevertheless, the basic flow of this example is as follows:

1. Application starts in Main.java
1. First, Main.java uses Java's `ClassLoader` and Reflections capabilities to discover classes in the `components` package marked with the `@Component` annotation
1. Main.java creates a `ComponentRegistry`, which will eventually cache instances of each component found in the previous step
1. For each component found in the class path scanning...
    1. `ComponentRegistry` uses Reflection to discover the constructor marked as `@Autowired`
    1. Then it gets all of the constructor's parameters. This represents the dependencies of the component.
    1. Next, recursively discover each dependency's constructor and it's dependencies.
    1. Eventually, we come to components that do not have any further dependencies.
    1. At this point, we can instantiate the component and cache it.
    1. Once we have cached all of the dependencies for a particular component, we can then call its constructor, using the cached dependencies
1. Finally, we get the instance of `AComponent` from the `ComponentRegistry`, and invoke the `takeRoll` method. This will invoke the `takeRoll` method of its injected dependencies.
