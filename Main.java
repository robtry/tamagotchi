import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
public class Main
{
	public static void main(String[] args) throws IOException
	{
		//if(Pet.exists())
		//	Pet.reset();

		if(Pet.exists())
		{
			Pet currentPet = null;
			String datoLeido = "";
			String[] todosDatos = new String[11];
			byte count = 0;
			Encriptador encriptador = new Encriptador();
			int noSecreto = 0;
			BufferedReader br = null;
			br = new BufferedReader(new FileReader("current/data.txt"));

			datoLeido = br.readLine();
			Scanner scan = new Scanner(datoLeido);
			scan.useDelimiter(" ");
			noSecreto = scan.nextInt();
			datoLeido = datoLeido.substring(datoLeido.indexOf(" ") + 1, datoLeido.length());
			datoLeido = encriptador.desencriptar(datoLeido, noSecreto);
			todosDatos = datoLeido.split("\n");
			br.close();


			String classBelong = todosDatos[0];
			String nombre = todosDatos[1];
			int edad = Integer.parseInt(todosDatos[2]);
			int suenio = Integer.parseInt(todosDatos[3]);
			int hambre = Integer.parseInt(todosDatos[4]);
			int salud = Integer.parseInt(todosDatos[5]);
			int amor = Integer.parseInt(todosDatos[6]);
			int diversion = Integer.parseInt(todosDatos[7]);
			int disciplina = Integer.parseInt(todosDatos[8]);
			int energia = Integer.parseInt(todosDatos[9]);


			currentPet = new Tamagochi(nombre, edad, suenio, hambre,
						salud, amor, diversion, disciplina, energia, classBelong);

			//System.out.println(currentPet.getStatus());
			//System.out.println(currentPet.getMellowing());

			InterfazGame g = new InterfazGame(currentPet);
			g.setVisible(true);

			if(g.showInstructionsAgain())
			{
				g.showInstructions();
			}

		}
		else
		{
			InterfazMenu m = new InterfazMenu();
			m.setVisible(true);
			m.showInstructions();
		}
	}
}
