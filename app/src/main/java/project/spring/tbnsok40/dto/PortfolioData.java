package project.spring.tbnsok40.dto;

import com.github.dozermapper.core.Mapping;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioData {

    private Long id;

    @Mapping("expected_salary")
    private Integer expected_salary;

    @Mapping("introduce")
    private String introduce;

    @Mapping("career_year")
    private Integer career_year;

    @Mapping("career")
    private String career;

    @Mapping("star")
    private Integer star;
}
