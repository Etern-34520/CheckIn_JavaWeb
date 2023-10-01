package indi.etern.checkIn.beans.question.interfaces.multipleChoice;

import java.util.List;

public interface MultipleCorrect extends MultipleChoice{
    List<Choice> getCorrectChoices();
    boolean checkAnswers(List<Choice> choices);
}
