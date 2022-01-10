package co.com.sofka.questions.model;


import co.com.sofka.questions.utils.Category;
import co.com.sofka.questions.utils.Type;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class QuestionDTO {
    private String id;
    @NotBlank
    private String userId;
    @NotBlank
    private String question;
    @NotNull
    private Type type;
    @NotNull
    private Category category;
    @NotBlank
    private String email;
    private List<AnswerDTO> answers;


    public QuestionDTO() {

    }

    public QuestionDTO(String userId, String question, Type type, Category category, String email) {
        this.userId = userId;
        this.question = question;
        this.type = type;
        this.category = category;
        this.email = email;
    }

    public QuestionDTO(String id, String userId, String question, Type type, Category category, String email) {
        this.id = id;
        this.userId = userId;
        this.question = question;
        this.type = type;
        this.category = category;
        this.email = email;
    }

    public List<AnswerDTO> getAnswers() {
        this.answers = Optional.ofNullable(answers).orElse(new ArrayList<>());
        this.category = category;
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", question='" + question + '\'' +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionDTO that = (QuestionDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
