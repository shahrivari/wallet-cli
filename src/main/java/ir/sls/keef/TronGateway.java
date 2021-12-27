package ir.sls.keef;

import com.google.common.io.BaseEncoding;
import com.typesafe.config.Config;
import io.javalin.Javalin;
import org.tron.core.config.Configuration;
import org.tron.core.exception.CancelException;
import org.tron.core.exception.CipherException;

import java.io.IOException;

import static io.javalin.apibuilder.ApiBuilder.get;

public class TronGateway {
    private static String masterPrivateKey = null;
    private static int restPort = 8080;

    public static void init() {
        Config config = Configuration.getByPath("config.conf");
        if (config.hasPath("masterPrivateKey")) {
            masterPrivateKey = config.getString("masterPrivateKey");
        }
        if (config.hasPath("restPort")) {
            restPort = config.getInt("restPort");
        }
    }

    public static void main(String[] args) throws CipherException, IOException, CancelException {
        init();

        Javalin app = Javalin.create(config -> {
            config.defaultContentType = "application/json; charset=utf-8";
        }).routes(() -> {
            get("v1/ping", WalletController::ping);
            get("v1/accounts/{address}", WalletController::getAccount);
        }).start(restPort);

        System.out.println("Listening on port: " + restPort);
    }
}
