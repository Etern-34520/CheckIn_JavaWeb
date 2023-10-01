package indi.etern.checkIn.beans.question;

public abstract class Question {
    protected String content;
    private String md5;
    
    public String getMd5() {
        return md5;
    }
    
    public void setMd5(String md5) {
        this.md5 = md5;
    }
    
    public Object getContent() {
        return content;
    }
}
