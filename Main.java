


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;




class LoginPanel extends JPanel implements ActionListener{
    LoginPanel() {
    	
        setOpaque(false);
    //------------------------- top label ---------------------------------------------------------------    
        JLabel label = new JLabel("Treasure Key Game 2017");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Serif", Font.BOLD, 45));
        label.setForeground(Color.WHITE);
        add(label);
    
    //======================== ALL Button =====================================================
        JButton btn1= new JButton("Start");
        JButton btn2= new JButton("Load");
        JButton btn4= new JButton("End");
        btn1.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn2.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn4.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        btn1.setBackground(new Color(38, 38, 38));
        btn1.setForeground(Color.WHITE);
        btn1.setFocusPainted(false);
        btn1.setFont(new Font("Tahoma", Font.BOLD, 35));
        
        btn2.setBackground(new Color(38, 38, 38));
        btn2.setForeground(Color.WHITE);
        btn2.setFocusPainted(false);
        btn2.setFont(new Font("Tahoma", Font.BOLD, 35));
        
        btn4.setBackground(new Color(38, 38, 38));
        btn4.setForeground(Color.WHITE);
        btn4.setFocusPainted(false);
        btn4.setFont(new Font("Tahoma", Font.BOLD, 35));
        
        btn4.addActionListener(this);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        
  //================================== Add button and spacing to panel =============================================    
        add(Box.createVerticalStrut(10));
        add(Box.createVerticalStrut(50));
        add(Box.createVerticalStrut(50));
        add(btn1);
        add(btn2);
        add(btn4);

   //=============================== togglebutton =================================================     
        music m1= new music();
        JToggleButton tButton = new JToggleButton("Music");
        tButton.setBackground(new Color(38, 38, 38));
        tButton.setForeground(Color.WHITE);
        tButton.setFocusPainted(false);
        tButton.setFont(new Font("Tahoma", Font.BOLD, 35));
        tButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        ItemListener itemListener = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                int state = itemEvent.getStateChange();
                if (state == ItemEvent.SELECTED) {
                   // System.out.println("Selected"); // show your message here
                    m1.playSound();
                } 
                else {
                   // System.out.println("Deselected"); // remove your message
                    m1.closeSound();
                }
            }
        };
        tButton.addItemListener(itemListener);
        add(tButton);
       

    }
    
   //============================================= Action Event when button clickded =========================================
     public void actionPerformed(ActionEvent e){
        	if (e.getActionCommand().equals("End")){
        		
        		 
        		System.exit(0);
        	}
        	if (e.getActionCommand().equals("Start")){
        		
       		 
        		Game keyCollectorGame = Game.getInstance();
        	}
        	if (e.getActionCommand().equals("Load")){
        		
          		 
        		Game keyCollectorGame = Game.getInstance();
        		try {
					keyCollectorGame.loadGame();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}

    }
}


public class Main extends JFrame{
	
    public static void main(String args[]) {
        Backgroundpanel bgPanel = new Backgroundpanel();
        bgPanel.setLayout(new BorderLayout());
        LoginPanel lpanel= new LoginPanel();
        lpanel.setLayout(new GridLayout(10,4,5,5));
        bgPanel.add(lpanel, BorderLayout.CENTER);
        bgPanel.setLayout(new BoxLayout(bgPanel, BoxLayout.Y_AXIS));


       
        Main t = new Main();
        t.setTitle("OOAD Assigment - Tiles Treasure Game");
        t.setContentPane(bgPanel);
        t.setDefaultCloseOperation(EXIT_ON_CLOSE);
        t.setSize(750, 550);
        t.setVisible(true);
    }
}

