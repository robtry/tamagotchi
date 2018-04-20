public enum TipoTamagochi {
  /*
  Llevan dos valores uno que suma cada que hace una actividad y otro cuando
    pasa el tiempo y se resta.
  */
  //      eat     sleep   health  love    funny   discipline
  PLAYER({1, 1}, {1, 1}, {1, 1}, {1, 1}, {5, 5}, {1, 1});
  SLEEPY({1, 1}, {5, 5}, {1, 1}, {1, 1}, {1, 1}, {1, 1});
  ORDER ({1, 1}, {1, 1}, {1, 1}, {1, 1}, {1, 1}, {5, 5});
  LOVELY({1, 1}, {1, 1}, {1, 1}, {5, 5}, {1, 1}, {1, 1});
  private int[][] values;

  TipoTamagochi(int[] eat, int[] sleep, int[] health, int[] love, int[] funny, int[] discipline) {
    values = new int[6][2];
    for(int i = 0; i < values.length; i++) {
      switch(i) {
        case 0:
          values[i] = eat;
        break;
        case 1:
          values[i] = sleep;
        break;
        case 2:
          values[i] = health;
        break;
        case 3:
          values[i] = love;
        break;
        case 4:
          values[i] = funny;
        break;
        case 5:
          values[i] = discipline;
        break;
      }
    }
  }
  int eatSum() {
    return values[0][0];
  }
  int sleepSum() {
    return return values[1][0];
  }
  int healthSum() {
    return return values[2][0];
  }
  int loveSum() {
    return return values[3][0];
  }
  int funSum() {
    return return values[4][0];
  }
  int disciplineSum() {
    return return values[5][0];
  }
  int eatMinus() {
    return return values[0][1];
  }
  int sleepMinus() {
    return return values[1][1];
  }
  int healthMinus() {
    return return values[2][1];
  }
  int loveMinus() {
    return return values[3][1];
  }
  int funMinus() {
    return return values[4][1];
  }
  int disciplineMinus() {
    return return values[5][1];
  }
}
