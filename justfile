jarfile := "~/playground/decline-test/target/scala-2.12/example-assembly-0.1.jar"

spark-shell:
    ~/apache/spark-3.1.2-bin-hadoop3.2/bin/spark-shell --jars {{ jarfile }}
    
assembly:
    sbt assembly
    
clean:
    sbt clean
    
test: clean assembly spark-shell
    