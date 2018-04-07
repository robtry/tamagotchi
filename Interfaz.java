import java.awt.*; //para las funciones gráficas
import javax.swing.*; //para las funciones gráficas
import java.io.FileReader; // leer archivos
import java.io.BufferedReader; // leer archivos
import java.io.IOException; //exceociones

public class Interfaz extends JFrame // extends por que es una clase que hereda de Jframe
{
	private static final long serialVersionUID = 1L;

	//definir tamaño del canvas
	private int ancho = 350;
	private int largo = 450;

	public Interfaz() // constructor
	{
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// para que se termine la execución cuando se cierra
		setResizable(false);// desabilita la opción de cambiar tamaño
		setSize(ancho, largo);// establecer tamaño
		setTitle("!pet");// titulo de la ventana
		setVisible(true);// para que sea visible
	}

	public void paint (Graphics g)
	{
		if(Tamagotchi.petExists())
		{
			BufferedReader br = null;
			FileReader fr = null;
			String sCurrentLine;

			try
			{
				fr = new FileReader("petsDraws/dog/main.txt");
				br = new BufferedReader(fr);

				while ((sCurrentLine = br.readLine()) != null)
				{
					System.out.println(sCurrentLine);
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

					if (fr != null)
					fr.close();
				}
				catch (IOException ex)
				{
					ex.printStackTrace();
				}
			}
		}

		super.paint(g);
		int anchoTimes = getWidth()/10;
		int largoTimes = getHeight()/10;
		for(int i  = 0; i < anchoTimes; i++)
		{
			for(int j = 1; j <= largoTimes; j++)
			{
				//drawRect(x,y,width,heigth)
				//g.setColor(Color.red);
				g.drawRect (10*i, 10*j+10, 10,10);
				//g.fillRect(10*i, 10*j+20, 10,10);
			}
		}

	}
}
