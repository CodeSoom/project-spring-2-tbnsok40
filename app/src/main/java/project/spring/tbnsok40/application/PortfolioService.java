package project.spring.tbnsok40.application;

import com.github.dozermapper.core.Mapper;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import project.spring.tbnsok40.domain.Portfolio;
import project.spring.tbnsok40.domain.PortfolioRepository;
import project.spring.tbnsok40.dto.PortfolioData;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final Mapper mapper;

    public PortfolioService(
            PortfolioRepository portfolioRepository,
            Mapper dozerMapper
    ) {
        this.mapper = dozerMapper;
        this.portfolioRepository = portfolioRepository;
    }

    public List<Portfolio> getAllPortfolio() {
        return portfolioRepository.findAll();
    }

    public Portfolio getPortfolio(Long id) throws NotFoundException {
        return findPortfolio(id);
    }

    public Portfolio createPortfolio(PortfolioData portfolioData){
        Portfolio portfolio = mapper.map(portfolioData, Portfolio.class);
        return portfolioRepository.save(portfolio);
    }

    private Portfolio findPortfolio(Long id) throws NotFoundException {
        return portfolioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Portfolio object not found"));
    }

    public Portfolio updatePortfolio(Long id, PortfolioData portfolioData) throws NotFoundException {
        Portfolio portfolio = findPortfolio(id);
        portfolio.changeWith(mapper.map(portfolioData, Portfolio.class));

        return portfolio;
    }
}
