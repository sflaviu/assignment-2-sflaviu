package bookstore.service.report;

import bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    private BookRepository bookRepository;

    @Autowired
    public ReportServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public void generateOutOfStockReport(String type) {
        ReportGenerator reportGenerator=ReportFactory.getReportGenerator(type);
        reportGenerator.generateReport(bookRepository.findByQuantity(0));
    }
}
