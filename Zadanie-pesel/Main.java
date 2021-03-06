import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class People {
    String city;
    String name;
    String surname;
    String pesel;

    public People(String city, String name, String surname, String pesel) {
        this.city = city;
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;

    }

    public void editPeople(String city, String name, String surname, String pesel) {
        this.city = city;
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
    }
}

public class Main {

    public static boolean validate(String pesel) {
        int[] weights = { 1, 3, 7, 9, 1, 3, 7, 9, 1, 3 };

        if (pesel.length() != 11)
            return false;

        int sum = 0;

        for (int i = 0; i < pesel.length() - 1; i++)
            sum += Integer.parseInt(pesel.substring(i, i + 1)) * weights[i];

        int checkDigit = Integer.parseInt(pesel.substring(pesel.length() - 1));
        sum %= 10;
        sum = 10 - sum;
        sum %= 10;

        return (sum == checkDigit);

    }

    // check if pesel exists in arraylist
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

            System.out.println("Czy chcesz doda?? kolejnego uzytkownika? (t/n)");
            String answer = scanner.nextLine();
            if (answer.equals("t")) {
                continue;
            } else {
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
