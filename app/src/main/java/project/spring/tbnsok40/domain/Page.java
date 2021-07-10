package project.spring.tbnsok40.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Page {

    @Id
    @GeneratedValue
    private Long id;

    private Integer expected_salary;

    private String introduce;

    private Integer career_year;

    private String career;

    private Integer star;

    public void changeWith(Page source) {
        this.expected_salary = source.expected_salary;
        this.introduce = source.introduce;
        this.career_year = source.career_year;
        this.career = source.career;
        this.star = source.star;
    }
}
