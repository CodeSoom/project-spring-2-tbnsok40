package project.spring.tbnsok40.Controllers;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;
import project.spring.tbnsok40.application.PageService;
import project.spring.tbnsok40.domain.Page;
import project.spring.tbnsok40.dto.PageData;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/page")
public class PageController {

    private final PageService pageService;

    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping
    public List<Page> GetAllPage() {
        return pageService.getAllPage();
    }

    @GetMapping("{id}")
    public Page getOnePage(@PathVariable Long id) throws NotFoundException {
        return pageService.getPage(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Page CreatePage(@RequestBody @Valid PageData pageData){
        return pageService.createPage(pageData);
    }
}
