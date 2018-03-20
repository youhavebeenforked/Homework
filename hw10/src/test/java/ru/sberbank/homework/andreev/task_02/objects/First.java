package ru.sberbank.homework.andreev.task_02.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class First {

    private String s;
    private Long l;
    private float f;
    private ArrayList<String> al;
    private Optional<Object> opt;

    public First(String s, Long l, float f, List<String> al, Optional<Object> opt) {
        this.s = s;
        this.l = l;
        this.f = f;
        this.al = new ArrayList<>(al);
        this.opt = opt;
    }

    public First() {
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public Long getL() {
        return l;
    }

    public void setL(Long l) {
        this.l = l;
    }

    public float getF() {
        return f;
    }

    public void setF(float f) {
        this.f = f;
    }

    public List<String> getAl() {
        return al;
    }

    public void setAl(List<String> al) {
        this.al = new ArrayList<>(al);
    }

    public Optional<Object> getOpt() {
        return opt;
    }

    public void setOpt(Optional<Object> opt) {
        this.opt = opt;
    }

    @Override
    public String toString() {
        return "First{" +
                "s='" + s + '\'' +
                ", l=" + l +
                ", f=" + f +
                ", al=" + al +
                ", opt=" + opt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        First first = (First) o;
        return Float.compare(first.f, f) == 0 &&
                Objects.equals(s, first.s) &&
                Objects.equals(l, first.l) &&
                Objects.equals(al, first.al) &&
                Objects.equals(opt, first.opt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(s, l, f, al, opt);
    }
}
