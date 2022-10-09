import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Share s = new Share();
        int reader = 0;
        int writer = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入您要创建的读进程数: ");
        reader = sc.nextInt();
        System.out.print("请输入您要创建的写进程数: ");
        writer = sc.nextInt();

        for (int i=1;i<=reader;i++){
            new Reader(s,i).start();
        }

        for (int i=1;i<=writer;i++){
            new Writer(s,i).start();
        }
    }
}
