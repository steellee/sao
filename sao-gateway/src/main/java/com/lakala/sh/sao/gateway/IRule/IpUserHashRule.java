package com.lakala.sh.sao.gateway.IRule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ribbon自定义负载均衡策略:
 * 同一个ip下的同一个用户的所有请求被代理到同一个实例（如果请求没有用户信息，那就相当于ip_hash策略）
 */
public class IpUserHashRule extends AbstractLoadBalancerRule {

    private static Logger log = LoggerFactory.getLogger(IpUserHashRule.class);

    /**
     * 用户cookie
     */
    private String COOKIE_TICKET_NAME= "user-sao";

    /**
     * 服务器选择
     * @param loadBalancer 均衡服务器
     * @param key
     * @return
     */
    public Server choose(ILoadBalancer loadBalancer, Object key) {

        if (loadBalancer == null) {
            log.warn("no load balancer");
            return null;
        }
        Server server = null;
        int count = 0;
        while (server == null && count++ < 10) {
            List<Server> reachableServers = loadBalancer.getReachableServers();
            List<Server> allServers = loadBalancer.getAllServers();
            int upCount = reachableServers.size();
            int serverCount = allServers.size();

            if ((upCount == 0) || (serverCount == 0)) {
                log.warn("No up servers available from load balancer: " + loadBalancer);
                return null;
            }

            int nextServerIndex = ipUserHash(serverCount);
            server = allServers.get(nextServerIndex);

            if (server == null) {
                // 线程暂停，但它不会阻塞该线程
                Thread.yield();
                continue;
            }

            if (server.isAlive() && (server.isReadyToServe())) {
                return (server);
            }

            // Next.
            server = null;
        }

        if (count >= 10) {
            log.warn("No available alive servers after 10 tries from load balancer: "
                + loadBalancer);
        }
        return server;
    }

    /**
     * 根据ip + 用户cookie生成唯一hash值
     * @param serverCount
     * @return hash值
     */
    private int ipUserHash(int serverCount) {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();

        String userIp = getIp(request);
        int userHashCode = Math.abs((userIp).hashCode());
//        // 从cookie获取ticket
//        String userTicket = CookieUtil.getCookie(request,COOKIE_TICKET_NAME);
//        int userHashCode = Math.abs((userIp + userTicket).hashCode());
        return userHashCode % serverCount;
    }

    private String getIp(HttpServletRequest request) {
        // 获取用户的登录IP地址
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
        // TODO Auto-generated method stub

    }


    public static void main(String[] args) {
        String ticket = "";
        String localIp = "127.0.0.1";
        System.out.println(Math.abs((ticket+localIp).hashCode())%5);
    }

}

