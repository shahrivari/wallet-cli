package ir.sls.keef.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.tron.protos.Protocol;
import org.tron.walletserver.WalletApi;

@ToString
@Data
@AllArgsConstructor
public class AccountDto {
    private String address;
    private long balance;

    public static AccountDto from(Protocol.Account account){
        String address = WalletApi.encode58Check(account.getAddress().toByteArray());
        return new AccountDto(address, account.getBalance());
    }
}
