package console_application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data //getter,setter method
@AllArgsConstructor //parameterised constructor
@NoArgsConstructor
public class bank_details implements Comparable<bank_details>, Serializable
{
    private String Acc_username;
    private String Acc_bankname;
    private long Acc_num;
    private int Acc_pin;

    public int compareTo(bank_details o)
    {
        return this.Acc_username.compareTo(o.Acc_username);
    }
}
