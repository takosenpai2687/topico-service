package usyd.elec5619.topicoservice.util;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class BitUtil {
    public static Boolean isBitSet(int bitMap, int position) {
        return (bitMap & (1 << position)) != 0;
    }

    public static int setBit(int bitMap, int position) {
        bitMap |= (1 << position);
        return bitMap;
    }

    public static int clearBit(int bitMap, int position) {
        bitMap &= ~(1 << position);
        return bitMap;
    }

    public static List<Boolean> toBooleanList(int bitMap) {
        List<Boolean> booleanList = new ArrayList<>(32);
        for (int i = 0; i < 32; i++) {
            booleanList.add(isBitSet(bitMap, i));
        }
        return booleanList;
    }

    public static List<Boolean> toBooleanList(int bitMap, int len) {
        List<Boolean> booleanList = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            booleanList.add(isBitSet(bitMap, i));
        }
        return booleanList;
    }

    public static int countBitsBeforeIth(int bitMap, int i) {
        if (i < 0 || i >= 32) {
            throw new IllegalArgumentException("Position must be between 0 and 31");
        }

        int count = 0;

        int mask = (1 << (i + 1)) - 1; // This creates a number with 'i+1' rightmost bits set.

        // Apply the mask to the bitMap, so we only consider bits from 0 to i.
        int maskedMap = bitMap & mask;

        // Count the number of 1 bits in the masked portion.
        while (maskedMap != 0) {
            count += (maskedMap & 1); // Check the rightmost bit and add it to the count if it's set.
            maskedMap >>>= 1; // Shift the bits to the right, preparing the next bit for checking.
        }

        return count;
    }

}
