package os.pkg3;
/**
 *
 * @author DELL
 */
class semaphore {

    protected int count = 0;

    protected semaphore() {
        count= 0;
    }
    protected semaphore(int s) {
        count = s;
    }
    public synchronized void P() {
        count--;
        if (count < 0) {
            try {wait();
                 } catch (InterruptedException e) {
                   }
        }
    }
    public synchronized void V() {
        count++;
        if (count <= 0) {
            notify();
        }
    }
}
