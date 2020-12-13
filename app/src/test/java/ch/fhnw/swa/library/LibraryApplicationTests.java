package ch.fhnw.swa.library;

import ch.fhnw.swa.library.web.ThymeleafBookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LibraryApplicationTests {

	@Autowired
	private ThymeleafBookController thymeleafBookController;

	@Test
	void contextLoads() throws Exception {
		assertThat(thymeleafBookController).isNotNull();
	}
}
