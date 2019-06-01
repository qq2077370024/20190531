package demo;

/**
 * 线程等待共同执行 sleep
 * 用sleep方法，让主线程睡眠一段时间，当然这个睡眠时间是主观的时间，是我们自己定的，
 * 这个方法不推荐，但是在这里还是写一下，毕竟是解决方法
 */
public class Demo1 {
    public static void main(String[] args) {
        try{
            Demo1.threadGo();
        }catch(Exception e){
            e.printStackTrace();
        }

    }


    private static void threadGo() throws Exception{
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        Thread.sleep(1000);
                        System.out.println("线程启动");
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        Thread.sleep(5000);
        System.out.println("主线程启动");
    }
}
