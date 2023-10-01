package indi.etern.checkIn.beans.question.impl.multipleQuestion;

import indi.etern.checkIn.beans.question.Question;
import indi.etern.checkIn.beans.question.interfaces.multipleChoice.Choice;
import indi.etern.checkIn.beans.question.interfaces.multipleChoice.MultipleCorrect;

import java.util.ArrayList;
import java.util.List;

public class MultipleCorrectQuestion extends Question implements MultipleCorrect {
    List<Choice> choices;
    List<Choice> correctChoices;
    
    public MultipleCorrectQuestion(String questionContent, List<Choice> choices) {
        if (choices.size() < 2) {
            throw new QuestionException("Less than two choices");
        }
        if (questionContent == null) {
            throw new QuestionException("Question content not set");
        }
        content = questionContent;
        this.choices = choices;
        this.correctChoices = getCorrectChoices(choices);
    }
    
    private static List<Choice> getCorrectChoices(List<Choice> choices) {
        List<Choice> correctChoice = new ArrayList<>();
        for (Choice choice : choices) {
            if (choice.isCorrect()) {
                correctChoice.add(choice);
            }
        }
        if (correctChoice.isEmpty()){
            throw new QuestionException("No correct choice found");
        } else if (correctChoice.size() == 1){
            throw new QuestionException("Only one correct choice found, use SingleCorrectQuestion instead");
        }
        return correctChoice;
    }
    
    @Override
    public List<Choice> getChoices() {
        return choices;
    }
    
    @Override
    public List<Choice> getCorrectChoices() {
        return correctChoices;
    }
    
    @Override
    public boolean checkAnswers(List<Choice> choices) {
        boolean correct = true;
        List<Choice> correctChoicesCopy = new ArrayList<>(correctChoices);
        for (Choice choice : choices){
            for (Choice choice1 : correctChoicesCopy) {
                if (choice.getContent().equals(choice1.getContent())) {
                    correctChoicesCopy.remove(choice1);
                    correct = true;
                    break;
                } else {
                    correct = false;
                }
            }
        }
        return correct;
    }
}
