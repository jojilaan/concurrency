package concurrency_week1;

import javax.swing.*;
import java.awt.event.*;

public class testmotor{
	Motor motor;
	JFrame f;
	JPanel p;
	JButton bStart, bStop, bForward, bReverse, bStationary, bSpeedUp;
	JLabel lStatusMotor, lCurrentSpeed, lErrorMessage, lForward;
	static final int SPEEDDIFF = 50;
	
	public testmotor()
	{
		Thread newMotor = new Motor();
		newMotor.start();
		this.motor = (Motor) newMotor;
		gui();
	}
	
	public void gui()
	{
		//JFrame Initialization
		f = new JFrame("TestMotor");
		f.setVisible(true);
		f.setSize(750, 150);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//JPanel Initialization
		p = new JPanel();
		
		//button declaration
		bStart = new JButton("Start Motor");
		bStop = new JButton("Stop Motor");
		bForward = new JButton("Go Forward");
		bReverse = new JButton("Go Reverse"); 
		bStationary = new JButton("Speed to 0 u/s");
		bSpeedUp = new JButton("Speed to 50 u/s");
		
		//label declaration
		lStatusMotor = new JLabel("Motor on: " + motor.getStatusMotor());
		lCurrentSpeed = new JLabel("Current Speed: " + motor.getCurrentSpeed());
		lForward = new JLabel("Go Backwards");
		lErrorMessage = new JLabel();
		
		//Add actions to buttons
		//Start
		bStart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				lErrorMessage.setText(null);
				motor.turnMotorOn();
				lStatusMotor.setText("Motor on: " + motor.getStatusMotor());
			}
		}); 
		//stop
		bStop.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(motor.getCurrentSpeed() != 0)
				{
					lErrorMessage.setText("Set the motor to zero before you turn off the motor");
				}
				else
				{
					motor.turnMotorOff();
					lStatusMotor.setText("Motor on: " + motor.getStatusMotor());
				}
			}
		});
		//forward
		bForward.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(motor.getStatusMotor() == true)
				{
					if(motor.getCurrentSpeed() < 0)
					{
						lErrorMessage.setText("Set speed to zero before you set the motor in forward");
					}
					else
					{
						motor.setForward();		
						lForward.setText("Going forward");
					}
				}
				else
				{
					lErrorMessage.setText("Motor isn't turned on. Please turn on the motor to go forwards or backwards");
				}
			}
		});
		
		//backwards
		bReverse.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(motor.getStatusMotor() == true)
				{
					if(motor.getCurrentSpeed() > 0)
					{
						lErrorMessage.setText("Set Speed to zero before you set the motor in reverse");
					}
					else{
						motor.setBackward();;		
						lForward.setText("Going backwards");
					} 
				}
				else
				{
					lErrorMessage.setText("Motor isn't turned on. Please turn on the motor to go forwards or backwards");
				}
			}
		});
		
		//stationary, also set speed to 0 u/s
		bStationary.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				motor.setZero();
				lCurrentSpeed.setText("Current speed: " + motor.getCurrentSpeed());
				lErrorMessage.setText("");
			}
		});
		
		//speed up with 50 u/s
		bSpeedUp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(motor.getStatusMotor() == false)
				{
					lErrorMessage.setText("Motor isn't turned on, please turn on the motor to accelerate");
				}
				else
				{
					if(motor.getStatusForward() == true)
					{
						if(motor.GoForwards(motor.getCurrentSpeed() + SPEEDDIFF))
						{
							lCurrentSpeed.setText("Current speed: " + motor.getCurrentSpeed());
						}
						else
						{
							lErrorMessage.setText("Speed is greater than 100");
						}
					}
					else
					{
						if(motor.GoBackwards(motor.getCurrentSpeed() - SPEEDDIFF))
						{
							lCurrentSpeed.setText("Current speed: " + motor.getCurrentSpeed());
						}
						else
						{
							lErrorMessage.setText("Speed is greater than -100");
						}
					}
				}
			}
		});
		//add components to panel
		p.add(bStart);
		p.add(bStop);
		p.add(lStatusMotor);
		p.add(bForward);
		p.add(bReverse);
		p.add(lForward);
		p.add(bStationary);
		p.add(lCurrentSpeed);
		p.add(bSpeedUp);
		p.add(lErrorMessage);
		
		
		//add panel to frame
		f.add(p);
	}
	public static void main(String[] args) {
		new testmotor();
	}

}
