import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lakala.sh.sao.common.vo.TestInfo;

import java.util.*;

public class FastJsonTest {

    // 实体转为Json
    public static void Entity2json(String Name, String Ip) {
        TestInfo info = new TestInfo(Name, Ip);
        String str_json = JSON.toJSONString(info); //
//        System.out.println(str_json);
    }

    // list转Json
    public static void list2Json() {
        List<TestInfo> list = new ArrayList<TestInfo>();
        TestInfo host8 = new TestInfo("host-8", "192.168.136.158");
        TestInfo host9 = new TestInfo("host-9", "192.168.136.159");
        list.add(host8);
        list.add(host9);
        String json = JSON.toJSONString(list, true);
//        System.out.println(json);
    }

    // 字符数组转化为JSon
    private static void String2JSONArray(String arrayAyy) {
        JSONArray array = JSONArray.parseArray(arrayAyy);
//        System.out.println(array);
        Collection nuCon = new Vector();
        nuCon.add(null);
        array.removeAll(nuCon);
    }

    // 复杂数据类型
    public static void Complexdata() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name", "张三");
        map.put("age", 24);
        map.put("phone", "13799313810");

        // map集合
        HashMap<String, Object> temp = new HashMap<String, Object>();
        temp.put("name", "李四");
        temp.put("age", "23");
        map.put("girlInfo", temp);

        // list集合
        List<String> list = new ArrayList<String>();
        list.add("编码");
        list.add("测试");
        list.add("需求");
        map.put("hobby", list);
        String jsonString = JSON.toJSONString(map);
//        System.out.println(jsonString);
    }

    // 复杂数据类型
    public static void Deserialization(String json) {
        TestInfo host1 = JSON.parseObject(json, TestInfo.class);
//        System.out.println(host1);
    }

    // Json转为实体
    private static void Json2Eetity(String json) {
        TestInfo host1 = JSON.parseObject(json, TestInfo.class);
//        System.out.println(host1);
    }

    public static void main(String[] args) {
        int testNum = 1000000;
        System.out.println(" ------------ ");
        long start = System.currentTimeMillis();
        for (int i = 0; i < testNum; i++)
            Entity2json("host-1", "192.168.136.1");
        long end = System.currentTimeMillis();
        System.out.println("实体转换成json，" + testNum + "次耗时: "
            + ((end - start) / 1000.0) + " seconds");

        System.out.println("");
        System.out.println(" ------------ ");
        start = System.currentTimeMillis();
        for (int i = 0; i < testNum; i++)
            list2Json();
        end = System.currentTimeMillis();
        System.out.println("List转换成json，" + testNum + "次耗时: "
            + ((end - start) / 1000.0) + " seconds");
        System.out.println("");

        System.out.println(" ------------ ");
        start = System.currentTimeMillis();
        for (int i = 0; i < testNum; i++)
            Complexdata();
        end = System.currentTimeMillis();
        System.out.println("复杂对象转换成json，" + testNum + "次耗时: "
            + ((end - start) / 1000.0) + " seconds");
        System.out.println("");

                String json = "{\"hostname\":\"host-2\",\"ip\":\"192.168.136.134\"}";
                String arrayAyy = "[[\'host-3','192.168.136.144'],null,[\'host-4','192.168.136.154']]";

        System.out.println(" ------------ ");
        start = System.currentTimeMillis();
        for (int i = 0; i < testNum; i++)
            Deserialization(json);
        end = System.currentTimeMillis();
        System.out.println("json 序列化，" + testNum + "次耗时: "
            + ((end - start) / 1000.0) + " seconds");
        System.out.println("");

        System.out.println(" ------------ ");
        start = System.currentTimeMillis();
        for (int i = 0; i < testNum; i++)
            Json2Eetity(json);
        end = System.currentTimeMillis();
        System.out.println("json转换为实体，" + testNum + "次耗时: "
            + ((end - start) / 1000.0) + " seconds");
        System.out.println("");

        System.out.println(" ------------ ");
        start = System.currentTimeMillis();
        for (int i = 0; i < testNum; i++)
            String2JSONArray(arrayAyy);
        end = System.currentTimeMillis();
        System.out.println("字符串转换为json数组，" + testNum + "次耗时: "
            + ((end - start) / 1000.0) + " seconds");
        System.out.println("");
    }
}