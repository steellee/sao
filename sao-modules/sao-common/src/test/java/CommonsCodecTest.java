//import com.lakala.sh.sao.common.utils.security.AESUtil;
//import org.apache.commons.codec.DecoderException;
//import org.apache.commons.codec.EncoderException;
//import org.apache.commons.codec.binary.Base64;
//import org.apache.commons.codec.binary.Hex;
//import org.apache.commons.codec.digest.DigestUtils;
//import org.apache.commons.codec.net.URLCodec;
//import org.junit.Test;
//
//import java.io.UnsupportedEncodingException;
//
//public class CommonsCodecTest {
//
//    /**
//     * Base64编码和解码（可逆）
//     * @throws EncoderException
//     * @throws UnsupportedEncodingException
//     */
//    @Test
//    public void testBase64() throws EncoderException, UnsupportedEncodingException {
//        Base64 base64 = new Base64();
//        String str = "Base64编码和解码";
//        String result = base64.encodeToString(str.getBytes("UTF-8"));//编码
//        System.out.println(result);
//        byte[] decode = base64.decode(result.getBytes());//解码
//        System.out.println(new String(decode));
//    }
//
//    /**
//     * Hex编码和解码<br>
//     *     字节数组转换为十六进制字符数组
//     * @throws DecoderException
//     * @throws UnsupportedEncodingException
//     */
//    @Test
//    public void testHex () throws DecoderException, UnsupportedEncodingException {
//        String str = "Hex编码和解码";
//        /**编码*/
//        String hexString = Hex.encodeHexString(str.getBytes("UTF-8"));//直接一步到位
//        System.out.println(hexString);
//        char[] encodeHex = Hex.encodeHex(str.getBytes(), true);//先转换成char数组，第二个参数意思是是否全部转换成小写
//        System.out.println(new String(encodeHex));
//        /**解码*/
//        byte[] decodeHex = Hex.decodeHex(encodeHex);//char数组型的
//        System.out.println(new String(decodeHex));
//        byte[] decodeHex2 = Hex.decodeHex(hexString.toCharArray());//字符串类型的，该方法要求传入的是char[]
//        System.out.println(new String(decodeHex2));
//    }
//
//    /**
//     * MD5 信息摘要算法（MD5是不可逆算法）- 数字签名
//     * @throws DecoderException
//     * @throws UnsupportedEncodingException
//     */
//    @Test
//    public void testMD5() throws UnsupportedEncodingException {
//        String str = "MD5加密";
//        System.out.println(str);
//        String md5 = DigestUtils.md5Hex(str.getBytes("UTF-8"));
//        System.out.println(md5);
//    }
//
//    /**
//     * SHA 安全散列算法- 数字签名
//     * @throws UnsupportedEncodingException
//     */
//    @Test
//    public void testSHA() throws UnsupportedEncodingException {
//        String str = "test中国";
//        System.out.println(str);
//        String sha1Hex = DigestUtils.sha1Hex(str.getBytes("UTF-8"));
//        System.out.println(sha1Hex);
//    }
//
//    /**
//     * 自带URL的encode
//     * @throws EncoderException
//     * @throws DecoderException
//     */
//    @Test
//    public void testURLCodec () throws EncoderException, DecoderException {
//        String url = "http://baidu.com?name=你好";
//        URLCodec codec = new URLCodec();
//        String encode = codec.encode(url);
//        System.out.println(encode);
//        String decode = codec.decode(encode);
//        System.out.println(decode);
//    }
//
//    /**
//     * 高级加密标准 AES(对称加密算法)
//     */
//    @Test
//    public void testAES() {
//        String bankdcard = "6223889011053390";
//        String bankdcardEncrypt = AESUtil.encrypt(bankdcard);
//        System.out.println(bankdcardEncrypt);
//        System.out.println(AESUtil.decrypt(bankdcardEncrypt));
//    }
//}
