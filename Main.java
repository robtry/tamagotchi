import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		if(Pet.exists())
		{
			int classBelong = 0;
			BufferedReader br = null;
			br = new BufferedReader(new FileReader("current/data.txt"));
			classBelong = Integer.parseInt(br.readLine());
			switch(classBelong)
			{
				case 1:
					//Pet c = new kindA();
				break;
				case 2:
					//Pet c = new kindA();
				break;
				case 3:
					//Pet c = new kindA();
				break;
			}
			System.out.println(classBelong);
			InterfazGame g = new InterfazGame("c.getName()");
			g.setVisible(true);
		}
		else
		{
			InterfazMenu m = new InterfazMenu();
			m.setVisible(true);
		}
	}
}