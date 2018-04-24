import java.util.Arrays;//Por mientras
import java.util.Random;
public class Tamagochi extends Pet {
	private int[] valores;
	private TipoTamagochi tt;
	private Random random;
	private int r;

	public Tamagochi(String name, int age, double weight, int sleep, int hungry,
	int health, int love, int funny, int discipline, int energy, String kind)
	{
		super(name, age, weight, sleep, hungry, health, love, funny, discipline, energy);

		switch(kind) {
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
			else if(this.status[i] > 100) this.statud[i] = 100;
		}
	}
	String getMellowing() {
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
		String s = "";
		for(int i = 0; i < this.status.length; i++) {
			if(this.status[i] <= 10) {
				switch(i) {
					case 0:
						gettingSleepy();
						s = "sleepy";
					break;
					case 1:
						gettingHungry();
						s = "hungry";
					break;
					case 2:
						gettingSick();
						s = "sick";
					break;
					case 3:
						gettingLonely();
						s = "sad";
					break;
					case 4:
						gettingBored();
						s = "boring";
					break;
					case 6:
						gettingTired();
						s = "sleepy";
					break;
					default:
					System.out.println("Todo bien");
					break;
				}
			}
			else s = "okay";
		}
		return "s";
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
		this.statud[4] -= tt.funMinus();
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
		this.statud[4] -= 3 * tt.funMinus();
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
