package eyeq.joshiryokuispower.common.capabilities;

public class Joshiryoku implements IJoshiryoku {
    private int jp;

    @Override
    public void set(int points) {
        this.jp = points;
    }

    @Override
    public int get() {
        return this.jp;
    }
}
