import java.awt.*; //para las funciones gráficas
import javax.swing.*; //para las funciones gráficas
import javax.imageio.ImageIO; //imagenes
import java.io.File; // leer imagenes
import java.io.FileReader; // leer archivos
import java.io.BufferedReader; // leer archivos
import java.awt.image.BufferedImage; //leer imagenes
import java.io.IOException; //excepciones

public class Interfaz extends JFrame // extends por que es una clase que hereda de Jframe
{
	private static final long serialVersionUID = 1L;

	//NO CAMBIAR LAS DIMENSIONES!
	private int largo = 460; //35 cuadritos || filas
	private int ancho = 350; //35 cuadritos || columnas
	private JPanel containerButtons;
	private JButton
		eatBtn,
		sleepBtn,
		playBtn,
		medicineBtn,
		showerBtn,
		talkBtn,
		needBtn,
		statusBtn;

	public Interfaz() // constructor
	{
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// para que se termine la execución cuando se cierra
		setResizable(false);// desabilita la opción de cambiar tamaño
		setSize(ancho, largo);// establecer tamaño
		if(Tamagotchi.petExists())
			setTitle(Tamagotchi.getName()); //titulo de la ventana
		else
			setTitle("!pet");
		//agregar los botones
		containerButtons = new JPanel();
		//containerButtons.setBackground(new Color(53, 56, 64));
		containerButtons.setLayout(new FlowLayout());
			eatBtn = new JButton();
			sleepBtn = new JButton();
			playBtn = new JButton();
			medicineBtn = new JButton();
			showerBtn = new JButton();
			talkBtn = new JButton();
			needBtn = new JButton();
			statusBtn = new JButton();
			BufferedImage img;
			try
			{
				img = ImageIO.read(new File("draws/general/buttons/eat.png"));
				eatBtn.setIcon(new ImageIcon(img));
				img = ImageIO.read(new File("draws/general/buttons/measurer.png"));
				statusBtn.setIcon(new ImageIcon(img));
				img = ImageIO.read(new File("draws/general/buttons/play.png"));
				playBtn.setIcon(new ImageIcon(img));
				img = ImageIO.read(new File("draws/general/buttons/shower.png"));
				showerBtn.setIcon(new ImageIcon(img));
				img = ImageIO.read(new File("draws/general/buttons/syringe.png"));
				medicineBtn.setIcon(new ImageIcon(img));
				img = ImageIO.read(new File("draws/general/buttons/bulb.png"));
				sleepBtn.setIcon(new ImageIcon(img));
				img = ImageIO.read(new File("draws/general/buttons/suggestion.png"));
				needBtn.setIcon(new ImageIcon(img));
				img = ImageIO.read(new File("draws/general/buttons/talk.png"));
				talkBtn.setIcon(new ImageIcon(img));
			}
			catch (IOException ex)
			{
				System.out.println(ex);
			}

			eatBtn.setPreferredSize(new Dimension(30, 30));
			sleepBtn.setPreferredSize(new Dimension(30, 30));
			playBtn.setPreferredSize(new Dimension(30, 30));
			medicineBtn.setPreferredSize(new Dimension(30, 30));
			showerBtn.setPreferredSize(new Dimension(30, 30));
			talkBtn.setPreferredSize(new Dimension(30, 30));
			statusBtn.setPreferredSize(new Dimension(30, 30));
			needBtn.setPreferredSize(new Dimension(30, 30));

			//eatBtn.setEnabled(false);
			//sleepBtn.setEnabled(false);
			//playBtn.setEnabled(false);
			//medicineBtn.setEnabled(false);
			//showerBtn.setEnabled(false);
			//talkBtn.setEnabled(false);
			//statusBtn.setEnabled(false);
			//needBtn.setEnabled(false);

		containerButtons.add(eatBtn);
		containerButtons.add(sleepBtn);
		containerButtons.add(playBtn);
		containerButtons.add(medicineBtn);
		containerButtons.add(showerBtn);
		containerButtons.add(talkBtn);
		containerButtons.add(statusBtn);
		containerButtons.add(needBtn);

		setLayout(new FlowLayout());//flujo en el que ordena elementos
		add(containerButtons);

		setVisible(true);// para que sea visible
	}

	public void paint(Graphics g)
	{
		if(Tamagotchi.petExists())
		{
			//ya existe un pet hay que dibujarlo como estaba para continuar

			BufferedReader br = null;
			String currentLine;
			String fileToDraw;
			byte ifilas = 0;
			String[] columns;

			try
			{
				super.paint(g);
				//botones de arriba se contruyen solos
				statusBtn.setEnabled(true);

				ifilas = 5;

				//todo el pet
				fileToDraw = Tamagotchi.getStatus();
				br = new BufferedReader(new FileReader(fileToDraw));

				while ((currentLine = br.readLine()) != null)
				{
					columns = currentLine.split("");
					for(byte j = 0; j < columns.length; j++)
					{
						//g.setColor(Color.red);
						//drawRect(x,y,width,heigth);
						g.drawRect (10*j, 10*ifilas+24, 10,10);
						//g.fillRect (10*j, 10*i+24, 10,10);
					}
					ifilas++;
				}

				//botones de abajo


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
		else
		{
			//no  hay nada creado, hay que empezar con el pet
			//funcion menu() o algo parecido
		}
	}
}
