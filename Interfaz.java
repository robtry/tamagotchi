import java.awt.*; //para las funciones gráficas
import javax.swing.*; //para las funciones gráficas
import java.io.FileReader; // leer archivos
import java.io.BufferedReader; // leer archivos
import java.io.IOException; //exceociones

public class Interfaz extends JFrame // extends por que es una clase que hereda de Jframe
{
	private static final long serialVersionUID = 1L;

	//definir tamaño del canvas. Si se cambian las dimesiones cambiar todos los dibujos
	private int largo = 475; //35 cuadritos || filas
	private int ancho = 350; //35 cuadritos || columnas

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
			//ya existe un pet hay que dibujarlo como estaba para continuar

			BufferedReader br = null;
			FileReader fr = null;
			String currentLine;
			String fileToDraw = Tamagotchi.getStatus();
			Byte i = 0;
			String[] columns;

			try
			{
				fr = new FileReader(fileToDraw);
				br = new BufferedReader(fr);

				super.paint(g);

				while ((currentLine = br.readLine()) != null)
				{
					columns = currentLine.split("");
					for(int j = 0; j < columns.length; j++)
					{
						//g.setColor(Color.red);
						//drawRect(x,y,width,heigth);
						g.drawRect (10*j, 10*i+24, 10,10);
						//g.fillRect (10*j, 10*i+24, 10,10);
					}
					i++;
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
		else
		{
			//no  hay nada creado, hay que empezar con el pet
			//funcion menu() o algo parecido
		}

	}
}
