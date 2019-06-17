package com.tamil.iot.crowedanalytical.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class OsExecuterService {

	@Value("application.dir")
	private String appDirPath;

	@Value("source.photo.path")
	private String sourcePhotoPath;

	@Value("target.photo.path")
	private String targetPhotoPath;

	@Value("opencv.haarcascades.path")
	private String opencvHaarcascadesPath;

	@Value("python.script.file")
	private String pythonScriptFile;

	public String executeOsCommand(List<String> commandString) {
		String output = "";
		try {
			Process osProcess = new ProcessBuilder(commandString)
					.redirectErrorStream(true).start();
			osProcess.waitFor();
			InputStream is = osProcess.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String in;
			while ((in = br.readLine()) != null) {
				output += in;
			}
		} catch (Exception e) {
			output = "Exception :" + e.getLocalizedMessage();
			e.printStackTrace();
		}
		return output;
	}

	public String getPhotoHomePath() {
		String path = "";
		try {
			File f = new File(System.getProperty("user.dir") + File.separator
					+ "Photos");
			path = f.getAbsolutePath();
			if (!f.exists()) {
				f.mkdir();
			}
			System.out.println(f.exists());
			System.out.println(f.getAbsolutePath());
		} catch (Exception e) {
			path = "Exception :" + e.getLocalizedMessage();
		}
		return path;
	}

	public String prepareHomeDirs() {

		try {

			// Home dir
			String homePath = createFolder(appDirPath);

			// Source
			createFolder(appDirPath + File.separatorChar + sourcePhotoPath);

			// Target
			createFolder(appDirPath + File.separatorChar + targetPhotoPath);
			
			// Script
			Resource resource = new ClassPathResource(pythonScriptFile);
			System.out.println(resource.exists());
			InputStream is = resource.getInputStream();
			byte[] buffer = new byte[is.available()];
			is.read(buffer);

			File targetFile = new File(homePath + File.separator
					+ pythonScriptFile);
			OutputStream outStream = new FileOutputStream(targetFile);
			outStream.write(buffer);

		} catch (Exception e) {

		}
		return path;
	}

	private String createFolder(String pathString) {
		String path = "";
		try {
			File f = new File(System.getProperty("user.dir") + File.separator
					+ pathString);
			path = f.getAbsolutePath();
			if (!f.exists()) {
				f.mkdirs();
			}
			System.out.println(f.exists());
			System.out.println(f.getAbsolutePath());
		} catch (Exception e) {
			path = "Exception :" + e.getLocalizedMessage();
		}
		return path;
	}

	public String capturePhoto() {
		String path = "";
		try {
			File f = new File(System.getProperty("user.dir") + File.separator
					+ "Photos");
			path = f.getAbsolutePath();
			if (!f.exists()) {
				f.mkdir();
			}
			System.out.println(f.exists());
			System.out.println(f.getAbsolutePath());
		} catch (Exception e) {
			path = "Exception :" + e.getLocalizedMessage();
		}
		return path;
	}

}
