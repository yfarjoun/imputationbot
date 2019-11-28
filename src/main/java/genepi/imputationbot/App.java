package genepi.imputationbot;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.restlet.engine.Engine;
import org.restlet.ext.slf4j.Slf4jLoggerFacade;

import genepi.base.Toolbox;
import genepi.imputationbot.commands.ConfigCloudgeneClient;
import genepi.imputationbot.commands.DownloadResults;
import genepi.imputationbot.commands.ListJobs;
import genepi.imputationbot.commands.ListProjects;
import genepi.imputationbot.commands.ListRefPanels;
import genepi.imputationbot.commands.RunImputationJob;
import genepi.imputationbot.commands.RunQualityControlJob;
import genepi.imputationbot.commands.ShowVersion;
import genepi.imputationbot.model.ProjectList;

public class App extends Toolbox {

	public static final String VERSION = "0.1.3";

	public App(String command, String[] args) {
		super(command, args);
		turnLoggingOff();
	}

	public void turnLoggingOff() {

		Slf4jLoggerFacade loggerFacade = new Slf4jLoggerFacade();
		Engine.getInstance().setLoggerFacade(loggerFacade);

		List<Logger> loggers = Collections.<Logger>list(LogManager.getCurrentLoggers());
		loggers.add(LogManager.getRootLogger());
		for (Logger logger : loggers) {
			logger.setLevel(Level.OFF);
		}
	}

	public static void main(String[] args) throws Exception {

		App toolbox = new App("imputationbot", args);
		toolbox.addTool("configure", ConfigCloudgeneClient.class);
		toolbox.addTool("download", DownloadResults.class);
		toolbox.addTool("impute", RunImputationJob.class);
		toolbox.addTool("refpanels", ListRefPanels.class);
		toolbox.addTool("jobs", ListJobs.class);
		toolbox.addTool("validate", RunQualityControlJob.class);
		toolbox.addTool("info", ShowVersion.class);
		toolbox.addTool("projects", ListProjects.class);
		toolbox.addTool("version", ShowVersion.class);
		toolbox.start();

	}
}
