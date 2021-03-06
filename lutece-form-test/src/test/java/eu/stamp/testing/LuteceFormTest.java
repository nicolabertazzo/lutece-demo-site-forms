package eu.stamp.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;

@RunWith(Parameterized.class)
public class LuteceFormTest {

	private static final String CAMP_OUT_PATH_SYSTEM_PROPERTY = "campOutPath";
	private static final String DOCKER_COMPOSE_YML = "docker-compose.yml";
	private static final String LUTECE_SITE_FORMS_URL = "http://localhost:8081/site-forms-demo/";

	private DockerComposeContainer environment;

	/**
	 * get list of directories generated from CAMP
	 * 
	 * @return
	 */
	@Parameters(name = "{0}")
	public static Collection<File> data() {
		String campOutPath = System.getProperty(CAMP_OUT_PATH_SYSTEM_PROPERTY);
		File[] directories = new File(campOutPath).listFiles(File::isDirectory);
		return Arrays.asList(directories);
	}

	public LuteceFormTest(File configFolder) {
		environment = new DockerComposeContainer(new File(configFolder, DOCKER_COMPOSE_YML)).withLocalCompose(true);
	}

	@Test
	public void checkServerIsRunning() throws IOException {
		try {
			URL url = new URL(LUTECE_SITE_FORMS_URL);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			int statusCode = http.getResponseCode();
			assertEquals(200, statusCode);
		} catch (IOException e) {
			fail();
		}

	}
	
	@Before
	public void startDockerCompose() throws InterruptedException {
		System.out.println("Before test");
		environment.start();
		environment.waitingFor("lutece", Wait.forLogMessage("*site-forms-demo has finished*", 1));
		Thread.sleep(20000);
	}

	@After
	public void stopDockerCompose() {
		System.out.println("After test");
		environment.stop();
	}
}
