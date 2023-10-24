package usyd.elec5619.topicoservice.util;

public class LevelUtil {

    public static int expToLevel(int exp) {
        return exp / EXP_STEP + 1;
    }

    public static int maxExpAtLevel(int level) {
        return level * EXP_STEP;
    }


    private static final int EXP_STEP = 5;
    public static final int CHECKIN_EXP = 6;
}
