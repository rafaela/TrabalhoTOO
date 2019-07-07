package too.trabalho.spa.arquivo;

import static too.trabalho.spa.dados.Constantes.CAMINHO_IMG;
import static too.trabalho.spa.dados.Constantes.CAMINHO_PDF;
import static too.trabalho.spa.dados.Constantes.TITULO_INST;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import too.trabalho.spa.tipos.RelatorioAvaliacao;

/**
 * Classe para a geração de arquivos pdf
 * @author Rafaela
 *
 */
public class PDF {
	//private static int quantidade = 0;
	/**
	 * Cria o arquivo pdf
	 * @param lista dados que serão inseridos
	 * @param titulo nome da pesquisa
	 * @param nomeImg nome da imagem a ser inserida
	 */
	public static void exportaPDF(List<RelatorioAvaliacao> lista, String titulo, String nomeImg){
		// criação do documento
        Document document = new Document();
        try {
           PdfWriter.getInstance(document, new FileOutputStream(CAMINHO_PDF + titulo + nomeImg + ".pdf"));
           document.open();
           Paragraph titulo_instituto = new Paragraph(TITULO_INST + " " + titulo);
           Font f3 = new Font(Font.FontFamily.TIMES_ROMAN, 14f, Font.NORMAL);
           f3.getCalculatedLeading(2f);
           f3.setSize(5f);
           titulo_instituto.setFont(f3);
           titulo_instituto.setIndentationLeft(80f);//Movimenta horizontal
           titulo_instituto.setLeading(20f);//Movimenta vertical
           
           document.add(titulo_instituto);
           
           Paragraph p = new Paragraph("\n");
           p.setLeading(30f);
           document.add(p);
           
           PdfPTable table = new PdfPTable(lista.size() + 1);
           
           table = criaTabela(lista);
           document.add(table);
           
           Image image = Image.getInstance(CAMINHO_IMG + nomeImg + ".jpg");
          image.scaleToFit(500, 500);
           document.add(image);
           
           	
        }catch(DocumentException de) {
            System.err.println(de.getMessage());
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        document.close();
    }   
	
	/**
	 * Cria a tabela que será inserida no PDF
	 * @param lista dados que serão inseridos
	 * @return tabela 
	 */
	public static PdfPTable criaTabela(List<RelatorioAvaliacao> lista){
		int numLinhas = lista.get(0).getMediaList().size();
		if (numLinhas == 0) return null;

		int numColunas = lista.size() + 1;
		
		PdfPTable table = new PdfPTable(lista.size() + 1);
		
		PdfPCell pergunta = new PdfPCell(new Phrase("Pergunta"));
        pergunta.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(pergunta);
        for(RelatorioAvaliacao rel : lista){
        	PdfPCell coluna = new PdfPCell(new Phrase(rel.getCategoria().contains("em") 
    				? rel.getCategoria().split("em ")[1] : rel.getCategoria()));
            coluna.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(coluna);
        }
        
        String[] perguntas = new String[lista.get(0).getMediaList().size()];
		for(int i = 0; i < lista.get(0).getMediaList().size(); i++)
			perguntas[i] = lista.get(0).getMediaList().get(i).getPergunta();
		for(int i = 0; i < numLinhas; i++){
			String dados[] = new String[numColunas + 1];
			dados[0] = perguntas[i];
			PdfPCell colP = new PdfPCell(new Phrase(dados[0]));
            colP.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(colP);
			for(int z = 1; z < numColunas; z++){
        		dados[z] = String.format("%.1f", lista.get(z-1).getMediaList().get(i).calculaMedia());
        		PdfPCell coluna = new PdfPCell(new Phrase(dados[z]));
                coluna.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(coluna);
        	}
        	
        }
        
		String[] dados = new String[numColunas + 1];
    	dados[0] = "Conceito médio geral por curso";
    	PdfPCell colP = new PdfPCell(new Phrase(dados[0]));
        colP.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(colP);
 	    for (int i = 1; i < numColunas; i++) {
 			dados[i] = String.format("%.0f", lista.get(i-1).getMediaGeral());
 			PdfPCell coluna = new PdfPCell(new Phrase(dados[i]));
            coluna.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(coluna);
 		}
		
      	return table;
	}
}
