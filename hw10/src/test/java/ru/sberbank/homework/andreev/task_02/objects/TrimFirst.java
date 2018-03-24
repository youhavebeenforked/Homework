package ru.sberbank.homework.andreev.task_02.objects;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TrimFirst {
    private String s;
    private Long l;
    private double f;
    private ArrayList<String> al;
    private File opt;

    public TrimFirst(String s, Long l, double f, List<String> al, File opt) {
        this.s = s;
        this.l = l;
        this.f = f;
        this.al = new ArrayList<>(al);
        this.opt = opt;
    }

    public TrimFirst() {
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    private  Long getL() {
        return l;
    }

    private void setL(Long l) {
        this.l = l;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public List<String> getAl() {
        return al;
    }

    public void setAl(List<String> al) {
        this.al = new ArrayList<>(al);
    }

    public File getOpt() {
        return opt;
    }

    public void setOpt(File opt) {
        this.opt = opt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrimFirst trimFirst = (TrimFirst) o;
        return Double.compare(trimFirst.f, f) == 0 &&
                Objects.equals(s, trimFirst.s) &&
                Objects.equals(l, trimFirst.l) &&
                Objects.equals(al, trimFirst.al) &&
                Objects.equals(opt, trimFirst.opt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(s, l, f, al, opt);
    }

    @Override
    public String toString() {
        return "TrimFirst{" +
                "s='" + s + '\'' +
                ", l=" + l +
                ", f=" + f +
                ", al=" + al +
                ", opt=" + opt +
                '}';
    }
}
