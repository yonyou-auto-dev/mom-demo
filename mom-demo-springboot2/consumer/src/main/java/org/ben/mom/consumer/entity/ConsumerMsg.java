package org.ben.mom.consumer.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "consumer_msg")
public class ConsumerMsg {
    /**
     * 消息key
     */
    @Id
    @Column(name = "msg_key")
    private String msgKey;

    /**
     * 业务类名称
     */
    @Column(name = "biz_class_name")
    private String bizClassName;

    /**
     * 监听类名称
     */
    @Column(name = "consumer_class_name")
    private String consumerClassName;

    /**
     * 消息接受时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 交换机名称
     */
    private String exchange;

    /**
     * 消息异常信息
     */
    @Column(name = "info_msg")
    private String infoMsg;

    /**
     * 消费次数
     */
    @Column(name = "retry_count")
    private Integer retryCount;

    /**
     * 消息队列名称
     */
    @Column(name = "router_key")
    private String routerKey;

    /**
     * 消息状态：100 消息消费初始化,101 消息消费成功,102 消息消费失败
     */
    private Integer status;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 消息体内容
     */
    @Column(name = "msg_content")
    private String msgContent;

    /**
     * 获取消息key
     *
     * @return msg_key - 消息key
     */
    public String getMsgKey() {
        return msgKey;
    }

    /**
     * 设置消息key
     *
     * @param msgKey 消息key
     */
    public void setMsgKey(String msgKey) {
        this.msgKey = msgKey;
    }

    /**
     * 获取业务类名称
     *
     * @return biz_class_name - 业务类名称
     */
    public String getBizClassName() {
        return bizClassName;
    }

    /**
     * 设置业务类名称
     *
     * @param bizClassName 业务类名称
     */
    public void setBizClassName(String bizClassName) {
        this.bizClassName = bizClassName;
    }

    /**
     * 获取监听类名称
     *
     * @return consumer_class_name - 监听类名称
     */
    public String getConsumerClassName() {
        return consumerClassName;
    }

    /**
     * 设置监听类名称
     *
     * @param consumerClassName 监听类名称
     */
    public void setConsumerClassName(String consumerClassName) {
        this.consumerClassName = consumerClassName;
    }

    /**
     * 获取消息接受时间
     *
     * @return create_time - 消息接受时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置消息接受时间
     *
     * @param createTime 消息接受时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取交换机名称
     *
     * @return exchange - 交换机名称
     */
    public String getExchange() {
        return exchange;
    }

    /**
     * 设置交换机名称
     *
     * @param exchange 交换机名称
     */
    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    /**
     * 获取消息异常信息
     *
     * @return info_msg - 消息异常信息
     */
    public String getInfoMsg() {
        return infoMsg;
    }

    /**
     * 设置消息异常信息
     *
     * @param infoMsg 消息异常信息
     */
    public void setInfoMsg(String infoMsg) {
        this.infoMsg = infoMsg;
    }

    /**
     * 获取消费次数
     *
     * @return retry_count - 消费次数
     */
    public Integer getRetryCount() {
        return retryCount;
    }

    /**
     * 设置消费次数
     *
     * @param retryCount 消费次数
     */
    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    /**
     * 获取消息队列名称
     *
     * @return router_key - 消息队列名称
     */
    public String getRouterKey() {
        return routerKey;
    }

    /**
     * 设置消息队列名称
     *
     * @param routerKey 消息队列名称
     */
    public void setRouterKey(String routerKey) {
        this.routerKey = routerKey;
    }

    /**
     * 获取消息状态：100 消息消费初始化,101 消息消费成功,102 消息消费失败
     *
     * @return status - 消息状态：100 消息消费初始化,101 消息消费成功,102 消息消费失败
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置消息状态：100 消息消费初始化,101 消息消费成功,102 消息消费失败
     *
     * @param status 消息状态：100 消息消费初始化,101 消息消费成功,102 消息消费失败
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取消息体内容
     *
     * @return msg_content - 消息体内容
     */
    public String getMsgContent() {
        return msgContent;
    }

    /**
     * 设置消息体内容
     *
     * @param msgContent 消息体内容
     */
    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
}