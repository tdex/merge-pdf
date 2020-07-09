package br.com.nagem.docbrasil;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

public class PdfMerge {

	public static void processaPdf() throws IOException {
		PDFMergerUtility PDFmerger = new PDFMergerUtility();

		String path = new File(".").getCanonicalPath();
		File folder = new File(path);
		String outputFolder = path + "/saida/";
		
		new File(outputFolder).mkdir();
		
//		String outputFolder = "/home/tarcisio/Área de Trabalho/teste/";
//		File folder = new File("/home/tarcisio/Área de Trabalho/teste/");
		
		File[] listOfFiles = folder.listFiles();

		int cont_arquivo = 0;
		int cont_nome_arquivo = 1;


		for (File file : listOfFiles) {
			cont_arquivo++;
			String nomeArquivoAtual = outputFolder + "parte_" + cont_nome_arquivo + ".pdf";

			if (file.isFile() && FilenameUtils.getExtension(file.getName().toLowerCase()).contains("pdf")) {
				if (cont_arquivo <= 90) {
					PDFmerger.addSource(file);
				} else {
					System.out.println(nomeArquivoAtual);
					PDFmerger.setDestinationFileName(nomeArquivoAtual);
					PDFmerger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
					
					PDFmerger = new PDFMergerUtility();
					cont_arquivo = 0;
					cont_nome_arquivo++;
				}
			} 
		}
		
		PDFmerger.setDestinationFileName(outputFolder + "parte_" + cont_nome_arquivo + ".pdf");
		PDFmerger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());

	}

}
