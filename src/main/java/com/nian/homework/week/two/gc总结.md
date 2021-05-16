1 测试用例：GCLogAnalysis.java，将持续时间改为3秒

启动参数： -XX:+PrintCommandLineFlags -Dfile.encoding=UTF-8 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:CompressedClassSpaceSize=128m -Xmx512m -Xms512m


|GC策略 | TotalTime(ms) | AvgTime(ms) | MaxTime(ms) | 主要分布区间 |
| :-: | :--: | :--: | :--: | :-:|
|串行 | 2030 |59.7 | 120 |20-30/60-70|
|并行 | 1880 |40 | 140 |0-10|
|CMS | 300 |27 | 130 |0-10|
|G1 | 350 |6.03 | 31.3 |0-10|

在该测试参数范围内，得到的结论是串行和并行的总GC时长相差并不多，但是主要的单次GC时长差距较大，可以看出并行GC次数较多，但是每次的GC耗时较短，而串行GC次数较少，但是每次GC耗时偏长。

而CMS和G1两种GC策略在GC总耗时上相差并不大，但明显看到G1的单次GC时间非常短，虽然CMS的单次GC耗时相较于串行和并行，但还是远远高于G1，也可以明显观察到G1的GC次数相对较多。可以得出，对GC时长有严格要求的情况下，选择G1策略会是不错的选择，并且当前测试并没有给G1添加参数-XX:MaxGCPauseMillis，如果进行配置可能会得到更好的结果。



2 测试用例 ：gateway-server-0.0.1-SNAPSHOT

启动参数：java -Xmx512m -Xms512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:-UseAdaptiveSizePolicy -XX:+UseSerialGC -jar gateway-server-0.0.1-SNAPSHOT.jar

用superBenchMarker工具进行压力测试，测试指令为 sb -u http://localhost:8088/api/hello -n 8000 -c 50

| GC策略 | TotalTime(ms) | AvgTime(ms) | MaxTime(ms) | 主要分布区间 |
| :----: | :-----------: | :---------: | :---------: | :----------: |
|  串行  |      340      |    48.6     |     100     |    10-20     |
|  并行  |      330      |    36,7     |     100     |     0-10     |
|  CMS   |      130      |    16.3     |     30      |    10-20     |
|   G1   |     61.8      |    15.5     |    42.6     |     0-10     |

通过以上数据可以得出，整个GC时间来看，串行和并行在一个量级上，并行的平均时间会小于串行的GC时间。

CMS和G1相比较，G1比CMS整个GC时间缩短了很多时间，平均时间和最大时间基本上和CMS持平。



总结：CMS优点在于可以并发操作并且会有较低的延迟，缺点在于因为使用的“标记-清除”算法，所以会有大量内存碎片产生。G1不再区分新生代和老年代，是按大小划分出来的数个Region，通过引入RSet避免全堆扫描来加快GC速度，使用“标记-整理”算法避免内存碎片，同时具有可预测的停顿。