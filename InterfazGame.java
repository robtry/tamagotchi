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

public class InterfazGame extends JFrame implements KeyListener // extends por que es una clase que hereda de Jframe
{
	private static final long serialVersionUID = 1L;

	//NO CAMBIAR LAS DIMENSIONES!
	private final short largo = 450; //35 cuadritos || filas
	private final short ancho = 350; //35 cuadritos || columnas
	private JPanel containerOptions, containerTags;
	private JLabel selectedButtonTag, pressedButtonTag;
	private ArrayList<JButton> optionBtns= new ArrayList<JButton>();
	private final String[] images = {"eat", //eatBtn[0]
									  "bulb", //sleepBtn[1]
									  "play", //playBtn [2]
									  "syringe", //medicineBtn [3]
									  "shower", //showerBtn [4]
									  "talk", //talkBtn [5]
									  "measurer", //statusBtn [6]
									  "suggestion", //needBtn [7]
									  "help" // helpBtn [8]
									 };
	private byte currenrBtn;


	public InterfazGame(String titulo) // constructor
	{
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// para que se termine la execución cuando se cierra
		setResizable(false);// desabilita la opción de cambiar tamaño
		setSize(ancho, largo);// establecer tamaño
		//setLayout(new FlowLayout());//flujo en el que ordena elementos, esta comentado para que el panel de abajo coloree todo
		setTitle(titulo); //titulo de la ventana
		//agregar los botones
		BufferedImage img;
		containerOptions = new JPanel();
		//containerOptions.setBackground(new Color(160, 178, 129));
		containerOptions.setBackground(new Color(193, 205, 172));
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
			containerTags.setBackground(new Color(193, 205, 172)); // para que sea igual que arriba
			selectedButtonTag = new JLabel();
			pressedButtonTag = new JLabel();
			containerTags.setLayout(new GridLayout(1,2));//ordenar 1 fila * 2 columnas
		containerTags.add(selectedButtonTag);
		containerTags.add(pressedButtonTag);

		currenrBtn = 6;
		optionBtns.get(currenrBtn).setEnabled(true);
		selectedButtonTag.setText(images[currenrBtn]);

		add(containerOptions);
		add(containerTags, BorderLayout.SOUTH);

		addKeyListener(this);// para que lea desde el teclado
		setFocusable(true);//para que el panel sea quien lee las teclas
		setLocationRelativeTo(null);
	}

	/*public void paint(Graphics g)
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
			//fileToDraw = Pet.getStatus();
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

	}*/

	@Override
	public void keyTyped(KeyEvent e) { }

	@Override
	public void keyPressed(KeyEvent e)
	{

		byte temp = currenrBtn;

		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			if(currenrBtn == images.length-1)
				currenrBtn = 0;
			else
				currenrBtn++;

		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			if(currenrBtn == 0)
				currenrBtn = (byte)(images.length-1);
			else
				currenrBtn--;
		}

		optionBtns.get(temp).setEnabled(false);
		optionBtns.get(currenrBtn).setEnabled(true);
		selectedButtonTag.setText(images[currenrBtn]);

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
//https://docs.oracle.com/javase/tutorial/uiswing/painting/closer.html