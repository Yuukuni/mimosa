package tw.com.fcb.mimosa.examples.gettingstarted;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import tw.com.fcb.mimosa.test.MimosaTest;
import tw.com.fcb.mimosa.test.TestProfile;

@TestProfile
@MimosaTest
@Timeout(3)
class GettingStartedApplicationTest {

  @Test
  @DisplayName("測試載入 Mimosa")
  void contextLoads() {
  }
}
