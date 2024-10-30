package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MioThread extends Thread {
    Socket s;

    public MioThread(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            String frase;
            String operazione;
            String username;
            String bigliettiacquistare = "";
            int stringa;
            int biglietti = 100;
            username = in.readLine();
            System.out.println(username);
            do {
                
                operazione = in.readLine();
                if (operazione.equals("QUIT")) {
                    System.out.println(operazione);
                    System.out.println("Il client: " + username + " vuole chiudere la connessione");
                    s.close();
                    break;
                }

                switch (operazione) {
                    case "N":
                        System.out.println(operazione);
                        
                        out.writeBytes("sono ancora disponibili: " + biglietti + " biglietti." + "\n");
                        
                        break;

                    case "BUY":
                        
                        bigliettiacquistare = in.readLine();
                        stringa = Integer.parseInt(bigliettiacquistare);
                        if (biglietti >= stringa) {
                            biglietti = biglietti - stringa;
                            frase = "biglietto acquistato correttamente";

                        } else {
                            frase = "errore nell'acquisto dei biglietti";

                        }
                        System.out.println(operazione);
                        System.out.println(bigliettiacquistare);
                        out.writeBytes(frase + "\n");
                        break;
                }

            } while (true);

        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
