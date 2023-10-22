package usyd.elec5619.topicoservice.util;

public class LevelUtil {

    public static int expToLevel(int exp) {
        int level = 1;
        int expForNextLevel = EXP_STEP;

        // Loop to calculate the next level
        while (exp >= expForNextLevel) {
            // Move to the next level
            level++;
            // Subtract the experience required for this level
            exp -= expForNextLevel;
            // Next level requires EXP_STEP more experience points than the previous one
            expForNextLevel += EXP_STEP;
        }
        return level;
    }

    public static int maxExpAtLevel(int level) {
        // Validate level is more than 0
        if (level <= 0) {
            throw new IllegalArgumentException("Level must be greater than 0");
        }

        level -= 1;

        return (level * (level + 1)) / 2 * EXP_STEP + EXP_STEP;
    }

    private static final int EXP_STEP = 5;
    public static final int CHECKIN_EXP = 6;
}
