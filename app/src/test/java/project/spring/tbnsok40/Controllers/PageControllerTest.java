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
import project.spring.tbnsok40.application.PageService;
import project.spring.tbnsok40.domain.Page;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@DisplayName("PageController Test")
@WebMvcTest(PageController.class)
class PageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PageService pageService;

    @BeforeEach
    void setUp() throws NotFoundException {
        Page page = Page.builder()
                .id(1L)
                .expected_salary(2000)
                .introduce("hi myname is")
                .career_year(2)
                .career("haha")
                .star(3)
                .build();

        given(pageService.getAllPage()).willReturn(List.of(page));
        given(pageService.getPage(1L)).willReturn(page);
    }

    @Nested
    @DisplayName("GetAllPage 메소드는, ")
    class Describe_list {

        @Test
        @DisplayName("모든 페이지를 리턴한다.")
        void list() throws Exception {
            mockMvc.perform(
                    get("/page")
                            .accept(MediaType.APPLICATION_JSON_UTF8)
            )
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("hi")));
        }
    }

    @Nested
    @DisplayName("GetOnePage 메소드는, ")
    class Describe_getOnePage{

        @Test
        @DisplayName("원하는 특정 페이지를 리턴한다.")
        void getOnePage() throws Exception{
            mockMvc.perform(
                    get("/page/1")
                            .accept(MediaType.APPLICATION_JSON_UTF8)
            )
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("1")));
        }
    }
}