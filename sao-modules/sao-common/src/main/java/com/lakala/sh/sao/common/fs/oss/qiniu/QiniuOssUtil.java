package com.lakala.sh.sao.common.fs.oss.qiniu;

import com.lakala.sh.sao.common.fs.oss.UploadObject;
import com.lakala.sh.sao.common.fs.oss.UploadTokenParam;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.HashMap;
import java.util.Map;

/**
 * 七牛云存储服务: https://developer.qiniu.com/kodo
 *
 * URI 说明: schema://[user[:password]@][:port]/path
 * 		如： aliyun://accessId:accessKey@bucket.endpoint/path, aliyun://accessId:accessKey@oss-ch-sh.oss-cn-shanghai.aliyuncs.com
 */
public class QiniuOssUtil {

	private static final String DIR_SPLITER = "/";
	private static final String DEFAULT_CALLBACK_BODY = "filename=${fname}&size=${fsize}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}";
	private static final String[] policyFields = new String[]{
            "callbackUrl",
            "callbackBody",
            "callbackHost",
            "callbackBodyType",
            "fileType",
            "saveKey",
            "mimeLimit",
            "fsizeLimit",
            "fsizeMin",
            "deleteAfterDays",
    };
	private static UploadManager uploadManager;
	private static BucketManager bucketManager;

	private static Auth auth;
	private static boolean isPrivate;
	private static String host;
	private static String urlprefix;
	private static String bucketName;

	public static void create(QiniuOss qiniuOss) {
		
		Validate.notBlank(qiniuOss.getBucketName(),"[bucketName] not defined");
		Validate.notBlank(qiniuOss.getAccessKey(), "[accessKey] not defined");
		Validate.notBlank(qiniuOss.getSecretKey(), "[secretKey] not defined");
		Validate.notBlank(qiniuOss.getUrlprefix(), "[urlprefix] not defined");
		
		urlprefix = urlprefix.endsWith(DIR_SPLITER) ? urlprefix : urlprefix + DIR_SPLITER;
		bucketName = qiniuOss.getBucketName();
		//密钥配置
		auth = Auth.create(qiniuOss.getAccessKey(), qiniuOss.getSecretKey());

		///////////////////////指定上传的Zone的信息//////////////////
		//第一种方式: 指定具体的要上传的zone
		//注：该具体指定的方式和以下自动识别的方式选择其一即可
		//要上传的空间(bucket)的存储区域为华东时
		// Zone z = Zone.zone0();
		//要上传的空间(bucket)的存储区域为华北时
		// Zone z = Zone.zone1();
		//要上传的空间(bucket)的存储区域为华南时
		// Zone z = Zone.zone2();

		//第二种方式: 自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。
		Zone z = Zone.autoZone();
		//构造一个带指定Zone对象的配置类
		Configuration c = new Configuration(z);
		//...其他参数参考类注释
		// 创建上传对象
		uploadManager = new UploadManager(c);

		// 创建资源管理(用于获取文件信息, 操作文件等)
		// https://developer.qiniu.com/kodo/sdk/1239/java
		bucketManager = new BucketManager(auth, c);

		isPrivate = qiniuOss.isPrivate();
		host = StringUtils.remove(urlprefix,"/").split(":")[1];
	}

	/**
	 * 上传文件
	 * @param object
	 * @return
	 */
	public static String upload(UploadObject object) {
		String fileName = object.getFileName();
		if(StringUtils.isNotBlank(object.getCatalog())){
			fileName = object.getCatalog().concat("/").concat(fileName);
		}
		try {
			Response res = null;
			//简单上传，使用默认策略，只需要设置上传的空间名就可以了
			String upToken = auth.uploadToken(bucketName);
			if(object.getFile() != null){
				res = uploadManager.put(object.getFile(), fileName, upToken);
			}else if(object.getBytes() != null){
				res = uploadManager.put(object.getBytes(), fileName, upToken);
			}else if(object.getInputStream() != null){
				res = uploadManager.put(object.getInputStream(), fileName, upToken, null, object.getMimeType());
			}else if(StringUtils.isNotBlank(object.getUrl())){
				return bucketManager.fetch(object.getUrl(), bucketName, fileName).key;
			}else{
				throw new IllegalArgumentException("upload object is NULL");
			}
			//打印返回的信息
			System.out.println(res.bodyString());
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			System.out.println(r.toString());
			try {
				//响应的文本信息
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public String getDownloadUrl(String fileKey) {
		String path = getFullPath(fileKey);
		if(isPrivate){
			path = auth.privateDownloadUrl(path, 3600);
		}
		return path;
	}

	protected String getFullPath(String file) {
		if(file.startsWith("http://") || file.startsWith("https://")){
			return file;
		}
		return urlprefix + file;
	}

	public boolean delete(String fileKey) {
		try {
			if (fileKey.contains(DIR_SPLITER))
				fileKey = fileKey.replace(urlprefix, "");
			bucketManager.delete(bucketName, fileKey);
			return true;
		} catch (QiniuException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Map<String, Object> createUploadToken(UploadTokenParam param) {
		
		if(StringUtils.isNotBlank(param.getCallbackUrl())){
			if(StringUtils.isBlank(param.getCallbackBody())){
				param.setCallbackBody(DEFAULT_CALLBACK_BODY);
			}
			if(StringUtils.isBlank(param.getCallbackHost())){
				param.setCallbackHost(host);
			}
		}
		
		Map<String, Object> result = new HashMap<>();
		StringMap policy = new StringMap();
		policy.putNotNull(policyFields[0], param.getCallbackUrl());
		policy.putNotNull(policyFields[1], param.getCallbackBody());
		policy.putNotNull(policyFields[2], param.getCallbackHost());
		policy.putNotNull(policyFields[3], param.getCallbackBodyType());
		policy.putNotNull(policyFields[4], param.getFileType());
		policy.putNotNull(policyFields[5], param.getFileKey());
		policy.putNotNull(policyFields[6], param.getMimeLimit());
		policy.putNotNull(policyFields[7], param.getFsizeMin());
		policy.putNotNull(policyFields[8], param.getFsizeMax());
		policy.putNotNull(policyFields[9], param.getDeleteAfterDays());

		String token = auth.uploadToken(bucketName, param.getFileKey(), param.getExpires(), policy, true);
		result.put("uptoken", token);
		result.put("host", this.urlprefix);
		result.put("dir", param.getUploadDir());
		return result;
	}

	public static void main(String[] args) {
		QiniuOss qiniuOss = new QiniuOss();
		qiniuOss.setAccessKey("Access_Key");
		qiniuOss.setSecretKey("Secret_Key");
		qiniuOss.setBucketName("Bucket_Name");
		QiniuOssUtil.create(qiniuOss);

		UploadObject uploadObject = new UploadObject("D:\\test.txt");
		QiniuOssUtil.upload(uploadObject);
	}
}
