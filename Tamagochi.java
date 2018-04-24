import java.util.Arrays;//Por mientras
import java.util.Random;
import java.io.File; // buscar la carpeta
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Tamagochi extends Pet {
	private int[] valores;
	private TipoTamagochi tt;
	private Random random;
	private int r;
	private String kind;

	public Tamagochi(String name, int age, int sleep, int hungry,
	int health, int love, int funny, int discipline, int energy, String kind)
	{
		super(name, age, sleep, hungry, health, love, funny, discipline, energy);

		this.kind = kind;

		switch(this.kind) {
			case "PLAYER": tt = TipoTamagochi.PLAYER;
			break;
			case "SLEEPY": tt = TipoTamagochi.SLEEPY;
			break;
			case "DELICATE": tt = TipoTamagochi.DELICATE;
			break;
			case "LOVELY": tt = TipoTamagochi.LOVELY;
		}
		random = new Random();
	}
	public void save() {
		File data = new File("current/data.txt");
		try
		{
			data.createNewFile();
			FileWriter escritor = new FileWriter(data);
			PrintWriter pw = new PrintWriter(escritor);

			pw.printf("%s%n", kind);
			pw.printf("%s%n", name);
			pw.printf("%s%n", age);

			for(int i = 0; i < status.length; i++) {
				if(i == status.length - 1) pw.printf("%s", status[i]);
				else pw.printf("%s%n", status[i]);
			}
			escritor.close();

		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	String getKind() {
		return kind;
	}
	void apply(int index, boolean option, int multiplier, int numMultiplier) {
		int sum, num;

		sum = 0;
		num = 0;

		switch(index) {
			case 0:
				if(option) {
					num = tt.sleepSum();
				} else {
					num = tt.sleepMinus();
				}
			break;
			case 1:
			if(option) {
				num = tt.eatSum();
			} else {
				num = tt.eatMinus();
			}
			break;
			case 2:
			if(option) {
				num = tt.healthSum();
			} else {
				num = tt.healthMinus();
			}
			break;
			case 3:
			if(option) {
				num = tt.loveSum();
			} else {
				num = tt.loveMinus();
			}
			break;
			case 4:
			if(option) {
				num = tt.funSum();
			} else {
				num = tt.funMinus();
			}
			break;
			case 5:
			if(option) {
				num = tt.disciplineSum();
			} else {
				num = tt.disciplineMinus();
			}
			break;
			case 6:
			if(option) {
				num = tt.energySum();
			} else {
				num = tt.energyMinus();
			}
			break;
			default:
			break;
		}

		if(option) {
			sum = multiplier * this.status[index] + (num * numMultiplier);
		} else {
			sum = multiplier * this.status[index] - (num * numMultiplier);
		}

		if(sum <= 0) this.status[index] = 0;
		else if(sum >= 100) this.status[index] = 100;
		else this.status[index] = sum;

	}
	public String getMellowing() {
		if(age > 80)
			return "old";
		else if(age >= 21)
			return "adult" + getFace();
		else if (age > 12)
			return "teen" + getFace();
		else if(age >= 5)
			return "kid" + getFace();
		else
			return "baby" + getFace();
	}
	private String getFace() {
			String lowStatus = getStatus();
			if(lowStatus.equals("okay"))
				return "_happy";
			else if(lowStatus.equals("sad") || lowStatus.equals("sick"))
				return "_sad";
			else
				return "_serious";
	}

	String getStatus() {
		byte min = 100;
		byte temp = 0;
		String s = "";
		for(byte i = 0; i < this.status.length; i++)
		{
			if(min > (byte)this.status[i])
			{
				min = (byte)this.status[i];
				temp = i;
			}
		}

		if(min >= 80)
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
				case 6:
					s = "sleepy";
				break;
			}
		}
		return s;
	}

	void conditionals() {
		for(byte i = 0; i < this.status.length; i++) {

			if(this.status[i] <= 10) {
				switch(i) {
					case 0:
						gettingSleepy();
					break;
					case 1:
						gettingHungry();
					break;
					case 2:
						gettingSick();
					break;
					case 3:
						gettingLonely();
					break;
					case 4:
						gettingBored();
					break;
					case 6:
						gettingTired();
					break;
					default:
					break;
				}
			}
		}
	}
	void life() {
		apply(0, false, 1, 1);
		apply(1, false, 1, 1);
		apply(3, false, 1, 1);
		apply(4, false, 1, 1);
		apply(6, false, 1, 1);
		conditionals();
	}
	void sleep() {
		apply(0, true, 1, 1);
		apply(1, false, 1, 1);
		apply(2, true, 1, 1);
		apply(3, true, 1, 1);
		apply(4, true, 1, 1/3);
		apply(6, true, 1, 1);
	}
	void gettingSleepy() {
		apply(0, false, 1, 2);
		if(kind.equals("DELICATE")) {
			apply(2, false, 1, 3);
		} else apply(2, false, 1, 2);
		apply(5, false, 1, 2);
		apply(6, false, 1, 2);

	}
	void eat() {
		apply(1, true, 1, 1);
		apply(6, true, 1, 1);
		apply(3, true, 1, 1);

		if(kind.equals("DELICATE")) {
			r = random.nextInt(2);
			if(r == 1) 		apply(2, true, 1, 1);
			else 		apply(2, false, 1, 1);
		} else 		apply(2, true, 1, 1);
	}
	void gettingHungry() {
		apply(1, false, 1, 2);
		apply(2, false, 1, 2);
		apply(3, false, 1, 2);
		apply(5, false, 1, 3);
		apply(6, false, 1, 1);
	}
	void health() {
		apply(2, true, 1, 1);
		apply(3, false, 1, 1);
		apply(4, true, 1/2, 1);
	}
	void gettingSick() {
		apply(0, false, 1, 2);
		apply(2, false, 1, 2);
		apply(4, false, 1, 3);
		apply(6, false, 1, 2);
	}
	void love() {
		apply(3, true, 1, 1);
	}
	void shower() {
		r = random.nextInt(2);
		if(r == 1) apply(6, true, 1, 1);
		else apply(6, false, 1, 1);

		r = random.nextInt(2);
		if(r == 1) apply(2, true, 1, 1);
		else apply(2, false, 1, 3);
		apply(4, true, 1, 1);
	}
	void gettingDirty() {
		r = random.nextInt(2);
		if(r == 1) apply(4, true, 1, 2);
		else apply(4, false, 1, 2);
		apply(2, false, 1, 2);
	}
	void gettingLonely() {
		apply(1, false, 1, 1);
		apply(3, false, 1, 2);
		apply(4, false, 1, 1);
		apply(5, false, 1, 1);
		apply(6, true, 1, 1);
	}
	void play() {
		apply(1, false, 1, 1);
		apply(3, true, 1, 1);
		apply(4, true, 1, 2);
		r = random.nextInt(2);
		if(r == 1) apply(5, true, 1, 1);
		else apply(5, false, 1, 1);
		apply(6, false, 1, 1);
	}
	void gettingBored() {
		apply(3, false, 1, 1);
		apply(4, false, 1, 3);
		apply(5, false, 1, 2);
		apply(6, true, 1, 1);
	}
	void talk() {
		r = random.nextInt(2);
		if(r == 1) apply(4, false, 1, 1);
		apply(5, true, 1, 1);
		r = random.nextInt(2);
		if(r == 1) apply(6, false, 1, 1);
	}
	double gettingUndisciplined() {
		return (double)(this.status[5]/100);
	}
	void gettingTired() {
		apply(0, false, 1, 3);
		apply(1, false, 1, 3);
	}
}
