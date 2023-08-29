package console_application;

import java.util.*;

public class bank_collection_implement implements bank_interface,Runnable
{
    ArrayList<bank_details> bank = new ArrayList<bank_details>();

    public bank_collection_implement()
    {
        bank.add(new bank_details("pavithra","icici",34442424333l,1123));
        bank.add(new bank_details("maya","sbi",25564446666646l,3434));
        bank.add(new bank_details("zara","indian",6546465656l,2343));
        bank.add(new bank_details("dhinu","hdfc",4554545453l,5454));

    }
    synchronized public void run()
    {
        System.out.println("welcome to Bank  "+Thread.currentThread().getName());
        bank_collection_implement ban=new bank_collection_implement();

        do {

            Scanner scan = new Scanner(System.in);
            System.out.println("1.Add\n2.Update\n3.Delete\n4.List\n5.Search\n6.Sort\n7.Exit");
            int use = scan.nextInt();
            switch (use) {
                case 1:
                    System.out.println("please add the details \n name\t bankname\t num\t pin");
                    bank_details cus1 = new bank_details(scan.next(), scan.next(), scan.nextLong(), scan.nextInt());
                    System.out.println(ban.AddBankDetails(cus1));
                    break;
                case 2:
                    System.out.println("Which detail you want to update  ");
                    ban.ListBankDetails();
                    System.out.println("enter the above listed usernames ");
                    String u_name= scan.next();
                    ban.UpdateBankDetails(u_name);
                    break;
                case 3:
                    System.out.println("which value you want to delete");
                    ban.ListBankDetails();
                    System.out.println("Enter which username you want to delete ");
                    String cus3= scan.next();
                    ban.DeleteBankDetails(cus3);
                    break;
                case 4:
                    System.out.println("list the deatils");
                    ban.ListBankDetails();
                    break;
                case 5:
                    System.out.println("Search the deatails , enter bank name (icici/sbi/hdfc)");
                    String bankname= scan.next();
                    System.out.println(ban.SearchBankDetatils(bankname));
                    break;
                case 6:
                    ban.sortBankDetails();
                    ban.ListBankDetails();
                default:
                    return;
            }
        }while (true);

    }

    @Override
    public String AddBankDetails(bank_details bankdetail)
    {
        bank.add(bankdetail);
        return bankdetail.getAcc_username() + " added successfully";
    }

    @Override
    public String UpdateBankDetails(String Acc_username) {
        try {
            Scanner scan = new Scanner(System.in);
            for (int i = 0; i < bank.size(); i++) {
                if (bank.get(i).getAcc_username().equalsIgnoreCase(Acc_username)) {
                    System.out.println("Which field you want to update (username or bankname or accnum)");
                    String field = scan.next();
                    switch (field) {
                        case "username":
                            System.out.println("please tell new name ");
                            String newname = scan.next();
                            bank.get(i).setAcc_username(newname);
                            System.out.println(Acc_username + " updated successfully");
                            break;
                        case "bankname":
                            System.out.println("please tell new bank name");
                            String newbankname = scan.next();
                            bank.get(i).setAcc_bankname(newbankname);
                            System.out.println(Acc_username + " updated successfully");
                            break;
                        case "accnum":
                            System.out.println("please tell new account number ");
                            long newacc = scan.nextLong();
                            bank.get(i).setAcc_num(newacc);
                            System.out.println(Acc_username + " updated successfully");
                            break;
                        default:
                            throw new bank_exception();
                    }
                }
            }
        }
        catch (bank_exception be)
        {
            Scanner scan=new Scanner(System.in);
            System.out.println(be + ", enter valid input ");
            for(bank_details bann:bank)
            {
                System.out.println(bann.getAcc_username());
            }
            UpdateBankDetails(scan.next());
        }
        return Acc_username +" updated successfully";
    }

    @Override
    public String DeleteBankDetails(String Acc_username)
    {
        for(int i=0;i< bank.size();i++)
        {
            if(bank.get(i).getAcc_username().equalsIgnoreCase(Acc_username))
            {
                bank.remove(bank.get(i));
                System.out.println(Acc_username+" has been deleted in successfully");
            }
        }
        return Acc_username;
    }

    public void sortBankDetails()
    {
        Collections.sort(bank);
    }
    @Override
    public void ListBankDetails()
    {
        Iterator<bank_details> it = bank.iterator();
        while(it.hasNext())
        {
            System.out.println(it.next());
        }
    }

    @Override
    public String SearchBankDetatils(String Acc_bankname)
    {
        for(int i=0;i<bank.size();i++)
        {
            if(bank.get(i).getAcc_bankname().equalsIgnoreCase(Acc_bankname))
            {
                System.out.println(Acc_bankname + " bank name in the index position is "+i);
                break;
            }
        }
        return Acc_bankname + " successfully searched ";
    }
}
