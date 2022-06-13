import java.util.Scanner;
import java.util.Random;

public class MatchingGame {

    static int[][] kaart = new int[4][5];
    static boolean upDown[][] = new boolean[4][5];
    static int score = 0;

    static int lifes = 10;
    static Scanner s = new Scanner(System.in);

    public static void Game() {
        Scanner start = new Scanner(System.in);
        System.out.println("Druk op S om te starten");
        String startgame = start.next();
        if (startgame.equals("s"))


            setup();

        game(upDown, kaart); // calls the game
    }


    //print the board
    public static void setup() {
        for (int i = 0; i < 4; i++) {
            for (int a = 0; a < 4; a++) {
                upDown[i][a] = false;

            }
        }
        kaart = randomizer(); //Shuffle kaart

    }

    //print the board
    public static void bordwijzen(boolean[][] upDown, int[][] kaart) {
        CountScore();
         lifesleft();
        System.out.println("       C1 C2 C3 C4 C5");
        System.out.println("---+----------------");
        for (int i = 0; i < 4; i++) {
            System.out.print(" R" + (i + 1) + " | ");
            for (int a = 0; a < 5; a++) {
                if (upDown[i][a]) {
                    System.out.print(kaart[i][a]);
                    System.out.print(" ");
                } else
                    System.out.print(" ? ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int[][] randomizer() {
        int num[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int kaart[][] = new int[4][5];
        Random random = new Random();
        int temp, t;
        for (int j = 0; j <= 20; j++) {
            for (int x = 0; x < 16; x++) { //Randomize the card order
                t = random.nextInt(1000) % 15;
                temp = num[x];
                num[x] = num[t];
                num[t] = temp;

            }
            t = 0;
            for (int r = 0; r < 4; r++) // kaart receive Numbers
            {
                for (int s = 0; s < 4; s++) {
                    kaart[r][s] = num[t];
                    t = t + 1;
                }
            }

        }
        return kaart;
    }

    //Start the Game
    public static void game(boolean[][] upDown, int[][] kaart) {
        int noDownkaart = 16;
        String matched = "Je heb een match gevonden!!";
        while (noDownkaart > 0) {
            bordwijzen(upDown, kaart);
            System.out.println("Eerste nummer!!");
            System.out.println("Voer in colum and row nummer :");
            String g1 = s.next();
            int g1x = Integer.valueOf(g1.substring(0, 1)) - 1;
            int g1y = Integer.valueOf(g1.substring(1, 2)) - 1;
            System.out.println(kaart[g1x][g1y]);

            System.out.println("Tweede nummer!!");
            System.out.println("Voer in colum and row nummer :");
            String g2 = s.next();
            int g2x = Integer.valueOf(g2.substring(0, 1)) - 1;
            int g2y = Integer.valueOf(g2.substring(1, 2)) - 1;
            System.out.println(kaart[g2x][g2y]);
            if (kaart[g1x][g1y] == kaart[g2x][g2y]) {
                System.out.println(matched);
                upDown[g1x][g1y] = true;
                upDown[g2x][g2y] = true;
                noDownkaart -= 2;
                if (kaart[g1x][g1y] == kaart[g2x][g2y]) {
                    score++;
                }


            } else {
                lifes = lifes -1;
                System.out.println("Geen match gevonden");

                if (lifes==0){
                    System.out.println("Game einde:(\n0 kansen over");
                Scanner start2 = new Scanner(System.in);
                System.out.println("Druk op r om te restarten");
                String startgame = start2.next();
                if (startgame.equals("r"))


                    setup();

                game(upDown, kaart); // calls the game

                }}
            }
            bordwijzen(upDown, kaart);
            System.out.println("Je heb gewonnen");
        }
        public static void CountScore () {
            System.out.println();
            System.out.print("Score: " + score);

        }
        public static void lifesleft () {
            System.out.println("      kansen over: " + lifes);
        }
    }



//    //shuffle the kaart
//    public static int[][] shuf() {
//        int start[] = {1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8};
//        int kaart[][] = new int[4][4];
//        Random ran = new Random();
//        int tmp, i;
//        for (int s = 0; s <= 20; s++) {
//            for (int x = 0; x < 16; x++) //randomize the card placements
//            {
//                i = ran.nextInt(100000) % 15;
//                tmp = start[x];
//                start[x] = start[i];
//                start[i] = tmp;
//            }
//        }
//        i = 0;
//
//        for (int r = 0; r < 4; r++) // put values in kaart here
//        {
//            for (int c = 0; c < 4; c++) {
//                kaart[r][c] = start[i];
//                i = i + 1;
//            }
//        }
//        return kaart;
//
//    }
//
//}

