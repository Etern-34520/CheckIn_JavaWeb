import indi.etern.checkIn.beans.question.Question;
import indi.etern.checkIn.beans.question.impl.multipleQuestion.MultipleCorrectQuestion;
import indi.etern.checkIn.beans.question.impl.multipleQuestion.MultipleQuestionFactory;
import indi.etern.checkIn.beans.question.impl.multipleQuestion.SingleCorrectQuestion;
import indi.etern.checkIn.beans.question.interfaces.multipleChoice.Choice;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class QuestionTest {
    List<Choice> choices1 = new ArrayList<>();
    {
        choices1.add(new Choice("A", true));
        choices1.add(new Choice("B", false));
        choices1.add(new Choice("C", false));
        choices1.add(new Choice("D", false));
    }
    Question question1;
    List<Choice> choices2 = new ArrayList<>();
    {
        choices2.add(new Choice("A", true));
        choices2.add(new Choice("B", true));
        choices2.add(new Choice("C", false));
        choices2.add(new Choice("D", false));
    }
    Question question2;
    @Test
    public void testGetAnswerAndContent() {
        {
            MultipleQuestionFactory multipleQuestionFactory = new MultipleQuestionFactory();
            Question question = multipleQuestionFactory.addAllChoices(choices1).setQuestionContent("testQuestion1").build();
            assert question instanceof SingleCorrectQuestion;
            SingleCorrectQuestion multipleCorrectQuestion = (SingleCorrectQuestion) question;
            assert multipleCorrectQuestion.getCorrectChoice().getContent().equals("A");
            assert multipleCorrectQuestion.getContent().equals("testQuestion1");
            question1 = question;
        }
        {
            MultipleQuestionFactory multipleQuestionFactory = new MultipleQuestionFactory();
            Question question = multipleQuestionFactory.addAllChoices(choices2).setQuestionContent("testQuestion2").build();
            assert question instanceof MultipleCorrectQuestion;
            MultipleCorrectQuestion multipleCorrectQuestion = (MultipleCorrectQuestion) question;
            assert multipleCorrectQuestion.getCorrectChoices().get(0).getContent().equals("A");
            assert multipleCorrectQuestion.getCorrectChoices().get(1).getContent().equals("B");
            assert multipleCorrectQuestion.getContent().equals("testQuestion2");
            question2 = question;
        }
    }
    @Test
    public void testExceptions(){
        List<Choice> choices = new ArrayList<>();
        {
            choices.add(new Choice("A", false));
            choices.add(new Choice("B", false));
            choices.add(new Choice("C", false));
            choices.add(new Choice("D", false));
        }
        MultipleQuestionFactory multipleQuestionFactory = new MultipleQuestionFactory();
        try {
            Question question = multipleQuestionFactory.addAllChoices(choices).build();
        } catch (Exception e){
            assert e.getMessage().equals("No correct choice found");
        }
        
        choices.add(new Choice("E", true));
        multipleQuestionFactory.addAllChoices(choices);
        try {
            Question question = multipleQuestionFactory.build();
        } catch (Exception e){
            assert e.getMessage().equals("Question content not set");
        }
        Question question = multipleQuestionFactory.setQuestionContent("testQuestion").build();
        try {
            Question question1 = multipleQuestionFactory.addAllChoices(choices).build();
        } catch (Exception e){
            assert e.getMessage().equals("MultipleQuestionFactory has already built");
        }
    }
    @Test
    public void testCheckAnswer() {
        testGetAnswerAndContent();
        assert question1 instanceof SingleCorrectQuestion;
        assert question2 instanceof MultipleCorrectQuestion;
        assert ((SingleCorrectQuestion) question1).checkAnswer(new Choice("A", true));
        assert !((SingleCorrectQuestion) question1).checkAnswer(new Choice("A", false));
        assert !((SingleCorrectQuestion) question1).checkAnswer(new Choice("B", true));
        assert ((MultipleCorrectQuestion) question2).checkAnswers(choices2);
        choices2.remove(0);
        assert !((MultipleCorrectQuestion) question2).checkAnswers(choices2);
    }
}
