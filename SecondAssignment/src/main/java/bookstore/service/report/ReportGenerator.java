package bookstore.service.report;

import bookstore.entity.Book;

import java.util.List;

public interface ReportGenerator {

    void generateReport(List<Book> reportBooks);
}
