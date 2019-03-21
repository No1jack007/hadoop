package com.zhang.hadoop.testMain;


/**
 * Created by zhang yufei on 2019/1/17.
 */
public class TestMain {

    public static void main(String[] args) throws Exception {
//        WordCountMain wordCountMain=new WordCountMain();
//        wordCountMain.test();
//        KafkaAndStormService kafkaAndStormService=new KafkaAndStormService();
//        kafkaAndStormService.test(args);
//        JedisUtil jedisUtil = JedisUtil.getInstance();
//        JedisUtil.Strings strings = jedisUtil.new Strings();
//        strings.set("nnn", "nnnn");
//        System.out.println("-----" + strings.get("nnn"));

//        ModuleService bean= new ModuleService();
//        Map<String,Object> map=new HashMap<>();
//        map.put("code","2");
//        map.put("cell","123123");
//        bean.insertOneModule(map);

//        HBaseUtil hBaseUtil=new HBaseUtil();
//        Map<String,Object> data=new HashMap<>();
//        data.put("code","2");
//        data.put("cell","123123");
//        hBaseUtil.insertOneRecord("test_table",data.get("code").toString(),"family1","cell",data.get("cell").toString());

        String date="2018.10";
        String a1[]=date.split("\\.");
        System.out.println(a1[0]+"\t"+a1[1]);
    }
}
