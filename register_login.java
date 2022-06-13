import jdk.dynalink.beans.StaticClass;

import java.io.Console;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;


public class register_login {
    static final String DB_URL = "jdbc:mariadb://localhost:3306/bp_opdracht"; // mariadb location
    static final String USER = "root"; // mariadb username
    static final String PASS = "$hanielG"; //mariadb password

    //public static final String RED = "\u001B[31m"; // colortest


    public static void main(String[]arg) throws SQLException, InterruptedException {
        // memory_game memory_gameObject= new memory_game();
        // Open a connection
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); // connection
        //Start
        Scanner start = new Scanner(System.in); //scanner
        System.out.println("Welcome bij de kaart matching game\n");
        System.out.println("Druk op ENTER om starten\n");
        String enter = start.nextLine();
        Boolean exitStatus = false; //if true einde code (Process finished with exit code 0)

        //choices
//        Scanner choices = new Scanner(System.in);
        while (!exitStatus) {
            System.out.println("\nDruk op 1 om account te maken\nDruk op 2 om in te loggen\nDruk op 3 om jouw account te updaten\nDruk op q om de game te stoppen");
            char reg_log = start.next().charAt(0);
            switch (reg_log) {  //switch zelfde als if/else if/else
                case '1' -> { //char
                    //register
                    Scanner register = new Scanner(System.in); //scanner voor register
                    System.out.println("Voornaam invoeren: ");
                    String v = register.nextLine();
                    System.out.println("Achternaam invoeren: ");
                    String a = register.next();
                    System.out.println("Geboortedatum invoeren: ");
                    String g = register.next();
//                    try {
//                        Date date1 = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(g);
//                    } catch (ParseException e) {
//                        System.out.println("Juiste geboortedatum format is Year-Month-Day");}
                    System.out.println("Usernaam invoeren: ");
                    String u = register.next();
                    PreparedStatement usercheck = conn.prepareStatement("SELECT username,password FROM users where username = ?"); //sql check username
                    usercheck.setString(1, u);
                    ResultSet rs = usercheck.executeQuery(); //usernaam check als het voorkomt
                    if (rs.next()) {
                        System.out.printf("\nusernaam %s komt al voor in systeem \n", u); //als de usernaam voorkomt in de database krijgt u deze message
                    } else {

                        System.out.println("Password invoeren: ");
                        String p = register.next();
                        Statement stmt = conn.createStatement();
                        String sql = "insert into users (voornaam,achternaam,geboortedatum,username,password) VALUES(" + "\"" + v + "\"" + "," + "\"" + a + "\"" + "," + "\"" + g + "\"" + "," + "\"" + u + "\"" + "," + "\"" + p + "\"" + ")"; //velden aangeven waar u de data wilt invoeren
                        stmt.executeUpdate(sql);

                        System.out.println("Account created!"); //message als je account succesvol gemaakt is

                    }


                }
                case '2' -> { //char else if(reg_log=='2')
                    //Login
                    Scanner login = new Scanner(System.in); // scanner login
                    System.out.println("Username invoeren: ");
                    String lu = login.nextLine();
                    PreparedStatement usercheck = conn.prepareStatement("SELECT username,password FROM users where username = ?"); // sql query
                    usercheck.setString(1, lu); //usercheck kijken of de username voorkomt
                    ResultSet rs = usercheck.executeQuery();
                    if (!rs.next()) {
                        System.out.printf("\nGebruiker %s is niet gevonden\n", lu); // message als je usernaam niet voorkomt in de database
                    } else {
                        System.out.println("Password invoeren: ");
                        String lp = login.next();
                        String gu = rs.getString("username"); //get usernaam
                        String gp = rs.getString("password"); //get password
                        if ((lu.equals(gu)) && (lp.equals(gp))) {
                            Scanner l2 = new Scanner(System.in);
                            System.out.println("\nLogin Successfull\n"); // message als login succesvol is
                           MatchingGame mg = new MatchingGame();
                           mg.Game();

                        } else {
                            System.out.println("\nverkeerde Username of Password\n"); //message als je login failed
                        }

                    }
                }
                case '3' -> {

                    Scanner edit = new Scanner(System.in); //scanner voor edit account
                    System.out.println("Juiste usernaam en passoword invoeren\nom uw accout te kunnen editen!!\n");
                    System.out.println("Usernaam invoeren");
                    String ue = edit.nextLine();
                    PreparedStatement usercheck = conn.prepareStatement("SELECT username,password FROM users where username = ?"); // sql query
                    usercheck.setString(1, ue); //usercheck kijken of de username voorkomt
                    ResultSet rs = usercheck.executeQuery();
                    if (!rs.next()) {
                        System.out.printf("\nGebruiker %s is niet gevonden\n", edit); // message als je usernaam niet voorkomt in de database
                    } else {
                        System.out.println("Password invoeren: ");
                        String pe = edit.next();
                        String ue2 = rs.getString("username"); //get usernaam
                        String pe2 = rs.getString("password"); //get password
                        if ((ue.equals(ue2)) && (pe.equals(pe2))) {
                            System.out.println("\nLogin Successvol\n");

                            Scanner update = new Scanner(System.in);
                            System.out.println("New voornaam invoeren");
                            String v2 = update.nextLine();
                            System.out.println("New achternaam invoeren: ");
                            String a2 = update.next();
                            System.out.println("New geboortedatum invoeren: ");
                            String g2 = update.next();
                            System.out.println("New usernaam invoeren: ");
                            String u2 = update.next();
                            System.out.println("New password invoeren: ");
                            String p2 = update.next();


                            try {
                                PreparedStatement preparedStmt = conn.prepareStatement("UPDATE users SET voornaam = ?  ,achternaam = ?,geboortedatum = ? ,username = ? ,password=  ? WHERE username = " + "\"" + ue2 + "\"" + "");


                                preparedStmt.setString(1, v2);
                                preparedStmt.setString(2, a2);
                                preparedStmt.setString(3, g2);
                                preparedStmt.setString(4, u2);
                                preparedStmt.setString(5, p2);
                                preparedStmt.executeUpdate();
                                System.out.println("Account geupdate");
                            } catch (SQLException e) {
                                System.out.println("Account update unsuccesvol");
                            }
                        } else {
                            System.out.println("\nverkeerde Username of Password\n");
                        }
                    }

                }

                case 'q' -> { //else(reg_log=='q')
                    exitStatus = true; //als je q druk gaat je app stoppen (Process finished with exit code 0)
                }
            }
        }
    }
}












