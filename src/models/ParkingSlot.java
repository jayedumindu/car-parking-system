package models;

public class ParkingSlot {
    private int slotNum;
    private boolean free = false;

    public ParkingSlot(int slotNum) {
        this.slotNum = slotNum;
    }

    public int getSlotNum() {
        return slotNum;
    }

    public void setSlotNum(int slotNum) {
        this.slotNum = slotNum;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }
}
