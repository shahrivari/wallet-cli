package ir.sls.keef.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiError {
    @JsonIgnore
    final int httpCode;
    final String message;

    public static ApiError from(String message) {
        return new ApiError(404, message);
    }

    public static ApiError from(int code, String message) {
        return new ApiError(code, message);
    }

    public void  sendInContext(Context ctx) {
        ctx.status(httpCode).json(this);
    }
}
