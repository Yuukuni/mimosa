package tw.com.fcb.mimosa.examples.gettingstarted;

import org.springframework.cloud.openfeign.EnableFeignClients;

import tw.com.fcb.mimosa.Mimosa;
import tw.com.fcb.mimosa.MimosaBootstrap;

@EnableFeignClients
@MimosaBootstrap
public class GettingStartedApplication {

  public static void main(String[] args) {

    Mimosa.bootstrap(GettingStartedApplication.class, args);
  }
}
