package nju.java;


import javax.swing.JFrame;

public final class Main extends JFrame {

    private final int OFFSET = 30;


    public Main() {
        InitUI();
    }

    public void InitUI() {
        Field field = new Field();
        add(field);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(field.getBoardWidth() + OFFSET,
                field.getBoardHeight() + 2 * OFFSET);
        setLocationRelativeTo(null);
        setTitle("My Huluwa");
    }

    public static void main(String[] args) {
    	Main ground = new Main();
        ground.setVisible(true);
    }
}