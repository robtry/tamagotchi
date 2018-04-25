import java.io.File; // buscar la carpeta
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
public abstract class Pet
{
	protected String name;
	protected int age;
	protected int[] status;

	Pet(String name, int age, int sleep, int hungry, int health,
			int love, int funny, int discipline, int energy)
	{
		this.name = name;
		this.age = age; //days
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
	protected String getName()
	{
		return name;
	}

	protected int getAge()
	{
		return age;
	}
	public String printStatus() {
		// String status = "[S-"+this.status[0]+", Hu-"+this.status[1]+", He-"+this.status[2]
		// +", L-"+this.status[3]+", F-"+this.status[4]+", D-"+this.status[5]+", E-"+this.status[6]+"]";
		String status = Arrays.toString(this.status);

		return status;
	}
	abstract boolean isAlive();
	abstract void life();
	abstract void save();
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
