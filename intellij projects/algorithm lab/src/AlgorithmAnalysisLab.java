import java.util.Calendar;
public class AlgorithmAnalysisLab{
    public static void main(String[] args) throws InterruptedException {
        long n = Long.parseLong(args[0]);
        //long n = Integer.MAX_VALUE;
        int fragNum = 1;
        long startTime = System.nanoTime();
        // Fragment 1
        long sum = 0;
        for( int i = 0; i < n; i++ )
        {
            sum++;
            Thread.sleep(1);
        }

        long endTime = System.nanoTime();
        long diff = endTime - startTime;
        System.out.println("Time to compute Fragment " + fragNum++ + " was " + diff + " milliseconds.");
        startTime = System.nanoTime();
        // Fragment 2
        sum = 0;
        for( int i = 0; i < n; i += 2 )
        {
            sum++;
            Thread.sleep(1);
        }
        endTime = System.nanoTime();
        diff = endTime - startTime;
        System.out.println("Time to compute Fragment " + fragNum++ + " was " + diff + " milliseconds.");
        startTime = System.nanoTime();
        // Fragment 3
        sum = 0;
        for( int i = 0; i < n; i++ )
            for( int j = 0; j < n; j++ )
            {
                sum++;
            Thread.sleep(1);
            }
        endTime = System.nanoTime();
            diff = endTime - startTime;
            System.out.println("Time to compute Fragment " + fragNum++ + " was " + diff + " milliseconds.");
            startTime = System.nanoTime();
            // Fragment 4
        sum = 0;
        for( int i = 0; i < n; i++ )
        {    sum++;    Thread.sleep(1);
        }
        for( int j = 0; j < n; j++ )
        {
            sum++;
            Thread.sleep(1);
        }
        endTime = System.nanoTime();
        diff = endTime - startTime;
        System.out.println("Time to compute Fragment " + fragNum++ + " was " + diff + " milliseconds.");
        startTime = System.nanoTime();
        // Fragment 5
        sum = 0;
        for( int i = 0; i < n; i++ )
            for( int j = 0; j < n * n; j++ )
            {    sum++;
            Thread.sleep(1);
            }
        endTime = System.nanoTime();
            diff = endTime - startTime;
            System.out.println("Time to compute Fragment " + fragNum++ + " was " + diff + " milliseconds.");
            startTime = System.nanoTime();
            //Fragment 6
        sum = 0;
        for( int i = 0; i < n; i++ )
            for( int j = 0; j < i; j++ )
            {
                sum++;
            Thread.sleep(1);
            }
        endTime = System.nanoTime();
            diff = endTime - startTime;
            System.out.println("Time to compute Fragment " + fragNum++ + " was " + diff + " milliseconds.");
            startTime = System.nanoTime();
            // Fragment 7
        sum = 0;
        for( int i = 0; i < n; i++ )
            for( int j = 0; j < n * n; j++ )
                for( int k = 0; k < j; k++ )
                {
                    sum++;
                Thread.sleep(1);
                }
        endTime = System.nanoTime();
                diff = endTime - startTime;
                System.out.println("Time to compute Fragment " + fragNum++ + " was " + diff + " milliseconds.");
                startTime = System.nanoTime();
                // Fragment 8
        sum = 0;
        for( int i = 1; i < n; i = i * 2 )
        {
            sum++;
        Thread.sleep(1);
        }
        endTime = System.nanoTime();
        diff = endTime - startTime;
        System.out.println("Time to compute Fragment " + fragNum + " was " + diff + " milliseconds.");   }
}
