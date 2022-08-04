package com.atguigu.qqzone.service;

import com.atguigu.qqzone.pojo.HostReply;

public interface HostReplyService {
    HostReply getHostReplyByReplyID(Integer id);
    //删除hostReply
    void delHostReply(Integer id);
}
