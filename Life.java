import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Life extends JFrame implements ActionListener
{
	public Timer timer;
	private Pet currentPet;

	public Life(Pet currentPet)
	{
		timer = new Timer(2000, this);
		//timer.setInitialDelay(1000);
		timer.setCoalesce(true);
		this.currentPet = currentPet;
	}

	public void startAnimation()
	{
		timer.start();
	}

	public void stopAnimation()
	{
		//Stop the animating thread.
		timer.stop();
	}

	public void actionPerformed (ActionEvent e)
	{
		currentPet.life();
		System.out.println(Arrays.toString(currentPet.status));
	}
}