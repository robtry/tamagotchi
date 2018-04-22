import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader; 

public class InterfazMenu extends JFrame implements KeyListener
{
	private static final long serialVersionUID = 1L;

	//NO CAMBIAR LAS DIMENSIONES!
	private final short largo = 450; //35 cuadritos || filas
	private final short ancho = 350; //35 cuadritos || columnas
	private JLabel instructionTag, pressedButtonTag;
	private JPanel containerTags, backgorund;

	public InterfazMenu()
	{
		setSize(ancho, largo);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("!pet");
		setBackground(new Color(193, 205, 172));

		backgorund = new JPanel();
		//containerOptions.setBackground(new Color(160, 178, 129));
		backgorund.setBackground(new Color(193, 205, 172));
		containerTags = new JPanel();
			containerTags.setBackground(new Color(193, 205, 172));
			instructionTag = new JLabel("Choose a kind of pet");
			pressedButtonTag = new JLabel("Press Next");
			containerTags.setLayout(new GridLayout(1,2));
		containerTags.add(instructionTag);
		containerTags.add(pressedButtonTag);

		add(backgorund);
		add(containerTags, BorderLayout.SOUTH);

		addKeyListener(this);
		setFocusable(true);

		setVisible(true);
	}

	public void paint(Graphics g)
	{
		//ya existe un pet hay que dibujarlo como estaba para continuar

		BufferedReader br = null;
		String currentLine;
		String fileToDraw;
		byte ifilas = 5;
		String[] columns;

		try
		{
			super.paint(g);

			//todo el pet
			fileToDraw = "draws/menu/first.txt";
			br = new BufferedReader(new FileReader(fileToDraw));

			while ((currentLine = br.readLine()) != null)
			{
				columns = currentLine.split(" ");
				for(byte j = 0; j < columns.length; j++)
				{
					if(columns[j].equals("0"))
						g.setColor(new Color(160, 178, 129));
					else
						g.setColor(Color.black);
					//drawRect(x,y,width,heigth);
					g.fillRect (10*j, 10*ifilas+24, 10,10);

					g.setColor(Color.black);
					g.drawRect (10*j, 10*ifilas+24, 10,10);
				}
				ifilas++;
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (br != null)
				br.close();
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}

	}


	@Override
	public void keyTyped(KeyEvent e) { }

	@Override
	public void keyPressed(KeyEvent e)
	{

		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			System.out.println("derecha");
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			System.out.println("izquierda");
		}


	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if( e.getKeyCode() == KeyEvent.VK_UP)
		{
			System.out.println("entrando");
		}
		else  if( e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			System.out.println("saliendo");
		}
	}
}
