package bookstore.service.report;


public class ReportFactory {

    public static ReportGenerator getReportGenerator(String type)
    {
        if(type.equals("csv"))
            return new CSVReportGenerator();
        if(type.equals("pdf"))
            return new PDFReportGenerator();
        return null;
    }

}
