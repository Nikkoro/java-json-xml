import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//class named people with name surname and age
class People {
    String city;
    String name;
    String surname;
    String pesel;

    // constructor with name, surname and age
    public People(String city, String name, String surname, String pesel) {
        this.city = city;
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;

    }

    // create method to edit people paramters
    public void editPeople(String city, String name, String surname, String pesel) {
        this.city = city;
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
    }
}

public class Main {

    public static boolean validate(String pesel) {

        int[] wagi = { 1, 3, 7, 9, 1, 3, 7, 9, 1, 3 };

        if (pesel.length() != 11)
            return false;

        int suma = 0;

        for (int i = 0; i < 10; i++)
            suma += Integer.parseInt(pesel.substring(i, i + 1)) * wagi[i];

        int cyfraKontrolna = Integer.parseInt(pesel.substring(10, 11));

        suma %= 10;
        suma = 10 - suma;
        suma %= 10;

        return (suma == cyfraKontrolna);

    }

    // class to check if pesel exists in arraylist
    public static boolean checkPesel(ArrayList<People> people, String pesel) {
        for (People p : people) {
            if (p.pesel.equals(pesel)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ArrayList<People> people = new ArrayList<People>(20);
        while (true) {
            System.out.println("Hello World!"); // Display the string.
            // user input to create new people and add them to arraylist
            Scanner scanner = new Scanner(System.in);

            System.out.println("Podaj miasto: ");
            String city = scanner.nextLine();
            System.out.println("Podaj imie: ");
            String name = scanner.nextLine();
            System.out.println("Podaj nazwisko: ");
            String surname = scanner.nextLine();
            System.out.println("Podaj pesel: ");
            String pesel = scanner.nextLine();

            if (validate(pesel) & !checkPesel(people, pesel)) {
                System.out.println("Pesel jest poprawny");
                people.add(new People(city, name, surname, pesel));
            } else if (checkPesel(people, pesel)) {

                for (People p : people) {
                    if (p.pesel.equals(pesel)) {
                        p.editPeople(city, name, surname, pesel);
                    }
                }
                System.out.println("Pesel istnieje w bazie, dane zostaly zaktualizowane");
            } else {
                System.out.println("Pesel jest niepoprawny");
            }

            // people.add(new People(city, name, surname, pesel));
            System.out.println("Czy chcesz dodać kolejnego uzytkownika? (t/n)");
            String answer = scanner.nextLine();
            if (answer.equals("t")) {
                continue;
            } else {
                // print all people
                for (People p : people) {
                    System.out.println(p.city + " " + p.name + " " + p.surname + " " + p.pesel);
                }
                try {
                    FileWriter fileWriter = new FileWriter("people.txt");
                    for (People p : people) {
                        fileWriter.write(p.city + " " + p.name + " " + p.surname + " " + p.pesel + "\n");
                    }
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            }

        }

    }
}