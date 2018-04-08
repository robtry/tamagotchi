import java.awt.*; //para las funciones gráficas
import javax.swing.*; //para las funciones gráficas
import javax.imageio.ImageIO; //imagenes
import java.io.File; // leer imagenes
import java.io.FileReader; // leer archivos
import java.io.BufferedReader; // leer archivos
import java.awt.image.BufferedImage; //leer imagenes
import java.io.IOException; //excepciones
import java.util.ArrayList; // meter los botones

public class Interfaz extends JFrame // extends por que es una clase que hereda de Jframe
{
	private static final long serialVersionUID = 1L;

	//NO CAMBIAR LAS DIMENSIONES!
	private final int largo = 460; //35 cuadritos || filas
	private final int ancho = 350; //35 cuadritos || columnas
	private JPanel containerOptions, containerButtons;
	private ArrayList<JButton> optionBtns= new ArrayList<JButton>();
	private final String[] images = {"eat", //eatBtn[0]
	                                  "bulb", //sleepBtn[1]
																		"play", //playBtn [2]
																		"syringe", //medicineBtn [3]
																		"shower", //showerBtn [4]
																		"talk", //talkBtn [5]
																		"measurer", //statusBtn [6]
																		"suggestion", //needBtn [7]
																		};

	public Interfaz() // constructor
	{
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// para que se termine la execución cuando se cierra
		setResizable(false);// desabilita la opción de cambiar tamaño
		setSize(ancho, largo);// establecer tamaño
		setLayout(new FlowLayout());//flujo en el que ordena elementos

		if(Tamagotchi.petExists())
			setTitle(Tamagotchi.getName()); //titulo de la ventana
		else
			setTitle("!pet");

		//agregar los botones
		BufferedImage img;
		containerOptions = new JPanel();
		//containerOptions.setBackground(new Color(53, 56, 64));
		containerOptions.setLayout(new FlowLayout());
			for(int i = 0; i < 8; i++) // 8 botones
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

		add(containerOptions);

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
				optionBtns.get(6).setEnabled(true);

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
