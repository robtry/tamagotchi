import java.awt.*; //para las funciones gráficas
import javax.swing.*; //para las funciones gráficas
import javax.imageio.ImageIO; //imagenes
import java.io.File; // leer imagenes
import java.io.FileReader; // leer archivos
import java.io.BufferedReader; // leer archivos
import java.awt.image.BufferedImage; //leer imagenes
import java.io.IOException; //excepciones
import java.util.ArrayList; // meter los botones
import java.awt.event.KeyListener;//cuando el usuario presione teclas
import java.awt.event.KeyEvent;//eventos de las teclas
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

public class InterfazGame extends JFrame implements KeyListener, ActionListener
{
	private static final long serialVersionUID = 1L;

	//NO CAMBIAR LAS DIMENSIONES!
	private final short largo = 450; //35 cuadritos || filas
	private final short ancho = 350; //35 cuadritos || columnas
	private Random rand;
	private JPanel containerOptions, containerTags;
	private JLabel selectedButtonTag, descriptionTag;
	private ArrayList<JButton> optionBtns= new ArrayList<JButton>();
	private final String[] images = {
										"eat", //eatBtn[0]
										"bulb", //sleepBtn[1]
										"play", //playBtn [2]
										"syringe", //medicineBtn [3]
										"shower", //showerBtn [4]
										"talk", //talkBtn [5]
										"measurer", //statusBtn [6]
										"help" // helpBtn [7] "suggestion" //sugestButon[8]
									 };
	private byte currentBtn;
	private Pet currentPet;
	private Timer life;
	private Timer during;
	private boolean drawPet, playing, bussy;
	private int op, countTime;
	private String petToDraw, statusToDraw, prevPet, prevStatus, menuToDraw;
	private final int timeUnit = 3000; //milisegs


	public InterfazGame(Pet currentPet) // constructor
	{
		this.currentPet = currentPet;

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// para que se termine la execución cuando se cierra
		setResizable(false);// desabilita la opción de cambiar tamaño
		setSize(ancho, largo);// establecer tamaño
		//setLayout(new FlowLayout());//flujo en el que ordena elementos, esta comentado para que el panel de abajo coloree todo
		setTitle(currentPet.getName()); //titulo de la ventana
		//agregar los botones
		BufferedImage img;
		containerOptions = new JPanel();
		//containerOptions.setBackground(new Color(160, 178, 129));
		//containerOptions.setBackground(new Color(193, 205, 172));
		containerOptions.setBackground(Color.WHITE);
		containerOptions.setLayout(new FlowLayout());
			for(int i = 0; i < images.length; i++) // 9 botones
			{
				optionBtns.add(new JButton());
				optionBtns.get(i).setPreferredSize(new Dimension(30, 30));
				optionBtns.get(i).setEnabled(false);
				optionBtns.get(i).setBorder(null);
				optionBtns.get(i).setFocusPainted(false);
				optionBtns.get(i).setContentAreaFilled(false);
				containerOptions.add(optionBtns.get(i));
				try
				{
					img = ImageIO.read(new File("draws/general/buttons/"+images[i]+".png"));
					optionBtns.get(i).setIcon(new ImageIcon(img));
				}
				catch (IOException ex)
				{
					System.out.println(ex);
				}
			}

		//agregar etiquetas de abajo
		containerTags = new JPanel();
			//containerTags.setBackground(new Color(193, 205, 172)); // para que sea igual que arriba
			containerTags.setBackground(Color.WHITE);
			selectedButtonTag = new JLabel();
			descriptionTag = new JLabel();
			containerTags.setLayout(new GridLayout(1,2));//ordenar 1 fila * 2 columnas
		containerTags.add(selectedButtonTag);
		containerTags.add(descriptionTag);

		currentBtn = 0;
		optionBtns.get(currentBtn).setEnabled(true);
		selectedButtonTag.setText(images[currentBtn]);

		add(containerOptions);
		add(containerTags, BorderLayout.SOUTH);

		addKeyListener(this);// para que lea desde el teclado
		setFocusable(true);//para que el panel sea quien lee las teclas
		setLocationRelativeTo(null);

		rand = new Random();

		drawPet = true;
		bussy = false;
		playing = false;
		op = 0;
		countTime = 0;

		statusToDraw = "draws/general/status/"+currentPet.getStatus()+".txt";
		petToDraw = "draws/main/"+currentPet.getMellowing()+".txt";
		prevPet = petToDraw;
		prevStatus = statusToDraw;

		life = new Timer(timeUnit, new ActionListener() {
			@Override
				public void actionPerformed(ActionEvent e) {
					currentPet.life();
					if(!currentPet.isAlive()) keepPlaying();
					else {
						System.out.println(Arrays.toString(currentPet.status));
						checkChanges();
					}
					currentPet.save();
			}
		});

		during = new Timer(100, this);
		if(!currentPet.isAlive()) keepPlaying();
		life.start();
	}

	public void paint(Graphics g)
	{
		//ya existe un pet hay que dibujarlo como estaba para continuar

		BufferedReader br = null;
		String currentLine;
		byte ifilas = 5;
		String[] columns;

		try
		{
			super.paint(g);
			if(drawPet)
			{
				br = new BufferedReader(new FileReader(statusToDraw));
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

				br = new BufferedReader(new FileReader(petToDraw));
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
			else
			{
				br = new BufferedReader(new FileReader(menuToDraw));
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
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			if(!bussy)
			{
					byte temp = currentBtn;
					if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					{
						if(currentBtn == images.length-1)
						currentBtn = 0;
						else
						currentBtn++;
					}
					else
					{
						if(currentBtn == 0)
						currentBtn = (byte)(images.length-1);
						else
						currentBtn--;
					}
					optionBtns.get(temp).setEnabled(false);
					optionBtns.get(currentBtn).setEnabled(true);
					selectedButtonTag.setText(images[currentBtn]);
			}
			else if(bussy && currentBtn == 0) {
				selectedButtonTag.setText("Wait for him to finish!");
			}
			else if(playing)
			{
					if (e.getKeyCode() == KeyEvent.VK_LEFT)
					{
						if(op == 0)
						{
							descriptionTag.setText("He lost!f");
						}
						else
						{
							descriptionTag.setText("He won!");
						}
					}
					else
					{
						if(op == 0)
						{
							descriptionTag.setText("You lost, loser!");
						}
						else
						{
							descriptionTag.setText("You won, lucky guy!");
						}
					}
					playing = false;
					selectedButtonTag.setText("press down");
					repaint();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if( e.getKeyCode() == KeyEvent.VK_UP && !bussy)
		{
			switch(currentBtn)
			{
				case 0:
					//eat
					op = rand.nextInt(4);
					if(op == 0)	statusToDraw = "draws/general/status/eat/muffin.txt";
					else if(op == 1) statusToDraw = "draws/general/status/eat/soup.txt";
					else if (op == 2) statusToDraw = "draws/general/status/eat/apple.txt";
					selectedButtonTag.setText("Yummy!");
					currentPet.eat();
					during.start();
				break;
				case 1:
					//sleep
					drawPet = false;
					menuToDraw = "draws/general/light/sleeping.txt";
					currentPet.sleep();
					during.start();
				break;
				case 2:
					//play
					op = rand.nextInt(3);
					playing = true;
					currentPet.play();
					descriptionTag.setText("guess <-- or -->");
					if(op == 0) statusToDraw = "draws/general/status/play/left.txt";
					else statusToDraw = "draws/general/status/play/right.txt";
				break;
				case 3:
				//curar
					op = rand.nextInt(3);
					currentPet.health();
					if(op == 0) statusToDraw = "draws/general/status/health/pills.txt";
					else statusToDraw = "draws/general/status/health/syringe.txt";
					during.start();
				break;
				case 4:
				//shower
					currentPet.shower();
					during.start();
					statusToDraw = "draws/general/status/shower/shower.txt";
				break;
				case 5:
				//talk
					currentPet.talk();
					during.start();
					statusToDraw = "draws/general/status/talk/talking.txt";
				break;
				case 6:
				//medidor
					statusToDraw = "draws/general/status/status/status.txt";
					descriptionTag.setText(currentPet.printStatus());
				break;
				case 7:
				//help
					drawPet = false;
					menuToDraw = "draws/general/help/help.txt";
				break;
			}
			bussy = true;
			currentPet.save();
			if(!playing)
			{
				repaint();
			}
			life.stop();
		}
		else  if( e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			if((currentBtn == 2 && !playing && bussy) || (currentBtn == 6 && bussy))
			{
				//play o medir
				bussy = false;
				life.start();
				prevStatus = statusToDraw;
				checkChanges();
			}
			else if(currentBtn == 7 && !drawPet && bussy)
			{
				// help
				drawPet = true;
				bussy = false;
				life.start();
				repaint();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		countTime++;
			switch (currentBtn)
			{
				case 0:
					descriptionTag.setText("eating now");
					if (countTime == 4)
					{
						countTime = 0;
						descriptionTag.setText("finished eating");
						bussy = false;
						prevStatus = statusToDraw;
						checkChanges();
						during.stop();
						life.start();

					}
				break;
				case 1:
					descriptionTag.setText("sleeping like a baby");
					if(countTime == 8)
					{
						countTime = 0;
						descriptionTag.setText("awake and ready");
						drawPet = true;
						prevPet = menuToDraw;
						bussy = false;
						checkChanges();
						during.stop();
						life.start();
					}
				break;
				case 3:
					descriptionTag.setText("getting better");
					if(countTime == 2)
					{
						countTime = 0;
						descriptionTag.setText("ready to fight");
						bussy = false;
						prevStatus = statusToDraw;
						checkChanges();
						during.stop();
						life.start();
					}
				break;
				case 4:
					descriptionTag.setText("bathing");
					if(countTime == 8)
					{
						countTime = 0;
						descriptionTag.setText("ready to mingle");
						bussy = false;
						prevStatus = statusToDraw;
						checkChanges();
						during.stop();
						life.start();
					}
				break;
				case 5:
					descriptionTag.setText("grounding in progress");
					if(countTime == 5)
					{
						descriptionTag.setText("better tamagochi now");
						countTime = 0;
						prevStatus = statusToDraw;
						bussy = false;
						checkChanges();
						during.stop();
						life.start();
					}
				break;
			}
	}

	private void checkChanges()
	{
		statusToDraw = "draws/general/status/"+currentPet.getStatus()+".txt";
		petToDraw = "draws/main/"+currentPet.getMellowing()+".txt";

		if(!prevPet.equals(petToDraw) || !prevStatus.equals(statusToDraw))
		{
			//uno cambio hay que actualizar
			prevPet = petToDraw;
			prevStatus = statusToDraw;
			repaint();
		}
	}
private void keepPlaying() {
	during.stop();
	life.stop();
	drawPet = false;
	menuToDraw = "draws/general/health/death.txt";
	bussy = true;
	repaint();
}
}
