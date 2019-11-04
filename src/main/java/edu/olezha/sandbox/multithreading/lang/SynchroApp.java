package edu.olezha.sandbox.multithreading.lang;

/**
 * Created by oleh on 13.11.16.
 */
public class SynchroApp {

    public static void main(String[] args) throws InterruptedException {
        SynchroCounter counter = new SynchroCounter();
        SynchroSynchronizedCounter synchronizedCounter = new SynchroSynchronizedCounter();

        for (int i = 0; i < 50; i++) {
            SynchroSingleProcess synchroSingleProcess = new SynchroSingleProcess(counter, synchronizedCounter);
            new Thread(synchroSingleProcess).start();
        }

        Thread.sleep(3000);

        System.out.println(synchronizedCounter.getCounter() + " > " + counter.getCounter());
        // OUT: 50000 > 47778
        // OUT: 50000 > 49884
        // OUT: 50000 > 50000
        // OUT: 50000 > 44781

        // (http://www.sei.cmu.edu/reports/10tr015.pdf)

        // (https://en.wikipedia.org/wiki/Memory_barrier)

        // (https://habrahabr.ru/post/108016/):

        // Когда JVM выполняет synchronized-метод, выполняющийся поток определяет,
        // что в method_info этого метода проставлен флаг ACC_SYNCHRONIZED.
        // Тогда он автоматически устанавливает блокировку на объект, вызывает метод и снимает блокировку.
        //
        // synchronized-блок обходит встроенную в JVM поддержку запросов блокировок объекта
        // но, в действительности работает точно так же, как и synchronized method.

        // ThreadLocal переменные отличаются от обычных переменных тем,
        // что у каждого потока свой собственный, индивидуально инициализируемый экземпляр переменной,
        // доступ к которой он получает через методы get/set.

        // определение переменной с ключевым словом volatile(«изменчивый») означает,
        // что значение переменной будет изменяться разными потоками.
        //
        // В целях повышения эффективности работы, спецификации языка Java позволяет JRE
        // сохранять локальную копию переменной в каждом потоке, который ссылается на нее.
        // Можно считать эти «внутрипоточные» копии переменных похожими на кэш,
        // помогающий избежать проверки главной памяти каждый раз, когда требуется доступ к значению переменной.
        //
        // Но представьте, что произойдёт в следующем случае:
        // запустятся два потока, и первый прочитает переменную А как 5, тогда как второй – как 10.
        // Однако если переменная А будет помечена как volatile, то то в любое время,
        // когда поток обращается к её значению, он
        // будет получать копию А и считывать её текущее значение.
        //
        // чтение volatile переменных синхронизировано и запись в volatile переменные синхронизирована,
        // а неатомарные операции – нет.
        // следующий код не безопасен для потоков: myVolatileVar++;
        //
        // если присвоенное значение volatile переменной зависит от её текущего значения
        // (например, во время операции инкремента), то нужно использовать синхронизацию,
        // если вы хотите, чтобы операция была потокобезопасной.
        // Или использовать volatile + java.utilDate.concurrent.atomic.Atomic-ANY-FieldUpdater
        //
        // Доступ к volatile полю окружен data memory barrier'ом,
        // что гарантирует упорядоченность операций чтения-записи памяти относительно доступа к этому полю.
        // Иначе компилятор или сам процессор вправе переупорядочивать инструкции (out-of-order execution).

        // Когда вам требуется примитивный тип, выполняющий операции инкремента и декремента,
        // гораздо лучше выбрать его среди новых атомарных классов в пакете java.utilDate.concurrent.atomic,
        // чем писать synchronized блок самому.
        // AtomicInteger, AtomicBoolean, AtomicLong, AtomicIntegerArray...
    }

}
