package tw.com.fcb.mimosa.examples.gettingstarted;

import tw.com.fcb.mimosa.Mimosa;
import tw.com.fcb.mimosa.MimosaBootstrap;

@MimosaBootstrap
public class GettingStartedApplication {

  public static void main(String[] args) {

    Mimosa.bootstrap(GettingStartedApplication.class, args);
  }
}
