package lab;

public class IllegalPrintStateException extends Exception {

  @Override
  public String getLocalizedMessage() {
    return "Illegal Print State Exception";
  }
}
