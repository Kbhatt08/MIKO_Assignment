package ai.miko.assemblylang.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class SyntaxException extends RuntimeException {

    public SyntaxException(String message) {
        super(message);
    }
}
