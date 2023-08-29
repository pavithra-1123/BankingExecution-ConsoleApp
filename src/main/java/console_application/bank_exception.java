package console_application;

public class bank_exception extends Exception
{
    bank_exception()
    {
      super("Memory is full, some input is wrong ");
    }
}
