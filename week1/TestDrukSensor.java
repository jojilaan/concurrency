package concurrency_week1;

import javax.swing.*;
import java.awt.event.*;

public class TestDrukSensor {
	DrukSensor d;
	JFrame f;
	JPanel p;
	JButton bButton;
	JLabel lStatusButton;
	
	public TestDrukSensor()
	{
		Thread newDrukSensor = new DrukSensor();
		newDrukSensor.start();
		this.d = (DrukSensor) newDrukSensor;
		gui();
	}
	
	public void gui()
	{
		//JFrame Initialization
		f = new JFrame("TestMotor");
		f.setVisible(true);
		f.setSize(150, 100);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		p = new JPanel();
		
		bButton = new JButton("DrukSensor");
		
		lStatusButton = new JLabel(""+d.getButtonStatus());
		
		bButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				d.pressButton();
				lStatusButton.setText("" + d.getButtonStatus());
			}
		}); 
		p.add(bButton);
		p.add(lStatusButton);
		
		f.add(p);
	}
	public static void main(String[] args) {
		new TestDrukSensor();
	}

}
