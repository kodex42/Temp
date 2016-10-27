package com.example.a1449877.assignment4gson.Model.Server;

import java.util.List;
import java.util.Map;

/**
 * Class to represent the response from an HTTP server request.
 * @author Ian Clement (ian.clement@johnabbott.qc.ca)
 */
public class HttpResponse {

    private int status;
    private Map<String, List<String>> headers;
    private String body;

    public HttpResponse(int status, Map<String, List<String>> headers, String body) {
        this.status = status;
        this.headers = headers;
        this.body = body;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    public List<String> getHeader(String key) {
        if(headers.containsKey(key))
            return headers.get(key);
        else
            return null;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "status=" + status +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                '}';
    }
}
