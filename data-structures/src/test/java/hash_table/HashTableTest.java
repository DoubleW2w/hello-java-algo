package hash_table;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


/**
 * @author: DoubleW2w
 * @description:
 * @date: 2023/12/24 14:03
 * @project: hello-java-algo
 */
public class HashTableTest {
    private final Logger logger = LoggerFactory.getLogger(HashTableTest.class);

    @Test
    public void test_hashMap01() {
        MyMap<String, String> map = new HashMap01<>();
        map.put("01", "花花");
        map.put("05", "豆豆");
        logger.info("碰撞前 key：{} value：{}", "01", map.get("01"));

        // 下标碰撞
        map.put("09", "蛋蛋");
        map.put("12", "苗苗");
        logger.info("碰撞前 key：{} value：{}", "01", map.get("01"));
    }

   @Test
    public void test_hashMap02() {
        MyMap<String, String> map = new HashMap02BySeparateChaining<>();
        map.put("01", "花花");
        map.put("05", "豆豆");
        logger.info("哈希表:{}",map);
        logger.info("碰撞前 key：{} value：{}", "01", map.get("01"));

        // 下标碰撞
        map.put("09", "蛋蛋");
        map.put("12", "苗苗");
       logger.info("哈希表:{}",map);
       logger.info("碰撞前 key：{} value：{}", "01", map.get("01"));
    }

    @Test
    public void test_hashMap03() {
        MyMap<String, String> map = new HashMap03ByOpenAddressing<>();
        map.put("01", "花花");
        map.put("05", "豆豆");
        logger.info("碰撞前 key：{} value：{}", "01", map.get("01"));

        // 下标碰撞
        map.put("09", "蛋蛋");
        map.put("12", "苗苗");
        logger.info("碰撞前 key：{} value：{}", "01", map.get("01"));

        logger.info("数据结构：{}", map);
    }

    @Test
    public void test_hashMap04() {
        MyMap<String, String> map = new HashMap04ByCoalescedHashing<>();
        map.put("01", "花花");
        map.put("05", "豆豆");
        logger.info("碰撞前 key：{} value：{}", "01", map.get("01"));

        // 下标碰撞
        map.put("09", "蛋蛋");
        map.put("12", "苗苗");
        logger.info("碰撞前 key：{} value：{}", "01", map.get("12"));

        logger.info("数据结构：{}", map);
    }

    // @Test
    // public void test_hashMap05() {
    //     MyMap<String, String> map = new HashMap05ByCuckooHashing<>();
    //     map.put("01", "花花");
    //     map.put("05", "豆豆");
    //     logger.info("碰撞前 key：{} value：{}", "01", map.get("01"));
    //
    //     // 下标碰撞
    //     map.put("09", "蛋蛋");
    //     map.put("12", "苗苗");
    //     logger.info("碰撞前 key：{} value：{}", "01", map.get("12"));
    //     logger.info("数据结构：{}", map);
    // }
    //
    // @Test
    // public void test_hashMap06() {
    //     HashMap06ByHopscotchHashing<Integer> map = new HashMap06ByHopscotchHashing<>();
    //     map.insert(1);
    //     map.insert(5);
    //     map.insert(9);
    //     map.insert(12);
    //
    //     logger.info("数据结构：{}", map);
    // }

    @Test
    public void test_hashMap07() {
        MyMap<String, String> map = new HashMap07ByRobinHoodHashing<>();
        map.put("01", "花花");
        map.put("05", "豆豆");
        logger.info("碰撞前 key：{} value：{}", "01", map.get("01"));

        // 下标碰撞
        map.put("09", "蛋蛋");
        map.put("12", "苗苗");
        logger.info("碰撞前 key：{} value：{}", "01", map.get("12"));

        logger.info("数据结构：{}", map);
    }
}
