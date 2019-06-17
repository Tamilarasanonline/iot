package com.tamil.iot.crowedanalytical.controller;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tamil.iot.crowedanalytical.entity.CameraStatus;
import com.tamil.iot.crowedanalytical.entity.PhotoStatus;
import com.tamil.iot.crowedanalytical.service.OsExecuterService;

@RestController
public class CameraController {

	@Autowired
	private OsExecuterService executer;

	// 1. Validate Camera module is attached and enabled
	@RequestMapping("/validateCamera")
	public CameraStatus validateCameraModule() {
		CameraStatus status = new CameraStatus();
		List<String> commandList = Arrays.asList(new String[] { "vcgencmd",
				"get_camera" });
		String output = executer.executeOsCommand(commandList);
		if (output.contains("Exception")) {
			status.setError(output);
			status.setMessage("Execution Exception");
		} else if (output.contains("supported=")
				&& output.contains("detected=")) {
			String arryStr[] = output.split(" ");

			if (arryStr.length >= 2) {
				String supported = arryStr[0].replace("supported=", "");
				String detected = arryStr[1].replace("detected=", "");
				if (supported.contains("0")) {
					status.setMessage("Camera Module is not Supported");
					status.setConnected(false);
				} else if (supported.contains("1")) {
					if (detected.contains("0")) {
						status.setEnabled(false);
						status.setMessage("Camera Module is detected But Not enabled");
					} else if (detected.contains("1")) {
						status.setMessage("Camera Module is detected and Enabled");
						status.setEnabled(true);
						status.setConnected(true);
					}
				}
			}
		}
		return status;
	}

	@RequestMapping("/takePhoto")
	public PhotoStatus capturePhoto() {
		PhotoStatus status = new PhotoStatus();
		String uuidStr =""+ UUID.randomUUID();
		String rndString = uuidStr.replaceAll("-", "_");
		
		String photoName = executer.getPhotoHomePath() + File.separator
				+ rndString + ".jpeg";
		// raspistill -o file.jpeg
		
		List<String> commandList = Arrays.asList(new String[] { "raspistill",
				"-o", photoName });
		String output = executer.executeOsCommand(commandList);
		status.setName(photoName);
		if (output.contains("Exception")) {
			status.setError(output);
			status.setMessage("Execution Exception");
		} else {
			status.setMessage("Successfully captured & Stored photo into :"
					+ photoName);
		}
		return status;
	}

}
