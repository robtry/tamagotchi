import java.io.File;
public class Main
{
	public static void main(String args[])
	{		
		if(Pet.exists())
		{
			String name = "";
			//leer todo para crear la clase con lo ya existente
			/*
			crear el objeto
			*/

			InterfazGame g = new InterfazGame("coco");
			g.setVisible(true);
		}
		else
		{
			InterfazMenu m = new InterfazMenu();
			//m.setVisible(true);
		}
	}
}