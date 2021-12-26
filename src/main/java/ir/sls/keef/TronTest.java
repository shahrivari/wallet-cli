package ir.sls.keef;

import com.google.common.io.BaseEncoding;
import org.tron.core.exception.CancelException;
import org.tron.core.exception.CipherException;
import org.tron.protos.Protocol;
import org.tron.walletcli.KeefApiWrapper;
import org.tron.walletserver.KeefApi;

import java.io.IOException;

public class TronTest {
    public static void main(String[] args) throws CipherException, IOException, CancelException {
        String privKeyHex = "ad1878cc71426709457100d839adb1"; //paste private key hex
        byte[] privKey = BaseEncoding.base16().lowerCase().decode(privKeyHex.toLowerCase());

        KeefApiWrapper wrapper = null;
        wrapper = KeefApiWrapper.createFromPrivateKey(privKey);
        Protocol.Account account = wrapper.queryAccount();

        String newPrivateHex = "aaaaaaaaaafd2105b921adf01221b1e3907807c207f62cc35709e6ff2d8f6cc9";
        byte[] newPrivKey = BaseEncoding.base16().lowerCase().decode(newPrivateHex.toLowerCase());
        byte[] myAddress = KeefApi.decodeFromBase58Check(wrapper.getAddress());
        boolean createResult = wrapper.createAccount(myAddress, KeefApi.getAddressFromPrivateKey(newPrivKey));
    }
}
