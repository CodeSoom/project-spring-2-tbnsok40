package project.spring.tbnsok40.application;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import project.spring.tbnsok40.domain.Page;
import project.spring.tbnsok40.domain.PageRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PageService {

    private final PageRepository pageRepository;

    public PageService(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    public List<Page> getAllPage() {
        return pageRepository.findAll();
    }

    public Page getPage(Long id) throws NotFoundException {
        return findPage(id);
    }

//    public Page createPage(PageData pageData){
//        return pageRepository.save(pageData)
//    }

    private Page findPage(Long id) throws NotFoundException {
        return pageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Page object not found"));
    }
}
