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

    @OneToOne
    private User user;

}
