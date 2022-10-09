public class Reader extends Thread {
    private Share share;
    private int id;

    Reader(Share s,int i) {
        share = s;
        id=i;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("读者"+id);
        try {
            share.reader();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
