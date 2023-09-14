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

//    public static void main(String[] args) {
//        int checkin = 0;
//        checkin = setBit(checkin, 0);   // 1
//        checkin = setBit(checkin, 4);   // 5
//        checkin = setBit(checkin, 30);  // 31
//        System.out.println(toBooleanList(checkin));
//    }
}
