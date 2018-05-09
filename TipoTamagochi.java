public enum TipoTamagochi {
	/*
	Llevan dos valores uno que suma cada que hace una actividad y otro cuando
		pasa el tiempo y se resta.
	*/
	//                  eat               sleep               health                love                funny                 discipline            energy
	PLAYER(new int[] {5, 10, 4}, new int[] {40, 10, 5}, new int[] {5, 10, 2}, new int[] {5, 10, 0}, new int[] {5, 20, 0}, new int[] {15, 30, 7}, new int[] {15, 40, 0}),
	SLEEPY(new int[] {5, 10, 5}, new int[] {40, 15, 15}, new int[] {5, 10, 2}, new int[] {5, 10, 0}, new int[] {5, 10, 0}, new int[] {15, 15, 5}, new int[] {5 , 40, 0}),
DELICATE(new int[] {5, 10, 4}, new int[] {40, 10, 5 }, new int[] {5, 15, 5}, new int[] {5, 10, 0}, new int[] {5, 10, 0}, new int[] {15, 15, 5}, new int[] {5 , 10, 0}),
	LOVELY(new int[] {5, 10, 4}, new int[] {40, 10, 8 }, new int[] {5, 10, 2}, new int[] {5, 15, 0}, new int[] {5, 10, 0}, new int[] {10, 15, 5}, new int[] {10, 10, 0});
	private int[][] values;

	TipoTamagochi(int[] eat, int[] sleep, int[] health, int[] love, int[] funny, int[] discipline, int[] energy) {
		values = new int[7][3];
		for(int i = 0; i < values.length; i++) {
			switch(i) {
				case 0: values[i] = eat;
				break;
				case 1: values[i] = sleep;
				break;
				case 2: values[i] = health;
				break;
				case 3: values[i] = love;
				break;
				case 4: values[i] = funny;
				break;
				case 5: values[i] = discipline;
				break;
				case 6: values[i] = energy;
				break;
			}
		}
	}
	public int eatSum() {
		return values[0][0];
	}
	public int sleepSum() {
		return values[1][0];
	}
	public int healthSum() {
		return values[2][0];
	}
	public int loveSum() {
		return values[3][0];
	}
	public int funSum() {
		return values[4][0];
	}
	public int disciplineSum() {
		return values[5][0];
	}
	public int energySum() {
		return values[6][1];
	}
	public int eatMinus() {
		return values[0][1];
	}
	public int sleepMinus() {
		return values[1][1];
	}
	public int healthMinus() {
		return values[2][1];
	}
	public int loveMinus() {
		return values[3][1];
	}
	public int funMinus() {
		return values[4][1];
	}
	public int disciplineMinus() {
		return values[5][1];
	}
	public int energyMinus() {
		return values[6][1];
	}
	public int timeEating() {
		return values[0][2];
	}
	public int timeSleeping() {
		return values[1][2];
	}
	public int timeHealthing() {
		return values[2][2];
	}
	public int timeDiciplining(){
		return values[5][2];
	}
}
