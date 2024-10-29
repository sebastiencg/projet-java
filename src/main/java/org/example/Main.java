package org.example;

import Model.Joke;
import org.json.JSONObject;

import javax.swing.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //ShowGameWindow();
        ShowFetchWindow();
    }

    public static void ShowGameWindow() {
        JFrame frame = new JFrame("Devine la bonne option !");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Création des boutons
        JButton button1 = new JButton("Option 1");
        JButton button2 = new JButton("Option 2");
        JButton button3 = new JButton("Option 3");

        // Instance de Random pour générer le numéro correct
        Random rand = new Random();
        int correctOption = rand.nextInt(3) + 1;

        // Ajout d'une action aux boutons avec vérification
        button1.addActionListener(e -> {
            if (correctOption == 1) {
                JOptionPane.showMessageDialog(frame, "Bravo ! Option 1 est la bonne réponse !");
            } else {
                JOptionPane.showMessageDialog(frame, "Dommage ! Option 1 n'est pas la bonne réponse.");
            }
        });

        button2.addActionListener(e -> {
            if (correctOption == 2) {
                JOptionPane.showMessageDialog(frame, "Bravo ! Option 2 est la bonne réponse !");
            } else {
                JOptionPane.showMessageDialog(frame, "Dommage ! Option 2 n'est pas la bonne réponse.");
            }
        });

        button3.addActionListener(e -> {
            if (correctOption == 3) {
                JOptionPane.showMessageDialog(frame, "Bravo ! Option 3 est la bonne réponse !");
            } else {
                JOptionPane.showMessageDialog(frame, "Dommage ! Option 3 n'est pas la bonne réponse.");
            }
        });

        // Création d'un panel pour contenir les boutons
        JPanel panel = new JPanel();
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);

        // Ajout du panel au cadre
        frame.getContentPane().add(panel);

        // Rendre le cadre visible
        frame.setVisible(true);
    }

    public static void ShowFetchWindow(){
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://api.chucknorris.io/jokes/random"))
                    .GET()
                    .build();

            // Envoi de la requête et réception de la réponse
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


            JFrame frame = new JFrame("response json ");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JSONObject jsonResponse = new JSONObject(response.body());

            String jokeValue = jsonResponse.getString("value");

            Joke joke = new Joke();



            joke.setValue(jokeValue);


            JOptionPane.showMessageDialog(frame, joke.getValue());


            frame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}