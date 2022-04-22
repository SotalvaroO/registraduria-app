package soft.synergy.registraduriaapp.report.utils.report;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import soft.synergy.registraduriaapp.report.models.dtos.ReportDto;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public class ReportExporterPDF {

    private List<ReportDto> report;

    private void writeHeader(PdfPTable tabla) {
        PdfPCell cell = new PdfPCell();

        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("id_puesto", font));
        tabla.addCell(cell);

        cell.setPhrase(new Phrase("nombre", font));
        tabla.addCell(cell);

        cell.setPhrase(new Phrase("direccion", font));
        tabla.addCell(cell);

        cell.setPhrase(new Phrase("id_mesa", font));
        tabla.addCell(cell);

        cell.setPhrase(new Phrase("votos_totales", font));
        tabla.addCell(cell);

    }

    private void writeDataInTable(PdfPTable table) {
        for (ReportDto r : report) {
            table.addCell(r.getStationId());
            table.addCell(r.getName());
            table.addCell(r.getAddress());
            table.addCell(r.getStandId());
            table.addCell(String.valueOf(r.getTotalPolls()));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.BLUE);
        font.setSize(18);

        Paragraph title = new Paragraph("Lista de mesas por puesto", font);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        table.setWidths(new float[] { 1f, 2.3f, 2.3f, 6f, 2.9f});
        table.setWidthPercentage(110);

        writeHeader(table);
        writeDataInTable(table);

        document.add(table);
        document.close();
    }

}
