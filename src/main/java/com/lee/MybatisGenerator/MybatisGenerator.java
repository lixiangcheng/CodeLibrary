package com.lee.MybatisGenerator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MybatisGenerator {

	public static void main(String[] args) throws IOException,
			XMLParserException, InvalidConfigurationException, SQLException,
			InterruptedException {
		boolean overwrite = true;
		List<String> warnings = new ArrayList<String>();
		
		String resource="generatorConfig.xml";

		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		URL url = cl.getResource(resource);
		
		File configFile = new File(url.getPath());
		
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,callback, warnings);
		myBatisGenerator.generate(null);
	}
}
