package edu.olezha.sandbox.string;

public class Str implements Comparable<Str> {

    private String string;
    private int from;
    private int to;

    public Str(String string) {
        this.string = string;
        from = 0;
        to = string.length();
    }

    @Override
    public int compareTo(Str o) {
        int oLength = o.length();
        for (int i = from; i < to; i++) {
            if (oLength < i) return 1;
            if (charAt(i) != o.charAt(i)) {
                return charAt(i) - o.charAt(i);
            }
        }
        return 0;
    }

    public int length() {
        return to - from;
    }
    public char charAt(int i) {
        return string.charAt(i);
    }

    public Str substring(int from) {
        if (this.from > from) throw new IllegalArgumentException();

        this.from = from;
        return this;
    }

    public Str substring(int from, int to) {
        if (this.from + from > to) throw new IllegalArgumentException();
        if (this.to < to) throw new IllegalArgumentException();

        this.from += from;
        this.to = this.from + to;
        return this;
    }

    public boolean startsWith(Str str) {
        if (to - from < str.length()) return false;

        for (int i = from; i < to; i++)
            if (string.charAt(i) != str.charAt(i - from))
                return false;

        return true;
    }

    public String toString() {
        return string.substring(from, to);
    }
}
