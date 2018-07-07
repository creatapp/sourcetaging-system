package business.dataproducer.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class ProduceData {

    public static void main(String[] args){
        produceWorkerTrait();
        produceWorkerInterest();
    }

    public static void produceWorkerTrait(){
        //生成工人能力数据>>随机生成--正态分布
        String path = ClassLoader.class.getResource("/trait.arff").getPath();
        FileWriter fileWritter = null;
        try {
            fileWritter = new FileWriter(path,true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);

            Random random;
            for (int i = 1; i <= 1000; i++){
                bufferWritter.write((100000000 + i) + "");
                random = new Random();
                for (int j = 0; j < 150; j++){
                    bufferWritter.write(",");
                    //方差30以内随机数，均值10
                    //将随机数间的数值变为0
                    double min = Math.random() * 10;
                    double rand = Math.sqrt(Math.random() * 30) * random.nextGaussian() + 10;
                    if (rand < 0) rand = 0;
                    if(rand > min && rand < min + 10) rand = 0;
                    bufferWritter.write((int) (rand) + "");
                }
                bufferWritter.write("\n");
            }
            bufferWritter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void produceWorkerInterest(){
        //生成工人兴趣数据>>随机生成--正态分布
        /*
        * 说明：
        *   0 -- 讨厌     1 -- 不喜欢    2 -- 一般(default)     3 -- 感兴趣    4 -- 非常喜欢
        * */
        String path = ClassLoader.class.getResource("/interest.arff").getPath();
        FileWriter fileWritter = null;
        try {
            fileWritter = new FileWriter(path,true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);

            Random random;
            for (int i = 1; i <= 1000; i++){
                bufferWritter.write((100000000 + i) + "");
                random = new Random();
                for (int j = 0; j < 150; j++){
                    bufferWritter.write(",");
                    //方差5以内随机数，均值2
                    double rand = Math.sqrt(Math.random() * 5) * random.nextGaussian() + 2;
                    if (rand < 0) rand = 2;
                    if (rand > 4.5) rand = 2;
                    bufferWritter.write((int) (rand) + "");
                }
                bufferWritter.write("\n");
            }
            bufferWritter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
