package co.com.sofka.questions.collections;

import co.com.sofka.questions.utils.Category;
import co.com.sofka.questions.utils.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Question {
    @Id
    private String id;
    private String userId;
    private String question;
    private Type type;
    private Category category;
    private String email;

}
