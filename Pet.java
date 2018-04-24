import java.io.File; // buscar la carpeta

public abstract class Pet
{
	protected String name;
	protected int age;
	protected double weight;
	protected int[] status;

	Pet(String name, int age, double weight, int sleep, int hungry, int health,
			int love, int funny, int discipline, int energy)
	{
		this.name = name;
		this.age = age; //days
		this.weight = weight; // kg
		status = new int[7];

		status[0] = sleep; //dormir[0]
		status[1] = hungry; //hambre[1]
		status[2] = health; //salud[2]
		status[3] = love; //amor[3]
		status[4] = funny; //diversion[4]
		status[5] = discipline; //diciplina[5]
		status[6] = energy;//energía[6]
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

	protected void save()
	{
		//guarda los datos actuales
	}

	protected String getName()
	{
		return name;
	}

	protected int getAge()
	{
		return age;
	}

	protected double getWeight()
	{
		return weight;
	}

	abstract void life();
	abstract String getMellowing();
	abstract String getStatus();
	abstract void eat();
	abstract void gettingHungry();
	abstract void sleep();
	abstract void gettingSleepy();
	abstract void health();
	abstract void gettingSick();
	abstract void love();
	abstract void gettingLonely();
	abstract void talk();
	abstract double gettingUndisciplined();
	abstract void play();
	abstract void shower();
	abstract void gettingDirty();
	abstract void gettingBored();
	abstract void gettingTired();
}
