package co.com.sofka.questions.useCases;

import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.utils.MapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class AddAnswerUseCase implements SaveAnswer {
    private final AnswerRepository answerRepository;
    private final MapperUtils mapperUtils;
    private final GetUseCase getUseCase;
    private final SendMailUseCase mailUseCase;

    public AddAnswerUseCase(MapperUtils mapperUtils, GetUseCase getUseCase, AnswerRepository answerRepository, SendMailUseCase mailUseCase) {
        this.answerRepository = answerRepository;
        this.getUseCase = getUseCase;
        this.mapperUtils = mapperUtils;
        this.mailUseCase = mailUseCase;
    }

    public Mono<QuestionDTO> apply(AnswerDTO answerDTO) {
        System.out.println(answerDTO.getAnswer());
        System.out.println(answerDTO.getQuestionId());
        System.out.println(answerDTO.getUserId());
        System.out.println(answerDTO.getPosition());
        Objects.requireNonNull(answerDTO.getQuestionId(), "Id of the answer is required");
        return getUseCase.apply(answerDTO.getQuestionId()).flatMap(question ->
                answerRepository.save(mapperUtils.mapperToAnswer().apply(answerDTO))
                        .map(answer -> {
                            question.getAnswers().add(mapperUtils.mapEntityToAnswer().apply(answer));

                            mailUseCase.sendMail(
                                    question.getEmail(),
                                    "Han respondido a tu pregunta: " + question.getQuestion(),
                                    "<table style=\"width:100%;border-collapse:collapse;border:0;border-spacing:0;\">" +
                                            "<tr>" +
                                            "<td style=\"background:#060B26; color:white;\"><h2>Respuesta: " + answer.getAnswer() + "</h2></td>" +
                                            "</tr>" +
                                            "</table>"
                            );
                            return question;
                        })
        );
    }
}
