package concurrency_week1;

public class DrukSensor extends Thread{
	private boolean pressed;
	
	public DrukSensor()
	{
		this.pressed = false;
	}
	public void run()
	{
		
	}
	public void pressButton()
	{
		this.pressed = !this.pressed;
	}
	
	public boolean getButtonStatus()
	{
		return this.pressed;
	}
}
