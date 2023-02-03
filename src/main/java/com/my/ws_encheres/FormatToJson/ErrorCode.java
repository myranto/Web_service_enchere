package com.my.ws_encheres.FormatToJson;

public class ErrorCode {
    String message;
    int status;

//    FileInputStream serviceAccount =
//            new FileInputStream("path/to/serviceAccountKey.json");
//
//    FirebaseOptions options = new FirebaseOptions.Builder()
//            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//            .build();
//
//FirebaseApp.initializeApp(options);


    public ErrorCode(String cd, int i) {
        message=cd;
        status=i;
    }
}
