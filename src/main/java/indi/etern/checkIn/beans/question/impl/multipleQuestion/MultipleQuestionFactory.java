package indi.etern.checkIn.beans.question.impl.multipleQuestion;

import indi.etern.checkIn.beans.question.Question;
import indi.etern.checkIn.beans.question.interfaces.multipleChoice.Choice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MultipleQuestionFactory {
    Question multipleQuestion;
    List<Choice> choices = new ArrayList<>();
    boolean haveBuilt = false;
    String questionContent;
    public MultipleQuestionFactory addChoice(Choice choice){
        choices.add(choice);
        return this;
    }
    public MultipleQuestionFactory addAllChoices(List<Choice> choices){
        this.choices.addAll(choices);
        return this;
    }
    public MultipleQuestionFactory addChoices(Choice... choices){
        Collections.addAll(this.choices, choices);
        return this;
    }
    public MultipleQuestionFactory setQuestionContent(String content){
        questionContent = content;
        return this;
    }
    public Question build(){
        if (haveBuilt){
            throw new MultipleQuestionFactoryException("MultipleQuestionFactory has already built");
        }
        boolean singleCorrect = false;
        boolean multipleCorrect = false;
        for (Choice choice:choices) {
            if (choice.isCorrect()&&!multipleCorrect&&!singleCorrect){
                singleCorrect = true;
            } else if (choice.isCorrect()&&singleCorrect){
                multipleCorrect = true;
                singleCorrect = false;
            }
        }
        if (!singleCorrect&&!multipleCorrect){
            throw new MultipleQuestionFactoryException("No correct choice found");
        }
        if (questionContent==null){
            throw new MultipleQuestionFactoryException("Question content not set");
        }
        if (choices.size() < 2) {
            throw new QuestionException("Less than two choices");
        }
        if (singleCorrect){
            multipleQuestion = new SingleCorrectQuestion(questionContent,choices);
        } else {
            multipleQuestion = new MultipleCorrectQuestion(questionContent,choices);
        }
        haveBuilt = true;
        return multipleQuestion;
    }
}
