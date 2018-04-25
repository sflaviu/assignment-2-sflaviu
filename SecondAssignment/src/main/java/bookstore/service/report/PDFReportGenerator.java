package bookstore.service.report;

import bookstore.entity.Book;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class PDFReportGenerator implements ReportGenerator {

    @Override
    public void generateReport(List<Book> outOfStockBooks) {
        PDDocument document = new PDDocument();

        try {

            PDPage myPage = new PDPage();

            document.addPage(myPage);
            PDPageContentStream contentStream = new PDPageContentStream(document, myPage);


            contentStream.beginText();
            contentStream.newLineAtOffset(25, 800);

            String header="Out of stock report";
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 18);

            contentStream.showText(header);
            contentStream.newLine();

            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

            int i=1;
            for(Book book:outOfStockBooks)
            {
                contentStream.showText(Integer.toString(i));
                contentStream.newLine();
                contentStream.showText("Name: "+book.getName());
                contentStream.newLine();
                contentStream.showText("ISBN: "+book.getIsbn());
                contentStream.newLine();
                contentStream.showText("Genre: "+book.getGenre());
                contentStream.newLine();
                contentStream.showText("Price: "+book.getPrice());
                contentStream.newLine();
                i++;
            }


            contentStream.endText();

            contentStream.close();

            document.save("StockReport" + new Date().toString() + ".pdf");
            document.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
