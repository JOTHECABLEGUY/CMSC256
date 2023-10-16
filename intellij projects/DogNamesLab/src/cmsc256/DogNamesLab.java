package cmsc256;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Jordan Dube
 * Lab 8, Fung, Data Structures and OOP - Fall 2020
 */
public class DogNamesLab {
  public static void main(String[] args) throws FileNotFoundException {
    ArrayList<Dog> dogs = new ArrayList<>();
    Scanner s = new Scanner(new File("C:\\Users\\jzdub\\Desktop\\intellij projects\\DogNamesLab\\src\\cmsc256\\Dog_Names.csv"));
    while (s.hasNextLine()) {
      String n = s.nextLine();
      String[] h = n.split(",");
      dogs.add(new Dog(h[0], h[1]));
    }
    Scanner scan = new Scanner(System.in);
    System.out.println("Which Part would like to run? Enter 1, 2, or 3.");
    String answer = scan.nextLine();
    switch (answer) {
      case "1" -> {
        System.out.println("Enter a dog's name?");
        String name = scan.nextLine();
        ArrayList<String> f = new ArrayList<>();
        for (Dog d : dogs) {
          f.add(d.getName().toUpperCase());
        }
        try{
          Dog d = dogs.get(f.indexOf(name.toUpperCase()));
          System.out.println(name + " is registered " + d.getCount() + " times.");}
        catch(IndexOutOfBoundsException e){
          break;}
        scan.reset();
      }
      case "2" -> {
        ArrayList<String> dogNames = new ArrayList<>();
        for (Dog d : dogs) {
          dogNames.add(d.getName());
        }
        Collections.sort(dogNames);
        System.out.println(dogNames);
        scan.reset();
      }
      case "3" -> {
        Scanner g = new Scanner(System.in);
        int count = 0;
        Dog d1 = dogs.get((int) (Math.random() * dogs.size()));
        Dog d2 = dogs.get((int) (Math.random() * dogs.size()));
        String contin = "Y";
        int correctGuesses = 0;
        while (contin.equals("Y")) {
          count++;
          System.out.println("Which name is more popular for Anchorage dogs? (Type 1 or 2)");
          System.out.println(count + " " + d1.getName() + " " + d2.getName());
          String guess = g.next().toUpperCase();
          if (guess.equals("1") && (d1.getCount().compareTo(d2.getCount())) > 0) {
            System.out.println("Yes, that's right.");
            correctGuesses++;
          } else if (guess.equals("2") && (d1.getCount().compareTo(d2.getCount())) < 0) {
            System.out.println("Yes, that's right");
            correctGuesses++;
          } else if (guess.equals("1") && (d1.getCount().compareTo(d2.getCount())) < 0) {
            System.out.println("Nope, the more popular dog name is " + d2.getName());
          } else if (guess.equals("2") && (d1.getCount().compareTo(d2.getCount())) > 0) {
            System.out.println("Nope, the more popular dog name is " + d1.getName());
          } else if (d1.getCount().equals(d2.getCount())){
            System.out.println ("That's right, but they both have the same level of popularity");
            correctGuesses++;
          } else {
            System.out.println("Did not enter 1 or 2, please try again.");
          }
          System.out.println("Do you want to play again? (Y/N)");
          contin = g.next().toUpperCase();
          d1 = dogs.get((int) (Math.random() * dogs.size()));
          d2 = dogs.get((int) (Math.random() * dogs.size()));
          g.reset();
        }
        System.out.println("You guessed correctly " + correctGuesses + " out of " + count + " times.");
        scan.reset();
        g.close();
      }
      default -> throw new IllegalStateException("Unexpected value: " + answer);
    }
    scan.close();
    s.close();
  }
}