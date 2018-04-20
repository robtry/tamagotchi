import java.util.Random;
import java.io.File; // buscar la carpeta

public abstract class Pet
{
	protected String name;
	protected int age;
	protected double weight;
	protected int[] status;
	private String kind;

	Pet(String name, String kind)
	{
		this.name = name;
		age = 0; //days
		weight = 0.100; // kg
		this.kind = kind;

		Random rndm = new Random();
		status = new int[6];
		status[0] = rndm.nextInt(100)+1; //dormir[0]
		status[1] = rndm.nextInt(50)+1; //hambre[1]
		status[2] = rndm.nextInt(100)+1; //salud[2]
		status[3] = rndm.nextInt(15)+1; //amor[3]
		status[4] = rndm.nextInt(30);  //diversion[4]
		status[5] = rndm.nextInt(15); //diciplina[5]

		//crear carpeta y empezar a guardar ahi
	}


	public static boolean exists()
	{
		boolean found = false;
		// función dice si ya hay una mascota inicializada o hay que crear una nueva
		File dir = new File(System.getProperty("user.dir"));
		File[] files = dir.listFiles();
		for (File file : files)
		{
			if (file.isDirectory())
			{
				if (file.getName().equals("current"))
				{
					found = true;
					break;
				}
			}
		}
		return found;
	}

	protected static String getKindOfPet()
	{
		return "draws/ball/";
	}

	public static String getName()
	{
		return "Coquito";
		//return this.name;
	}

	public static void reset()
	{
		//asumiendo que no existen directorios y solo archivos
		File element = new File(System.getProperty("user.dir")+"/current");
		for (File sub : element.listFiles())
		{
			sub.delete();
		}
		element.delete();
	}

	abstract void eat();
	abstract void gettingHungry();
	abstract void sleep();
	abstract void gettingSleepy();
	abstract void health();
	abstract void gettingSick();
	abstract void love();
	abstract void gettingLonely();
	abstract void dicipline();
	abstract void gettingUndisciplined();
	abstract void play();
	abstract void gettingBored();
	abstract String getStatus();
}
