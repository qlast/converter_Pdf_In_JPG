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
     * */
    public void convertPDFToJPG(String pdfFilePath, String outputFolder) {
        try {
            File pdfFile = new File(pdfFilePath);
            PDDocument document = Loader.loadPDF(pdfFile);
            PDFRenderer pdfRenderer = new PDFRenderer(document);

            File outputDir = new File(outputFolder);
            outputDir.mkdirs();

            for (int page = 0; page < document.getNumberOfPages(); page++) {
                BufferedImage image = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);

                String jpgFileName = "page_" + (page + 1) + ".jpg";
                File jpgFile = new File(outputDir, jpgFileName);
                ImageIO.write(image, "jpg", jpgFile);
            }

            document.close();
            System.out.println("Конвертация завершена.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
