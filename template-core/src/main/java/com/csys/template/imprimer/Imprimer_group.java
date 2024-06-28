/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csys.template.imprimer;

import com.csys.template.domain.GroupUser;
import com.csys.template.domain.Groupe;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author C
 */
public class Imprimer_group {
       private List<Groupe> listGroupe;

    public Imprimer_group(List<Groupe> listGroupe) {
        this.listGroupe = listGroupe;
    }

    private void writeTableData(PdfPTable table) {
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        BaseColor Blue = new BaseColor(72, 61,139);
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        headerFont.setColor(BaseColor.WHITE);

        PdfPCell headerCell = new PdfPCell(new Phrase("Groupe", headerFont));
        headerCell.setBackgroundColor(Blue);
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell);

        headerCell = new PdfPCell(new Phrase("Désignation", headerFont));
        headerCell.setBackgroundColor(Blue);
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell);

  
        headerCell = new PdfPCell(new Phrase("Utilisateur de création", headerFont));
        headerCell.setBackgroundColor(Blue);
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell);

        headerCell = new PdfPCell(new Phrase("Date de création", headerFont));
        headerCell.setBackgroundColor(Blue);
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell);

        headerCell = new PdfPCell(new Phrase("Active", headerFont));
        headerCell.setBackgroundColor(Blue);
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell);

        for (Groupe group : listGroupe) {
            table.addCell(group.getGroupe());
            table.addCell(group.getDesignation());
            table.addCell(group.getUserCreation());
                        table.addCell(String.valueOf(group.getDateCreation()));

            table.addCell(String.valueOf(group.getActive()));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Paragraph title = new Paragraph("Liste des groups", font);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        PdfPTable table = new PdfPTable(5); // Corrected the number of columns to match the headers
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10);

        writeTableData(table);

        document.add(table);

        document.close();
    }
}
