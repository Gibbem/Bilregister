package com.theslof;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final ArrayList<Car> cars = new ArrayList<Car>();
    private static final ArrayList<Person> people = new ArrayList<Person>();
    private static final ArrayList<String> COLORS = new ArrayList<String>() {{
        add("Svart");
        add("Blå");
        add("Grön");
        add("Röd");
        add("Vit");
    }};
    private static final ArrayList<String> MENU_ITEMS = new ArrayList<String>() {{
        add("Skriv ut registret");
        add("Lägg till person");
        add("Lägg till bil");
        add("Ändra person");
        add("Ändra bil");
        add("Avsluta");
    }};

    public static void main(String[] args) {
        people.add(new Person("Tripp", 20));
        people.add(new Person("Trapp", 19));
        people.add(new Person("Trull", 18));
        people.add(new Person("Kålle", 43));
        people.add(new Person("Ada", 47));

        cars.add(new Car(COLORS.get((int)(Math.random() * COLORS.size())), people.get((int)(Math.random() * people.size()))));
        cars.add(new Car(COLORS.get((int)(Math.random() * COLORS.size())), people.get((int)(Math.random() * people.size()))));
        cars.add(new Car(COLORS.get((int)(Math.random() * COLORS.size())), people.get((int)(Math.random() * people.size()))));
        cars.add(new Car(COLORS.get((int)(Math.random() * COLORS.size())), people.get((int)(Math.random() * people.size()))));
        cars.add(new Car(COLORS.get((int)(Math.random() * COLORS.size())), people.get((int)(Math.random() * people.size()))));
        cars.add(new Car(COLORS.get((int)(Math.random() * COLORS.size())), people.get((int)(Math.random() * people.size()))));

        int menuSelection;
        while (true) {
            menuSelection = arrayMenu(MENU_ITEMS);

            switch (menuSelection) {
                case 0:
                    printRegistry();
                    break;
                case 1:
                    addPerson();
                    break;
                case 2:
                    addCar();
                    break;
                case 3:
                    changePerson();
                    break;
                case 4:
                    changeCar();
                    break;
                case 5:
                    return;
            }
        }
    }

    private static void changeCar() {
        if (cars.size() == 0) {
            System.out.println("Det finns inga bilar registrerade!");
            return;
        }

        System.out.println("Vilken bil vill du ändra? ");
        int choice = arrayMenu(cars);
        Car car = cars.get(choice);

        System.out.print("Vem är den nya ägaren? ");
        choice = arrayMenu(people);
        Person owner = people.get(choice);
        System.out.print("Vilken färg har bilen? ");
        choice = arrayMenu(COLORS);
        String color = COLORS.get(choice);

        car.setOwner(owner);
        car.setColor(color);
    }

    private static void changePerson() {
        if (people.size() == 0) {
            System.out.println("Det finns inga ägare registrerade!");
            return;
        }

        System.out.println("Vem är ägaren du vill ändra? ");
        int choice = arrayMenu(people);
        Person owner = people.get(choice);

        Scanner input = new Scanner(System.in);

        System.out.print("Vad ska personen heta? ");
        String name = input.next();
        System.out.print("Hur gammal är personen? ");
        int age = input.nextInt();

        owner.setAge(age);
        owner.setName(name);
    }

    private static void addCar() {
        if (people.size() == 0) {
            System.out.println("Det finns inga ägare registrerade!");
            return;
        }

        System.out.println("Lägger till ny bil!\nVem är ägaren? ");
        int choice = arrayMenu(people);
        Person owner = people.get(choice);

        System.out.print("Vilken färg har bilen? ");
        choice = arrayMenu(COLORS);
        String color = COLORS.get(choice);

        cars.add(new Car(color, owner));

    }

    private static void addPerson() {
        Scanner input = new Scanner(System.in);

        System.out.println("Lägger till ny person!\nVad heter personen? ");
        String name = input.next();
        System.out.print("Hur gammal är personen? ");
        int age = input.nextInt();

        people.add(new Person(name, age));
    }

    private static void printRegistry() {
        if(cars.size() == 0){
            System.out.println("Registret är tomt!");
        }
        for (Car c : cars) {
            System.out.println(c);
        }
        System.out.println("");
    }

    private static int arrayMenu(ArrayList menuList) {
        int i = 1;
        for (Object item : menuList) {
            System.out.println("(" + i++ + ") " + item);
        }
        int choice;
        System.out.print("Välj ett alternativ: ");
        do {
            choice = inputInt()-1;
        } while (choice < 0 || choice >= menuList.size());

        return choice;
    }

    private static int inputInt() {
        Scanner input = new Scanner(System.in);

        int i = 0;
        boolean waiting = true;

        do {
            try {
                i = input.nextInt();
                waiting = false;
            } catch (InputMismatchException e) {
                input.next();
            }
        } while (waiting);

        return i;
    }
}
