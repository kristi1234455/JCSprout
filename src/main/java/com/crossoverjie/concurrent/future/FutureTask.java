package com.crossoverjie.concurrent.future;

/**
 * Function:
 *
 * @author crossoverJie
 * Date: 2019-06-03 23:56
 * @since JDK 1.8
 */
public class FutureTask<T> implements Runnable,Future<T> {

    private Callable<T> callable ;

    private T reslut ;

    private Object notify ;

    public FutureTask(Callable<T> callable) {
        this.callable = callable;
        notify = new Object() ;
    }

    @Override
    public T get() throws InterruptedException {

        synchronized (notify){
            while (reslut == null){
                notify.wait();
            }

            return reslut ;
        }
    }

    @Override
    public void run() {

        T call = callable.call();

        this.reslut = call ;

        synchronized (notify){
            notify.notify();
        }
    }
}