package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {

        Scanner scan = new Scanner(System.in);
        String frase, operazione, bigliettiacquistare, username;

        System.out.println("Client avviato!");
        Socket s = new Socket("localhost", 3000);
        System.out.println("Client connesso!");

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        DataOutputStream out = new DataOutputStream(s.getOutputStream());

        System.out.println("inserisci il tuo username: ");
        username = scan.nextLine();
        out.writeBytes(username + "\n");

        do {
            System.out.println("benvenuto nella biglietteria: " + username + " , digita cosa vuoi fare: ");
            System.out.println("--------------------");
            System.out.println("1 per chiedere la disponibilità");
            System.out.println("2 per acquistare biglietti");
            System.out.println("3 per chiudere la connessione");
            System.out.println("--------------------");

            operazione = scan.nextLine();

            if (operazione.equals("3")) {

                out.writeBytes("QUIT" + "\n");
                System.out.println("arrivederci!!!");

                break;
            }

            if (operazione.equals("1")) {
                out.writeBytes("N" + "\n");
                frase = in.readLine();
                System.out.println(frase);

            }

            if (operazione.equals("2")) {
                out.writeBytes("BUY" + "\n");
                System.out.println("quanti biglietti vuoi acquistare?");
                bigliettiacquistare = scan.nextLine();
                out.writeBytes(bigliettiacquistare + "\n");
                frase = in.readLine();
                System.out.println(frase);
            }

        } while (true);

    }
}