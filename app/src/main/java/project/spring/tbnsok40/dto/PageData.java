package project.spring.tbnsok40.dto;

import com.github.dozermapper.core.Mapping;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageData {

    private Long id;

    @Mapping("")
    private Integer expected_salary;

    private String introduce;

    private Integer career_year;

    private String career;

    private Integer star;

}
