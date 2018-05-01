import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Random;

public class InterfazMenu extends JFrame implements KeyListener
{
	private static final long serialVersionUID = 1L;

	//NO CAMBIAR LAS DIMENSIONES!
	private final short largo = 450; //35 cuadritos || filas
	private final short ancho = 350; //35 cuadritos || columnas
	private int noSecreto;
	private JLabel instructionTag, possibleActionTag;
	private JPanel containerTags, backgorund;
	private final String[] menu =
							   {
								"tuto",
								"SLEEPY",
								"PLAYER",
								"DELICATE",
								"LOVELY"
							   };
	private byte currentLayout;

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
			possibleActionTag = new JLabel("Press Next");
			containerTags.setLayout(new GridLayout(1,2));
		containerTags.add(instructionTag);
		containerTags.add(possibleActionTag);

		add(backgorund);
		add(containerTags, BorderLayout.SOUTH);

		addKeyListener(this);
		setFocusable(true);
		setLocationRelativeTo(null);

		 currentLayout = 0;
	}

	public void paint(Graphics g)
	{
		BufferedReader br = null;
		String currentLine;
		String fileToDraw;
		byte ifilas = 5;
		String[] columns;

		try
		{
			super.paint(g);

			//todo el pet
			fileToDraw = "draws/menu/"+menu[currentLayout]+".txt";
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
			if(currentLayout == menu.length-1)
				currentLayout = 1;
			else
				currentLayout++;

			repaint();
			possibleActionTag.setText("Press Up to Select");
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			if(currentLayout == 1 || currentLayout == 0)
				currentLayout = (byte)(menu.length-1);
			else
				currentLayout--;

			repaint();
			possibleActionTag.setText("Press Up to Select");
		}


	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if( e.getKeyCode() == KeyEvent.VK_UP)
		{
			if(currentLayout != 0)
			{
				//System.out.println("intanciar y todo");
				possibleActionTag.setText("Write a name");
				instructionTag.setText("This was chose");
				String name;
				name = JOptionPane.showInputDialog("Name of your Tamagotchi","A simple name");
				if(name == null)
				{
					possibleActionTag.setText("Press Up to Select");
					instructionTag.setText("Choose a kind of pet");
				}
				else
				{
					//crear carpeta que tendra
					File dir = new File("current");
					Encriptador encriptador = new Encriptador();
					dir.mkdir();
					String kind = "";

					switch(currentLayout)
					{
						case 1:
							//System.out.println("normal");
							kind = "SLEEPY";
						break;
						case 2:
							//System.out.println("delicado");
							kind = "PLAYER";
						break;
						case 3:
							//System.out.println("dormilon");
							kind = "DELICATE";
						case 4:
							//System.out.println("dormilon");
							kind = "LOVELY";
						break;
					}

					File data = new File("current/data.txt");
					File inst = new File("current/showhelp.txt");
					Random rndm = new Random();

					try
					{
						inst.createNewFile();
						data.createNewFile();
						FileWriter escritor = new FileWriter(data);
						PrintWriter pw = new PrintWriter(escritor);
						int sleep, hungry, health, love, funny, discipline, energy;
						sleep = rndm.nextInt(50)+50;
						hungry = rndm.nextInt(50)+50;
						health = rndm.nextInt(50)+50;
						love = rndm.nextInt(50)+50;
						funny = rndm.nextInt(50)+50;
						discipline = rndm.nextInt(50)+50;
						energy = rndm.nextInt(50)+50;
						String valores = kind+"\n"+name+"\n"+"0\n"+sleep+"\n"+hungry+"\n"+health+"\n"+love+"\n"+funny+"\n"+discipline+"\n"+energy;
						valores = encriptador.encriptar(valores);
						noSecreto = encriptador.darNumeroSecreto();

						pw.print(noSecreto + " ");
						pw.print(valores);



						escritor.close();
						Pet currentPet = new Tamagochi(name, 0, sleep, hungry, health, love, funny, discipline, energy, kind);
						InterfazGame g = new InterfazGame(currentPet);
						g.setVisible(true);
						dispose();
						g.showInstructions();
					}
					catch(IOException ex)
					{
						ex.printStackTrace();
					}
				}
			}
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			dispose();
		}
	}

	public void showInstructions()
	{
		JOptionPane.showMessageDialog(null, "Please review always suggestions down");
	}

}
