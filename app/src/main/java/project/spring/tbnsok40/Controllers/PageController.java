package project.spring.tbnsok40.Controllers;

import javassist.NotFoundException;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;
import project.spring.tbnsok40.application.PageService;
import project.spring.tbnsok40.domain.Page;

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

//    @PostMapping
//    public Task CreatePage(){
//        return pageService.
//    }
}
