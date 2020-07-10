package com.lanju.ui;

import com.lanju.decry.FortifyRuleDecrypter;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Author: li wei bin
 * Date: 2020-07-10 16:44
 * Description: <描述>
 */
public class CommonComponent extends JFrame {

    private JPanel jPanel2;
    private JPanel jPanel3;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JScrollPane scroll;

    private JLabel jLabel1;
    private JLabel jLabel2;

    private JTextField field1, field2;
    public static JTextArea jTextArea;
    private FortifyRuleDecrypter fortifyRuleDecrypter;
    private ActionEvent e;

    public CommonComponent() {

        init();

        this.setTitle("规则解密");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 自适应
        // frame.pack();
        this.setVisible(true);
        decrypt();
    }

    /**
     * 初始化组件
     */
    private void init() {

        //初始化四个panel对象
        JPanel jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        jPanel3 = new JPanel();

        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        // 标签
        jLabel1 = new JLabel("encrypt-rule:");
        // 文本域
        field1 = new JTextField(30);
        //按钮
        jButton1 = new JButton("选择路径");
        setComponent(jPanel1,jButton1,field1,jLabel1);

        // 标签
        jLabel2 = new JLabel("decrypt-rule:");
        // 文本域
        field2 = new JTextField(30);
        //按钮
        jButton2 = new JButton("选择路径");
        setComponent(jPanel2,jButton2,field2,jLabel2);

        //按钮
        jButton3 = new JButton("开始解密");
        jPanel3.add(jButton3);
        this.add(jPanel3);
        //下方显示信息
        jTextArea = new JTextArea(1, 1);
        //把定义的JTextArea放到JScrollPane里面去
        scroll = new JScrollPane(jTextArea);
        //分别设置垂直滚动条自动出现
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jTextArea.setLineWrap(true);
        this.add(scroll);

    }

    private void setComponent(JPanel jpanel, JButton jButton, JTextField jTextField, JLabel jLabel) {

        jpanel.add(jLabel);
        jpanel.add(jTextField);
        jpanel.add(jButton);
        this.add(jpanel);
    }


    private void decrypt() {

        //选择加密规则路径
        jButton1.addActionListener(e -> choiceDirPath(field1));
        //选择解密路径
        jButton2.addActionListener(e -> choiceDirPath(field2));

        jButton3.addActionListener(//开启一个子线程提高执行效率
                this::actionPerformed);
    }

    private void choiceDirPath(JTextField field) {
        JFileChooser fileChooser = new JFileChooser("D:\\");

        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnVal = fileChooser.showOpenDialog(fileChooser);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            field.setText(filePath);
        }
    }

    private void actionPerformed(ActionEvent e) {
        this.e = e;
        String encrytPath = field1.getText();
        String decrytPath = field2.getText();
        //开启一个子线程提高执行效率
        new Thread(() -> {
            synchronized (CommonComponent.class) {
                fortifyRuleDecrypter = new FortifyRuleDecrypter(encrytPath, decrytPath);
                fortifyRuleDecrypter.doDecrypt();
            }
        }).start();
    }
}
