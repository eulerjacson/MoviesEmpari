package br.com.ejb.projetoempari.model.entity;

/**
 * Created by Euler on 07/02/2018.
 */

public class RateMovie {
    private int status_code;
    private String status_message;

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }
}
