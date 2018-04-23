import java.util.Arrays;//Por mientras
public class Tamagochi extends Pet {
	private int[] valores;
	TipoTamagochi tt;

	//Tamagochi(String name, String kind) {
	//	super(name, kind);
	public Tamagochi(String name, int age, double weight,
		int sleep, int hungry, int health, int love, int funny, int dicipline, String kind)
	{
		super(name, age, weight, sleep, hungry, health, love, funny, dicipline);

		switch(kind) {
			case "PLAYER": tt = TipoTamagochi.PLAYER;
			break;
			case "SLEEPY": tt = TipoTamagochi.SLEEPY;
			break;
			case "DISOB": tt = TipoTamagochi.ORDER;
			break;
			case "LOVELY": tt = TipoTamagochi.LOVELY;
		}
	}

	/*
	String getStatus() {
		return Arrays.toString(this.status); //Por mientras
	}*/
	void sleep() {
		this.status[0] += tt.sleepSum();
	}
	void gettingSleepy() {
		this.status[0] -= tt.sleepMinus();
	}
	void eat() {
		this.status[1] += tt.eatSum();
	}
	void gettingHungry() {
		this.status[1] -= tt.eatMinus();
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
}
