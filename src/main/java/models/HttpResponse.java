package models;

public class HttpResponse {
    int responseCode;
    String body;

    public HttpResponse(int responseCode, String body) {
        this.responseCode = responseCode;
        this.body = body;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;

    }
}
