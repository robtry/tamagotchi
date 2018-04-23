import java.util.Arrays;//Por mientras
import java.util.Random;
public class Tamagochi extends Pet {
	private int[] valores;
	TipoTamagochi tt;
	Random random;

	//Tamagochi(String name, String kind) {
	//	super(name, kind);
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
	/*
	status[0] = sleep; //dormir[0]
	status[1] = hungry; //hambre[1]
	status[2] = health; //salud[2]
	status[3] = love; //amor[3]
	status[4] = funny; //diversion[4]
	status[5] = discipline; //diciplina[5]
	status[6] = energy;//energía[6]
	*/
	void life() {
		this.status[0] -= tt.sleepMinus();
		this.status[1] -= tt.eatMinus();
		this.status[3] -= tt.loveMinus();
		this.status[4] -= tt.funMinus();
		this.status[6] -= tt.energyMinus();
	}
	void sleep() {
		this.status[0] += tt.sleepSum();
		this.status[1] -= tt.eatMinus();
		this.status[2] += tt.healthSum();
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
			int r = random.nextInt(2);
			if(r == 1) this.status[2] += tt.healthSum();
			else this.status[2] -= tt.healthMinus();
		} else this.status[2] += tt.healthSum();
	}
	void gettingHungry() {
		this.status[1] -= 2 * tt.eatMinus();
		
	}
	void health() {
		this.status[2] += tt.healthSum();
	}
	void gettingSick() {
		this.status[2] -= tt.healthMinus();
	}
	void love() {
		this.status[3] += tt.loveSum();
	}
	void gettingLonely() {
		this.status[3] -= tt.loveMinus();
	}
	void play() {
		this.status[4] += tt.funSum();
	}
	void gettingBored() {
		this.status[4] -= tt.funMinus();
	}
	void discipline() {
		this.status[5] += tt.disciplineSum();
	}
	void gettingUndisciplined() {
		this.status[5] -= tt.disciplineMinus();
	}
	void gettingTired() {
		this.status[6] -= tt.energyMinus();
	}
}
