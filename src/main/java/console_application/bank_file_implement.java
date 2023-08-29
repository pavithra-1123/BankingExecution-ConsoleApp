package console_application;

import java.io.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class bank_file_implement implements bank_interface,Runnable
{
        File myfile = new File("C:\\Users\\pavib\\IdeaProjects\\MyProjectCorejava\\src\\main\\java\\FundamentalJava\\File\\bankdetails.doc");
//        myfile.createNewFile();
//        System.out.println(myfile.getName());


    // memory initialization
    //ArrayList<bank_details> bank =new ArrayList<bank_details>();//initialized object

    ArrayList <bank_details> bank=null;
    public bank_file_implement()
    {
        System.out.println("i am using file concept");
        bank=new ArrayList<bank_details>();
    }

    public void writing() throws IOException
    {
        FileOutputStream fos = new FileOutputStream(myfile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(bank);
        oos.close();
        fos.close();

    }

    public void reading() throws IOException,ClassNotFoundException
    {
        FileInputStream fis=new FileInputStream(myfile);
        ObjectInputStream ois=new ObjectInputStream(fis);

        bank = (ArrayList<bank_details>) ois.readObject();
        ois.close();
        fis.close();
    }


    synchronized public void run()
    {
        System.out.println("welcome to Bank  "+Thread.currentThread().getName());
        bank_file_implement ban=new bank_file_implement();

        do {

            Scanner scan = new Scanner(System.in);
            System.out.println("1.Add\n2.Update\n3.Delete\n4.List\n5.Search\n6.Sort\n7.Exit");
            int use = scan.nextInt();
            switch (use) {
                case 1:
                    System.out.println("please add the details \n name\t bankname\t num\t pin");
                    bank_details cus1 = new bank_details(scan.next(), scan.next(), scan.nextLong(), scan.nextInt());
                    try {
                        System.out.println(ban.AddBankDetails(cus1));
                    } catch (bank_exception e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
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
    public String AddBankDetails(bank_details bankdetail) throws bank_exception, IOException, ClassNotFoundException
    {
        try
        {
            reading();
            bank.add(bankdetail);
            writing();
        }
        catch(EOFException eofe)
        {
            System.out.println(eofe);
            bank.add(bankdetail);
            try
            {
                writing();
            }
            catch (IOException ioe)
            {
                System.out.println(ioe);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return bankdetail.getAcc_username()+" added successfully";
    }

    @Override
    public String UpdateBankDetails(String Acc_username)
    {
        try {
            reading();
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
            writing();
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
            try
            {
                writing();
            }
            catch (IOException ioe)
            {
                System.out.println(ioe);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return " updated successfully" ;
    }

    @Override
    public String DeleteBankDetails(String Acc_username)
    {
        try {
            reading();
            for (int i = 0; i < bank.size(); i++) {
                if (bank.get(i).getAcc_username().equalsIgnoreCase(Acc_username)) {
                    bank.remove(bank.get(i));
                    System.out.println(Acc_username + " has been deleted in successfully");
                }
                writing();
            }
        }
        catch(EOFException eofe)
        {
            System.out.println(eofe);
            try {
                writing();
            }
            catch (IOException ioe)
            {
                System.out.println(ioe);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public void ListBankDetails()
    {
        try
        {
            reading();
            Iterator<bank_details> it = bank.iterator();
            while(it.hasNext())
            {
                System.out.println(it.next());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sortBankDetails()
    {
        try
        {
            reading();
            Collections.sort(bank);
            writing();
        }
        catch(EOFException eofe)
        {
            System.out.println(eofe);
            try
            {
                writing();
            }
            catch (IOException ioe)
            {
                System.out.println(ioe);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String SearchBankDetatils(String Acc_bankname)
    {
        try
        {
            reading();
            for(int i=0;i<bank.size();i++)
            {
                if(bank.get(i).getAcc_bankname().equalsIgnoreCase(Acc_bankname))
                {
                    System.out.println(Acc_bankname + " bank name in the index position is "+i);
                    break;
                }
            }
            writing();
        }
        catch(EOFException eofe)
        {
            System.out.println(eofe);
            try
            {
                writing();
            }
            catch (IOException ioe)
            {
                System.out.println(ioe);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return Acc_bankname + " successfully searched";
    }
}
