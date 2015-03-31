/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.io.ZipInputStream;
import net.lingala.zip4j.io.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import cu.uci.coj.config.Config;
import cu.uci.coj.dao.UtilDAO;
import cu.uci.coj.model.DatagenDataset;
import cu.uci.coj.model.Language;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.SubmissionJudge;
import cu.uci.coj.pdf.ProblemPdf;

public class FileUtils {

	static ProblemPdf problem = new ProblemPdf();

	public static void generatePdf(Problem p) {
		problem.generate(p);
	}

	public static boolean saveToFile(MultipartFile mpf, String baseDir, String file) {
		File f = new File(baseDir, file);
		try {
			mpf.transferTo(f);
		} catch (IllegalStateException | IOException e1) {
			System.out.println(e1.getMessage());
			return false;
		}
		return true;
	}

	private static FilenameFilter modelSolutionFilter = new FilenameFilter() {
		@Override
		public boolean accept(File dir, String name) {
			return name.startsWith("Model");
		}
	};
	private static FilenameFilter inputGenFilter = new FilenameFilter() {
		@Override
		public boolean accept(File dir, String name) {
			return name.startsWith("Datagen");
		}
	};

	public static boolean inputGenExists(DatagenDataset dataset) {
		File folder = new File(Config.getProperty("problems.directory") + String.valueOf(dataset.getProblemId()));
		File[] files = folder.listFiles(inputGenFilter);
		return (files != null && files.length == 1);
	}

	public static boolean modelSolutionExists(DatagenDataset dataset) {
		File folder = new File(Config.getProperty("problems.directory") + String.valueOf(dataset.getProblemId()));
		File[] files = folder.listFiles(modelSolutionFilter);
		return (files != null && files.length == 1);
	}

	public static boolean getModelCode(DatagenDataset dataset, UtilDAO utilDAO) {

		File folder = new File(Config.getProperty("problems.directory") + String.valueOf(dataset.getProblemId()));
		File[] files = folder.listFiles(modelSolutionFilter);
		if (files != null && files.length == 1) {
			try {
				StringBuilder buffer = new StringBuilder();
				List<String> lineas = org.apache.commons.io.FileUtils.readLines(files[0]);

				for (String line : lineas) {
					buffer.append(line);
					buffer.append("\n");
				}
				dataset.setCode(buffer.toString());
				Language language = utilDAO.getLanguageByExtension(files[0].getName().substring(files[0].getName().indexOf(".") + 1));
				dataset.setLanguage(language.getLanguage());
				dataset.setLid(language.getLid());
				dataset.setKey(language.getKey());
			} catch (Exception e) {
			}
			return true;
		}
		return false;
	}

	public static boolean getInputGenCode(DatagenDataset dataset, UtilDAO utilDAO) {

		File folder = new File(Config.getProperty("problems.directory") + String.valueOf(dataset.getProblemId()));
		File[] files = folder.listFiles(inputGenFilter);
		if (files != null && files.length == 1) {
			try {
				StringBuilder buffer = new StringBuilder();
				List<String> lineas = org.apache.commons.io.FileUtils.readLines(files[0]);

				for (String line : lineas) {
					buffer.append(line);
					buffer.append("\n");
				}
				dataset.setCode(buffer.toString());
				Language lang = utilDAO.getLanguageByExtension(files[0].getName().substring(files[0].getName().indexOf(".") + 1));
				dataset.setLanguage(lang.getLanguage());
				dataset.setKey(lang.getKey());
			} catch (Exception e) {
			}
			return true;
		}
		return false;
	}

	public static void deleteDirectory(File file) throws IOException {
		org.apache.commons.io.FileUtils.deleteDirectory(file);
	}

	public static void redirectStreams(InputStream is, OutputStream os) {
		try {
			IOUtils.copy(is, os);
			os.flush();
		} catch (IOException ex) {
			throw new RuntimeException("IOError writing file to output stream");
		}
	}

	public static void crearArchivoComprimido(OutputStream zipStream, List<SubmissionJudge> solutions) {

		
		// Create a buffer for reading the files

		try {
			// Create the ZIP file
			java.util.zip.ZipOutputStream out = new java.util.zip.ZipOutputStream(zipStream);
			// Compress the files
			for (SubmissionJudge solution : solutions) {
				// Add ZIP entry to output stream.
				out.putNextEntry(new ZipEntry(solution.getFilename()));
				// Transfer bytes from the file to the ZIP file
				out.write(solution.getCode().getBytes());
				out.closeEntry();
			}
			out.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void decompressFile(String zipfile, String dir, boolean erase) {
		try {
			ZipFile zipFile = new ZipFile(zipfile);
			zipFile.extractAll(dir);
			if (erase) {
				org.apache.commons.io.FileUtils.deleteQuietly(new File(zipfile));
			}
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}

	public static void crearArchivoDatasets(OutputStream zipStream, List<DatagenDataset> datasets) {

		// Create a buffer for reading the files

		try {
			// Create the ZIP file
			java.util.zip.ZipOutputStream out = new java.util.zip.ZipOutputStream(zipStream);

			// Compress the files
			for (DatagenDataset dataset : datasets) {
				String nom = dataset.getId() == null ? "data" : String.valueOf(dataset.getId());
				out.putNextEntry(new ZipEntry(nom + ".in"));
				out.write(dataset.getInput().getBytes());

				out.putNextEntry(new ZipEntry(nom + ".out"));
				out.write(dataset.getOutput().getBytes());

				out.closeEntry();
			}
			out.close();

		} catch (IOException e) {
		}
	}
}
