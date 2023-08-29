package console_application;

import java.io.IOException;

public interface bank_interface
{
    public String AddBankDetails(bank_details bankdetail) throws bank_exception, IOException,ClassNotFoundException;
    public String UpdateBankDetails(String Acc_username);
    public String DeleteBankDetails(String Acc_username);
    public void ListBankDetails();
    public void sortBankDetails();
    public String SearchBankDetatils(String Acc_bankname);
}
