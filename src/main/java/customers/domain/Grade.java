package customers.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Grade {
    private String grade;
    private String courseName;

    @Override
    public String toString() {
        return "Grade{" +
                "grade='" + grade + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
