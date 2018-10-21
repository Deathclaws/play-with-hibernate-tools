package com.deathclaws.hibernate;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Path;
import org.hibernate.tool.ant.Hbm2HbmXmlExporterTask;
import org.hibernate.tool.ant.HibernateToolTask;
import org.hibernate.tool.ant.JDBCConfigurationTask;

import java.io.File;

public class HibernateToolsExampleRunner {

	public static void main(final String... args) {
		try {
			final File destDir = new File("target/entities");
			final File hibernateConfig = new File("src/main/resources/hibernate.cfg.xml");
			Project project = new Project();
			final Path reverseConfig = new Path(project, "src/main/resources/hibernate.reveng.xml");

			final HibernateToolTask entityGenerator = new HibernateToolTask();
			entityGenerator.setDestDir(destDir);

			final JDBCConfigurationTask jdbcConfiguration = entityGenerator.createJDBCConfiguration();
			jdbcConfiguration.setConfigurationFile(hibernateConfig);
			jdbcConfiguration.setRevEngFile(reverseConfig);
			jdbcConfiguration.setDetectManyToMany(true);
			jdbcConfiguration.setDetectOneToOne(true);
			jdbcConfiguration.setReverseStrategy(CustomStrategy.class.getName());
			jdbcConfiguration.execute();

			final Hbm2HbmXmlExporterTask hbm2Java = (Hbm2HbmXmlExporterTask) entityGenerator.createHbm2HbmXml();
			hbm2Java.setDestdir(destDir);
			hbm2Java.execute();
			
			System.exit(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
