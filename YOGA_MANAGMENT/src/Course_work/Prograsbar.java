package Course_work;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Prograsbar extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Prograsbar frame = new Prograsbar();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Prograsbar() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 599, 430);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setForeground(new Color(0, 0, 128));
        panel.setLayout(null);
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(0, 0, 584, 391);
        contentPane.add(panel);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\eclips\\e_slip\\YOGA_MANAGMENT\\photo\\09.png"));
        lblNewLabel.setBounds(10, 63, 564, 292);
        panel.add(lblNewLabel);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setBounds(10, 366, 564, 14);
        panel.add(progressBar);

        JLabel lblNewLabel_1 = new JLabel("YOGA FITNESS");
        lblNewLabel_1.setBackground(new Color(0, 0, 128));
        lblNewLabel_1.setForeground(new Color(0, 0, 128));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblNewLabel_1.setBounds(211, 11, 228, 40);
        panel.add(lblNewLabel_1);

        
        
        
        Timer t = new Timer(100, new ActionListener() {
            int p = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (p < 100) {
                	
                    p++;
                    
                    progressBar.setValue(p);
                } else {
                   
                	
                	
                    ((Timer) e.getSource()).stop();

                  
                    Login l = new Login();
                    
                    l.setVisible(true);

                   
                    dispose();
                }
            }
        });
        t.start(); 
    }
}




