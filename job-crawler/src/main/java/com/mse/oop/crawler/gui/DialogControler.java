/**
 * 
 */
package com.mse.oop.crawler.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.mse.oop.crawler.models.JobSite;

import javafx.fxml.Initializable;

/**
 * @author Petar Ivanov - pesho02@abv.bg
 *
 */
public class DialogControler implements Initializable {
	private JobSite site;
	private MainController parent;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		System.out.println("DIALOG INIT");

	}

	protected void initObject(JobSite site) {
		System.out.println("  INIT  OBJETC");
		System.out.println(site.getUrl());
		site.setUrl("BRADA");
		System.out.println(site.getUrl());

	}

}
