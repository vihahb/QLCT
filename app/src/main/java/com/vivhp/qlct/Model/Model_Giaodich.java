package com.vivhp.qlct.Model;

/**
 * Created by vivhp on 10/22/2016.
 */

public class Model_Giaodich {
    int magiaodich;
    int mathuchi;
    String lydo;
    String trangthai;
    String gio;

    public Model_Giaodich() {
    }

    public Model_Giaodich(int magiaodich, String lydo, String trangthai, String gio, int mathuchi) {
        this.magiaodich = magiaodich;
        this.lydo = lydo;
        this.trangthai = trangthai;
        this.gio = gio;
        this.mathuchi = mathuchi;
    }

    public int getMagiaodich() {
        return magiaodich;
    }

    public void setMagiaodich(int magiaodich) {
        this.magiaodich = magiaodich;
    }

    public String getLydo() {
        return lydo;
    }

    public void setLydo(String lydo) {
        this.lydo = lydo;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getGio() {
        return gio;
    }

    public void setGio(String gio) {
        this.gio = gio;
    }

    public int getMathuchi() {
        return mathuchi;
    }

    public void setMathuchi(int mathuchi) {
        this.mathuchi = mathuchi;
    }
}
