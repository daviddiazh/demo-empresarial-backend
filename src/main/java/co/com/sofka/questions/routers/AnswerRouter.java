package co.com.sofka.questions.routers;

import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.useCases.AddAnswerUseCase;
import co.com.sofka.questions.useCases.DeleteAnswerUseCase;
import co.com.sofka.questions.useCases.SendMailUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AnswerRouter {


    @Bean
    public RouterFunction<ServerResponse> addAnswer(AddAnswerUseCase useCase, SendMailUseCase mailUseCase) {
        return route(POST("answer/add").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(AnswerDTO.class)
                        .flatMap(addAnswerDTO -> useCase.apply(addAnswerDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
        );
    }


    @Bean
    public RouterFunction<ServerResponse> deleteAnswer(DeleteAnswerUseCase useCase) {
        return route(DELETE("answer/delete/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("id")), Void.class))
        );
    }

}
