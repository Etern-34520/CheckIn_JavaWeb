package indi.etern.checkIn.beans.question.interfaces.multipleChoice;

public interface SingleCorrect extends MultipleChoice{
    Choice getCorrectChoice();
    boolean checkAnswer(Choice choice);
    
    boolean checkAnswer(String choiceContent);
}
