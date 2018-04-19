import java.util.Random;
public abstract class Pet
{
	private String name;
	private int age;
	private int[] status;

	Pet(String name, int age)
	{
		this.name = name;
		this.age = age;

		Random rndm = new Random();
		status = new int[6];
		status[0] = rndm.nextInt(100)+1; //dormir[0]
		status[1] = rndm.nextInt(50)+1; //hambre[1]
		status[2] = rndm.nextInt(100)+1; //salud[2]
		status[3] = rndm.nextInt(15)+1; //amor[3]
		status[4] = rndm.nextInt(30);  //diversion[4]
		status[5] = rndm.nextInt(15); //diciplina[5]

	}


	public static boolean petExists()
	{
		// función dice si ya hay una mascota inicializada o hay que crear una nueva
		return true;
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

	abstract void eat();
	abstract void sleep();
	abstract void health();
	abstract void love();
	abstract void funny();
	abstract void dicipline();
	abstract void play();
	abstract String getStatus();
}