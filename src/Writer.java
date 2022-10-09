public class Writer extends Thread {
    private Share share;
    private int id;

    Writer(Share s,int i) {
        share = s;
        id=i;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("写者"+id);
        try {
            share.writer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
