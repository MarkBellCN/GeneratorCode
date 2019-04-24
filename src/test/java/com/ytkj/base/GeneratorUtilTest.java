package com.ytkj.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.ytkj.GenCodeApplication;
import com.ytkj.base.generator.prop.GeneratorProperties;
import com.ytkj.base.generator.util.GeneratorFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GenCodeApplication.class)
public class GeneratorUtilTest {
	private Connection connection;
	@Before
	public void init(){
		GeneratorProperties generatorProperties = new GeneratorProperties();
		try {
			Class.forName(generatorProperties.getDriverClassName());
			connection = DriverManager.getConnection(generatorProperties.getUrl(), generatorProperties.getUsername(),
					generatorProperties.getPassword());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void createDomain(){
		try {
			GeneratorFactory.getInstance().gen("com.ytkj.sims", "DEVICE");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void createCode(){
		try {
			GeneratorFactory.getInstance().gen("com.ytkj.sims.model.Device");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
