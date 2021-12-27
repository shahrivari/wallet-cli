package ir.sls.keef;

import com.google.common.io.BaseEncoding;
import io.javalin.http.Context;
import ir.sls.keef.dto.AccountDto;
import ir.sls.keef.dto.ApiError;
import org.tron.core.exception.CipherException;
import org.tron.walletcli.KeefApiWrapper;

import java.io.IOException;

public class WalletController {
    public static final String PRIVATE_KEY_HEADER = "X-PK";

    public static void ping(Context ctx) {
        ctx.json("OK");
    }

    public static void getAccount(Context ctx) throws CipherException, IOException {
        String address = ctx.pathParam("address");
        KeefApiWrapper wrapper = createKeefFromContext(ctx);
        if(!wrapper.getAddress().equalsIgnoreCase(address)){
            ApiError.from(400, "Address and private key are not consistent!").sendInContext(ctx);
        }else {
            ctx.json(AccountDto.from(wrapper.queryAccount()));
        }
    }

    private static KeefApiWrapper createKeefFromContext(Context ctx) throws CipherException, IOException {
        String privateKey = ctx.header(PRIVATE_KEY_HEADER);
        byte[] privKey = BaseEncoding.base16().lowerCase().decode(privateKey.toLowerCase());
        return KeefApiWrapper.createFromPrivateKey(privKey);
    }

}
