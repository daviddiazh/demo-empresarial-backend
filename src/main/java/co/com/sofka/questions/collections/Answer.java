package co.com.sofka.questions.collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Answer {

    @Id
    private String id;
    private String userId;
    private String questionId;
    private String answer;
    private Integer position;

    public Integer getPosition() {
        return position;
    }

}
