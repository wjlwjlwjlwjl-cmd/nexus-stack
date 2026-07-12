package com.nexus.nexusfileservice.controller;

import com.aliyun.oss.common.utils.BinaryUtil;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import com.fasterxml.jackson.databind.ObjectMapper; 
import java.util.Map; 
import java.util.HashMap; 
import java.util.List; 
import java.util.ArrayList; 
import java.util.Arrays; 

public class Demo {
    public static void main(String[] args) throws Exception {
        // 运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        String accesskeyid =  System.getenv().get("OSS_ACCESS_KEY_ID");
        String accesskeysecret =  System.getenv().get("OSS_ACCESS_KEY_SECRET");

        // 步骤1：创建policy。
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> policy = new HashMap<>();
        policy.put("expiration", "2024-12-03T13:00:00.000Z");

        List<Object> conditions = new ArrayList<>();

        Map<String, String> bucketCondition = new HashMap<>();
        bucketCondition.put("bucket", "examplebucket");
        conditions.add(bucketCondition);

        Map<String, String> signatureVersionCondition = new HashMap<>();
        signatureVersionCondition.put("x-oss-signature-version", "OSS4-HMAC-SHA256");
        conditions.add(signatureVersionCondition);

        Map<String, String> credentialCondition = new HashMap<>();
        credentialCondition.put("x-oss-credential", accesskeyid + "/20241203/cn-hangzhou/oss/aliyun_v4_request"); // 替换为实际的 access key id
        conditions.add(credentialCondition);

        Map<String, String> dateCondition = new HashMap<>();
        dateCondition.put("x-oss-date", "20241203T121212Z");
        conditions.add(dateCondition);

        conditions.add(Arrays.asList("content-length-range", 1, 10));
        conditions.add(Arrays.asList("eq", "$success_action_status", "201"));
        conditions.add(Arrays.asList("starts-with", "$key", "user/eric/"));
        conditions.add(Arrays.asList("in", "$content-type", Arrays.asList("image/jpg", "image/png")));
        conditions.add(Arrays.asList("not-in", "$cache-control", Arrays.asList("no-cache")));

        policy.put("conditions", conditions);

        String jsonPolicy = mapper.writeValueAsString(policy);
        // 步骤2：构造待签名字符串（StringToSign）。
        String stringToSign = new String(Base64.encodeBase64(jsonPolicy.getBytes()));
        System.out.println(stringToSign);
        
        // 步骤3：计算SigningKey。
        byte[] dateKey = hmacsha256(("aliyun_v4" + accesskeysecret).getBytes(), "20241203");
        byte[] dateRegionKey = hmacsha256(dateKey, "cn-hangzhou");
        byte[] dateRegionServiceKey = hmacsha256(dateRegionKey, "oss");
        byte[] signingKey = hmacsha256(dateRegionServiceKey, "aliyun_v4_request");

        // 步骤4：计算Signature。
        byte[] result = hmacsha256(signingKey, stringToSign);
        String signature = BinaryUtil.toHex(result);
        System.out.println("signature:" + signature);

    }

    public static byte[] hmacsha256(byte[] key, String data) {
        try {
            // 初始化HMAC密钥规格，指定算法为HMAC-SHA256并使用提供的密钥。
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "HmacSHA256");

            // 获取Mac实例，并通过getInstance方法指定使用HMAC-SHA256算法。
            Mac mac = Mac.getInstance("HmacSHA256");
            // 使用密钥初始化Mac对象。
            mac.init(secretKeySpec);

            // 执行HMAC计算，通过doFinal方法接收需要计算的数据并返回计算结果的数组。
            byte[] hmacBytes = mac.doFinal(data.getBytes());

            return hmacBytes;
        } catch (Exception e) {
            throw new RuntimeException("Failed to calculate HMAC-SHA256", e);
        }
    }
}