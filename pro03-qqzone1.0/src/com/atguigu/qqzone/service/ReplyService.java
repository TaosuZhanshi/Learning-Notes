package com.atguigu.qqzone.service;

import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;

import java.util.List;

public interface ReplyService {
    //根据Topic ID获取其所有关联的回复
    List<Reply> getRelyListByTopicId(Integer id);

    //添加回复
    void addReply(Reply reply);

    //删除回复
    void delReply(Integer replyId);

    //删除指定的日志关联的所有的回复
    void delReplyByTopic(Topic topic);
}
