package br.com.nagem.docbrasil;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

public class PdfMerge {

	private static double getFileSizeMegaBytes(File file) {
		return (double) file.length() / (1024 * 1024);
	}

	public static void processaPdf() throws IOException {
		PDFMergerUtility PDFmerger = new PDFMergerUtility();

		String path = new File(".").getCanonicalPath();
		File folder = new File(path);
		String outputFolder = path + "/saida/";

		new File(outputFolder).mkdir();

//		String outputFolder = "/home/tarcisio/Área de Trabalho/teste/";
//		File folder = new File("/home/tarcisio/Área de Trabalho/teste/");

		File[] listOfFiles = folder.listFiles();

		int cont_nome_arquivo = 1;
		double tamanhoPDF = 0;

		for (int i = 0; i < listOfFiles.length; i++) {
			String nomeArquivoAtual = outputFolder + "parte_" + cont_nome_arquivo + ".pdf";

			File file = listOfFiles[i];

			if (file.isFile() && FilenameUtils.getExtension(file.getName().toLowerCase()).contains("pdf")) {
				tamanhoPDF = tamanhoPDF + getFileSizeMegaBytes(file);

				if (tamanhoPDF <= 14) {
					PDFmerger.addSource(file);
				} else {
					System.out.println(nomeArquivoAtual);
					PDFmerger.setDestinationFileName(nomeArquivoAtual);
					PDFmerger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());

					PDFmerger = new PDFMergerUtility();
					PDFmerger.addSource(file);
					cont_nome_arquivo++;
					tamanhoPDF = getFileSizeMegaBytes(file);
				}
			}
		}

		PDFmerger.setDestinationFileName(outputFolder + "parte_" + cont_nome_arquivo + ".pdf");
		PDFmerger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());

	}

}
