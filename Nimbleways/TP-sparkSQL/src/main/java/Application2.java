import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Application2 {
    public static void main(String[] args) {
        RootLogger rootLogger = (RootLogger) Logger.getRootLogger();
        rootLogger.setLevel(Level.ERROR);
        Logger.getLogger("org.apache.spark").setLevel(Level.WARN);
        Logger.getLogger("org.spark-project").setLevel(Level.WARN);

        SparkSession ss=SparkSession.builder().
                appName("TP Spark SQL").
                master("local[*]").getOrCreate();

        Dataset<Row> df=ss.read().option("multiLine",true).json("employes.json");
        //df.printSchema();
        //df.show();
        //df.select("name","note").show();
        //df.select(col("name"),col("note").plus(1)).show();
        // df.filter(col("note").gt(10).and(col("name").startsWith("A"))).show();
        df.createOrReplaceTempView("employes");
        //ss.sql("select * from employes where age between 30 AND 35 ").show();
        ss.sql("select departement," +
                "avg(salary) as moyenne from employes group by departement").show();
        ss.sql("select departement,count(*) as count from employes group by departement").show();
        ss.sql("select max(salary) as max from employes").show();

    }
}



