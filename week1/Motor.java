package concurrency_week1;

public class Motor extends Thread{
	private static final int MAXSPEED = 100;
	private static final int MINSPEED = -100;
	private int currentspeed = 0;
	private boolean motorOn = false;
	private boolean forward = false;
	
	public Motor()
	{

	}
	
	public void run()
	{
		while(true)
		{
			try {
				Thread.sleep(1000);
				System.out.println("Running...");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public boolean turnMotorOn()
	{
		motorOn = true;
		return motorOn;
	}
	
	public boolean turnMotorOff()
	{
		motorOn = false;
		return motorOn;
	}
	
	public boolean GoForwards(int speed)
	{
		if(speed < this.currentspeed && this.forward == false)
		{
			return false;
		}
		else if(speed > MAXSPEED)
		{
			return false;
		}
		this.currentspeed = speed;
		return true;
	}
	
	public boolean GoBackwards(int speed)
	{
		if(speed > this.currentspeed && this.forward == true)
		{
			return false;
		}
		else if(speed < MINSPEED)
		{
			return false;
		}
		this.currentspeed = speed;
		return true;
	}
	
	public boolean getStatusMotor()
	{
		return this.motorOn;
	}
	
	public int getCurrentSpeed()
	{
		return this.currentspeed;
	}
	
	public boolean getStatusForward()
	{
		return this.forward;
	}
	
	public void setForward()
	{
		this.forward = true;
	}
	
	public void setBackward()
	{
		this.forward = false;
	}
	
	public void setZero()
	{
		this.currentspeed = 0;
	}
}
