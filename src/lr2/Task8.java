package lr2;



class Animal {
    protected String name;
    protected int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void makeSound() {
        System.out.println("Животное издает какой-то звук");
    }
}


class Dog extends Animal {
    private String breed;

    public Dog(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
    }

    @Override
    public void makeSound() {
        System.out.println("гав");
    }

    public void fetch() {
        System.out.println(name + " (" + breed + ") бежит за мячиком");
    }
}


class Cat extends Animal {
    private String color;

    public Cat(String name, int age, String color) {
        super(name, age);
        this.color = color;
    }

    @Override
    public void makeSound() {
        System.out.println("мяу");
    }

    public void scratch() {
        System.out.println(name + " (" + color + ") точит когти о диван");
    }
}

class Bird extends Animal {
    private boolean canFly;

    public Bird(String name, int age, boolean canFly) {
        super(name, age);
        this.canFly = canFly;
    }

    @Override
    public void makeSound() {
        System.out.println("чик чирик");
    }

    public void fly() {
        if (canFly) {
            System.out.println(name + " взлетает в небо");
        } else {
            System.out.println(name + " не может летать");
        }
    }
}

// абстрактный класс, так как нет общей формулы площади
abstract class Shape {
    protected String color;

    public Shape(String color) {
        this.color = color;
    }

    public abstract double getArea();
    public abstract double getPerimeter();

    public void draw() {
        System.out.println("Рисуем фигуру цвета: " + color);
    }
}

class Circle extends Shape {
    private double radius;

    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    public double getDiameter() {
        return 2 * radius;
    }
}


class Square extends Shape {
    private double side;

    public Square(String color, double side) {
        super(color);
        this.side = side;
    }

    @Override
    public double getArea() {
        return side * side;
    }

    @Override
    public double getPerimeter() {
        return 4 * side;
    }
}


class Triangle extends Shape {
    private double firstSide, secondSide, thirdSide;

    public Triangle(String color, double firstSide, double secondSide, double thirdSide) {
        super(color);
        this.firstSide = firstSide;
        this.secondSide = secondSide;
        this.thirdSide = thirdSide;
    }

    @Override
    public double getPerimeter() {
        return firstSide + secondSide + thirdSide;
    }

    @Override
    public double getArea() {
        double p = getPerimeter() / 2;
        return Math.sqrt(p * (p - firstSide) * (p - secondSide) * (p - thirdSide));
    }
}

public class Task8 {
    public static void main(String[] args) {
        System.out.println("=== ТЕСТИРОВАНИЕ ЖИВОТНЫХ ===");
        testAnimals();

        System.out.println("\n=== ТЕСТИРОВАНИЕ ФИГУР ===");
        testShapes();
    }


    private static void testAnimals() {
        Dog dog = new Dog("Бобик", 3, "Овчарка");
        Cat cat = new Cat("Барсик", 5, "Полосатый");
        Bird bird = new Bird("Кеша", 2, true);
        Bird penguin = new Bird("Пин", 4, false);

        dog.makeSound();
        dog.fetch();
        System.out.println();

        cat.makeSound();
        cat.scratch();
        System.out.println();

        bird.makeSound();
        bird.fly();
        System.out.println();

        penguin.fly();
    }

    private static void testShapes() {
        Circle circle = new Circle("Красный", 5.0);
        Square square = new Square("Синий", 4.0);
        Triangle triangle = new Triangle("Зеленый", 3.0, 4.0, 5.0);

        circle.draw();
        System.out.printf("Площадь круга: %.2f\n", circle.getArea());
        System.out.printf("Периметр круга: %.2f\n", circle.getPerimeter());
        System.out.println("Диаметр: " + circle.getDiameter());
        System.out.println();

        square.draw();
        System.out.printf("Площадь квадрата: %.2f\n", square.getArea());
        System.out.printf("Периметр квадрата: %.2f\n", square.getPerimeter());
        System.out.println();

        triangle.draw();
        System.out.printf("Площадь треугольника: %.2f\n", triangle.getArea());
        System.out.printf("Периметр треугольника: %.2f\n", triangle.getPerimeter());
    }
}
