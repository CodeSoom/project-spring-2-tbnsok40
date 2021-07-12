package project.spring.tbnsok40.Controllers;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import project.spring.tbnsok40.application.PortfolioService;
import project.spring.tbnsok40.domain.Portfolio;
import project.spring.tbnsok40.dto.PortfolioData;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping
    public List<Portfolio> GetAllPortfolio() {
        return portfolioService.getAllPortfolio();
    }

    @GetMapping("{id}")
    public Portfolio getOnePortfolio(@PathVariable Long id) throws NotFoundException {
        return portfolioService.getPortfolio(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Portfolio CreatePortfolio(@RequestBody @Valid PortfolioData portfolioData) {
        return portfolioService.createPortfolio(portfolioData);
    }

    @PatchMapping("{id}")
    public Portfolio updatePortfolio(
            @PathVariable Long id,
            @RequestBody @Valid PortfolioData portfolioData) throws NotFoundException {
        return portfolioService.updatePortfolio(id, portfolioData);
    }
}
