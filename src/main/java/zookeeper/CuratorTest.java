package zookeeper;

import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/1 6:12 PM
 * @description : curator客户端测试
 */
public class CuratorTest {

    public static void main(String[] args) throws Exception{
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", new ExponentialBackoffRetry(1000, 3));
        client.start();

        // 创建节点
        client.create().withMode(CreateMode.PERSISTENT).forPath("/curator");

        //
    }

}
