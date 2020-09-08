package common;

public enum Outcomes {
    HIT(0),
    INVALID(1),
    MISS(2),
    GAME_OVER(3),
    USERHIT(4),
    PARTIAL_HIT(5),
    FULL_HIT(6);


    private int code;

    private Outcomes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
