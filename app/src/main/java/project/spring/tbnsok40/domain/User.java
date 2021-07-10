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
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String user_name;

    private String email;

    private String password;

    private Integer career_year;

    private Object photo;

    @OneToOne
    private Page page;

}

