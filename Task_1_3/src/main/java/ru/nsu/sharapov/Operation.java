package ru.nsu.sharapov;

/**
 * Enum of existed operations.
 */
public enum Operation {
    ADD('+'), SUB('-'), MUL('*'), DIV('/');
    public static final char ADD_CHAR = '+';
    public static final char SUB_CHAR = '-';
    public static final char MUL_CHAR = '*';
    public static final char DIV_CHAR = '/';
    private final char s;

    Operation(char s) {
        this.s = s;
    }

    /**
     * Operation sign getter.
     *
     * @return Sign char
     */
    public char getSign() {
        return this.s;
    }

    /**
     * String representation of sign char.
     *
     * @return Sign string
     */
    @Override
    public String toString() {
        return String.valueOf(s);
    }
}
