import java.util.Random;
import java.util.concurrent.Semaphore;

public class Share {
    private final Semaphore rMutex = new Semaphore(1);//控制读者进入的信号量
    private final Semaphore wMutex = new Semaphore(1);//控制写者进入的信号量
    private final Semaphore rCountMutex = new Semaphore(1);//控制对rCount的修改
    private final Semaphore wCountMutex = new Semaphore(1);//控制对wCount的修改

    private int rCount = 0;//正在进行的读者个数
    private int wCount = 0;//正在进行的写者个数

    //写者线程执行的函数
    public void writer() throws InterruptedException {
        Thread.sleep((long) (Math.random()*10000));
        wCountMutex.acquire();//进入临界区修改wCount
        if (wCount == 0)
            rMutex.acquire();//当第一个写者进入，如果有读者则阻塞读者
        wCount++;//写者进程数+1
        wCountMutex.release();//离开临界区

        wMutex.acquire();//写者与写者之间互斥进入临界区
        writing();//写数据
        wMutex.release();//离开临界区

        wCountMutex.acquire();//进入临界区修改wCount
        wCount--;//写者进程数--;
        if (wCount == 0)
            rMutex.release();//最后一个写者离开，唤醒读者
        wCountMutex.release();//离开临界区

    }

    //写函数
    public void writing() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "正在写数据");
        Thread.sleep((long) (Math.random()*10000));
    }

    //读者线程执行的函数
    public void reader() throws InterruptedException {
        Thread.sleep((long) (Math.random()*10000));
        rMutex.acquire();
        rCountMutex.acquire(); //进入临界区
        if (rCount == 0)
            wMutex.acquire();//当第一个读者进入，如果有写者则阻塞写者
        rCount++;
        rCountMutex.release();
        rMutex.release();//离开临界区

        reading();//读数据

        rCountMutex.acquire();//进入临界区
        rCount--;
        if (rCount == 0)
            wMutex.release();//最后一个写者离开，唤醒写者
        rCountMutex.release();//离开临界区
    }

    //读函数
    public void reading() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "正在读数据");
        Thread.sleep((long) (Math.random()*10000));
    }


}
