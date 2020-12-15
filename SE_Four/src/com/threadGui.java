package com;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.util.Date;
import javax.swing.border.Border;

public class threadGui {

    public  JFrame frame;

    private  JTextArea textDisplayArea;
    private  JScrollPane scrollPane;
    private  JButton loadBtn;

    public void initialise(){

        frame = new JFrame("Thread GUI");
        frame.setLayout(new GridLayout(2 , 1));

        loadBtn = new JButton("To Load Data");
        Border BtnBorder = BorderFactory.createLineBorder(Color.BLACK);
        loadBtn.setBorder(BtnBorder);

        textDisplayArea = new JTextArea("Data Display Area");
        textDisplayArea.setLineWrap(true);
        scrollPane = new JScrollPane(textDisplayArea);

        loadBtn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Current Thread: "
                                + Thread.currentThread().getName());

                        SwingWorkerLoader();
                    }
                });
        frame.add(scrollPane);
        frame.add((loadBtn));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(10, 10, 250, 450);
        frame.setVisible(true);
    }

    void  SwingWorkerLoader()
    {

        textDisplayArea.setText("Waiting for thread to Load data");

        new SwingWorker<Object, Object>() {

            @Override
            protected Object doInBackground() throws Exception {


                System.out.println("Current Thread: " + Thread.currentThread().getName());


                for (int i = 0; i < 888888888; i++) {
                    new Date();
                }

                File fileForLoading = new File("SE_Four\\resources\\data.txt");
                System.out.println(("Size of file: " + fileForLoading.getAbsolutePath()));
                BufferedReader fr;
                try {
                    fr = new BufferedReader((new FileReader(fileForLoading)));
                    String text;
                    String textForFile = "";

                    while ((text = fr.readLine()) != null) {
                        textForFile += text;
                    }
                    textDisplayArea.setText((textForFile));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            }.execute();

        }

        public static void main(String[] args){

            threadGui outputPanel = new threadGui();
            outputPanel.initialise();

        }

    }

