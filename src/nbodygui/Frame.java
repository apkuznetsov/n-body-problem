package nbodygui;

import nbody.Coords;
import nbody.NbodySolvers;
import nbody.NbodySolver;

import javax.swing.*;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class Frame extends JFrame {

    public Frame() {
        super(Panels.TITLE);
        init();
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            Frame frame = new Frame();
            frame.setVisible(true);
        });
    }

    private void initGui() {

        Coords[] coords = new Coords[NbodySolvers.MIN_BODIES_NUM];
        int x, y;
        Random random = new Random();

        for (int i = 0; i < coords.length; i++) {
            x = Math.abs(random.nextInt()) % Panels.WIDTH;
            y = Math.abs(random.nextInt()) % Panels.HEIGHT;
            coords[i] = new Coords(x, y);
        }

        final NbodySolver solver = new NbodySolver(coords, NbodySolvers.MIN_DELTA_TIME);
        final Panel panel = new Panel(solver);
        add(panel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Timer timer = panel.timer();
                timer.stop();
            }
        });

        setSize(Panels.WIDTH, Panels.HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
