package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.pojo.HostReply;

public interface HostReplyDAO {
    //根据replyID查询关联的HostReply实体
    HostReply getHostReplyByReplyId(Integer id);
    //根据id删除hostReply
    void delHostReply(Integer id);
}
