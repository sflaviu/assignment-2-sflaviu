package bookstore.service.report;

import bookstore.entity.Book;
import bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

public class CSVReportGenerator implements ReportGenerator {

    @Override
    public void generateReport(List<Book> outOfStockBooks) {

        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File("StockReport" + new Date().toString() + ".csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder builder = new StringBuilder();

        String ColumnNamesList = "Name,Genre,ISBN,price";

        builder.append(ColumnNamesList + "\n");

        for(Book book:outOfStockBooks) {
            builder.append(book.getName() + ",");
            builder.append(book.getGenre() + ",");
            builder.append(book.getIsbn() + ",");
            builder.append(book.getPrice() + ",");

            builder.append('\n');
        }
        pw.write(builder.toString());
        pw.close();
    }
}
