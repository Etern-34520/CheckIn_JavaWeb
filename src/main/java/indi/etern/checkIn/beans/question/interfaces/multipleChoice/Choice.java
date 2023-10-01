package indi.etern.checkIn.beans.question.interfaces.multipleChoice;

public class Choice {
    private final String content;
    private final boolean isCorrect;
    private String md5;
    
    public String getMd5() {
        return md5;
    }
    
    public void setMd5(String md5) {
        this.md5 = md5;
    }
    
    public Choice(String content, boolean isCorrect) {
        this.content = content;
        this.isCorrect = isCorrect;
    }
    public String getContent() {
        return content;
    }
    public boolean isCorrect() {
        return isCorrect;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Choice) {
            Choice choice = (Choice) obj;
            return choice.content.equals(this.content) && choice.isCorrect == this.isCorrect;
        } else {
            return false;
        }
    }
}
