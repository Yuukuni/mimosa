package tw.com.fcb.mimosa.examples.gettingstarted;

import static java.lang.String.format;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "沙發裡", description = "safari")

@Slf4j
@RestController
@RequestMapping("/")
public class GreetingController {
	
	
@Operation(summary = "你好")
  @GetMapping("/greeting/{name}")
  public String greeting(@Parameter(description = "姓名") @PathVariable("name") String name) {
    log.info("Hello, {}!", name);
    return format("Hello, %s!", name);
  }
}
