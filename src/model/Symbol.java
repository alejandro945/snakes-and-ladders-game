package model;

public class Symbol {
    private Symbol next;
    private Symbol before;
    private String value;

    public Symbol(String value) {
        this.value = value;
    }

    public Symbol getNext() {
        return next;
    }

    public void setNext(Symbol next) {
        this.next = next;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Symbol getBefore() {
        return before;
    }

    public void setBefore(Symbol before) {
        this.before = before;
    }
}
