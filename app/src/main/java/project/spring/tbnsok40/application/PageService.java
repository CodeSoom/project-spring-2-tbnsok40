package project.spring.tbnsok40.application;

import com.github.dozermapper.core.Mapper;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import project.spring.tbnsok40.domain.Page;
import project.spring.tbnsok40.domain.PageRepository;
import project.spring.tbnsok40.dto.PageData;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PageService {

    private final PageRepository pageRepository;
    private final Mapper mapper;

    public PageService(
            PageRepository pageRepository,
            Mapper dozerMapper
    ) {
        this.mapper = dozerMapper;
        this.pageRepository = pageRepository;
    }

    public List<Page> getAllPage() {
        return pageRepository.findAll();
    }

    public Page getPage(Long id) throws NotFoundException {
        return findPage(id);
    }

    public Page createPage(PageData pageData){
        Page page = mapper.map(pageData, Page.class);
        return pageRepository.save(page);
    }

    private Page findPage(Long id) throws NotFoundException {
        return pageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Page object not found"));
    }
}
