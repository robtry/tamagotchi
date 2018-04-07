

public class Tamagotchi
{
	//esta debe ser la clase padre, las que hereden seran los diferentes tipos de tamagochis
	public static boolean petExists()
	{
		// función dice si ya hay una mascota inicializada o hay que crear una nueva
		return true;
	}

	public static String getStatus()
	{
		return getKindOfPet() + "main.txt";
	}

	private static String getKindOfPet()
	{
		return "petsDraws/ball/";
	}
}
