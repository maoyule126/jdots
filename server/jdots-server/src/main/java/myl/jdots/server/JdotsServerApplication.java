package myl.jdots.server;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.stage.Stage;
import myl.jdots.server.views.JdotsSplash;
import myl.jdots.server.views.MainStageView;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class JdotsServerApplication extends AbstractJavaFxApplicationSupport {
	@Override
	public void start(Stage stage) throws Exception {
		super.start(stage);
	}

	public static void main(String[] args) {
		//SpringApplication.run(JdotsServerApplication.class, args);
		launch(JdotsServerApplication.class, MainStageView.class, new JdotsSplash(), args);
	}

}
