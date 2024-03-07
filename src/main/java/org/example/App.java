package org.example;
public class App {


    public static int add(int a, int b) {
        return a + b;
    }

    public static int subtract(int a, int b) {
        return a - b;
    }

    public static void main(String[] args) {
        int sum = add(5, 3);
        int difference = subtract(10, 4);

        System.out.println("The sum is: " + sum);
        System.out.println("The difference is: " + difference);
    }
}
