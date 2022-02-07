package by.paul.monsterservice.entity;

public enum DifficultyEnum {
  EASY("EASY",  new int[]{25, 50,75,125,250,300,350,400,450,550,600,800,1000,1100,1250,1400,1600,1000,2000,2100,2400,2800}),
  MEDIUM("MEDIUM",  new int[]{50,100,150,250,500,600,750,900,1100,1200,1600,2000,2200,2500,2800,3200,3900,4200,4900,5700}),
  HARD("HARD",  new int[]{75,150,225,375,750,900,110,1400,1600,1900,2400,3000,3400,3800,4300,4800,5900,6300,7300,8500}),
  DEADLY("DEADLY", new int[]{100,200,400,500,1100,1400,1700,2100,2400,2800,3600,4500,5100,5700,6400,7200,8800,9500,10900,12700});
  private String difficulty;
  private int [] expList;
  DifficultyEnum(String difficulty,int[] expList) {
    this.difficulty = difficulty;
    this.expList = expList;
  }

  public int getExpLevel(int level) {
    return expList[level-1];
  }

  public boolean isDifficulty(String userDifficulty) {
    return userDifficulty.equals(difficulty);
  }
}
