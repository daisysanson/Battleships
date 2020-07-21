package Controllers;
import javax.swing.*;

public class SwingBoard extends JFrame{//inheriting JFrame

    public SwingBoard(){
        JFrame f=new JFrame("Button Example");
        JButton b=new JButton("click");//create button
        b.setBounds(130,100,100, 40);

        add(b);//adding button on frame
        setSize(400,500);
        setLayout(null);
        setVisible(true);
    }

    }