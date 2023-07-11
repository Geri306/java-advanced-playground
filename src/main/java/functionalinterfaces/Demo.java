package functionalinterfaces;

import java.util.List;
import java.util.function.*;

public class Demo {
    public static void chainConsumers() {
        List<String> list = List.of("a", "b", "c");
        Consumer<String> print = item -> System.out.println(item);
        Consumer<String> printUpperCase = item -> System.out.println(item.toUpperCase());
        Consumer<String> printDouble = item -> System.out.println(item.repeat(2));
        list.forEach(print.andThen(printUpperCase).andThen(printDouble));
    }

    public static void supplierDemo() {
        // lazy evaluation: the function is not executed ...
        Supplier<Double> getRandom = () -> Math.random();
        // ... until we explicitly call it
        var random = getRandom.get();
        System.out.println(random);

        // use the primitive specialisations (e.g. BooleanSupplier) of the supplier interface when we work with primitive values because then we don't have to pay the cost of autoboxing the primitive value inside a reference type and then unboxing it later on.

        BooleanSupplier booSup = () -> (Math.random() > 0.5);
        System.out.println(booSup.getAsBoolean());
    }

    public static void functionInterface() {
        Function<String, Integer> map = str -> str.length();
        var length = map.apply("sky");
        System.out.println(length);
    }

    public static void composeFunctions() {
        // Goal
        // initial value to be transformed: "key:value"
        // first transformation: "key=value"
        // second transformation: "{key=value}"

        // Let's build our small, reusable and composable functions!
        Function<String, String> replaceColon = str -> str.replace(":", "=");
        Function<String, String> addBraces = str -> "{" + str + "}";

        // Let's compose functions! here we just store the newly composed function, or...
        Function<String, String> stringStringFunction = replaceColon.andThen(addBraces);

        // ... we can directly call the apply method, pass our string to it and then get the result. Btw, this is declarative programming.
        var result = replaceColon
                .andThen(addBraces)
                .apply("key:value");
        System.out.println(result);

        // Alternatively, we can use .compose to reverse the order. The result will be the same.
        result = addBraces.compose(replaceColon).apply("key:value");
        System.out.println(result);
    }

    public static void predicateInterface() {
        var testStr = "sky";
        Predicate<String> isLongerThan5 = str -> str.length() > 5;
        var result = isLongerThan5.test(testStr);
        System.out.println("result = " + result);
    }

    public static void combinePredicates() {
        Predicate<String> hasLeftBrace = str -> str.startsWith("{");
        Predicate<String> hasRightBrace = str -> str.endsWith("}");
        Predicate<String> hasLeftAndRightBraces = hasLeftBrace.and(hasRightBrace);

        var result = hasLeftAndRightBraces.test("{key:value}");
        System.out.println("result = " + result);

        result = hasLeftBrace.or(hasRightBrace).test("key:value");
        System.out.println("result = " + result);

        result = hasLeftBrace.negate().test("{key:value");
        System.out.println("result = " + result);
    }

    public static void binaryOperatorInterface() {
        // a special type of functional interface
        BinaryOperator<Integer> add = (a, b) -> a + b;
        var result = add.apply(1, 2); // these primitives have to be autoboxed, so  when we're dealing with large number of primitive integers, it is more efficient to use the IntBinaryOperator interface
        System.out.println("result = " + result);

        // a, b -> a + b -> square
        Function<Integer, Integer> square = a -> a * a;
        result = add.andThen(square).apply(1, 2);
        System.out.println("result = " + result);
    }

    public static void unaryOperator() {
        // The "UnaryOperator<T>" interface extends the "Function<T,T>" interface -> takes an argument of type T and returns a value of type T

        UnaryOperator<Integer> square = n -> n * n;
        UnaryOperator<Integer> increment = n -> n + 1;

        var result = increment.andThen(square).apply(1);
        System.out.println("result = " + result);


    }
}
