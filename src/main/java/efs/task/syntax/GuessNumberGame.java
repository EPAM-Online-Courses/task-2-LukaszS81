package efs.task.syntax;
import java.util.Random;
import java.util.Scanner;
public class GuessNumberGame {
    private final int M;
    int randomNumber;
    int L;
    public static void main(String[] args) {
        try {
            GuessNumberGame game;
            if (args.length > 0) {
                game = new GuessNumberGame(args[0]);
            } else {
                game = new GuessNumberGame("");
            }
            game.play();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
    public GuessNumberGame(String argument) {
        try {
            M = Integer.parseInt(argument);
        } catch (Exception e) {
            System.out.println(argument + "NIEPOPRAWNY ARGUMENT - podaj liczbę ");
            throw new IllegalArgumentException();
        }

        if (M < 1 || M > 400) {
            System.out.println(argument + "NIEPOPRAWNY ARGUMENT - dostępny zakres to <1,400>");
            throw new IllegalArgumentException();
        }
    }
    public void play() {
        Random random = new Random();
        randomNumber = random.nextInt(M) + 1;
        L = (int) Math.abs(Math.log(M)/Math.log(2)) + 1;
        System.out.println("Zgadnij liczbę z zakresu <1," + M + ">");

        try (Scanner scanner = new Scanner(System.in)) {
            int trials = 1;
            for(int i = trials; i<=L;i++){
                System.out.print("Ilość prób: [");
                for (int j = 0; j < trials; j++) {
                    System.out.print("*");
                }
                for (int z = trials; z < L; z++) {
                    System.out.print(".");
                }
                System.out.println("]");
                trials++;

                System.out.println("PODAJ:");
                int trial = 0;
                try {
                    trial = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("NIE LICZBA");
                    continue;
                }
                if (trial == randomNumber) {
                    System.out.println("TAK");
                    System.out.println("GRATULACJE, " + trials + " - wykonana ilość prób " + randomNumber);
                    return;
                } else if (trial > randomNumber) {
                    System.out.println("ZBYT WIELE");
                } else {
                    System.out.println("NIE WYSTARCZY");
                }
            }
            System.out.println("NIESTETY, limit prób wynosił (" + L + ") i został on wyczerpany " + randomNumber);
        }
    }
}