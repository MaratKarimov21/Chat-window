package ru.smak.net;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Function;

public class GraphicIO extends JFrame implements UI {
    private JLabel lbl;
    private JTextField tf;
    private JButton btn;
    private JScrollPane sp;
    private JTextArea ta;
    public GraphicIO(){
        setSize(600, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        lbl = new JLabel("");
        tf = new JTextField("Представьтесь, пожалуйста");
        btn = new JButton("Отправить");
        ta = new JTextArea();
        sp = new JScrollPane(ta);
        GroupLayout gl = new GroupLayout(getContentPane());
        setLayout(gl);

        gl.setHorizontalGroup(
                gl.createSequentialGroup()
                        .addGap(8)
                        .addGroup(gl.createParallelGroup()
                                .addComponent(lbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                                .addGroup(
                                        gl.createSequentialGroup()
                                                .addComponent(tf, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                                                .addGap(4)
                                                .addComponent(btn, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                )
                                .addComponent(sp, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                        )
                        .addGap(8)
        );
        gl.setVerticalGroup(
                gl.createSequentialGroup()
                        .addGap(8)
                        .addComponent(lbl, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                        .addGap(8)
                        .addGroup(
                                gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(tf, 27, GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(8)
                        .addComponent(sp, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                        .addGap(8)
        );

        this.setVisible(true);
    }

    @Override
    public void displayMessage(String message) {
        ta.append(message + "\n");
    }

    @Override
    public void displaySystemMessage(String message) {
        lbl.setText(message);
    }

    @Override
    public void startSending(Function<String,Void> send) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                send.apply(tf.getText());
                lbl.setText("");
                tf.setText("");
            }
        });
    }
}