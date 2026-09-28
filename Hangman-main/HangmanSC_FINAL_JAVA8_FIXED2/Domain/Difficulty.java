package Domain;
public enum Difficulty {
    EASY(6), MEDIUM(5), HARD(4);
    private final int maxWrong;
    Difficulty(int maxWrong){ this.maxWrong=maxWrong; }
    public int getMaxWrong(){ return maxWrong; }
}
