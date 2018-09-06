package g.takeru.renshu;

import java.io.Serializable;

/**
 * ref: https://medium.com/exploring-code/how-to-make-the-perfect-singleton-de6b951dfdb0
 * Create a singleton
 */

public class PerfectSingleton implements Serializable {

    // use "volatile" keyword
    private static volatile PerfectSingleton sInstance;

    private PerfectSingleton() {
        // avoid reflection
        if (sInstance != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    // use "synchronized" make Singleton thread safe
    public synchronized static PerfectSingleton getInstance() {
        if (sInstance == null) {
            //if there is no instance available... create new one
            synchronized (PerfectSingleton.class) {
                if (sInstance == null) sInstance = new PerfectSingleton();
            }
        }

        return sInstance;
    }

    //Make singleton from serialize and deserialize operation.
    protected PerfectSingleton readResolve() {
        return getInstance();
    }
}
