import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

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

			BufferedReader br = null;
			br = new BufferedReader(new FileReader("current/data.txt"));

			while((datoLeido = br.readLine())!=null)
			{
				todosDatos[count] = datoLeido;
				count++;
			}
			br.close();

			String classBelong = todosDatos[0];
			String nombre = todosDatos[1];
			int edad = Integer.parseInt(todosDatos[2]);
			double peso = Double.parseDouble(todosDatos[3]);
			int suenio = Integer.parseInt(todosDatos[4]);
			int hambre = Integer.parseInt(todosDatos[5]);
			int salud = Integer.parseInt(todosDatos[6]);
			int amor = Integer.parseInt(todosDatos[7]);
			int diversion = Integer.parseInt(todosDatos[8]);
			int disciplina = Integer.parseInt(todosDatos[9]);
			int energia = Integer.parseInt(todosDatos[10]);


			currentPet = new Tamagochi(nombre, edad ,peso, suenio, hambre,
						salud, amor, diversion, disciplina, energia, classBelong);

			System.out.println(currentPet.getStatus());
			System.out.println(currentPet.getMellowing());

			InterfazGame g = new InterfazGame(currentPet);
			g.setVisible(true);
		}
		else
		{
			InterfazMenu m = new InterfazMenu();
			m.setVisible(true);
		}
	}
}
