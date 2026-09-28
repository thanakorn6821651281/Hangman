package Domain;
import java.util.LinkedHashSet;
import java.util.Set;

public class HangmanGame {
    private final Word word;
    private final Difficulty difficulty;
    private final Set<Character> guessed = new LinkedHashSet<>();
    private int wrong;

    public HangmanGame(Word word, Difficulty difficulty){
        this.word=word; this.difficulty=difficulty;
    }
    public boolean guess(char input){
        if(getStatus()!=GameStatus.PLAYING) return false;
        char c=Character.toUpperCase(input);
        if(c<'A'||c>'Z'||guessed.contains(c)) return false;
        guessed.add(c);
        if(word.getWord().indexOf(c)<0) wrong++;
        return true;
    }
    public String displayWord(){
        StringBuilder s=new StringBuilder();
        for(char c:word.getWord().toCharArray()){
            s.append(guessed.contains(c)?c:'_').append(' ');
        }
        return s.toString().trim();
    }
    public GameStatus getStatus(){
        boolean complete=true;
        for(char c:word.getWord().toCharArray())
            if(!guessed.contains(c)){ complete=false; break; }
        if(complete) return GameStatus.WON;
        if(wrong>=difficulty.getMaxWrong()) return GameStatus.LOST;
        return GameStatus.PLAYING;
    }
    public boolean isGuessed(char c){ return guessed.contains(Character.toUpperCase(c)); }
    public int getWrong(){ return wrong; }
    public int getMaxWrong(){ return difficulty.getMaxWrong(); }
    public String getAnswer(){ return word.getWord(); }
    public String getHint(){ return word.getHint(); }
    public String getCategory(){ return word.getCategory(); }
    public Difficulty getDifficulty(){ return difficulty; }
}
