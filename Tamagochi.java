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
		int noSecreto = 0;
		Encriptador encriptador = new Encriptador();
		try
		{
			File data = new File("current/data.txt");
			File inst = new File("current/showhelp.txt");
			inst.createNewFile();
			data.createNewFile();
			FileWriter escritor = new FileWriter(data);
			PrintWriter pw = new PrintWriter(escritor);

			String valores = kind+"\n"+name+"\n"+"0\n"+status[0]+"\n"+status[1]+"\n"+status[2]+"\n"+status[3]+"\n"+status[4]+"\n"+status[5]+"\n"+status[6];
			valores = encriptador.encriptar(valores);
			noSecreto = encriptador.darNumeroSecreto();

			pw.print(noSecreto + " ");
			pw.print(valores);

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
		apply(0, false);
		apply(1, false);
		apply(3, false);
		apply(4, false);
		apply(6, false);
		age++;
		conditionals();
	}
	void sleep() {
		apply(0, true);
		apply(1, false, 2);
		apply(2, true);
		apply(3, true);
		apply(4, true);
		apply(6, true);
	}
	void gettingSleepy() {
		if(kind.equals("DELICATE")) {
			gettingSick();
		}
		apply(5, false, 2);
		apply(6, false, 2);

	}
	void eat() {
		apply(1, true, 2);
		apply(3, true);
		apply(6, true, 2);

		if(kind.equals("DELICATE")) {
			r = random.nextInt(2);
			if(r == 1) gettingSick();
			else 		apply(2, false);
		} else 		apply(2, true);
	}
	void gettingHungry() {
		apply(2, false);
		apply(3, false, 2);
		apply(5, false, 2);
		apply(6, false, 2);
	}
	void health() {
		apply(2, true, 2);
		apply(3, false);
		apply(4, false, 2);
	}
	void gettingSick() {
		apply(0, false);
		apply(2, false);
		apply(4, false);
		apply(6, false);
	}
	void love() {
		apply(3, true, 2);
	}
	void shower() {
		double r2 = random.nextDouble();
		if(r2 > .95) apply(2, false, 100);
		else apply(2, true);

		r = random.nextInt(2);
		if(r == 1) apply(6, true);
		else apply(6, false);

		r = random.nextInt(2);
		if(r == 1) apply(2, true);
		else apply(2, false);
	}
	void gettingDirty() {
		apply(2, false);
	}
	void gettingLonely() {
		apply(1, false);
		apply(4, false);
		apply(5, false);
		apply(6, true);
	}
	void play() {
		apply(1, false, 2);
		apply(3, true);
		if(kind.equals("PLAYER")) apply(4, true);
		else apply(4, true, 2);
		r = random.nextInt(2);
		apply(6, false, 2);
	}
	void gettingBored() {
		apply(3, false);
		apply(5, false, 2);
		apply(6, true);
	}
	void talk() {
		r = random.nextInt(2);
		if(r == 1) apply(4, false);
		apply(5, true, 2);
		r = random.nextInt(2);
		if(r == 1) apply(6, false);
	}
	double gettingUndisciplined() {
		return (double)(this.status[5]/100);
	}
	void gettingTired() {
		apply(0, false, 2);
		apply(1, false, 2);
	}
	boolean isAlive() {
		if(status[2] <= 0) return false;
		else return true;
	}
	void apply(int index, boolean option, int multiplier) {
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
			sum = this.status[index] + (num * multiplier);
		} else {
			sum = this.status[index] - (num * multiplier);
		}

		if(sum <= 0) this.status[index] = 0;
		else if(sum >= 100) this.status[index] = 100;
		else this.status[index] = sum;

	}
	void apply(int index, boolean option) {
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
			sum = this.status[index] + (num);
		} else {
			sum = this.status[index] - (num);
		}

		if(sum <= 0) this.status[index] = 0;
		else if(sum >= 100) this.status[index] = 100;
		else this.status[index] = sum;

	}
	public int getTimeEating()
	{
		return tt.timeEating();
	}
	public int getTimeSleeping()
	{
		return tt.timeSleeping();
	}
	public int getTimeHealthing()
	{
		return tt.timeHealthing();
	}
	public int getTimeDiciplining()
	{
		return tt.timeDiciplining();
	}
}
