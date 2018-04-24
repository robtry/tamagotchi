import java.util.Arrays;//Por mientras
import java.util.Random;
public class Tamagochi extends Pet {
	private int[] valores;
	private TipoTamagochi tt;
	private Random random;
	private int r;
	private String kind;

	public Tamagochi(String name, int age, double weight, int sleep, int hungry,
	int health, int love, int funny, int discipline, int energy, String kind)
	{
		super(name, age, weight, sleep, hungry, health, love, funny, discipline, energy);

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
	void checkLimits() {
		for(int i = 0; i < this.status.length; i++) {
			if(this.status[i] < 0) this.status[i] = 0;
			else if(this.status[i] > 100) this.status[i] = 100;
		}
	}
	public String getMellowing() {
		if(age >= 21)
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
					System.out.println("Todo bien");
					break;
				}
			}
		}
	}
	void life() {
		this.status[0] -= tt.sleepMinus();
		this.status[1] -= tt.eatMinus();
		this.status[3] -= tt.loveMinus();
		this.status[4] -= tt.funMinus();
		this.status[6] -= tt.energyMinus();
		checkLimits();
		conditionals();
	}
	void sleep() {
		this.status[0] += tt.sleepSum();
		this.status[1] -= tt.eatMinus();
		this.status[2] += tt.healthSum();
		this.status[3] += tt.loveSum();
		this.status[4] += tt.funSum()/3;
		this.status[6] += tt.energySum();
	}
	void gettingSleepy() {
		this.status[0] -= 2 * tt.sleepMinus();
		if(kind.equals("DELICATE")) {
			this.status[2] -= 3 * tt.healthMinus();
		} else this.status[2] -= 2 * tt.healthMinus();
		this.status[5] -= 2 * tt.disciplineMinus();
		this.status[6] -= 2 * tt.energyMinus();

	}
	void eat() {
		this.status[1] += tt.eatSum();
		this.status[6] += tt.energySum();
		this.status[3] += tt.loveSum();
		if(kind.equals("DELICATE")) {
			r = random.nextInt(2);
			if(r == 1) this.status[2] += tt.healthSum();
			else this.status[2] -= tt.healthMinus();
		} else this.status[2] += tt.healthSum();
	}
	void gettingHungry() {
		this.status[1] -= 2 * tt.eatMinus();
		this.status[2] -= 2 * tt.healthMinus();
		this.status[3] -= 2 * tt.loveMinus();
		this.status[6] -= tt.energyMinus();
		this.status[5] -= 3 * tt.disciplineMinus();
	}
	void health() {
		this.status[2] += 10;
		this.status[3] -= 10;
		this.status[4] /= 2;
	}
	void gettingSick() {
		this.status[0] -= 2 * tt.sleepMinus();
		this.status[2] -= 2 * tt.healthMinus();
		this.status[4] -= 3 * tt.funMinus();
		this.status[6] -= 4 * tt.energyMinus();
	}
	void love() {
		this.status[3] += tt.loveSum();
	}
	void shower() {
		r = random.nextInt(2);
		if(r == 1) this.status[6] += tt.energySum();
		else this.status[6] -= tt.energyMinus();

		r = random.nextInt(2);
		if(r == 1) this.status[2] += tt.healthSum();
		else this.status[2] -= 3 * tt.healthMinus();

		this.status[4] += tt.funSum();
	}
	void gettingDirty() {
		r = random.nextInt(2);
		if(r == 1) this.status[4] += 2 * tt.funSum();
		else this.status[4] -= 2 * tt.funMinus();
		this.status[2] -= 2 * tt.healthMinus();
	}
	void gettingLonely() {
		this.status[1] -= tt.eatMinus();
		this.status[3] -= 2 * tt.loveMinus();
		this.status[4] -= tt.funMinus();
		this.status[5] -= tt.disciplineMinus();
		this.status[6] += tt.energySum();
	}
	void play() {
		this.status[1] -= tt.eatMinus();
		this.status[3] += tt.loveSum();
		this.status[4] += 2 * tt.funSum();
		r = random.nextInt(2);
		if(r == 1) this.status[5] += tt.disciplineSum();
		else this.status[5] -= tt.disciplineMinus();
		this.status[6] -= tt.energyMinus();
	}
	void gettingBored() {
		this.status[3] -= tt.loveMinus();
		this.status[4] -= 3 * tt.funMinus();
		this.status[5] -= 2 * tt.disciplineMinus();
		this.status[6] += tt.energySum();
	}
	void talk() {
		r = random.nextInt(2);
		if(r == 1) this.status[4] -= tt.funMinus();
		this.status[5] += tt.disciplineSum();
		r = random.nextInt(2);
		if(r == 1) this.status[6] -= tt.energyMinus();
	}
	double gettingUndisciplined() {
		return (double)(this.status[5]/100);
	}
	void gettingTired() {
		this.status[0] -= 3 * tt.sleepMinus();
		this.status[1] -= 3 * tt.eatMinus();
	}
}
