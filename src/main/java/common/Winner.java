package common;

public enum Winner {
        PLAYER(0),
        COMPUTER(1),
        DRAW(2);

        private int code;


    Winner(int code) {
        this.code = code;
    }

    public int getCode() {
            return code;
        }
    }


