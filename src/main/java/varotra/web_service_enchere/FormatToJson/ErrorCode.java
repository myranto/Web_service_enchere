package varotra.web_service_enchere.FormatToJson;

public class ErrorCode {
    String message;
    int status;


    public ErrorCode(String cd, int i) {
        message=cd;
        status=i;
    }
}
