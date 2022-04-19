package com.codeyaa.utils.tripartite;

import com.aliyun.ecs20140526.Client;
import com.aliyun.ecs20140526.models.*;
import com.aliyun.teaopenapi.models.Config;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Funtionalcode
 * @className AliYunUtil
 * @description 阿里云Api工具类
 * @date 2021/6/9 09:22
 */
@Data
public class AliYunUtil {
    private String accessKeyId;
    private String accessKeySecret;
    private String endpoint;
    /**
     * 添加安全组规则
     */
    /**
     * 安全组id （sg-2ze7gjh27h6fvtbh0712）
     */
    private String securityGroupId;
    /**
     * 允许访问的ip （0.0.0.0/0）
     */
    private String sourceCidrIp;
    /**
     * 访问模式 （tcp）
     */
    private String ipProtocol;
    /**
     * 放通端口 （8099/8099）
     */
    private String portRange;
    /**
     * 内外网 （internet/外网 intranet/内网）
     */
    private String nicType;
    /**
     * 策略 （accept）
     */
    private String policy;
    /**
     * 安全组所在地区id （cn-beijing）
     */
    private String regionId;
    /**
     * 安全组规则优先级，数字越小，代表优先级越高 （1-100）
     */
    private String priority;
    /**
     * 备注信息 （jrebel验证）
     */
    private String description;

    public AliYunUtil(String accessKeyId, String accessKeySecret) {
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
    }

    /**
     * 使用AK&SK初始化账号Client
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    private Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = endpoint;
        return new Client(config);
    }

    /**
     * @param
     * @Description: 获取阿里云可用区信息
     * @return:
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/6/9 10:15
     */
    public List<ArrayList<String>> describeRegions() {
        List<ArrayList<String>> list = null;
        try {
            Client client = createClient(accessKeyId, accessKeySecret);
            // 复制代码运行请自行打印 API 的返回值
            DescribeRegionsRequest describeRegionsRequest = new DescribeRegionsRequest();
            DescribeRegionsResponse regions = client.describeRegions(describeRegionsRequest);
            DescribeRegionsResponseBody body = regions.getBody();
            DescribeRegionsResponseBody.DescribeRegionsResponseBodyRegions bodyRegions = body.getRegions();
            List<DescribeRegionsResponseBody.DescribeRegionsResponseBodyRegionsRegion> region = bodyRegions.getRegion();

            list = region.stream().map(item -> {
                ArrayList<String> objects = new ArrayList<>();
                objects.add(item.getLocalName());
                objects.add(item.getRegionId());
                objects.add(item.getRegionEndpoint());
                return objects;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param
     * @Description: 获取安全组下所有规则
     * @return:
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/6/9 11:02
     */
    public List<String> describeSecurityGroupAttribute() {
        List<String> collect = null;
        try {
            Client client = createClient(accessKeyId, accessKeySecret);
            DescribeSecurityGroupAttributeRequest description = new DescribeSecurityGroupAttributeRequest();
            description.setSecurityGroupId(securityGroupId);
            description.setNicType(nicType);
            description.setRegionId(regionId);
            DescribeSecurityGroupAttributeResponse group = client.describeSecurityGroupAttribute(description);
            DescribeSecurityGroupAttributeResponseBody body = group.getBody();
            DescribeSecurityGroupAttributeResponseBody.DescribeSecurityGroupAttributeResponseBodyPermissions permissions = body.getPermissions();
            List<DescribeSecurityGroupAttributeResponseBody.DescribeSecurityGroupAttributeResponseBodyPermissionsPermission> permission = permissions.getPermission();
            collect = permission.stream().map(item -> {
                        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date parse = null;
                        try {
                            parse = sf.parse(item.getCreateTime());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String createTime = sf1.format(parse);

                        String res = String.format("direction:%7s --> sourceGroupId:%1s --> descGroupOwnerAccount:%1s --> sourceCidrIp:%10s --> ipv6DestCidrIp:%1s --> ipv6SourceCidrIp:%1s --> createTime:%20s --> descGroupId:%1s --> ipProtocol:%4s --> destCidrIp:%1s --> priority:%3s --> destGroupName:%1s --> nicType:%8s --> policy:%6s --> description:%20s --> portRange:%11s --> sourceGroupOwnerAccount:%1s --> sourceGroupName:%1s --> sourcePortRange:%1s\n",
                                item.getDirection(), item.getSourceGroupId(), item.getDestGroupOwnerAccount(), item.getSourceCidrIp(), item.getIpv6DestCidrIp(), item.getIpv6SourceCidrIp(),
                                createTime, item.getDestGroupId(), item.getIpProtocol(), item.getDestCidrIp(), item.getPriority(),
                                item.getDestGroupName(), item.getNicType(), item.getPolicy(), item.getDescription(), item.getPortRange(),
                                item.getSourceGroupOwnerAccount(), item.getSourceGroupName(), item.getSourcePortRange());
                        return res;
                    }
            ).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return collect;
    }

    /**
     * @param
     * @Description: 添加安全组规则
     * @return:
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/6/9 10:44
     */
    public String authorizeSecurityGroup() {
        String requestId = null;
        try {
            Client client = createClient(accessKeyId, accessKeySecret);
            AuthorizeSecurityGroupRequest authorizeSecurityGroupRequest = new AuthorizeSecurityGroupRequest();
            authorizeSecurityGroupRequest.setSecurityGroupId(securityGroupId);
            authorizeSecurityGroupRequest.setSourceCidrIp(sourceCidrIp);
            authorizeSecurityGroupRequest.setIpProtocol(ipProtocol);
            authorizeSecurityGroupRequest.setPortRange(portRange);
            authorizeSecurityGroupRequest.setNicType(nicType);
            authorizeSecurityGroupRequest.setPolicy(policy);
            authorizeSecurityGroupRequest.setRegionId(regionId);
            authorizeSecurityGroupRequest.setDescription(description);
            authorizeSecurityGroupRequest.setPriority(priority);
            AuthorizeSecurityGroupResponse group = client.authorizeSecurityGroup(authorizeSecurityGroupRequest);
            AuthorizeSecurityGroupResponseBody body = group.getBody();
            requestId = body.getRequestId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestId;
    }


    /**
     * @param
     * @Description: 删除一个安全组规则
     * @return:
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/6/9 11:10
     */
    public String revokeSecurityGroup() {
        String requestId = null;
        try {
            Client client = createClient(accessKeyId, accessKeySecret);
            RevokeSecurityGroupRequest authorizeSecurityGroupRequest = new RevokeSecurityGroupRequest();
            authorizeSecurityGroupRequest.setSecurityGroupId(securityGroupId);
            authorizeSecurityGroupRequest.setNicType(nicType);
            authorizeSecurityGroupRequest.setRegionId(regionId);
            authorizeSecurityGroupRequest.setPortRange(portRange);
            authorizeSecurityGroupRequest.setIpProtocol(ipProtocol);
            authorizeSecurityGroupRequest.setSourceCidrIp(sourceCidrIp);
            RevokeSecurityGroupResponse group = client.revokeSecurityGroup(authorizeSecurityGroupRequest);
            RevokeSecurityGroupResponseBody body = group.getBody();
            requestId = body.getRequestId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestId;
    }
}
