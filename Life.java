import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Life extends JFrame implements ActionListener {
	public Timer timer;
	private boolean frozen;

	public Life(){
		timer = new Timer(1000, this);
		timer.setInitialDelay(1000);
		timer.setCoalesce(true);
		frozen = false;
	}

	public void startAnimation() {
		if (frozen) {
			System.out.println("aqui ando");
		} else {
			//Start (or res!
			timer.start();
		}
	}

	public void stopAnimation() {
		//Stop the animating thread.
		timer.stop();
	}

	public void actionPerformed (ActionEvent e) {
		//Advance the animation frame.
		//frameNumber++;
		System.out.println("mejor");
		//Display it.
		//repaint();
	}
}