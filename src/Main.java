public class Main {
    public static void main(String[] args) {
        PDFToJPGConverter converter = new PDFToJPGConverter();
        /**Путь к файлу пишется в формате "C:\\Users\\quantum\\Downloads\\cat.pdf"*/
        converter.convertPDFToJPG("C:\\Users\\quantum\\Downloads\\cat.pdf", "Преобразованные в JPG");
    }
}
