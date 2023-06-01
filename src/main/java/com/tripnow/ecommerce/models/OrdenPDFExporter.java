package com.tripnow.ecommerce.models;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import com.itextpdf.text.Image;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;




public class OrdenPDFExporter {
    private Orden orden;
    private Cliente cliente;

    public OrdenPDFExporter(Cliente cliente, Orden orden) {
        this.cliente = cliente;
        this.orden = orden;

    }

    private static final String LOGO_PATH = new File("C:Users/Usuarios/Desktop/ecommerceMerge/src/main/resources/static/assets/logoTripNow.png").getAbsolutePath();

    public void usePDFExport(ByteArrayOutputStream pdfOutputStream) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, pdfOutputStream);
        document.open();

        // Agregar contenido al documento PDF
        addLogo(document);
        addClientInfo(document);
        addOrderInfo(document);

        document.close();
    }

    private void addLogo(Document document) throws IOException, DocumentException {
        InputStream is = getClass().getResourceAsStream("/static/assets/logoTripNow.png");
        Image logo = Image.getInstance(IOUtils.toByteArray(is));
        logo.setAbsolutePosition(50, 770); // Reduce este valor para bajar el logo
        logo.scaleToFit(40, 40); // Ajusta el tamaño del logo
        document.add(logo);
        /*
        Image logo = Image.getInstance(LOGO_PATH);
        logo.setAbsolutePosition(50, 770);
        logo.scaleToFit(40, 40);
        document.add(logo);*/
    }

    private void addClientInfo(Document document) throws DocumentException {
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(BaseColor.BLACK);

        Paragraph clientInfo = new Paragraph("Cliente: " + cliente.getApellido() + " " + cliente.getNombre(), font);
        clientInfo.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(clientInfo);
    }

    private void addOrderInfo(Document document) throws DocumentException {
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(BaseColor.BLACK);

        Paragraph orderId = new Paragraph("Orden Numero: " + orden.getId(), font);
        orderId.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(orderId);


        Paragraph fecha = new Paragraph("Fecha Orden: " + orden.getFechaCreacion(), font);
        fecha.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(fecha);

        Paragraph paquete = new Paragraph("Paquetes: " + orden.getPaquetes(), font);
        paquete.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(paquete);

        Paragraph precioPaquete = new Paragraph("Precio Paquete: " + orden.getPrecioTotalPaquete(), font);
        precioPaquete.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(precioPaquete);

        Paragraph precioOrden = new Paragraph("Precio Orden: " + orden.getPrecioTotalOrden(), font);
        precioOrden.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(precioOrden);


    }



}