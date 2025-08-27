package main;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Cracky Town 2D");

        GamePanel gamePanel = new GamePanel();
        gamePanel.setUpGame(); //wurde verschoben als Bugfix, weil window.setVisible sonst zu früh painted
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }

}

//Hier werden ab jetzt auch noch Ideen und so in Textform gespeichert, weil es grad praktisch ist es auch im Code zu machen
// Namensideen: (nicht AI generiert!!)
// 4-Buchstaben-Namen-Ideen: Wold, Alto, Nout, Chad, Nibs, Tygs, Mlik, Ghik
// weitere: Gango, Brotis, Schepp