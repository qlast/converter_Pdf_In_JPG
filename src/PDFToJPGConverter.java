import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.rendering.ImageType;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PDFToJPGConverter {
    /**
     * public void convertPDFToJPG(String pdfFilePath, String outputFolder):
     * Метод принимает два параметра - путь к PDF-файлу (pdfFilePath) и путь
     * к папке для сохранения JPG-изображений (outputFolder).
     */
    public void convertPDFToJPG(String pdfFilePath, String outputFolder) {
        try {
            /** Создается объект File для представления PDF-файла по указанному пути. */
            File pdfFile = new File(pdfFilePath);
            /** Загружается PDF-документ с использованием Loader из библиотеки PDFBox. */
            PDDocument document = Loader.loadPDF(pdfFile);
            /** Создается объект PDFRenderer, который будет использоваться для рендеринга изображений из PDF-документа.*/
            PDFRenderer pdfRenderer = new PDFRenderer(document);

            /** Создается объект File для представления папки, в которую будут сохранены JPG-изображения.*/
            File outputDir = new File(outputFolder);
            /** Создается папка (и подпапки, если необходимо), если они еще не существуют. */
            outputDir.mkdirs();

            for (int page = 0; page < document.getNumberOfPages(); page++) {
                /** Для каждой страницы создается объект BufferedImage, который представляет из себя изображение с
                 * разрешением 300 DPI.*/
                BufferedImage image = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
                /** Формируется имя файла для JPG-изображения например, "page_1.jpg" для первой страницы*/
                String jpgFileName = "page_" + (page + 1) + ".jpg";
                /** Создается объект File для представления пути сохранения JPG-изображения.*/
                File jpgFile = new File(outputDir, jpgFileName);
                /**Само изображение записывается в файл формата JPG*/
                ImageIO.write(image, "jpg", jpgFile);
            }
            /** Закрывается PDF-документ после обработки всех страниц.*/
            document.close();
            System.out.println("Конвертация завершена.");
            /** Обрабатываются исключения ввода-вывода. Если произойдет ошибка при чтении/записи файлов,
             * будет выведено сообщение об ошибке. */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
