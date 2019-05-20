package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class NetApplication {

	public static void main(String... args) {

		SpringApplication.run(NetApplication.class, args);

	}
	
	//docker ps for list down what are all containers running
	//docker exec -it names /bin/bash to check what are there in image
	//docker run -d -p 8080:8080 imagename to run image in detached mode[like deamon thread] once system shutdown it will stop
	//docker run -p 8080:8080 imagename to run image in attached mode[] once server stop it will shutdown
    //docker stop names to stop particular image
	//docker rm names names names ====>to remove images for container

	@GetMapping
	public String createNetProject(@RequestParam("projectType") String projectType,
			@RequestParam("projectName") String projectName) throws URISyntaxException, GitAPIException {

		String command1 = "dotnet  new " + projectType + " -o " + projectName;

		try {
			File path = new File(".");

			System.out.println(path);
			Process child = Runtime.getRuntime().exec(command1);
			BufferedReader reader = new BufferedReader(new InputStreamReader(child.getInputStream()));
			while (reader.readLine() != null) {

			}
			// restTemplate.exchange(requestEntity, responseType).
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
			return "Project Not Created";
		}
		return "Project  Created";
	}

}
