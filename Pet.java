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

	protected String getStatus()
	{
		byte menor = 100;
		byte temp = 0;
		String s = "";
		for(byte i = 0; i < status.length; i++)
		{
			if(menor > (byte)status[i])
			{
				menor = (byte)status[i];
				temp = i;
			}
		}

		if(status[temp] >= 80)
		{
			s = "okay";
		}
		else
		{
			switch(temp)
			{
				case 0:
					s = "sleepy";
				break;
				case 1:
					s = "hungry";
				break;
				case 2:
					s = "sick";
				break;
				case 3:
					s = "sad";
				break;
				case 4:
					s = "boring";
				break;
				case 5:
					s = "spoiled";
				break;
			}
		}

		return s;
	}

	protected String getMellowing()
	{
		if(age >= 21)
			return "adult" + getFace();
		else if (age > 12)
			return "teen" + getFace();
		else if(age >= 5)
			return "kid" + getFace();
		else
			return "baby" + getFace();
	}

	private String getFace()
	{
		String lowStatus = getStatus();
		if(lowStatus.equals("okay"))
			return "_happy";
		else if(lowStatus.equals("sad") || lowStatus.equals("sick"))
			return "_sad";
		else
			return "_serious";
	}

	abstract void eat();
	abstract void gettingHungry();
	abstract void sleep();
	abstract void gettingSleepy();
	abstract void health();
	abstract void gettingSick();
	abstract void love();
	abstract void gettingLonely();
	abstract void discipline();
	abstract void gettingUndisciplined();
	abstract void play();
	abstract void gettingBored();
	abstract void gettingTired();
}
