package console_application;

import com.sun.security.jgss.GSSUtil;

import java.util.InputMismatchException;
import java.util.Scanner;

public class bank_implementation implements bank_interface,Runnable
{

    //public static void main(String[] args)
    synchronized public void run()
    {
        System.out.println("welcome to Bank  "+Thread.currentThread().getName());
        bank_implementation ban=new bank_implementation();

        do {

            Scanner scan = new Scanner(System.in);
            System.out.println("1.Add\n2.Update\n3.Delete\n4.List\n5.Search\n6.Exit");
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
                    System.out.println("Search the deatails (bank name)");
                    String bankname= scan.next();
                    System.out.println(ban.SearchBankDetatils(bankname));
                    break;
                default:
                    return;
            }
        }while (true);

    }

    bank_details bank[]=new bank_details[2];

    public bank_implementation()
    {
        bank[0]=new bank_details("pavithra","icici",342356245,11234);
        bank[1]=new bank_details("Thara","SBI",433323432,12343);
    }

    @Override
    public String AddBankDetails(bank_details bankdetail)
    {
//        for(int i=0;i<bank.length;i++)
//        {
//            if(bank[i]==null)
//            {
//                bank[i]=bankdetail;
//                return (bankdetail.getAcc_username() +"\tadded successfully");
//            }
//        }
//        return bankdetail.getAcc_username()+"\tnot added";

        Scanner scan = new Scanner(System.in);
        try
        {
            for(int i=0;i<bank.length;i++)
        {
            if(bank[i]==null)
            {
                bank[i]=bankdetail;
                return bankdetail.getAcc_username() +"\tadded successfully";
            }
        }
        throw new bank_exception();
        }
        catch(bank_exception be)
        {
            System.out.println(be+ "\t memory size is full");
            for(bank_details bank1:bank)
            {
                System.out.println(bank1);
            }
            System.out.println("which index want to delete ");
            DeleteBankDetails(scan.next());
            AddBankDetails(bankdetail);
        }
        return bankdetail.getAcc_username() +"\tadded successfully";
    }

    @Override
    synchronized public String UpdateBankDetails(String Acc_username)
    {

        Scanner scan = new Scanner(System.in);
        try
        {
            for(int i=0;i<bank.length;i++)
            {
                    if (bank[i].getAcc_username().equalsIgnoreCase(Acc_username)) {
                        System.out.println(bank[i] + "\nwhich field you want to update (username or cardpin or bankname)");
                        String f1 = scan.next();
                        if (f1.equalsIgnoreCase("username")) {
                            System.out.println("tell new user name");
                            String un = scan.next();
                            bank[i].setAcc_username(un);
                            System.out.println("username updated");
                            return Acc_username + "username updated";
                        } else if (f1.equalsIgnoreCase("cardpin")) {
                            System.out.println("tell new card pin number");
                            int up = scan.nextInt();
                            bank[i].setAcc_pin(up);
                            System.out.println("pin updated");
                            return Acc_username + "card pin updated ";
                        } else if (f1.equalsIgnoreCase("bankname")) {
                            System.out.println("tell new bank name ");
                            String bn = scan.next();
                            bank[i].setAcc_bankname(bn);
                            System.out.println("Bank name ");
                            return Acc_username + "bank name updated ";
                        }
                    }
                }
            try
            {
                for(int i=0;i<bank.length;i++)
                {
                    if (bank[i].getAcc_username().equalsIgnoreCase(Acc_username)) {
                        System.out.println(bank[i] + "\nwhich field you want to update (username or cardpin or bankname)");
                        String f1 = scan.next();
                        if (f1.equalsIgnoreCase("username")) {
                            System.out.println("tell new user name");
                            String un = scan.next();
                            bank[i].setAcc_username(un);
                            System.out.println("username updated");
                            return Acc_username + "username updated";
                        } else if (f1.equalsIgnoreCase("cardpin")) {
                            System.out.println("tell new card pin number");
                            int up = scan.nextInt();
                            bank[i].setAcc_pin(up);
                            System.out.println("pin updated");
                            return Acc_username + "card pin updated ";
                        } else if (f1.equalsIgnoreCase("bankname")) {
                            System.out.println("tell new bank name ");
                            String bn = scan.next();
                            bank[i].setAcc_bankname(bn);
                            System.out.println("Bank name ");
                            return Acc_username + "bank name updated ";
                        }
                    }
                }
            }
            catch(InputMismatchException ime)
            {
                System.out.println(ime+"\tgive correct input");
                UpdateBankDetails(Acc_username);
            }
        }
        catch(NullPointerException npe)
        {
            System.out.println(npe + " \nplease give correct username ");
            System.out.println("update only the given list username ");
            ListBankDetails();

            System.out.println("enter which username want to update");
            UpdateBankDetails(scan.next());
        }
        return Acc_username + "added successfully";
    }

    @Override
    public String DeleteBankDetails(String Acc_username) {
        Scanner scan = new Scanner(System.in);
        try {
            for (int i = 0; i < bank.length; i++) {
                if (bank[i].getAcc_username().equalsIgnoreCase(Acc_username)) {
                    bank[i] = null;
                    System.out.println(Acc_username + "\tdeleted successfully");
                    break;
                }
            }
            throw new bank_exception();
            // DeleteBankDetails(Acc_username);
        } catch (bank_exception be) {
            System.out.println(be);
            //DeleteBankDetails(Acc_username);
            //ListBankDetails();
            for (int i = 0; i < bank.length; i++) {
                if (bank[i].getAcc_username().equalsIgnoreCase(Acc_username)) {
                    bank[i] = null;
                    System.out.println(Acc_username + "\tdeleted successfully");
                    break;
                }
            }

            return Acc_username + "\tnot deleted";
        }
    }

    @Override
    synchronized public void ListBankDetails()
    {
        for(bank_details bank1:bank)
        {
            System.out.println(bank1);
        }
    }
    public void sortBankDetails()
    {
        System.out.println("sorted");
    }

    @Override
    public String SearchBankDetatils(String Acc_bankname) throws NullPointerException,InputMismatchException
    {
        Scanner scan = new Scanner(System.in);
        try {
            for (int i = 0; i < bank.length; i++) {
                if (bank[i].getAcc_bankname().equalsIgnoreCase(Acc_bankname)) {
                    System.out.println(Acc_bankname + "\tcard position founded at " + i);
                    return Acc_bankname+" in this name of bank founded at this position :"+bank[i];
                }
            }
        }
        catch(NullPointerException npe)
        {
            System.out.println(npe + "\t please enter valid string input");
            System.out.println("Enter which bankname want to search");
            SearchBankDetatils(scan.next());
            SearchBankDetatils(Acc_bankname);
        }
        catch (InputMismatchException ime)
        {
            System.out.println(ime + "\t please enter valid string input value");
            System.out.println("Enter which bankname want to search");
            SearchBankDetatils(scan.next());
            SearchBankDetatils(Acc_bankname);
        }
        return Acc_bankname ;

    }
}
