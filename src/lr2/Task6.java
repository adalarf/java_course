package lr2;


interface ShapeInterface {
    double getArea();
    double getPerimeter();
}


class CircleFigure implements ShapeInterface {
    private double radius;

    public CircleFigure(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

}

class SquareFigure implements ShapeInterface {
    private double side;

    public SquareFigure(double side) {
        this.side = side;
    }

    public double getArea() {
        return side * side;
    }

    public double getPerimeter() {
        return 4 * side;
    }

}

class TriangleFigure implements ShapeInterface {
    private double firstSide;
    private double secondSide;
    private double thirdSide;

    public TriangleFigure(double firstSide, double secondSide, double thirdSide) {
        this.firstSide = firstSide;
        this.secondSide = secondSide;
        this.thirdSide = thirdSide;
    }

    public double getPerimeter() {
        return firstSide + secondSide + thirdSide;
    }

    public double getArea() {
        double p = getPerimeter() / 2;
        return Math.sqrt(p * (p - firstSide) * (p - secondSide) * (p - thirdSide));
    }
}


public class Task6 {
    public static void main(String[] args) {
        CircleFigure CircleFigure = new CircleFigure(10);
        SquareFigure SquareFigure = new SquareFigure(5);
        TriangleFigure TriangleFigure = new TriangleFigure(7, 6, 5);

        System.out.println("Круг");
        System.out.println("Площадь = " + CircleFigure.getArea());
        System.out.println("Периметр = " + CircleFigure.getPerimeter());

        System.out.println("Квадрат");
        System.out.println("Площадь = " + SquareFigure.getArea());
        System.out.println("Периметр = " + SquareFigure.getPerimeter());

        System.out.println("Треугольник");
        System.out.println("Площадь = " + TriangleFigure.getArea());
        System.out.println("Периметр = " + TriangleFigure.getPerimeter());
    }
}
