package project.spring.tbnsok40.Controllers;

import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import project.spring.tbnsok40.application.PortfolioService;
import project.spring.tbnsok40.domain.Portfolio;
import project.spring.tbnsok40.dto.PortfolioData;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.in;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@DisplayName("PortfolioController Test")
@WebMvcTest(PortfolioController.class)
class PortfolioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PortfolioService portfolioService;

    @BeforeEach
    void setUp() throws NotFoundException {
        Portfolio portfolio = Portfolio.builder()
                .id(1L)
                .expected_salary(2000)
                .introduce("I'm Backend developer")
                .career_year(2)
                .career("Codesoom")
                .star(3)
                .build();

        given(portfolioService.getAllPortfolio()).willReturn(List.of(portfolio));
        given(portfolioService.getPortfolio(1L)).willReturn(portfolio);
        given(portfolioService.createPortfolio(any(PortfolioData.class)))
                .willReturn(portfolio);

        given(portfolioService.updatePortfolio(eq(1L), any(PortfolioData.class)))
                .will(invocation -> {
                    Long id = invocation.getArgument(0);
                    PortfolioData portfolioData = invocation.getArgument(1);
                    return Portfolio.builder()
                            .id(id)
                            .expected_salary(portfolioData.getExpected_salary())
                            .introduce(portfolioData.getIntroduce())
                            .career_year(portfolioData.getCareer_year())
                            .career(portfolioData.getCareer())
                            .star(portfolioData.getStar())
                            .build();
                });

    }

    @Nested
    @DisplayName("updatePortfolio ????????????, ")
    class Describe_update {

        @Nested
        @DisplayName("????????? ???????????? ???????????????, ")
        class Context_WithValidData {


            @Test
            @DisplayName("????????? ????????? ????????????. ")
            void updatePortfolio() throws Exception {
                mockMvc.perform(
                        patch("/portfolio/1")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"expected_salary\":\"200000\"," +
                                        "\"introduce\":\"frontEnd\"," +
                                        "\"career_year\":2," +
                                        "\"career\":\"toss\"," +
                                        "\"star\":5" +
                                        "}")
                )
                        .andExpect(status().isOk())
                        .andExpect(content().string(containsString("frontEnd")));
            }
        }
    }

    @Nested
    @DisplayName("GetAllPortfolio ????????????, ")
    class Describe_list {

        @Test
        @DisplayName("?????? ???????????? ????????????.")
        void list() throws Exception {
            mockMvc.perform(
                    get("/portfolio")
                            .accept(MediaType.APPLICATION_JSON_UTF8)
            )
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("Backend")));
        }
    }

    @Nested
    @DisplayName("GetOnePortfolio ????????????, ")
    class Describe_getOnePortfolio {

        @Nested
        @DisplayName("???????????? ?????? ???????????? ????????? ???, ")
        class Context_getExistedPortfolio {

            @Test
            @DisplayName("?????? ???????????? ????????????.")
            void getOnePortfolio() throws Exception {
                mockMvc.perform(
                        get("/portfolio/1")
                                .accept(MediaType.APPLICATION_JSON)
                )
                        .andExpect(status().isOk())
                        .andExpect(content().string(containsString("1")));
            }
        }

        @Nested
        @DisplayName("???????????? ???????????? ???????????? ?????????, ")
        class Context_getNotExistedPortfolio {

            @BeforeEach
            void setUp() throws NotFoundException {
                given(portfolioService.getPortfolio(1000L)).willThrow(new NotFoundException("Portfolio Not Found"));
            }

            @Test
            @DisplayName("????????? ?????????.")
            void getOnePortfolio() throws Exception {
                mockMvc.perform(
                        get("/portfolio/1000")
                ).andExpect(status().isNotFound());
            }
        }
    }


    @Nested
    @DisplayName("CreatePortfolio ????????????")
    class Describe_create {

        @Nested
        @DisplayName("????????? ???????????? ???????????????,  ")
        class Context_createPortfolio {

            @Test
            @DisplayName("????????? Portfolio ??? ????????????.  ")
            void create() throws Exception {
                mockMvc.perform(
                        post("/portfolio")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"expected_salary\": 1000," +
                                        "\"introduce\" : \"hi myname is\"," +
                                        "\"career_year\" : 2," +
                                        "\"career\": \"nana\"," +
                                        "\"star\": 3" +
                                        "}")
                ).andExpect(status().isCreated());
            }
        }
    }
}